package com.usman.mvvmsample.features.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.usman.mvvmsample.persistence.RoomConvertors

@Entity(tableName = "DogBreeds")
data class DogBreeds(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("bred_for")
    val bredFor: String,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @Embedded(prefix = "height_")
    @SerializedName("height")
    val height: Measurement,
    @TypeConverters(RoomConvertors::class)
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
    @Embedded
    @SerializedName("weight")
    val weight: Measurement,
    @SerializedName("description")
    val description: String?
)