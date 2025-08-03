package com.ifechukwu.moveapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.R
import com.ifechukwu.moveapp.ui.components.MoveAppBar
import com.ifechukwu.moveapp.ui.theme.NunitoSans

@Composable
fun CalculateScreen(
    onCalculateClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("") }
    val categories = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")

    var triggerAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        triggerAnimation = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App Bar
        MoveAppBar(stringResource(R.string.calculate))

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {
            // Destination Section
            DestinationSection(triggerAnimation = triggerAnimation)

            // Packaging Section
            AnimatedVisibility(
                visible = triggerAnimation,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600, delayMillis = 200)
                ) + fadeIn(animationSpec = tween(600, delayMillis = 200))
            ) {
                PackagingSection()
            }

            // Categories Section
            CategoriesSection(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                triggerAnimation = triggerAnimation
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Calculate Button
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600, delayMillis = 600)
                ) + fadeIn(animationSpec = tween(600, delayMillis = 600))
            ) {
                CalculateButton(onClick = onCalculateClick)
            }
        }
    }
}

@Composable
fun DestinationSection(
    triggerAnimation: Boolean
) {
    var senderLocation by remember { mutableStateOf("") }
    var receiveLocation by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.destination),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoSans,
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            AnimatedVisibility(
                visible = triggerAnimation,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600)
                ) + fadeIn(animationSpec = tween(600))
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    // Sender Location
                    LocationField(
                        icon = R.drawable.ic_sender_location,
                        hint = stringResource(R.string.sender_location),
                        value = senderLocation,
                        onValueChange = { senderLocation = it }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Receiver Location
                    LocationField(
                        icon = R.drawable.ic_receiver_location,
                        hint = stringResource(R.string.receiver_location),
                        value = receiveLocation,
                        onValueChange = { receiveLocation = it }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Weight Field
                    LocationField(
                        icon = R.drawable.ic_approx_weight,
                        hint = "Approx weight",
                        value = weight,
                        keyboardType = KeyboardType.Number,
                        onValueChange = { weight = it }
                    )
                }
            }
        }
    }
}

@Composable
fun LocationField(
    icon: Int,
    hint: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray.copy(alpha = 0.1f))
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.width(10.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    color = Color.Gray,
                    fontSize = 14.sp,

                    fontFamily = NunitoSans
                )
            },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Composable
fun PackagingSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.packaging),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoSans,
            modifier = Modifier.padding(top = 30.dp)
        )

        Text(
            text = stringResource(R.string.what_are_you_sending),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 15.sp,
            fontFamily = NunitoSans,
            modifier = Modifier.padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.parcel),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(40.dp),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Box",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontFamily = NunitoSans,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun CategoriesSection(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    triggerAnimation: Boolean
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        AnimatedVisibility(
            visible = triggerAnimation,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(600)
            ) + fadeIn(animationSpec = tween(600))
        ) {
            Text(
                text = stringResource(R.string.categories),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoSans,
                modifier = Modifier.padding(top = 30.dp)
            )

            Text(
                text = stringResource(R.string.what_are_you_sending),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontFamily = NunitoSans,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        AnimatedVisibility(
            visible = triggerAnimation,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(600)
            ) + fadeIn(animationSpec = tween(600))
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 5,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    CategoryChip(
                        text = category,
                        isSelected = selectedCategory == category,
                        onClick = { onCategorySelected(category) }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        onClick = onClick,
        label = {
            Text(
                text = text,
                fontSize = 12.sp,
                fontFamily = NunitoSans
            )
        },
        selected = isSelected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun CalculateButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = NunitoSans,
        )
    }
}