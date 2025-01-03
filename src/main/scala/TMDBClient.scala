import org.json4s._
import org.json4s.native.JsonMethods._
import CacheManager._

object TMDBClient {
  // Implicit formats for JSON parsing using json4s
  implicit val formats: Formats = DefaultFormats

  // API Key for TMDB
  val apiKey: String = "cc8e82b02aeda3e3d9bbacdad30d5fba"

  /**
   * Finds the ID of an actor using their name and surname.
   *
   * @param name    Actor's first name
   * @param surname Actor's last name
   * @return        Option containing the actor's ID
   */
  def findActorId(name: String, surname: String): Option[Int] = {
    val query = s"${name.trim}+${surname.trim}".replace(" ", "+")
    val url = s"https://api.themoviedb.org/3/search/person?api_key=$apiKey&query=$query"
    val response = getCachedResponse(s"actor_search_$query.json", url)
    val json = parse(response)
    (json \ "results").extract[List[Map[String, Any]]].headOption
      .flatMap(_.get("id"))
      .map(_.toString.toInt)
  }

  /**
   * Fetches the movies associated with an actor.
   *
   * @param actorId Actor's ID
   * @return        Set of tuples containing movie ID and title
   */
  def findActorMovies(actorId: Int): Set[(Int, String)] = {
    val url = s"https://api.themoviedb.org/3/person/$actorId/movie_credits?api_key=$apiKey"
    val response = getCachedResponse(s"actor_movies_$actorId.json", url)
    val json = parse(response)
    (json \ "cast").extract[List[Map[String, Any]]]
      .map(movie => (movie("id").toString.toInt, movie("title").toString))
      .toSet
  }

  /**
   * Fetches the director of a movie.
   *
   * @param movieId Movie ID
   * @return        Option containing the director's ID and name
   */
  def findMovieDirector(movieId: Int): Option[(Int, String)] = {
    val url = s"https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$apiKey"
    val response = getCachedResponse(s"movie_director_$movieId.json", url)
    val json = parse(response)
    (json \ "crew").extract[List[Map[String, Any]]]
      .find(_.get("job").contains("Director"))
      .map(crew => (crew("id").toString.toInt, crew("name").toString))
  }

  /**
   * Finds common movies between two actors, along with the directors of those movies.
   *
   * @param actor1 Tuple containing first actor's first and last name
   * @param actor2 Tuple containing second actor's first and last name
   * @return       Set of tuples containing director's name and movie title
   */
  def collaboration(actor1: (String, String), actor2: (String, String)): Set[(String, String)] = {
    val actor1Movies = findActorId(actor1._1, actor1._2).map(findActorMovies).getOrElse(Set.empty)
    val actor2Movies = findActorId(actor2._1, actor2._2).map(findActorMovies).getOrElse(Set.empty)
    val commonMovies = actor1Movies.intersect(actor2Movies)
    commonMovies.flatMap { case (movieId, movieTitle) =>
      findMovieDirector(movieId).map(director => (director._2, movieTitle))
    }
  }
}
