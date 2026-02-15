package com.projet.sncf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.projet.sncf.ui.theme.SncfTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    // Initialisation de Retrofit
    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://cef52544-7e83-43ae-a503-691008ba89ea@api.sncf.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SncfApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SncfTheme {
                var currentScreen by remember { mutableStateOf("search") }
                var tripsList by remember { mutableStateOf<List<TrainTrip>>(emptyList()) }

                if (currentScreen == "search") {
                    SNCFSearchScreen(onSearchRequested = { dep, arr ->
                        // On utilise lifecycleScope car on est dans l'Activity
                        lifecycleScope.launch {
                            try {
                                val apiKey = "cef52544-7e83-43ae-a503-691008ba89ea"

                                // 1. Trouver l'ID du lieu de départ
                                val startPlaces = apiService.getPlaces(apiKey, dep)
                                val startId = startPlaces.places.firstOrNull()?.id ?: throw Exception("Lieu de départ non trouvé")

                                // 2. Trouver l'ID du lieu d'arrivée
                                val endPlaces = apiService.getPlaces(apiKey, arr)
                                val endId = endPlaces.places.firstOrNull()?.id ?: throw Exception("Lieu d'arrivée non trouvé")

                                // 3. Rechercher le trajet avec les IDs obtenus
                                val response = apiService.getJourneys(apiKey, startId, endId)

                                tripsList = response.journeys.map { journey ->
                                    TrainTrip(
                                        depart = dep,
                                        arrivee = arr,
                                        heureDepart = formatSncfTime(journey.departure_date_time),
                                        heureArrivee = formatSncfTime(journey.arrival_date_time),
                                        type = journey.sections.firstOrNull()?.display_informations?.commercial_mode ?: "Train"
                                    )
                                }
                                currentScreen = "results"
                            } catch (e: Exception) {
                                Log.e("SNCF_API", "Erreur : ${e.message}")
                            }
                        }
                    })
                } else {
                    ResultScreen(trips = tripsList, onBack = { currentScreen = "search" })
                }
            }
        }
    }

    private fun formatSncfTime(date: String): String {
        return try { date.substring(9, 11) + ":" + date.substring(11, 13) } catch (e: Exception) { date }
    }
}
@Composable
fun SNCFSearchScreen(onSearchRequested: (String, String) -> Unit) {
    var villeDepart by remember { mutableStateOf("") }
    var villeArrivee by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Center) {
        Text("Itinéraire SNCF", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value = villeDepart,
            onValueChange = { villeDepart = it },
            label = { Text("Ville de départ") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = villeArrivee,
            onValueChange = { villeArrivee = it },
            label = { Text("Ville d'arrivée ") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                if (villeDepart.isNotBlank() && villeArrivee.isNotBlank()) {
                    onSearchRequested(villeDepart, villeArrivee)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Rechercher les horaires")
        }
    }
}

@Composable
fun ResultScreen(trips: List<TrainTrip>, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Button(onClick = onBack, modifier = Modifier.padding(16.dp)) { Text("Retour") }
        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(trips) { trip ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("${trip.heureDepart} ➔ ${trip.heureArrivee}", style = MaterialTheme.typography.titleLarge)
                            Text(trip.type, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}