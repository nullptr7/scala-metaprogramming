package com.github.nullptr7

import scala.quoted.*

object MacroPerson:
  inline def apply(name: String, age: Int): Demo.Person =
    ${ applyImpl('name, 'age) }

  private def applyImpl(name: Expr[String], age: Expr[Int])(using Quotes): Expr[Demo.Person] = {
    import quotes.reflect.*

    age.value match
      case Some(value) =>
        if value > 18 then '{ Demo.Person.apply($name, $age) }
        else report.errorAndAbort(s"Person must have age > 18, but got: $value")
      case None        =>
        '{
          if $age > 18 then Demo.Person.apply($name, $age)
          else {
            println("Attempted to construct Person with age <= 18: " + $age)
            throw new IllegalArgumentException("Person must have age > 18")
          }
        }
  }
