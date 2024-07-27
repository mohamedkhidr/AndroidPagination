package com.khidrew.pagination.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cats")
data class Cat(@PrimaryKey val id:String, val url:String)
