package com.github.nullptr7

import scala.quoted.*

trait URL[T <: DataType]:
  def gen(id: String): String

object URL:
  inline def apply[T <: DataType]: URL[T] = ${ URLImpl[T] }

private object URLImpl:
  private def extractCaseName(using quotes: Quotes)(tpe: quotes.reflect.TypeRepr): String =
    import quotes.reflect.*
    val rawName =
      val term = tpe.termSymbol
      if term != Symbol.noSymbol then term.name
      else tpe.typeSymbol.name
    rawName.stripSuffix("$")

  // Note the bound <: DataType here
  def apply[T <: DataType: Type](using quotes: Quotes): Expr[URL[T]] =
    import quotes.reflect.*
    val name:     String       = extractCaseName(TypeRepr.of[T]).toLowerCase
    val nameExpr: Expr[String] = Expr(name)

    '{
      new URL[T]:
        override def gen(id: String): String =
          s"https://example.com/" + $nameExpr + "/" + id
    }
