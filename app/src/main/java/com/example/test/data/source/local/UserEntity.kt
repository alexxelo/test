package com.example.test.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "users")
data class UserEntity(
  @PrimaryKey
  val uuid: String,
  val gender: String,
  val title: String,
  val firstName: String,
  val lastName: String,
  val streetNumber: Int,
  val streetName: String,
  val city: String,
  val state: String,
  val country: String,
  val postcode: String,
  val latitude: String,
  val longitude: String,
  val timezoneOffset: String,
  val timezoneDescription: String,
  val email: String,
  val username: String,
  val password: String,
  val salt: String,
  val md5: String,
  val sha1: String,
  val sha256: String,
  val dobDate: String,
  val dobAge: Int,
  val registeredDate: String,
  val registeredAge: Int,
  val phone: String,
  val cell: String,
  val idName: String,
  val idValue: String,
  val pictureLarge: String,
  val pictureMedium: String,
  val pictureThumbnail: String,
  val nat: String
)
