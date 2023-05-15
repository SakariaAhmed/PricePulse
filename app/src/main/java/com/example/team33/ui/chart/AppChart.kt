package com.example.team33.ui.chart

import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.edges.rememberFadingEdges
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.chart.decoration.Decoration
import com.patrykandpatrick.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import kotlin.math.ceil

private const val COLOR_1_CODE = 0xffffbb00
private const val COLOR_2_CODE = 0xff9db591
private val color1 = Color(COLOR_1_CODE)
private val color2 = Color(COLOR_2_CODE)
private val charColorOverThreshold = color2
private val charColorUnderThreshold = color1
private val axisTitleHorizontalPaddingValue = 8.dp
private val axisTitleVerticalPaddingValue = 2.dp
private val axisTitlePadding =
    dimensionsOf(axisTitleHorizontalPaddingValue, axisTitleVerticalPaddingValue)
private val axisTitleMarginValue = 4.dp
private val startAxisTitleMargins = dimensionsOf(end = axisTitleMarginValue)
private val bottomAxisTitleMargins = dimensionsOf(top = axisTitleMarginValue)

private val thresholdLineLabelMarginValue = 4.dp
private val thresholdLineLabelHorizontalPaddingValue = 8.dp
private val thresholdLineLabelVerticalPaddingValue = 2.dp
private val thresholdLineThickness = 1.dp
private val thresholdLineLabelPadding =
    dimensionsOf(thresholdLineLabelHorizontalPaddingValue, thresholdLineLabelVerticalPaddingValue)
private val thresholdLineLabelMargins = dimensionsOf(thresholdLineLabelMarginValue)

@Composable
private fun ChartCard(state: MainUiState) {

}

@Composable
fun PopulatedChartCard(
    list: List<Double>, modifier: Modifier = Modifier, thresholdValue: Float? = null
) {
    val model: ChartEntryModel =
        entryModelOf(List(list.size) { FloatEntry(it.toFloat(), list[it].toFloat()) })

    val axisValuesOverrider: AxisValuesOverrider<ChartEntryModel> =
        AxisValuesOverrider.fixed(minY = 0.0f, maxY = ceil(list.max() + 0.25).toFloat())
    val decoration = when (val thresholdLine = rememberThresholdLine(thresholdValue)) {
        else -> remember(thresholdLine!!) { listOf(thresholdLine) }
    }

    val chartColors: List<Color> = MutableList(list.size) {
        if (list[it].toFloat() <= (thresholdValue ?: list[it].toFloat())) charColorUnderThreshold
        else charColorOverThreshold
    }
    Log.d("test", "Size:${chartColors.size}")


    ChartTemplate(
        chartColors = chartColors,
        model = model,
        axisValuesOverrider = axisValuesOverrider,
        decoration = decoration,
        modifier = modifier,
        xAxisTitlemsg = stringResource(R.string.x_axis)
    )
}

/**
 * Draws an empty chart with styling
 *@param size the length of X axis
 */
@Composable
fun EmptyAppChartCard(size: Int) {
    val model = entryModelOf(List(size) { FloatEntry(it.toFloat(), 0.0f) })
    ChartTemplate(chartColors = listOf(Color.Black), model = model)
}

@Composable
private fun ChartTemplate(
    chartColors: List<Color>,
    model: ChartEntryModel,
    modifier: Modifier = Modifier,
    axisValuesOverrider: AxisValuesOverrider<ChartEntryModel>? = null,
    decoration: List<Decoration>? = null,
    xAxisTitlemsg:String? = null
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(),
        modifier = modifier
    ) {
        Box(Modifier.padding(16.dp)) {
            ProvideChartStyle(rememberChartStyle(chartColors)) {
                val defaultLines = currentChartStyle.lineChart.lines

                Chart(
                    chart = lineChart(
                        lines = remember(defaultLines) {
                            defaultLines.mapIndexed { index, defaultLine ->
                                LineChart.LineSpec(
                                    lineColor = defaultLine.lineColor
                                )
                            }
                        },
                        pointPosition = LineChart.PointPosition.Start,
                        axisValuesOverrider = axisValuesOverrider,
                        decorations = decoration
                    ),
                    model = model,
                    startAxis = startAxis(
                        guideline = null,
                        horizontalLabelPosition = VerticalAxis.HorizontalLabelPosition.Inside,
                        titleComponent = textComponent(
                            color = Color.Black,
                            background = shapeComponent(Shapes.pillShape, color1),
                            padding = dimensionsOf(
                                axisTitleHorizontalPaddingValue, axisTitleVerticalPaddingValue
                            ),
                            margins = startAxisTitleMargins,
                            typeface = Typeface.SANS_SERIF
                        ),
                        title = stringResource(R.string.y_axis),
                        maxLabelCount = 5
                    ),
                    bottomAxis = bottomAxis(
                        titleComponent = textComponent(
                            background = shapeComponent(Shapes.pillShape, color2),
                            color = Color.Black,
                            padding = axisTitlePadding,
                            margins = bottomAxisTitleMargins,
                            typeface = Typeface.MONOSPACE,
                        ),
                        title = xAxisTitlemsg,  // TODO: better name for X axis
                    ),
                    marker = rememberMarker(),
                    fadingEdges = rememberFadingEdges(),
                )
            }
        }
    }
}

@Composable
private fun rememberThresholdLine(thresholdValue: Float?): ThresholdLine? {
    if (thresholdValue == null) {
        return null
    }

    val line = shapeComponent(
        strokeWidth = thresholdLineThickness, strokeColor = color2.copy(alpha = 0.75f)
    )
    val label = textComponent(
        color = Color.Black,
        background = shapeComponent(Shapes.pillShape, color2),
        padding = thresholdLineLabelPadding,
        margins = thresholdLineLabelMargins,
        typeface = Typeface.MONOSPACE,
    )
    return remember(line, label) {
        ThresholdLine(
            thresholdValue = thresholdValue,
            thresholdLabel = "max",  // TODO: a better name for threshold indication
            lineComponent = line,
            labelComponent = label,
            labelHorizontalPosition = ThresholdLine.LabelHorizontalPosition.End
        )
    }
}

@Composable //This composable is here temporarily and will get removed
fun ApplianceChartCard(
    list: List<Float>, modifier: Modifier = Modifier, thresholdValue: Float? = null
) {
    val model: ChartEntryModel =
        entryModelOf(List(list.size) { FloatEntry(it.toFloat(), list[it].toFloat()) })

    val axisValuesOverrider: AxisValuesOverrider<ChartEntryModel> =
        AxisValuesOverrider.fixed(minY = 0.0f, maxY = ceil(list.max() + 0.25).toFloat())
    val decoration = when (val thresholdLine = rememberThresholdLine(thresholdValue)) {
        else -> remember(thresholdLine!!) { listOf(thresholdLine) }
    }

    val chartColors: List<Color> = MutableList(list.size) {
        if (list[it].toFloat() <= (thresholdValue ?: list[it].toFloat())) charColorUnderThreshold
        else charColorOverThreshold
    }
    Log.d("test", "Size:${chartColors.size}")


    ChartTemplate(
        chartColors = chartColors,
        model = model,
        axisValuesOverrider = axisValuesOverrider,
        decoration = decoration,
        modifier = modifier,
        xAxisTitlemsg = "Price per hour"
    )
}
