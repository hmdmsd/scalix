import java.io.{File, PrintWriter}
import scala.io.Source
import scala.collection.mutable

object CacheManager {

  val cacheDir: String = "data" // Directory for file-based (secondary) cache
  private val primaryCache: mutable.Map[String, String] = mutable.Map() // Primary in-memory cache

  /**
   * Fetches the cached response from either the primary or secondary cache.
   * If no cached response exists, fetches from the URL, updates caches, and returns the result.
   *
   * @param filename The filename used for secondary cache
   * @param url      The URL to fetch data if no cache exists
   * @return         The content of the response as a String
   */
  def getCachedResponse(filename: String, url: String): String = {
    // Check Primary Cache
    primaryCache.get(filename) match {
      case Some(response) =>
        println(s"Primary cache hit: $filename")
        response
      case None =>
        val file = new File(s"$cacheDir/$filename")
        if (file.exists()) {
          // Secondary Cache Hit: Load from file
          println(s"Secondary cache hit: $filename")
          val response = Source.fromFile(file).mkString
          primaryCache.put(filename, response) // Update Primary Cache
          response
        } else {
          // Cache Miss: Fetch from URL
          println(s"Cache miss: Fetching data for $filename")
          val response = fetchAndCache(file, url)
          response
        }
    }
  }

  /**
   * Writes fetched content to both primary and secondary caches.
   *
   * @param file    The file for secondary cache
   * @param url     The URL to fetch data
   * @return        The fetched response as a String
   */
  private def fetchAndCache(file: File, url: String): String = {
    val response = Source.fromURL(url).mkString
    writeToFile(file, response)
    primaryCache.put(file.getName, response) // Update Primary Cache
    response
  }

  /**
   * Writes content to a file (secondary cache).
   *
   * @param file    The file to write the content to
   * @param content The content to be written
   */
  private def writeToFile(file: File, content: String): Unit = {
    file.getParentFile.mkdirs() // Ensure directory exists
    val writer = new PrintWriter(file)
    writer.write(content)
    writer.close()
  }
}
