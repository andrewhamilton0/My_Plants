package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.android.R
import com.example.myplants.android.core.theme.GrayishBlack
import com.example.myplants.android.core.theme.Neutrals0
import com.example.myplants.android.core.theme.Neutrals100
import com.example.myplants.android.core.theme.OtherG100

@Preview
@Composable
fun PlantItemHolder() {
    Card {
        Column(
            modifier = Modifier.width(167.dp)
        ) {
            PlantImageBox()
            Box(
                Modifier.background(
                    color = Neutrals100
                )
            ) {
                Row(
                    modifier = Modifier.height(60.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // TODO FIND DIFFERENCE BETWEEN SPACE AROUND AND SPACEBETWEEN AND SPACEEVENLY
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "HI 2!", color = Color.Black)
                    Text(text = "HI 2!", color = Color.Black)
                }
            }
        }
    }
}

@Preview
@Composable
fun ClearGreyCard() {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = GrayishBlack.copy(alpha = 0.2f),
        elevation = 0.1.dp
    ) {
        Box(
            modifier = Modifier
                .padding(start = 6.dp, top = 2.dp, end = 6.dp, bottom = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Neutrals0,
                fontSize = 10.sp,
                fontStyle = FontStyle(R.font.poppins_medium),
                text = "I went to the mall",
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Preview
@Composable
fun PlantImageBox() {
    Box(
        Modifier
            .background(
                color = OtherG100
            )
            .height(196.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_single_plant),
                contentDescription = null // Todo
            )
        }
        Box(
            modifier = Modifier
                .padding(12.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column {
                ClearGreyCard()
                Spacer(modifier = Modifier.height(4.dp))
                ClearGreyCard()
            }
        }
    }
}
