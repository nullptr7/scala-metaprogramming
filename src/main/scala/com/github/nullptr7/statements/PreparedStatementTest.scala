package com.github.nullptr7.statements

import Domain.*
import com.github.nullptr7.LogAST

object PreparedStatementTest extends App {
  val statement: PreparedStatement[(Int, String)] =
    StatementGenerator.createPreparedStatement("user")(
      ColDef[Int]("id"),
      ColDef[String]("username"),
    )

  statement.insert(1, "Alice")

  LogAST.logAST {
    (ColDef[Int]("id"), ColDef[String]("lastName"))
  }

}
