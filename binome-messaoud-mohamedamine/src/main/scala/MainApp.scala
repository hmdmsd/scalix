import TMDBClient._

object MainApp extends App {

  val testActor1 = ("Christian", "Bale")
  val testActor2 = ("Michael", "Caine")
  val testMovieId = 49026 // The Dark Knight

  println("=== Testing TMDBClient Functions ===")

  // 1. Test findActorId
  println("\n1. Testing findActorId:")
  val actor1Id = findActorId(testActor1._1, testActor1._2)
  val actor2Id = findActorId(testActor2._1, testActor2._2)
  println(s"ID for ${testActor1._1} ${testActor1._2}: ${actor1Id.getOrElse("Not Found")}")
  println(s"ID for ${testActor2._1} ${testActor2._2}: ${actor2Id.getOrElse("Not Found")}")

  // 2. Test findActorMovies
  println("\n2. Testing findActorMovies:")
  actor1Id.foreach { id =>
    val movies = findActorMovies(id)
    println(s"Movies for ${testActor1._1} ${testActor1._2}:")
    movies.foreach { case (movieId, title) =>
      println(s" - ID: $movieId, Title: $title")
    }
  }

  actor2Id.foreach { id =>
    val movies = findActorMovies(id)
    println(s"Movies for ${testActor2._1} ${testActor2._2}:")
    movies.foreach { case (movieId, title) =>
      println(s" - ID: $movieId, Title: $title")
    }
  }

  // 3. Test findMovieDirector
  println("\n3. Testing findMovieDirector:")
  val director = findMovieDirector(testMovieId)
  println(s"Director for movie ID $testMovieId: ${director.map(_._2).getOrElse("Not Found")}")

  // 4. Test collaboration
  println("\n4. Testing collaboration:")
  val collaborations = collaboration(testActor1, testActor2)
  println(s"Collaborations between ${testActor1._1} ${testActor1._2} and ${testActor2._1} ${testActor2._2}:")
  collaborations.foreach { case (director, movie) =>
    println(s" - Director: $director, Movie: $movie")
  }

  println("\n=== All Tests Completed ===")
}
