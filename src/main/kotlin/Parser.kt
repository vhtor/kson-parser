package com.vhtor

class Parser(private val lexer: Lexer) {
  private var currentToken: Token = lexer.nextToken()

  private fun advance() {
    currentToken = lexer.nextToken()
  }

  fun parse(): Boolean {
    return parseValue() && currentToken.type == TokenType.EOF
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

    while (true) {
      if (!parseKeyValue()) {
        return false
      }

      if (currentToken.type == TokenType.RIGHT_BRACE) {
        advance()
        return true
      }

      if (currentToken.type != TokenType.COMMA) {
        return false
      }

      advance()
    }
  }

  private fun parseKeyValue(): Boolean {
    if (currentToken.type != TokenType.STRING) {
      return false
    }

    advance()

    if (currentToken.type != TokenType.COLON) {
      return false
    }

    advance()

    return parseValue()
  }

  private fun parseValue(): Boolean {
    return when (currentToken.type) {
      TokenType.STRING, TokenType.NUMBER -> {
        advance()
        true
      }
      TokenType.LEFT_BRACE -> parseObject()
      TokenType.LEFT_BRACKET -> parseArray()
      else -> false
    }
  }

  private fun parseArray(): Boolean {
    if (currentToken.type != TokenType.LEFT_BRACKET) {
      return false
    }

    advance()

    if (currentToken.type == TokenType.RIGHT_BRACKET) {
      advance()
      return true
    }

    while (true) {
      if (!parseValue()) {
        return false
      }

      if (currentToken.type == TokenType.RIGHT_BRACKET) {
        advance()
        return true
      }

      if (currentToken.type != TokenType.COMMA) {
        return false
      }

      advance()
    }
  }
}
