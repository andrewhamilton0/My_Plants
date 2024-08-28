package com.example.myplants.android.plant.presentation.editplantscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals100
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.OtherG100
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantSize
import kotlinx.datetime.toJavaLocalTime
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

@Composable
fun EditPlantScaffold(
    plant: Plant?,
    onTitleChange: (String) -> Unit,
    onWaterAmountChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onPhotoButtonClick: () -> Unit
) {
    val dateBoxTextStringId = remember {
        derivedStateOf {
            when (plant?.waterDays?.firstOrNull()) {
                DayOfWeek.MONDAY -> SharedRes.strings.monday
                DayOfWeek.TUESDAY -> SharedRes.strings.tuesday
                DayOfWeek.WEDNESDAY -> SharedRes.strings.wednesday
                DayOfWeek.THURSDAY -> SharedRes.strings.thursday
                DayOfWeek.FRIDAY -> SharedRes.strings.friday
                DayOfWeek.SATURDAY -> SharedRes.strings.saturday
                DayOfWeek.SUNDAY -> SharedRes.strings.sunday
                null -> null
            }
        }
    }
    val timeText = remember {
        derivedStateOf {
            val time = plant?.waterTime?.toJavaLocalTime()
            time?.format(DateTimeFormatter.ISO_TIME) ?: ""
        }
    }
    val plantSizeTextStringId = remember {
        derivedStateOf {
            when (plant?.plantSize) {
                PlantSize.SMALL -> SharedRes.strings.small
                PlantSize.MEDIUM -> SharedRes.strings.medium
                PlantSize.LARGE -> SharedRes.strings.large
                PlantSize.EXTRA_LARGE -> SharedRes.strings.extra_large
                null -> null
            }
        }
    }
    val titleMaxChar = 25
    val waterMaxChar = 7
    val descMaxChar = 250

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val screenHeight = maxHeight
        val screenWidth = maxWidth
        val screenHorizontalPadding = screenWidth * 0.07f
        val scaleFactor = (screenHeight.value / 792f).coerceIn(0.5f, 1.5f)

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (topBox, bottomBox, changeImageBox) = createRefs()

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topBox) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomBox.top, margin = screenHeight * -0.05f)
                        height = Dimension.fillToConstraints
                    }
                    .background(color = OtherG100)
            ) {
                val topBoxHeight = maxHeight
                val byteArray = plant?.photo?.byteArray
                if (byteArray == null) {
                    AsyncImage(
                        model = R.drawable.img_plants_background,
                        contentDescription = null, // TODO
                        contentScale = ContentScale.FillWidth
                    )
                    Box(
                        modifier = Modifier
                            .height(topBoxHeight * 0.5f)
                            .align(Alignment.Center)
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_single_plant,
                            contentDescription = null, // TODO
                            contentScale = ContentScale.FillHeight
                        )
                    }
                } else {
                    AsyncImage(
                        model = byteArray,
                        contentDescription = null, // TODO
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(changeImageBox) {
                        bottom.linkTo(bottomBox.top, margin = screenHeight * 0.02f)
                    }
            ) {
                IconButton(
                    onClick = { onPhotoButtonClick() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(screenHeight * 0.01f))
                        .background(color = Accent500)
                        .width(screenWidth * 0.33f)
                        .height(screenHeight * 0.05f)
                        .padding(horizontal = screenWidth * 0.03f, vertical = screenHeight * 0.01f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_upload),
                            contentDescription = null, // TODO
                            tint = Neutrals0,
                            modifier = Modifier.size(screenHeight * 0.025f)
                        )
                        Text(
                            text = stringResource(SharedRes.strings.add_image.resourceId),
                            color = Neutrals0,
                            fontWeight = FontWeight(500),
                            fontStyle = FontStyle(R.font.poppins_medium),
                            fontSize = (14 * scaleFactor).sp
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomBox) {
                        bottom.linkTo(parent.bottom)
                        height = Dimension.wrapContent
                    }
                    .clip(
                        RoundedCornerShape(
                            topStart = screenHeight * 0.04f,
                            topEnd = screenHeight * 0.04f
                        )
                    )
                    .wrapContentHeight()
                    .background(color = Neutrals0)
            ) {
                val bottomInset = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                BoxWithConstraints(
                    modifier = Modifier
                        .padding(horizontal = screenHorizontalPadding)
                        .padding(bottom = bottomInset)
                        .fillMaxWidth()
                ) {
                    val boxWidth = maxWidth
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    ) {
                        Spacer(modifier = Modifier.height(screenHeight * 0.025f))
                        PlantDetailEditBox(
                            detailTypeText = stringResource(SharedRes.strings.plant_name.resourceId),
                            greyBoxText = plant?.name ?: "",
                            hasArrow = false,
                            screenHeight = screenHeight,
                            screenWidth = screenWidth,
                            maxChars = titleMaxChar,
                            onOpenDialogPress = { },
                            onTextValueChange = { onTitleChange(it) },
                            modifier = Modifier
                                .height(screenHeight * 0.1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(screenHeight * 0.015f))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().height(screenHeight * 0.1f)
                        ) {
                            PlantDetailEditBox(
                                detailTypeText = stringResource(SharedRes.strings.dates.resourceId),
                                greyBoxText = dateBoxTextStringId.value?.let { stringResource(it.resourceId) }
                                    ?: "",
                                hasArrow = true,
                                screenHeight = screenHeight,
                                screenWidth = screenWidth,
                                onOpenDialogPress = { Unit }, // TODO
                                onTextValueChange = { },
                                modifier = Modifier.width(boxWidth * 0.48f).fillMaxHeight()
                            )
                            PlantDetailEditBox(
                                detailTypeText = stringResource(SharedRes.strings.time.resourceId),
                                greyBoxText = timeText.value,
                                hasArrow = true,
                                screenHeight = screenHeight,
                                screenWidth = screenWidth,
                                onOpenDialogPress = { Unit }, // TODO
                                onTextValueChange = { },
                                modifier = Modifier.width(boxWidth * 0.48f).fillMaxHeight()
                            )
                        }
                        Spacer(modifier = Modifier.height(screenHeight * 0.015f))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().height(screenHeight * 0.1f)
                        ) {
                            PlantDetailEditBox(
                                detailTypeText = stringResource(SharedRes.strings.water_amount.resourceId),
                                greyBoxText = plant?.waterAmount ?: "",
                                hasArrow = false,
                                screenHeight = screenHeight,
                                screenWidth = screenWidth,
                                maxChars = waterMaxChar,
                                onOpenDialogPress = { },
                                onTextValueChange = { onWaterAmountChange(it) },
                                modifier = Modifier.width(boxWidth * 0.48f).fillMaxHeight()
                            )
                            PlantDetailEditBox(
                                detailTypeText = stringResource(SharedRes.strings.plant_size.resourceId),
                                greyBoxText = plantSizeTextStringId.value?.let { stringResource(it.resourceId) }
                                    ?: "",
                                hasArrow = true,
                                screenHeight = screenHeight,
                                screenWidth = screenWidth,
                                onOpenDialogPress = { Unit }, // TODO
                                onTextValueChange = { },
                                modifier = Modifier.width(boxWidth * 0.48f).fillMaxHeight()
                            )
                        }
                        Spacer(modifier = Modifier.height(screenHeight * 0.015f))

                        PlantDetailEditBox(
                            detailTypeText = stringResource(SharedRes.strings.description.resourceId),
                            greyBoxText = plant?.description ?: "",
                            hasArrow = false,
                            isSingleLine = false,
                            screenHeight = screenHeight,
                            screenWidth = screenWidth,
                            maxChars = descMaxChar,
                            onOpenDialogPress = { },
                            onTextValueChange = { onDescriptionChange(it) },
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                        val buttonText = if (plant?.id == null) {
                            stringResource(SharedRes.strings.create_plant.resourceId)
                        } else {
                            stringResource(SharedRes.strings.save_changes.resourceId)
                        }
                        IconButton(
                            onClick = { onSaveClick() },
                            Modifier
                                .clip(RoundedCornerShape(screenHeight * 0.012f))
                                .fillMaxWidth()
                                .height(screenHeight * 0.06f)
                                .background(color = Accent500)
                        ) {
                            Text(
                                text = buttonText,
                                color = Neutrals0,
                                fontWeight = FontWeight(500),
                                fontSize = 16.sp * scaleFactor,
                                fontStyle = FontStyle(R.font.poppins_medium)
                            )
                        }
                        Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                    }
                }
            }
        }
    }
}

