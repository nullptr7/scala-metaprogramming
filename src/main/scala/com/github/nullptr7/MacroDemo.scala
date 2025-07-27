package com.github.nullptr7

import quoted.*

object MacroDemo {

  // 1. Macro definition
  inline def debug(inline value: String): String = ${ debugImpl('value) }
  // inline keyword
  // splice body
  // all quote arguments
  // we can optionally inline the parameters, but that depends on your requirement.
  // If we want to match the structure 'value' then we should use inline for e.g., we are doing expr.asTerm match so we need inline

  // 2. Macro implementation
  // essentially mirrors the structure of the face/definition
  //   everything is wrapped in Expr
  // using quotes
  private def debugImpl(expr: Expr[String])(using quotes: Quotes): Expr[String] = {

    // import this thing...
    import quotes.reflect.*

    println(s"value = ${expr.asTerm.underlyingArgument}")
    expr.asTerm.underlyingArgument match {
      case Ident(name) => '{ ${ Expr(name) } + " = " + $expr }
      case _           => expr
    }

  }

}
