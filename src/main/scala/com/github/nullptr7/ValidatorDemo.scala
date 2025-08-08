package com.github.nullptr7

import DataType.*

object ValidatorDemo extends App {
  case class User(@NonEmpty name: String, @Min(18) age: Int)

  private val invalid = User("", 16)
  private val valid   = User("Alice", 42)

  given Validator[User] = Validator.derived[User]

  println(s"invalid -> " + summon[Validator[User]].validate(invalid))
  println(s"valid   -> " + summon[Validator[User]].validate(valid))

  println(URL[Bar].gen("123567"))
  println(summon[URLWithoutMacro[Foo]].gen("123567"))
  println(URLWithoutQuotes[Foo].gen("123567"))

}
