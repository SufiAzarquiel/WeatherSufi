package net.azarquiel.weathersufi.model

import com.google.gson.annotations.SerializedName

// Data class to parse the JSON response from the GSON library
data class Result(
    @SerializedName("list")
    var days: List<Day>
)

data class Day(
    @SerializedName("dt_txt")
    var date: String,
    @SerializedName("main")
    var temp: Temp,
    var weather: List<Weather>,
    @SerializedName("pop")
    var probability: Double
)

data class Temp (
    @SerializedName("temp_min")
    var min: Double = 0.0,
    @SerializedName("temp_max")
    var max: Double = 0.0
)

class Weather (
    var description: String,
    var icon: String
)
