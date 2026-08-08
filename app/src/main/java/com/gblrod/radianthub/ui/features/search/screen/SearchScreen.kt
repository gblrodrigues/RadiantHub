package com.gblrod.radianthub.ui.features.search.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.extensions.navigateFromSearch
import com.gblrod.radianthub.ui.features.search.components.EmptySearchResult
import com.gblrod.radianthub.ui.features.search.components.SearchField
import com.gblrod.radianthub.ui.features.search.components.SearchInitialContent
import com.gblrod.radianthub.ui.features.search.components.SearchResultItem
import com.gblrod.radianthub.ui.features.search.model.SearchType
import com.gblrod.radianthub.ui.features.search.state.SearchUiState
import com.gblrod.radianthub.ui.features.search.viewmodel.SearchViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    val uiState by searchViewModel.searchState.collectAsState()

    val focus = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    when (val state = uiState) {
        is SearchUiState.Loading -> {
            LoadingScreen()
        }

        is SearchUiState.Error -> {
            val message = if (state.code == null) {
                stringResource(id = state.messageResId)
            } else {
                stringResource(id = state.messageResId, state.code)
            }

            ErrorMessage(
                message = message,
                onRetry = { searchViewModel.retry() }
            )
        }

        is SearchUiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                focus.clearFocus(force = true)
                                keyboardController?.hide()
                            }
                        )
                    }
            ) {
                SearchField(
                    query = state.query,
                    onQueryChange = { searchViewModel.updateQuery(it) },
                    onBackClick = { navHostController.popBackStack() }
                )

                when {
                    state.query.isBlank() -> {
                        SearchInitialContent()
                    }

                    state.results.isEmpty() -> {
                        EmptySearchResult(query = state.query)
                    }

                    else -> {
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyColumn {
                            items(state.results) { item ->
                                SearchResultItem(
                                    item = item,
                                    onClick = {
                                        when (item.type) {
                                            SearchType.AGENT -> {
                                                searchViewModel.clearSearch()

                                                navHostController.navigateFromSearch(
                                                    targetRoute = Routes.Agents.createRoute(item.uuid),
                                                    targetBaseRoute = Routes.Agents.ROUTE
                                                )
                                            }

                                            SearchType.MAP -> {
                                                searchViewModel.clearSearch()

                                                navHostController.navigateFromSearch(
                                                    targetRoute = Routes.Maps.createRoute(item.uuid),
                                                    targetBaseRoute = Routes.Maps.ROUTE
                                                )
                                            }

                                            SearchType.CARD -> {
                                                searchViewModel.clearSearch()

                                                navHostController.navigateFromSearch(
                                                    targetRoute = Routes.Cards.createRoute(item.uuid),
                                                    targetBaseRoute = Routes.Cards.ROUTE
                                                )
                                            }

                                            SearchType.TIER -> {
                                                searchViewModel.clearSearch()

                                                navHostController.navigateFromSearch(
                                                    targetRoute = Routes.Tiers.createRoute(item.tierId),
                                                    targetBaseRoute = Routes.Tiers.ROUTE
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}