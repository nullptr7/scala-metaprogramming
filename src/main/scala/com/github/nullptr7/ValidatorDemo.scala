package com.github.nullptr7

object ValidatorDemo extends App {
  case class User(@NonEmpty name: String, @Min(18) age: Int)

  private val invalid = User("", 16)
  private val valid   = User("Alice", 42)

  given Validator[User] = Validator.derived[User]

  println(s"invalid -> " + summon[Validator[User]].validate(invalid))
  println(s"valid   -> " + summon[Validator[User]].validate(valid))

}
