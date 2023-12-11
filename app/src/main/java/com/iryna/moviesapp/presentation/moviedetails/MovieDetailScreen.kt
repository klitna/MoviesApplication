package com.iryna.moviesapp.presentation.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.iryna.moviesapp.presentation.error.ShowErrorScreen
import com.iryna.moviesapp.presentation.model.MovieDetailedViewEntry
import com.iryna.moviesapp.presentation.topbar.TopBar
import com.iryna.moviesapp.util.Paddings
import com.iryna.moviesapp.R
import com.iryna.moviesapp.util.Constants

@Composable
fun MovieDetails(
    viewModel: MovieDetailedViewModel = hiltViewModel(),
    onCloseClick: () -> Unit,
    movieId: Int
) {
    val state by viewModel.state.collectAsState()

    viewModel.movieId = movieId

    LaunchedEffect(viewModel) {
        viewModel.getWorkerDetailed(viewModel.movieId)
    }

    Surface(modifier =  Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        WorkerDetailedScreen(state, onCloseClick)
    }
}

@Composable
fun WorkerDetailedScreen(state: MovieDetailedState, onCloseClick: () -> Unit, viewModel: MovieDetailedViewModel = hiltViewModel()){
    Column(modifier = Modifier.fillMaxSize()
    ){
        TopBar(text = stringResource(id = R.string.movie_details), onCloseClick = onCloseClick)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column{

                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                if(!state.error.isNullOrEmpty()){
                    ShowErrorScreen { viewModel.getWorkerDetailed(viewModel.movieId) }
                }

                if (!state.movieDetailed?.title.isNullOrEmpty()) {
                    MovieDetailedContent(state)
                }
            }
        }
    }
}

@Composable
fun MovieDetailedContent(state: MovieDetailedState) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(Paddings.big)
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = stringResource(id = R.string.path) + state.movieDetailed?.posterPath).apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.image_default)
                                error(R.drawable.image_error)
                            }).build()
                    ),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Paddings.medium)
                        .clip(RoundedCornerShape(Paddings.medium)),
                    contentScale = ContentScale.Crop
                )

                SectionTitle(stringResource(id = R.string.description))

                state.movieDetailed?.overview?.let { RoundedDescription(it) }

                SectionTitle(stringResource(id = R.string.movie_info))

                FilmInfoSection(state.movieDetailed)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String){
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .padding(top = Paddings.medium, bottom = Paddings.small)
            .fillMaxSize()
    )
}
@Composable
fun RoundedDescription(description: String) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(Paddings.medium)
            )
            .fillMaxWidth()
            .padding(bottom = Paddings.medium)
    ) {
            Text(
                text = description,
                modifier = Modifier.padding(Paddings.medium),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
    }
}

@Composable
fun FieldValuePair(field: String, value: String, dividerVisible: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.medium, vertical = Paddings.regular)
    ) {
        Text(
            text = field,
            modifier = Modifier
                .weight(1f)
                .padding(end = Paddings.regular),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
    if(dividerVisible)
        Divider(
            color = MaterialTheme.colorScheme.primaryContainer,
            thickness = Paddings.extratiny
        )
}

@Composable
fun FilmInfoSection(movieDetails: MovieDetailedViewEntry?){
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(Paddings.medium)
            )
            .fillMaxWidth()
            .padding(bottom = Paddings.medium)
    ) {
        Column{
            FieldValuePair(stringResource(id = R.string.title), "${movieDetails?.title} ${movieDetails?.id}", true)
            FieldValuePair(stringResource(id = R.string.date), movieDetails?.releaseDate.toString(), true)
            FieldValuePair(stringResource(id = R.string.average_vote), movieDetails?.voteAverage.toString(), true)
            FieldValuePair(stringResource(id = R.string.number_of_votes), movieDetails?.voteCount.toString(), true)
            FieldValuePair(stringResource(id = R.string.original_language), movieDetails?.originalLanguage.toString(), true)
            if(movieDetails?.originalLanguage.toString() != Constants.ENGLISH)
                FieldValuePair(stringResource(id = R.string.original_title), movieDetails?.originalTitle.toString(), true)
            FieldValuePair(stringResource(id = R.string.footage_location), movieDetails?.productionCountries?.get(0)?.name ?: stringResource(id = R.string.unknown), true)
        }
    }
}





