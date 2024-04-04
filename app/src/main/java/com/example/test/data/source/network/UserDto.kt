package com.example.test.data.source.network

import com.example.test.domain.models.UserDetails


data class UserResponse(
  val results: List<User>,
  val info: Info
)


data class User(
  val gender: String,
  val name: UserName,
  val location: UserLocation,
  val email: String,
  val login: UserLogin,
  val dob: UserDob,
  val registered: UserRegistered,
  val phone: String,
  val cell: String,
  val id: UserId,
  val picture: UserPicture,
  val nat: String
)

data class UserName(
  val title: String,
  val first: String,
  val last: String
)
data class UserLocation(
  val street: UserStreet,
  val city: String,
  val state: String,
  val country: String,
  val postcode: String,
  val coordinates: UserCoordinates,
  val timezone: UserTimezone
)
data class UserStreet(
  val number: Int,
  val name: String
)

data class UserCoordinates(
  val latitude: String,
  val longitude: String
)

data class UserTimezone(
  val offset: String,
  val description: String
)

data class UserLogin(
  val uuid: String,
  val username: String,
  val password: String,
  val salt: String,
  val md5: String,
  val sha1: String,
  val sha256: String
)

data class UserDob(
  val date: String,
  val age: Int
)

data class UserRegistered(
  val date: String,
  val age: Int
)

data class UserId(
  val name: String,
  val value: String? = ""
)

data class UserPicture(
  val large: String,
  val medium: String,
  val thumbnail: String
)

data class Info(
  val seed: String,
  val results: Int,
  val page: Int,
  val version: String
)
fun UserResponse.toUserDetails(): UserDetails {
  val user = results.first()
  val name = user.name
  val location = user.location
  val street = location.street
  val coordinates = location.coordinates
  val timezone = location.timezone
  val login = user.login
  val dob = user.dob
  val registered = user.registered
  val id = user.id
  val picture = user.picture
  val info = info

  return UserDetails(
    uuid = login.uuid,
    gender = user.gender,
    title = name.title,
    firstName = name.first,
    lastName = name.last,
    streetNumber = street.number,
    streetName = street.name,
    city = location.city,
    state = location.state,
    country = location.country,
    postcode = location.postcode,
    latitude = coordinates.latitude,
    longitude = coordinates.longitude,
    timezoneOffset = timezone.offset,
    timezoneDescription = timezone.description,
    email = user.email,
    username = login.username,
    password = login.password,
    salt = login.salt,
    md5 = login.md5,
    sha1 = login.sha1,
    sha256 = login.sha256,
    dobDate = dob.date,
    dobAge = dob.age,
    registeredDate = registered.date,
    registeredAge = registered.age,
    phone = user.phone,
    cell = user.cell,
    idName = id.name,
    idValue = id.value,
    pictureLarge = picture.large,
    pictureMedium = picture.medium,
    pictureThumbnail = picture.thumbnail,
    nat = user.nat
  )
}
