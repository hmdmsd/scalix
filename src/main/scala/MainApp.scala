import TMDBClient.*

object MainApp extends App {

  // Test Data
  val testActor1 = ("Christian", "Bale")
  val testActor2 = ("Michael", "Caine")
  val testMovieId = 49026 // The Dark Knight

  println("=== Testing TMDBClient Functions ===")

  // Test findActorId
  println("\n1. Testing findActorId:")
  val actor1Id = findActorId(testActor1._1, testActor1._2)
  val actor2Id = findActorId(testActor2._1, testActor2._2)
  println(s"ID for ${testActor1._1} ${testActor1._2}: ${actor1Id.getOrElse("Not Found")}")
  println(s"ID for ${testActor2._1} ${testActor2._2}: ${actor2Id.getOrElse("Not Found")}")

  // Test Actor Movies
  println("\n2. Testing Actor Movies:")
  actor1Id.foreach { id =>
    val actor = new Actor(id, s"${testActor1._1} ${testActor1._2}")
    println(s"Movies for ${actor.name}:")
    actor.movies.foreach { movie =>
      println(s" - ${movie.title} (ID: ${movie.id})")
    }
  }

  actor2Id.foreach { id =>
    val actor = new Actor(id, s"${testActor2._1} ${testActor2._2}")
    println(s"Movies for ${actor.name}:")
    actor.movies.foreach { movie =>
      println(s" - ${movie.title} (ID: ${movie.id})")
    }
  }

  // Test findMovieDirector
  println("\n3. Testing Movie Director:")
  val movie = new Movie(testMovieId, "The Dark Knight")
  val director = movie.director
  println(s"Director for '${movie.title}': ${director.map(_.name).getOrElse("Not Found")}")

  // Test Collaboration
  println("\n4. Testing Collaboration:")
  val collaborations = collaboration(testActor1, testActor2)
  println(s"Collaborations between ${testActor1._1} ${testActor1._2} and ${testActor2._1} ${testActor2._2}:")
  collaborations.foreach { case (director, movieTitle) =>
    println(s" - Director: $director, Movie: $movieTitle")
  }

  println("\n=== All Tests Completed ===")
}
