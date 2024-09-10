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

    assertFalse(parser.parse())
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

    assertTrue(parser.parse())
  }

  @Test
  fun `Test valid JSON file with key_value`() {
    val content = File("test_jsons/with_values/valid_0.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertTrue(parser.parse())
  }

  @Test
  fun `Test valid JSON file with more than one key_value`() {
    val content = File("test_jsons/with_values/valid_1.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertTrue(parser.parse())
  }

  @Test
  fun `Test valid JSON file with various value types`() {
    val content = File("test_jsons/with_values/valid_2.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertTrue(parser.parse())
  }

  @Test
  fun `Test valid JSON file with empty object and array`() {
    val content = File("test_jsons/with_values/valid_3.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertTrue(parser.parse())
  }

  @Test
  fun `Test valid JSON file with filled object and array`() {
    val content = File("test_jsons/with_values/valid_4.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertTrue(parser.parse())
  }
}
