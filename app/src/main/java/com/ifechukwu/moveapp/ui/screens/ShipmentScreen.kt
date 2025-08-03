package com.ifechukwu.moveapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.R
import com.ifechukwu.moveapp.data.Shipment
import com.ifechukwu.moveapp.data.Status
import com.ifechukwu.moveapp.ui.components.MoveAppBar
import com.ifechukwu.moveapp.ui.theme.MoveAppTheme
import com.ifechukwu.moveapp.ui.theme.NunitoSans
import kotlinx.coroutines.delay

@Composable
fun ShipmentScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var shipments by remember { mutableStateOf(Shipment.shipmentHistory()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App Bar
        MoveAppBar(stringResource(R.string.shipments))

        // Tab Layout
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(600)
            ) + fadeIn(animationSpec = tween(600))
        ) {
            ShipmentTabs(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                    val status = Status.entries[index]
                    shipments = if (status == Status.All) {
                        Shipment.shipmentHistory()
                    } else {
                        Shipment.shipmentHistory().filter { it.status == status }
                    }
                }
            )
        }

        // Shipment List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(shipments) { shipment ->
                AnimatedShipmentCard(shipment)
            }
        }
    }
}

@Composable
fun AnimatedShipmentCard(shipment: Shipment, animationDelay: Int = 0) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(animationDelay.toLong())
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(200, easing = LinearEasing)
        ) + fadeIn()
    ) {
        ShipmentHistoryCard(shipment = shipment)
    }
}

@Composable
fun ShipmentHistoryCard(shipment: Shipment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Status Badge
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Gray.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = getStatusIcon(shipment.status),
                                contentDescription = null,
                                tint = getStatusColor(shipment.status),
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = shipment.status.value,
                                color = getStatusColor(shipment.status),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = NunitoSans
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = shipment.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = NunitoSans
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = shipment.subtitle,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = NunitoSans,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = shipment.amount,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = NunitoSans
                        )

                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color.Gray.copy(alpha = 0.3f))
                        )

                        Text(
                            text = shipment.date,
                            color = Color.Gray,
                            fontSize = 11.sp,
                            fontFamily = NunitoSans
                        )
                    }
                }

                Image(
                    painter = painterResource(R.drawable.parcel),
                    contentDescription = stringResource(R.string.packaging),
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
fun getStatusColor(status: Status): Color {
    return when (status) {
        Status.Completed -> Color(0xFF4CAF50) // Green
        Status.InProgress -> Color(0xFF2196F3) // Blue
        Status.Pending -> Color(0xFFFF9800) // Orange
        Status.Cancelled -> Color(0xFFF44336) // Red
        Status.All -> Color(0xFF9C27B0) // Purple
    }
}

@Composable
fun getStatusIcon(status: Status): ImageVector {
    return when (status) {
        Status.Completed -> Icons.Default.Check
        Status.InProgress -> Icons.Default.Sync
        Status.Pending -> Icons.Default.History
        Status.Cancelled -> Icons.Default.Close
        Status.All -> Icons.Default.Check
    }
}

@Composable
fun ShipmentTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = Status.entries

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth(),
        edgePadding = 20.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 3.dp,
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        tabs.forEachIndexed { index, status ->
            val shipmentCount = if (status == Status.All) {
                Shipment.shipmentHistory().size
            } else {
                Shipment.shipmentHistory().count { it.status == status }
            }

            val isSelected = selectedTabIndex == index

            Tab(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = status.value,
                            fontSize = 14.sp,
                            fontFamily = NunitoSans,
                            color = if (isSelected) MaterialTheme.colorScheme.surface else Color.Gray,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // Badge
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = if (isSelected)
                                        MaterialTheme.colorScheme.tertiary
                                    else
                                        Color.Gray.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (shipmentCount > 999) "999+" else shipmentCount.toString(),
                                color = if (isSelected) Color.White else Color.Gray,
                                fontSize = 10.sp,
                                fontFamily = NunitoSans
                            )
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentCardPreview() {
    MoveAppTheme {
        ShipmentHistoryCard(shipment = Shipment.shipmentHistory().first())
    }
}

@Preview
@Composable
fun ShipmentScreenPreview() {
    MoveAppTheme {
        ShipmentScreen()
    }
}

