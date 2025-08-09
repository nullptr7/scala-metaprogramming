package com.github.nullptr7

private enum DataType:
  case Foo, Bar

object DataType:
  // Type aliases to the singleton types of the enum cases
  final type Foo = DataType.Foo.type
  final type Bar = DataType.Bar.type
