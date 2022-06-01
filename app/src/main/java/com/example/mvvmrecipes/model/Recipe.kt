package com.example.mvvmrecipes.model
import com.google.gson.annotations.SerializedName

data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int = 0,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: String,
    val dateUpdated: String
)

data class RecipeDto(

    @SerializedName("pk")
    var pk: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("publisher")
    var publisher: String,

    @SerializedName("featured_image")
    var featuredImage: String,

    @SerializedName("rating")
    var rating: Int = 0,

    @SerializedName("source_url")
    var sourceUrl: String,

    @SerializedName("ingredients")
    var ingredients: List<String> = emptyList(),

    @SerializedName("date_added")
    var dateAdded: String,

    @SerializedName("date_updated")
    var dateUpdated: String,
)

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)