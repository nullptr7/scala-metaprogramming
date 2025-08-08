package com.github.nullptr7

// Scala 3
import scala.compiletime.{ erasedValue, error }

trait URLWithoutQuotes[T <: DataType]:
  def gen(id: String): String

object URLWithoutQuotes:
  inline def apply[T <: DataType]: URLWithoutQuotes[T] =
    (id: String) => s"https://example.com/${pathOf[T]}/$id"

  inline private def pathOf[T <: DataType]: String =
    inline erasedValue[T] match
      case _: DataType.Foo.type => "foo"
      case _: DataType.Bar.type => "bar"
      case _ => error("Unsupported DataType case for URLWithoutQuotes")
