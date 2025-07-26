package com.github.nullptr7.statements

object Domain {
  type CallArgs[Xs <: Tuple] <: Tuple = Xs match {
    case EmptyTuple      => Xs
    case ColDef[b] *: xs => b *: CallArgs[xs]
  }

  class UnsafeStatement(sql: String) {
    def insert(rowItems: Any*): Unit =
      println(s"Execute SQL: $sql for args: ${rowItems.mkString(",")}")

  }

  class PreparedStatement[A <: Tuple](statement: UnsafeStatement) {
    def insert(rowItems: A): Unit =
      statement.insert(rowItems.toList: _*)

  }

  case class ColDef[A](name: String)

}
