import com.vhtor.ExitException
import com.vhtor.main
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

class MainTest {
  private val originalOut = System.out
  private val originalErr = System.err
  private lateinit var outContent: ByteArrayOutputStream
  private lateinit var errContent: ByteArrayOutputStream

  @BeforeEach
  fun setUpStreams() {
    outContent = ByteArrayOutputStream()
    errContent = ByteArrayOutputStream()
    System.setOut(PrintStream(outContent))
    System.setErr(PrintStream(errContent))
  }

  @AfterEach
  fun restoreStreams() {
    System.setOut(originalOut)
    System.setErr(originalErr)
  }

  @Test
  fun `test no arguments`() {
    runMainWithArgs(emptyArray())

    assertEquals("Usage: <program> <file>\n", outContent.toString())
  }

  @Test
  fun `test file not found`() {
    val fileArgument = "non_existent_file.json"

    runMainWithArgs(arrayOf(fileArgument))

    assertEquals("File not found: $fileArgument\n", outContent.toString())
  }

  @Test
  fun `test valid JSON file`() {
    val fileArgument = "test_jsons/no_values/valid_0.json"

    val exitStatus = runMainWithArgs(arrayOf(fileArgument))
    assertEquals(0, exitStatus)
    assertEquals("Valid JSON\n", outContent.toString())
  }

  @Test
  fun `test invalid JSON file`() {
    val fileArgument = "test_jsons/no_values/invalid_0.json"

    val exitStatus = runMainWithArgs(arrayOf(fileArgument))
    assertEquals(1, exitStatus)
    assertEquals("Invalid JSON\n", outContent.toString())
  }

  private fun runMainWithArgs(args: Array<String>): Int {
    return try {
      main(args)
      0
    } catch (e: ExitException) {
      e.status
    }
  }
}
