import scala.io.Source
import org.json4s._
import org.json4s.native.JsonMethods._
import CacheManager._

object TMDBClient {

  val apiKey: String = "cc8e82b02aeda3e3d9bbacdad30d5fba"

  def findActorId(name: String, surname: String): Option[Int] = {
    val query = s"${name.trim}+${surname.trim}".replace(" ", "+")
    val url = s"https://api.themoviedb.org/3/search/person?api_key=$apiKey&query=$query"
    val response = getCachedResponse(s"actor_search_$query.json", url)
    val json = parse(response)
    (json \ "results").extract[List[Map[String, Any]]].headOption.flatMap(_.get("id")).map(_.toString.toInt)
  }

  def findActorMovies(actorId: Int): Set[(Int, String)] = {
    val url = s"https://api.themoviedb.org/3/person/$actorId/movie_credits?api_key=$apiKey"
    val response = getCachedResponse(s"actor_movies_$actorId.json", url)
    val json = parse(response)
    (json \ "cast").extract[List[Map[String, Any]]]
      .map(movie => (movie("id").toString.toInt, movie("title").toString))
      .toSet
  }

  def findMovieDirector(movieId: Int): Option[(Int, String)] = {
    val url = s"https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$apiKey"
    val response = getCachedResponse(s"movie_director_$movieId.json", url)
    val json = parse(response)
    (json \ "crew").extract[List[Map[String, Any]]]
      .find(_.get("job").contains("Director"))
      .map(crew => (crew("id").toString.toInt, crew("name").toString))
  }

  def collaboration(actor1: (String, String), actor2: (String, String)): Set[(String, String)] = {
    val actor1Movies = findActorId(actor1._1, actor1._2).map(findActorMovies).getOrElse(Set.empty)
    val actor2Movies = findActorId(actor2._1, actor2._2).map(findActorMovies).getOrElse(Set.empty)
    val commonMovies = actor1Movies.intersect(actor2Movies)
    commonMovies.flatMap { case (movieId, movieTitle) =>
      findMovieDirector(movieId).map(director => (director._2, movieTitle))
    }
  }
}
