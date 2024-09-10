package com.vhtor

class Parser(private val lexer: Lexer) {
  private var currentToken: Token = lexer.nextToken()

  private fun advance() {
    currentToken = lexer.nextToken()
  }

  fun parse(): Boolean {
    return parseObject() && currentToken.type == TokenType.EOF
  }

  private fun parseObject(): Boolean {
    if (currentToken.type != TokenType.LEFT_BRACE) {
      return false
    }

    advance()

    if (currentToken.type == TokenType.RIGHT_BRACE) {
      advance()
      return true
    }

    return false
  }
}