@Composable
private fun PlantDetailEditBox(
    greyBoxText: String,
    detailTypeText: String,
    hasArrow: Boolean,
    screenHeight: Dp,
    screenWidth: Dp,
    maxChars: Int = 100,
    modifier: Modifier = Modifier,
    isSingleLine: Boolean = true,
    onTextValueChange: (String) -> Unit,
    onOpenDialogPress: () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val height = maxHeight
        val roundedCornerShape = RoundedCornerShape(screenHeight * 0.012f)
        val scaleFactor = (screenHeight.value / 792f).coerceIn(0.5f, 1.5f)
        val fontSize = 14 * scaleFactor
        Column(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = detailTypeText,
                color = Neutrals500,
                fontSize = fontSize.sp,
                fontWeight = FontWeight(500),
                fontStyle = FontStyle(R.font.poppins_medium)
            )
            Spacer(Modifier.height(screenHeight * 0.01f))
            GreyBox(
                text = greyBoxText,
                screenWidth = screenWidth,
                hasArrow = hasArrow,
                fontSize = fontSize,
                isSingleLine = isSingleLine,
                roundedCornerShape = roundedCornerShape,
                screenHeight = screenHeight,
                maxChars = maxChars,
                onTextValueChange = onTextValueChange,
                onOpenDialogPress = onOpenDialogPress,
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .heightIn(max = screenHeight * 0.2f)
            )
        }
    }
}

