package com.github.nullptr7

object Demo extends App {
  case class Person private (name: String, age: Int)

  object Person {
    def apply(name: String, age: Int): Person = new Person(name, age)

  }

  private def demoMacro(): Unit = {
    // This is OK
    val p = MacroPerson("Alice", 25)
    println(p)

    // Compile-time error (uncommenting will not compile):
    // val bad = MacroPerson("Bob", 10)

    val ageFromInput = 3 + 9
    // Runtime check (this produces Person, age=19):
    val dynamic      = MacroPerson("Charlie", ageFromInput)
    println(dynamic)
  }

  demoMacro()

}
