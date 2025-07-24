package com.github.nullptr7

import quoted.*

object MacroDemo {

  // text -> AST -> binary
  // text -> AST (quoting) -> AST -> splicing
  // Quoting is converting the existing code to some form of Expr and Splicing is converting the Expr compile pipeline.

  // Quoting and Re-inserting back the AST
  inline def firstMacro(number: Int, string: String): String =
    ${ firstMacroImpl('number, 'string) }

  // manipulating the AST
  // this method is invoked at compile time only.
  private def firstMacroImpl(
      numAST:    Expr[Int],
      stringAST: Expr[String],
    )(using
      Quotes
    ): Expr[String] = {

    // Below means if the constant is known at compile time i.e. via a val then it is fine
    // else we will get a compilation issue.
    val numValue    = numAST.valueOrAbort
    val stringValue = stringAST.valueOrAbort

    val finalValue =
      if numValue < 4 then stringValue.repeat(numValue)
      else stringValue.repeat(numValue / 10)

    Expr("The macro expansion is: " + finalValue)

  }

  // quote and quote matching

  inline def pmOptions(inline opt: Option[Int]): String =
    ${ pmOptionsImpl('opt) }

  private def pmOptionsImpl(opt: Expr[Option[Int]])(using Quotes): Expr[String] = {

    val result = opt match {
      case '{ Some(42) }                   => "got meaning of life"
      case '{ Some($x) }                   => s"got a variable ${x.show}"
      case '{ ($o: Option[a]).map[b]($f) } => "mapping an option"
      case _                               => "got something else"
    }
    Expr(result)
  }

}