@Composable
private fun GreyBox(
    text: String,
    screenWidth: Dp,
    hasArrow: Boolean,
    fontSize: Float,
    roundedCornerShape: RoundedCornerShape,
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    maxChars: Int = 100,
    isSingleLine: Boolean = true,
    onTextValueChange: (String) -> Unit,
    onOpenDialogPress: () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val height = maxHeight
        val horizontalTextPadding = if (hasArrow) screenWidth * 0.04f else screenWidth * 0.01f
        val scrollState = rememberScrollState()
        val boxModifier = if (isSingleLine) {
            Modifier
                .height(screenHeight * 0.08f)
                .fillMaxWidth()
                .clip(roundedCornerShape)
                .background(color = Neutrals100)
        } else {
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clip(roundedCornerShape)
                .background(color = Neutrals100)
                .verticalScroll(scrollState)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = boxModifier
        ) {
            val arrangement = if (hasArrow)Arrangement.SpaceAround else Arrangement.Start
            Row(
                horizontalArrangement = arrangement,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = horizontalTextPadding)
                    .clickable {
                        if (hasArrow) { onOpenDialogPress() }
                    }
            ) {
                if (hasArrow) {
                    Text(
                        text = text,
                        color = Neutrals500,
                        fontStyle = FontStyle(R.font.poppins_medium),
                        fontWeight = FontWeight(500),
                        fontSize = fontSize.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_down_arrow),
                        tint = Neutrals500,
                        contentDescription = null, // TODO
                        modifier = Modifier.size(screenHeight * 0.025f)
                    )
                } else {
                    TextField(
                        value = text,
                        onValueChange = { newValue ->
                            if (newValue.length <= maxChars) {
                                onTextValueChange(newValue)
                            }
                        },
                        readOnly = false,
                        singleLine = isSingleLine,
                        textStyle = TextStyle(
                            color = Neutrals500,
                            fontStyle = FontStyle(R.font.poppins_medium),
                            fontWeight = FontWeight(500),
                            fontSize = fontSize.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Accent500,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedLabelColor = Color.Transparent
                        )
                    )
                }
            }
        }
    }
}
