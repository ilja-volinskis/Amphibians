package com.example.amphibians.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.R
import com.example.amphibians.ui.model.AmphibianUiState
import com.example.amphibians.ui.model.AmphibianViewModel
import com.example.amphibians.ui.theme.AmphibiansTheme


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
        is AmphibianUiState.Error -> AmphibianErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
        is AmphibianUiState.Loading -> AmphibianLoadingScreen(
            modifier = modifier.fillMaxSize()
        )
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
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun AmphibianErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.ic_broken_image),
            contentDescription = stringResource(R.string.error_loading_content),
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = stringResource(R.string.error_loading_content)
        )
        OutlinedButton(
            onClick = retryAction
        ) {
            Text(
                text = stringResource(R.string.retry)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        AmphibianLoadingScreen(
            Modifier
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        AmphibianErrorScreen(
            {},
            Modifier.fillMaxSize()
        )
    }
}
