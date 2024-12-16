import java.io.{File, PrintWriter}
import scala.io.Source

object CacheManager {

  val cacheDir: String = "data"

  def getCachedResponse(filename: String, url: String): String = {
    val file = new File(s"$cacheDir/$filename")
    if (!file.exists()) {
      val response = Source.fromURL(url).mkString
      writeToFile(file, response)
      response
    } else {
      Source.fromFile(file).mkString
    }
  }

  private def writeToFile(file: File, content: String): Unit = {
    file.getParentFile.mkdirs()
    val writer = new PrintWriter(file)
    writer.write(content)
    writer.close()
  }
}
