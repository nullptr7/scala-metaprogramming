package com.github.nullptr7

import MacroDemo.*

object MacroUsage extends App {
//  private def meaningOfLife(): Int = 42 // if you add this method below it will not work
//
//  private val firstMacroUsage:     String = firstMacro(2 + 10, "Scala")
//  private val pmOptionMacroUsage:  String = pmOptions(Some(2))
//  private val pmOptionMacroUsage1: String = pmOptions(Some(42))

  /*
  We do not have any match for Option(2) even though during runtime Option(2) === Some(2),
  but marco does everything at compile-time so it will not have information that
  Option(2) will be evaluated to Some(2)
   */
//  private val pmOptionMacroUsage2: String = pmOptions(Option(2))
//  private val pmOptionMacroUsage3: String = pmOptions(Option(2).map(_ + 1))
  private val helloMessage:              String = "hello"
  private val debugOptsUsageViaDirect:   String = debug("hello")
  private val debugOptsUsageViaVariable: String = debug(helloMessage)

  println(debugOptsUsageViaDirect)
  println(debugOptsUsageViaVariable)

}
