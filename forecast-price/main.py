import requests
import pandas as pd
from datetime import date, timedelta


FROM_DATE = date(2021, 12, 1)
TO_DATE = date(2023, 4, 1)

FROST_CLIENT_ID = ""
with open("frost_client_id") as f:
    line = f.readline().strip()
    if line[0] == "#":
        print("Add your Frost API Client ID to file `frost_client_id`\n")
        exit(1)

    FROST_CLIENT_ID = line


def get_data_from_frost():
    # TODO: Find the closest stations for each region's waterplants
    stations = {
        "oslo": "SN90450",
        "bergen": "",
        "kristiansand": "",
        "trondheim": "",
        "tromsø": "",
    }

    endpoint = "https://frost.met.no/observations/v0.jsonld?timeresolutions=PT1H"
    parameters = {
        "sources": stations["oslo"],
        "elements": "air_temperature",
        "referencetime": f"{FROM_DATE.strftime('%Y-%m-%d')}/{TO_DATE.strftime('%Y-%m-%d')}",
    }

    print("Fetching data from frost.met.no...")

    r = requests.get(endpoint, parameters, auth=(FROST_CLIENT_ID, ""))
    json = r.json()

    data = []
    if r.status_code == 200:
        data = json["data"]
        print("Data retrieved from frost.met.no!")
    else:
        print("Error! Returned status code %s" % r.status_code)
        print("Message: %s" % json["error"]["message"])
        print("Reason: %s" % json["error"]["reason"])
        print("Shutting down now, try agin!")
        exit(1)

    print("Creating DataFrame...")
    df = pd.DataFrame()
    for i in range(len(data)):
        row = pd.DataFrame(data[i]["observations"])
        row["time_start"] = pd.to_datetime(data[i]["referenceTime"])
        row["time_start"] = row["time_start"].dt.strftime("%Y-%m-%dT%H:%M:%S")
        row["region"] = "Oslo"

        df = pd.concat([df, row], ignore_index=True)

    # df["time_start"] = df["time_start"].dt.strftime("%Y-%m-%d %H:%M:%S")

    df = df.drop(
        columns=[
            "elementId",
            "unit",
            "level",
            "timeOffset",
            "timeResolution",
            "timeSeriesId",
            "performanceCategory",
            "exposureCategory",
            "qualityCode",
        ]
    )

    df = df.rename(columns={"value": "temperature"})

    print("Finished creating DataFrame for frost.met.no!\n", df.head(5), "\n")

    return df


def daterange(start_date, end_date):
    for n in range(int((end_date - start_date).days)):
        yield start_date + timedelta(n)


def get_data_from_stroempris():
    print("Fething data from www.hvakosterstrommen.no...")
    r = requests.get(
        f"https://www.hvakosterstrommen.no/api/v1/prices/{TO_DATE.strftime('%Y/%m-%d')}_NO1.json"
    )

    if r.status_code == 200:
        print("Data retrieved from www.hvakosterstrommen.no!")
    else:
        print("Error! Returned status code %s" % r.status_code)
        print("Could not get today's electric prices from www.hvakosterstrommen.no")
        print("Shutting down now, try agin!")
        exit(1)

    print("Creating DataFrame...")

    df = pd.DataFrame()
    for single_date in daterange(FROM_DATE, TO_DATE):
        r = requests.get(
            f"https://www.hvakosterstrommen.no/api/v1/prices/{single_date.strftime('%Y/%m-%d')}_NO1.json"
        )

        if r.status_code != 200:
            print("Error! Returned status code %s" % r.status_code)
            print(
                f"Could not get prices from www.hvakosterstrommen.no ({single_date.strftime('%Y-%m-%d')})"
            )
            print("Shutting down now, try agin!")
            exit(1)

        data = r.json()
        day_data = pd.DataFrame.from_dict(data)
        day_data["time_start"] = pd.to_datetime(
            day_data["time_start"], format="%Y-%m-%dT%H:%M:%S", utc=True
        )
        day_data["time_start"] = day_data["time_start"].dt.strftime("%Y-%m-%dT%H:%M:%S")
        df = pd.concat([df, day_data], ignore_index=True)

    df = df.drop(columns=["EUR_per_kWh", "EXR", "time_end"])

    print(
        "Finished creating DataFrame for www.hvakosterstrommen.no!\n", df.head(5), "\n"
    )

    return df


df1 = get_data_from_frost()
df2 = get_data_from_stroempris()

print("Merging the two DataFrames...")
df = pd.merge(df1, df2, how="inner", on="time_start")

print(df.head(5))
df2.to_csv("oslo.csv", sep="\t")
print("\nOutput: oslo.csv")
