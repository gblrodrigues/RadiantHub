package com.gblrod.radianthub.ui.features.maps.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
                name = maps.name,
                onFavoriteClick = {}
            )

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = maps.icon,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.weight(1f)
            )
        }

        PagerIndicator(
            currentPage = currentPage,
            pageCount = pageCount,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
        )
    }
}