import com.vhtor.Lexer
import com.vhtor.Parser
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JsonParserTest {
  @Test
  fun `Test invalid empty JSON file`() {
    val content = File("test_jsons/no_values/invalid_0.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertEquals(false, parser.parse())
  }

  @Test
  fun `Test invalid JSON file with improper separating comma`() {
    val content = File("test_jsons/with_values/invalid_0.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertFalse(parser.parse())
  }

  @Test
  fun `Test invalid JSON file with a key not being wrapped by quotes`() {
    val content = File("test_jsons/with_values/invalid_1.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertEquals(false, parser.parse())
  }

  @Test
  fun `Test invalid JSON file with a value with improper case`() {
    val content = File("test_jsons/with_values/invalid_2.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertFalse(parser.parse())
  }

  @Test
  fun `Test invalid JSON file with a value with single quote value`() {
    val content = File("test_jsons/with_values/invalid_3.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertFalse(parser.parse())
  }

  @Test
  fun `Test empty valid JSON file`() {
    val content = File("test_jsons/no_values/valid_0.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertEquals(true, parser.parse())
  }
}
