package id.aej.jeky.core.domain.model

import id.aej.jeky.core.data.source.local.room.entity.UserEntity

data class User(
  val email: String = "",
  val password: String = "",
  val name: String = "",
  val phoneNumber: String = ""
)

fun User.toEntity() = UserEntity (
  this.email,
  this.password,
  this.name,
  this.phoneNumber
)

