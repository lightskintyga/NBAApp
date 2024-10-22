package com.example.nbaapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.nbaapp.R
import com.example.nbaapp.model.Team

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(team: Team, onBackPress: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = team.name, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_back),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xffcc5500),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val (image, nameText, cityText, abbreviationText, conferenceText, statsCard) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.team_logo),
                contentDescription = "Team Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "Команда: ${team.name}",
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(image.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Город: ${team.city}",
                modifier = Modifier.constrainAs(cityText) {
                    top.linkTo(nameText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Аббревиатура: ${team.abbreviation}",
                modifier = Modifier.constrainAs(abbreviationText) {
                    top.linkTo(cityText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Конференция: ${team.conference}",
                modifier = Modifier.constrainAs(conferenceText) {
                    top.linkTo(abbreviationText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(statsCard) {
                        top.linkTo(conferenceText.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "И", fontWeight = FontWeight.Bold)
                            Text(text = team.gamesPlayed.toString())
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "В", fontWeight = FontWeight.Bold)
                            Text(text = team.gamesWin.toString())
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "П", fontWeight = FontWeight.Bold)
                            Text(text = team.gamesLose.toString())
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "ПП", fontWeight = FontWeight.Bold)
                            Text(text = team.winsPercentage.toString())
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TeamDetailScreenPreview() {
    TeamDetailScreen(
        team = Team(
            id = 1,
            name = "Los Angeles Lakers",
            city = "Los Angeles",
            abbreviation = "LAL",
            conference = "West",
            gamesPlayed = 40,
            gamesWin = 19,
            gamesLose = 21,
            winsPercentage = 0.475
        ),
        onBackPress = {}
    )
}