package com.ifechukwu.moveapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.data.Vehicle
import com.ifechukwu.moveapp.ui.theme.MoveAppTheme
import com.ifechukwu.moveapp.ui.theme.NunitoSans

@Composable
fun VehicleCard(vehicle: Vehicle) {
    var triggerAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        triggerAnimation = true
    }

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            Text(
                text = vehicle.name,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = NunitoSans
            )

            Text(
                text = vehicle.type,
                color = Color.Gray,
                fontSize = 12.sp,
                fontFamily = NunitoSans
            )

            AnimatedVisibility(
                visible = triggerAnimation,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(600)
                ) + fadeIn(animationSpec = tween(600))
            ) {
                Image(
                    painter = painterResource(vehicle.icon),
                    contentDescription = vehicle.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.End)
                        .offset(x = 43.dp, y = (-10).dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun VehicleCardPreview() {
    MoveAppTheme {
        VehicleCard(vehicle = Vehicle.availableVehicles()[0])
    }
}