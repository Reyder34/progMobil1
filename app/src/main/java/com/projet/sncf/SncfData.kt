package com.projet.sncf

data class SncfResponse(
    val journeys: List<Journey> = emptyList()
)

data class PlacesResponse(
    val places: List<Place> = emptyList()
)

data class Place(
    val id: String,
    val name: String
)

data class Journey(
    val departure_date_time: String,
    val arrival_date_time: String,
    val sections: List<Section>
)

data class Section(
    val display_informations: DisplayInfo? = null
)

data class DisplayInfo(
    val commercial_mode: String? = null,
    val network: String? = null
)