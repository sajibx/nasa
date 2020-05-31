package com.ss.nasa.fragment.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

data class Covid(
    var Countries: List<Country>,
    var Date: String,
    var Global: Global
)

data class Country(
    var Country: String,
    var CountryCode: String,
    var Date: String,
    var NewConfirmed: Int,
    var NewDeaths: Int,
    var NewRecovered: Int,
    var Slug: String,
    var TotalConfirmed: Int,
    var TotalDeaths: Int,
    var TotalRecovered: Int
)

data class Global(
    var NewConfirmed: Int,
    var NewDeaths: Int,
    var NewRecovered: Int,
    var TotalConfirmed: Int,
    var TotalDeaths: Int,
    var TotalRecovered: Int
)

data class Covid2(
    var Countries: List<Country>,
    var Date: String,
    var Global: Global
)
{
    class Deserializer : ResponseDeserializable<Covid> {
        override fun deserialize(content: String): Covid? = Gson().fromJson(content, Covid::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<Covid>> {
        override fun deserialize(reader: Reader): List<Covid> {
            val type = object : TypeToken<List<Covid>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}

data class Air_Quality(
    var meta: Meta,
    var results: List<Result>
)

data class Meta(
    var found: Int,
    var license: String,
    var limit: Int,
    var name: String,
    var page: Int,
    var website: String
)

data class Result(
    var cities: List<String>,
    var city: String,
    var coordinates: Coordinates,
    var count: Int,
    var country: String,
    var countsByMeasurement: List<CountsByMeasurement>,
    var firstUpdated: String,
    var id: String,
    var lastUpdated: String,
    var location: String,
    var locations: List<String>,
    var parameters: List<String>,
    var sourceName: String,
    var sourceNames: List<String>,
    var sourceType: String,
    var sourceTypes: List<String>
)

data class Coordinates(
    var latitude: Double,
    var longitude: Double
)

data class CountsByMeasurement(
    var count: Int,
    var parameter: String
)
data class Air_Quality2(
    var meta: Meta,
    var results: List<Result>
)
{
    class Deserializer : ResponseDeserializable<Air_Quality> {
        override fun deserialize(content: String): Air_Quality? = Gson().fromJson(content, Air_Quality::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<Air_Quality>> {
        override fun deserialize(reader: Reader): List<Air_Quality> {
            val type = object : TypeToken<List<Air_Quality>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}




data class Weather_Data(
    var city: City,
    var cnt: Int,
    var cod: String,
    var list: List<X>,
    var message: Int
)

data class City(
    var coord: Coord,
    var country: String,
    var id: Int,
    var name: String,
    var population: Int,
    var sunrise: Int,
    var sunset: Int,
    var timezone: Int
)

data class Coord(
    var lat: Int,
    var lon: Int
)

data class X(
    var clouds: Clouds,
    var dt: Int,
    var dt_txt: String,
    var main: Main,
    var rain: Rain,
    var sys: Sys,
    var weather: List<Weather>,
    var wind: Wind
)

data class Clouds(
    var all: Int
)

data class Main(
    var feels_like: Double,
    var grnd_level: Int,
    var humidity: Int,
    var pressure: Int,
    var sea_level: Int,
    var temp: Double,
    var temp_kf: Double,
    var temp_max: Double,
    var temp_min: Double
)

data class Rain(
    var `3h`: Double
)

data class Sys(
    var pod: String
)

data class Weather(
    var description: String,
    var icon: String,
    var id: Int,
    var main: String
)

data class Wind(
    var deg: Int,
    var speed: Double
)
data class Weather_Data2(
    var city: City,
    var cnt: Int,
    var cod: String,
    var list: List<X>,
    var message: Int
)
{
    class Deserializer : ResponseDeserializable<Weather_Data> {
        override fun deserialize(content: String): Weather_Data? = Gson().fromJson(content, Weather_Data::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<Weather_Data>> {
        override fun deserialize(reader: Reader): List<Weather_Data> {
            val type = object : TypeToken<List<Weather_Data>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}
