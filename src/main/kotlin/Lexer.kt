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

        '[' -> {
          position++
          Token(TokenType.LEFT_BRACKET, char.toString())
        }

        ']' -> {
          position++
          Token(TokenType.RIGHT_BRACKET, char.toString())
        }

        ':' -> {
          position++
          Token(TokenType.COLON, char.toString())
        }

        ',' -> {
          position++
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

        in '0'..'9', '-' -> {
          val start = position

          if (char == '-') {
            position++
          }

          while (position < input.length && input[position].isDigit()) {
            position++
          }

          if (position < input.length && input[position] == '.') {
            position++

            while (position < input.length && input[position].isDigit()) {
              position++
            }
          }

          Token(TokenType.NUMBER, input.substring(start, position))
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
