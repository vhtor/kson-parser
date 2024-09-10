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
          if (char.isLetter()) {
            val start = position

            while (position < input.length && input[position].isLetter()) {
              position++
            }

            return when (val value = input.substring(start, position)) {
              "true" -> Token(TokenType.BOOLEAN, value)
              "false" -> Token(TokenType.BOOLEAN, value)
              "null" -> Token(TokenType.NULL, value)
              else -> {
                val lowercaseValue = value.lowercase()

                when {
                  lowercaseValue in listOf("true", "false", "null") -> Token(TokenType.INVALID, value)
                  else -> Token(TokenType.STRING, value)
                }
              }
            }
          }

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
