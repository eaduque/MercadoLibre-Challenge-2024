package co.com.mercadolibre.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.com.mercadolibre.core.designsystem.component.AnimatedVectorDrawable
import co.com.mercadolibre.features.search.ui.SuggestionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeliSearchBar(modifier: Modifier = Modifier) {
  val viewModel: SearchViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  SearchBar(
    modifier = modifier
      .fillMaxWidth()
      .semantics { traversalIndex = 0f },
    inputField = {
      SearchBarDefaults.InputField(
        query = uiState.searchQuery,
        onQueryChange = viewModel::onQueryChanged,
        onSearch = viewModel::onSearch,
        expanded = uiState.expanded,
        onExpandedChange = viewModel::onExpandedChange,
        placeholder = { Text(stringResource(R.string.search_on_mercado_libre)) },
        leadingIcon = {
          IconButton(onClick = viewModel::leadingIconClick) {
            AnimatedVectorDrawable(uiState.expanded, R.drawable.search_icon_anim)
          }
        },
        trailingIcon = {
          if (uiState.searchQuery.isNotEmpty() && uiState.expanded) {
            IconButton(onClick = viewModel::clearQuery) {
              Icon(Icons.Default.Cancel, contentDescription = null)
            }
          }
        },
      )
    },
    expanded = uiState.expanded,
    colors = SearchBarColors(
      containerColor = SearchBarDefaults.colors().containerColor,
      dividerColor = Color.Transparent,
    ),
    onExpandedChange = viewModel::onExpandedChange,
  ) {
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
      Box {
        Column {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color.Transparent)
              .height(1.dp)
              .shadow(
                elevation = 4.dp,
                shape = RectangleShape,
              )
          )
          LazyColumn {
            items(uiState.suggestions, key = { it.suggestion }) { suggestion ->
              SuggestionItem(suggestion) {
                viewModel.onSuggestionClick(it)
              }
            }
          }
        }
        if (uiState.isLoading) {
          LinearProgressIndicator(
            modifier = Modifier
              .fillMaxWidth()
              .height(2.dp),
            strokeCap = StrokeCap.Square,
          )
        }
      }
    }
  }
}