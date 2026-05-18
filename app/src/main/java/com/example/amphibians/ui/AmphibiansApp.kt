package com.example.amphibians.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.amphibians.model.AmphibianData
import com.example.amphibians.ui.model.AmphibianUiState
import com.example.amphibians.ui.model.AmphibianViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            AmphibiansTopApp(scrollBehavior)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
     ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val amphibianViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)
            AmphibianHomeScreen(
                uiState = amphibianViewModel.uiState,
                retryAction = { amphibianViewModel.getAmphibians() },
                contentPadding = it,
                modifier = Modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopApp(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.amphibians),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}

@Composable
fun AmphibianHomeScreen(
    uiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (uiState) {
        is AmphibianUiState.Error -> AmphibianErrorScreen(modifier.fillMaxSize())
        is AmphibianUiState.Loading -> AmphibianLoadingScreen(modifier.fillMaxSize())
        is AmphibianUiState.Success -> AmphibiansList(
            amphibians = uiState.data,
            modifier = modifier
                .fillMaxWidth()
                .padding(contentPadding)
        )
    }
}

@Composable
fun AmphibianLoadingScreen(modifier: Modifier = Modifier) {

}

@Composable
fun AmphibianErrorScreen(modifier: Modifier = Modifier) {

}
