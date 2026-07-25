package com.gblrod.radianthub.ui.features.maps.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gblrod.radianthub.domain.maps.model.Maps
import com.gblrod.radianthub.ui.shared.components.PagerIndicator
import com.gblrod.radianthub.ui.shared.components.SectionHeader

@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    maps: Maps,
    currentPage: Int,
    pageCount: Int
) {
    var showCallouts by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionHeader(
                name = maps.name
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                AsyncImage(
                    model = maps.splash,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            MapInfoSection(
                callouts = maps.callouts,
                maps = maps,
                onViewCallouts = { showCallouts = true }
            )
        }

        PagerIndicator(
            currentPage = currentPage,
            pageCount = pageCount,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

    if (showCallouts) {
        MapCalloutsBottomSheet(
            onDismiss = { showCallouts = false },
            callouts = maps.callouts
        )
    }
}