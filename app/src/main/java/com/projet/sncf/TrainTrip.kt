package com.projet.sncf

data class TrainTrip(
    val depart: String,
    val arrivee: String,
    val heureDepart: String,
    val heureArrivee: String,
    val type: String
)