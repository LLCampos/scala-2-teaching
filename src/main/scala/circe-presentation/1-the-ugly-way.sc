import io.circe._
import io.circe.parser._

case class User(name: String, age: Int)

val apiResponse: Json =
  parse("""{"name": "John", "age": 2}""").getOrElse(Json.Null)

val cursor = apiResponse.hcursor

val user: Either[DecodingFailure, User] = for {
  name <- cursor.get[String]("name")
  age <- cursor.get[Int]("age")
} yield User(name, age)


// ------------------------------ //

// What if we have this case class?

case class ComplicatedUser(
    firstName: String,
    lastName: String,
    age: Int,
    numberOfKids: Int,
    likesDogs: Boolean,
    married: Boolean,
    spouseName: String,
    eatsBurritos: Boolean
    // etc, etc
)

// Doing the above would be become overwhelming

// Can we do better?
