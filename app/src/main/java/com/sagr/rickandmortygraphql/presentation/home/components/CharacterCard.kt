package com.sagr.rickandmortygraphql.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.R

@Composable
fun CharacterGridCard(
    character: GetAllCharactersQuery.Result,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,

    ) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick(character.id!!)
            }
    ) {
        Box() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image!!)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.height(150.dp),
                contentScale = ContentScale.FillBounds,
            )
            CharacterInfo(
                modifier = Modifier.align(Alignment.BottomStart),
                character = character,
            )
        }
    }
}


