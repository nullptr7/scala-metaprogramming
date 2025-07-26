package com.github.nullptr7

import scala.quoted.*

object LogAST {
  inline def logAST[T](inline expression: T) = ${ logASTImpl('expression) }

  private def logASTImpl[T: Type](expression: Expr[T])(using Quotes): Expr[T] = {
    import quotes.reflect.*
    val term = expression.asTerm
    println(s"===========Tree of type ${Type.show}=========:")
    println()
    println(term.show(using Printer.TreeAnsiCode))
    println()
    println(term.show(using Printer.TreeStructure))
    println()
    println("===========================")
    expression
  }

}
