package com.usman.mvvmsample.features.model

import com.google.gson.annotations.SerializedName

data class DogBreeds(
    @SerializedName("id")
    val id: Int,
    @SerializedName("bred_for")
    val bredFor: String,
    @SerializedName("breed_group")
    val breedGroup: String,
    @SerializedName("height")
    val height: Height,
    @SerializedName("image")
    val image: Image,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("reference_image_id")
    val referenceImageId: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("weight")
    val weight: Weight,
    @SerializedName("description")
    val description: String?
)