package com.ifechukwu.moveapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.R
import com.ifechukwu.moveapp.ui.theme.NunitoSans

@Composable
fun StatusScreen(
    onBackClick: () -> Unit = {}
) {
    var isAnimationComplete by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Parcel Animation
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(1000, delayMillis = 300)
                ) + fadeIn(animationSpec = tween(1000, delayMillis = 300))
            ) {
                Image(
                    painter = painterResource(R.drawable.parcel),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Amount Text
            AnimatedCounter(
                targetValue = 1460,
                durationMillis = 1000,
                onAnimationComplete = {
                    isAnimationComplete = true
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Status Text
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600, delayMillis = 700)
                ) + fadeIn(animationSpec = tween(600, delayMillis = 700))
            ) {
                Text(
                    text = stringResource(R.string.total_estimated_amount),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoSans
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Info Text
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600, delayMillis = 900)
                ) + fadeIn(animationSpec = tween(600, delayMillis = 900))
            ) {
                Text(
                    text = stringResource(R.string.estimated_amount_info),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = NunitoSans,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Back to Home Button
            AnimatedVisibility(
                visible = isAnimationComplete,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600)
                ) + fadeIn(animationSpec = tween(600))
            ) {
                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = stringResource(R.string.back_to_home),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = NunitoSans
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedCounter(
    targetValue: Int = 1460,
    durationMillis: Int = 2000,
    onAnimationComplete: () -> Unit,
) {
    var startAnimation by remember { mutableStateOf(false) }

    val animatedValue by animateIntAsState(
        targetValue = if (startAnimation) targetValue else 0,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = LinearOutSlowInEasing
        ),
        label = "counter",
        finishedListener = { finalValue ->
            // This is called when animation completes
            if (finalValue == targetValue) {
                onAnimationComplete()
            }
        }
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Text(
        text = "$${animatedValue} USD",
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = NunitoSans
    )
}