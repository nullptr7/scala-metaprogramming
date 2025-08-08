package com.github.nullptr7

// Scala 3
trait URLWithoutMacro[T <: DataType]:
  def gen(id: String): String

object URLWithoutMacro:
  def apply[T <: DataType](using u: URLWithoutMacro[T]): URLWithoutMacro[T] = u

  given URLWithoutMacro[DataType.Foo.type] with
    def gen(id: String): String = s"https://example.com/foo/$id"

  given URLWithoutMacro[DataType.Bar.type] with
    def gen(id: String): String = s"https://example.com/bar/$id"
