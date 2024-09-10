import com.vhtor.Lexer
import com.vhtor.Parser
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonParserTest {
  @Test
  fun `Test invalid empty JSON file`() {
    val content = File("test_jsons/no_values/invalid_0.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertEquals(false, parser.parse())
  }

  @Test
  fun `Test empty valid JSON file`() {
    val content = File("test_jsons/no_values/valid_0.json").readText()
    val lexer = Lexer(content)
    val parser = Parser(lexer)

    assertEquals(true, parser.parse())
  }
}
