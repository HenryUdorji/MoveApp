package com.ifechukwu.moveapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.R
import com.ifechukwu.moveapp.data.SearchItem
import com.ifechukwu.moveapp.ui.components.SearchTextField
import com.ifechukwu.moveapp.ui.theme.NunitoSans

@Composable
fun SearchScreen(
    onBackClick: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var searchItems by remember { mutableStateOf(SearchItem.searchItems()) }

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
        SearchAppBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                searchQuery = query
                searchItems = if (query.isEmpty()) {
                    SearchItem.searchItems()
                } else {
                    SearchItem.searchItems().filter {
                        it.number.lowercase().contains(query.lowercase())
                    }
                }
            },
            onBackClick = onBackClick
        )

        // Search Results
        AnimatedVisibility(
            visible = triggerAnimation,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(600)
            ) + fadeIn(animationSpec = tween(600))
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(searchItems) { searchItem ->
                        SearchItemCard(searchItem = searchItem)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchAppBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            SearchTextField(
                modifier = Modifier.weight(1f),
                onValueChange = onSearchQueryChange,
                value = searchQuery,
            )
        }
    }
}

@Composable
fun SearchItemCard(searchItem: SearchItem) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Profile Image with Parcel Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.parcel),
                    contentDescription = stringResource(R.string.packaging),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = searchItem.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = NunitoSans
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = searchItem.number,
                        color = Color.Gray,
                        fontSize = 13.sp,
                        fontFamily = NunitoSans
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = 0.3f))
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = searchItem.location.first,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = NunitoSans
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Icon(
                        imageVector = Icons.Default.East,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = searchItem.location.second,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = NunitoSans,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Divider(
            color = MaterialTheme.colorScheme.background,
            thickness = 1.dp
        )
    }
}