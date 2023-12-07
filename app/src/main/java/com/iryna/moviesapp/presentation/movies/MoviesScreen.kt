package com.iryna.moviesapp.presentation.movies

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import com.iryna.moviesapp.presentation.model.MoviesViewEntry
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import coil.compose.rememberAsyncImagePainter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.sp
import com.iryna.moviesapp.presentation.error.ShowErrorScreen
import com.iryna.moviesapp.util.Paddings
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.request.ImageRequest
import com.iryna.moviesapp.R


@Composable
fun MoviesScreen(viewModel: MoviesViewModel = hiltViewModel(), onMovieClick: (movieId: Long) -> Unit) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.getMovies()
    }

    Movies(state = state, onMovieClick = onMovieClick)

}

@Composable
fun Movies(
    state: MoviesState,
    onMovieClick: (workerId: Long) -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
        } else if (!state.error.isNullOrEmpty()) {
            ShowErrorScreen { viewModel.getMovies() }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                if (state.moviesList.movies?.isNotEmpty() == true) {
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        MovieListScreen(state.moviesList.movies, onMovieClick)
                    }
                }
                else
                    FilterComponent(
                        onFilterChanged = { titleFilter, yearFilter ->
                            viewModel.titleFilter = titleFilter
                            viewModel.yearFilter = yearFilter
                            viewModel.filterMovies()
                        },
                        initialTitleFilter = viewModel.titleFilter,
                        initialYearFilter = viewModel.yearFilter
                    )
            }
        }
    }
}


@Composable
fun MovieListScreen(
    movies: List<MoviesViewEntry>,
    onMovieClick: (movieId: Long) -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    LazyColumn(modifier = Modifier
        .padding(Paddings.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(movies) { index, worker ->
            if(index<1)
                FilterComponent(
                    onFilterChanged = { titleFilter, yearFilter ->
                        viewModel.titleFilter = titleFilter
                        viewModel.yearFilter = yearFilter
                        viewModel.filterMovies()
                    },
                    initialTitleFilter = viewModel.titleFilter,
                    initialYearFilter = viewModel.yearFilter
                )
            Spacer(modifier = Modifier.height(10.dp))

            WorkerListItem(worker, onMovieClick)

            if (index == movies.size - 1) {
                Row(horizontalArrangement = Arrangement.Center) {
                    if (viewModel.page > 1) {
                        LoadMoreButton(stringResource(R.string.load_previous_page), onClick = {
                            viewModel.page--
                            viewModel.getMovies()
                        })
                    }

                    LoadMoreButton(stringResource(R.string.load_next_page), onClick = {
                        viewModel.page++
                        viewModel.getMovies()
                    })
                }
            }
        }
    }
}

@Composable
fun WorkerListItem(
    movie: MoviesViewEntry,
    onMovieClick: (movieId: Long) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation =  10.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .clickable { onMovieClick(movie.id ?: 1) }
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = stringResource(id = R.string.path) + movie.posterPath)
                        .apply {
                            placeholder(R.drawable.image_default)
                            error(R.drawable.image_error)
                        }
                        .build()
                ),
                contentDescription = "Movie poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(Paddings.big)
                    .width(70.dp)
                    .height(100.dp)
                    .align(Alignment.CenterVertically)
            )


            Column {
                Spacer(modifier = Modifier.height(Paddings.medium))

                Text(
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "${movie.title}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(Paddings.medium))

                Text(
                    text = if(movie.adult == true) stringResource(id = R.string.adult) else  stringResource(id = R.string.all_ages),
                    color = MaterialTheme.colorScheme.secondary
                )
                IconWithText(icon = Icons.Default.DateRange, contentDescription = "release date", text = "${movie.releaseDate}")
                IconWithText(icon = Icons.Filled.Star, contentDescription = "vote", text = "${movie.voteAverage}")
                Spacer(modifier = Modifier.height(Paddings.medium))
            }
        }
    }
}

@Composable
fun IconWithText(icon: ImageVector, contentDescription: String, text: String){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = Paddings.small)
    ){
        Icon(
            icon, contentDescription,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(Paddings.medium)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LoadMoreButton(
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(56.dp)
            .padding(horizontal = Paddings.medium)
            .padding(vertical = Paddings.regular),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.onPrimary,
        onClick = onClick
    ) {
        Row(
            Modifier.padding(Paddings.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = Paddings.medium)
            )
        }
    }
}

@Composable
fun FilterComponent(
    onFilterChanged: (String, String) -> Unit,
    initialTitleFilter: String = "",
    initialYearFilter: String = ""
) {
    var titleFilter by remember { mutableStateOf(initialTitleFilter) }
    var yearFilter by remember { mutableStateOf(initialYearFilter) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Title Filter
        OutlinedTextField(
            value = titleFilter,
            onValueChange = { titleFilter = it },
            label = { Text("Title Filter") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Date Filter
        OutlinedTextField(
            value = yearFilter,
            onValueChange = { yearFilter = it },
            label = { Text("Date Filter (yyyy-mm-dd)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                onFilterChanged(titleFilter, yearFilter)
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary),
            shape = CircleShape
        ) {
            Text(stringResource(id = R.string.search), color = MaterialTheme.colorScheme.primary)
        }
    }
}




