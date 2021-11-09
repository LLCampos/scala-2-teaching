import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser.parse

case class User(name: String, age: Int)

implicit val userDecoder: Decoder[User] = deriveDecoder

val apiResponse: Json =
  parse("""{"name": "John", "age": 2}""").getOrElse(Json.Null)

val user: Result[User] = apiResponse.as[User]

// ------------------------------ //

case class ComplicatedUser(
    firstName: String,
    lastName: String,
    age: Int,
    numberOfKids: Int,
    likesDogs: Boolean,
    married: Boolean,
    spouseName: Option[String],
    eatsBurritos: Boolean
    // etc, etc
)

implicit val complicatedUserDecoder: Decoder[ComplicatedUser] = deriveDecoder

val apiResponse: Json = parse("""{
                            |  "firstName" : "John",
                            |  "lastName" : "Doe",
                            |  "age" : 34,
                            |  "numberOfKids" : 2,
                            |  "likesDogs" : true,
                            |  "married" : false,
                            |  "spouseName" : null,
                            |  "eatsBurritos" : false
                            |}""".stripMargin).getOrElse(Json.Null)

val complicatedUser: Result[ComplicatedUser] = apiResponse.as[ComplicatedUser]

// What if my API fields are in a different case?
// e.g.

"""{
  "first_name" : "John",
  "last_name" : "Doe",
  "age" : 34,
  "number_of_kids" : 2,
  "likes_dogs" : true,
  "married" : false,
  "spouse_name" : null,
  "eats_burritos" : false
}"""

// Back to doing things manually?
