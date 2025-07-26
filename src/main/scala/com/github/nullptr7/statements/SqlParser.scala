package com.github.nullptr7.statements

import scala.util.Using

enum DbType {
  case DbInt
  case DbString

}

case class ColumnInfo(val dbType: DbType, name: String)

object SqlParser {
  def parse(path: String): Map[String, Seq[ColumnInfo]] = {

    val content: String =
      Using
        .resource(scala.io.Source.fromFile(path))(_.mkString)

    val statements: Array[String] = content.split(";")
    
    statements.map(parseStatement).toMap
  }

  private def parseStatement(statement: String): (String, Seq[ColumnInfo]) = {
    val splatStatement: Array[String] =
      statement.split("[\\s,:]").filterNot(_.isBlank).map(_.toLowerCase)

    println(splatStatement.mkString(","))
    val tableName = splatStatement(2)
    val columns   = splatStatement.drop(4).dropRight(1)
    val colInfo   =
      columns
        .toSeq
        .sliding(2, 2)
        .map(col => ColumnInfo(typeByName(col(1)), col.head))
        .toSeq

    (tableName, colInfo)
  }

  private def typeByName(name: String): DbType =
    if name.contains("int") then DbType.DbInt
    else if name.startsWith("varchar") then DbType.DbString
    else throw new IllegalArgumentException(s"Unknown type: $name")

}
