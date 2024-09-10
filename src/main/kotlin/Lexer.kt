package com.vhtor

class Lexer(private val input: String) {
  private var position = 0

  fun nextToken(): Token {
    while (position < input.length) {
      return when (val char = input[position]) {
        '{' -> {
          position++
          Token(TokenType.LEFT_BRACE, char.toString())
        }

        '}' -> {
          position++
          Token(TokenType.RIGHT_BRACE, char.toString())
        }

        ':' -> {
          Token(TokenType.COLON, char.toString())
        }

        ',' -> {
          Token(TokenType.COMMA, char.toString())
        }

        '"' -> {
          val start = position
          position++

          while (position < input.length && input[position] != '"') {
            position++
          }

          if (position < input.length) {
            position++
            Token(TokenType.STRING, input.substring(start, position))
          } else {
            Token(TokenType.INVALID, input.substring(start))
          }
        }

        else -> {
          if (char.isWhitespace()) {
            position++
            continue
          }
          Token(TokenType.INVALID, char.toString())
        }
      }
    }
    return Token(TokenType.EOF, "")
  }
}
