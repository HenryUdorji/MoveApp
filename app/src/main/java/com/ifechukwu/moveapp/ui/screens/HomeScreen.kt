package com.ifechukwu.moveapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Flip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.R
import com.ifechukwu.moveapp.data.Vehicle
import com.ifechukwu.moveapp.ui.components.SearchTextField
import com.ifechukwu.moveapp.ui.components.VehicleCard
import com.ifechukwu.moveapp.ui.theme.MoveAppTheme
import com.ifechukwu.moveapp.ui.theme.NunitoSans

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit
) {
    var triggerAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        triggerAnimation = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App Bar
        AnimatedVisibility(
            visible = triggerAnimation,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(600)
            ) + fadeIn(animationSpec = tween(600))
        ) {
            HomeAppBar { onSearchClick() }
        }

        // Content
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            AnimatedVisibility(
                visible = triggerAnimation,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600)
                ) + fadeIn(animationSpec = tween(600))
            ) {
                // Tracking Section
                Text(
                    text = stringResource(R.string.tracking),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Shipment Info Card
                ShipmentInfoCard()
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Available Vehicles Section
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = stringResource(R.string.available_vehicles),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Vehicles List
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(Vehicle.availableVehicles()) { vehicle ->
                VehicleCard(vehicle = vehicle)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun HomeAppBar(
    onSearchClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    // Profile Image
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = stringResource(R.string.profile_image),
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_location),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = stringResource(R.string.your_location),
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp,
                                fontFamily = NunitoSans
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Wertheimer, illinois",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = NunitoSans
                            )

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_notification_item),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Search Bar
            SearchTextField(
                onSearchClick = onSearchClick,
                onValueChange = {},
                value = "",
                readOnly = true
            )
        }
    }
}

@Composable
fun ShipmentInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.shipment_number),
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = NunitoSans
                    )

                    Text(
                        text = "NEJ2005631233332",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = NunitoSans,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Image(
                    painter = painterResource(R.drawable.forklift),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colorScheme.background
            )

            // Sender Info
            ShipmentInfoRow(
                icon = R.drawable.empty_box,
                label = stringResource(R.string.sender),
                value = "Atlanta, 5243",
                timeLabel = stringResource(R.string.time),
                timeValue = "2days - 3days",
                showGreenIndicator = true,
                backgroundColor = Color(0xFFFA6161)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Receiver Info
            ShipmentInfoRow(
                icon = R.drawable.empty_box,
                label = stringResource(R.string.receiver),
                value = "Chicago, 6342",
                timeLabel = stringResource(R.string.status),
                timeValue = "Waiting to collect",
                showGreenIndicator = false,
                backgroundColor = Color(0xFFC1F875)
            )

            Divider(
                modifier = Modifier.padding(vertical = 15.dp),
                color = MaterialTheme.colorScheme.background
            )

            // Add Shop Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.add_shop),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoSans
                )
            }
        }
    }
}

@Composable
fun ShipmentInfoRow(
    icon: Int,
    label: String,
    value: String,
    timeLabel: String,
    timeValue: String,
    showGreenIndicator: Boolean,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 12.sp,
                fontFamily = NunitoSans
            )

            Text(
                text = value,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontFamily = NunitoSans,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = timeLabel,
                color = Color.Gray,
                fontSize = 12.sp,
                fontFamily = NunitoSans
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                if (showGreenIndicator) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }

                Text(
                    text = timeValue,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontFamily = NunitoSans
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MoveAppTheme {
        HomeScreen {  }
    }
}