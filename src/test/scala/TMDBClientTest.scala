import org.scalatest.funsuite.AnyFunSuite
import TMDBClient._

class TMDBClientTest extends AnyFunSuite {

  test("findActorId should return a valid actor ID for Christian Bale") {
    val actorId = findActorId("Christian", "Bale")
    assert(actorId.nonEmpty, "Actor ID for Christian Bale should not be empty")
  }

  test("findActorMovies should return a non-empty list for a valid actor ID") {
    val actorId = findActorId("Christian", "Bale").getOrElse(fail("Actor ID not found"))
    val movies = findActorMovies(actorId)
    assert(movies.nonEmpty, "Movies for Christian Bale should not be empty")
  }

  test("findMovieDirector should return Christopher Nolan for The Dark Knight") {
    val movieId = 49026 // The Dark Knight
    val director = findMovieDirector(movieId)
    assert(director.nonEmpty, "Director for The Dark Knight should not be empty")
    assert(director.get._2 == "Christopher Nolan", "Director should be Christopher Nolan")
  }

  test("collaboration should find common movies between Christian Bale and Michael Caine") {
    val actor1 = ("Christian", "Bale")
    val actor2 = ("Michael", "Caine")
    val collaborations = collaboration(actor1, actor2)
    assert(collaborations.nonEmpty, "Collaborations between Christian Bale and Michael Caine should not be empty")
  }
}
