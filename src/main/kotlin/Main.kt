package com.vhtor

import java.io.File

fun main(args: Array<String>) {
  if (args.size != 1) {
    println("Usage: <program> <file>")
    return
  }

  val file = File(args[0])
  if (!file.exists()) {
    println("File not found: ${args[0]}")
    return
  }

  val content = file.readText()
  val lexer = Lexer(content)
  val parser = Parser(lexer)

  if (parser.parse()) {
    println("Valid JSON")
    exitWithStatus(0)
  } else {
    println("Invalid JSON")
    exitWithStatus(1)
  }
}

fun exitWithStatus(status: Int): Nothing {
  throw ExitException(status)
}

class ExitException(val status: Int) : RuntimeException()
