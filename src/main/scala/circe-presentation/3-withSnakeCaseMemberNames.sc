import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import io.circe.parser.parse

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

implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
implicit val complicatedUserDecoder: Decoder[ComplicatedUser] = deriveConfiguredDecoder

val apiResponse: Json = parse("""{
  "first_name" : "John",
  "last_name" : "Doe",
  "age" : 34,
  "number_of_kids" : 2,
  "likes_dogs" : true,
  "married" : false,
  "spouse_name" : null,
  "eats_burritos" : false
}""".stripMargin).getOrElse(Json.Null)

val complicatedUser: Result[ComplicatedUser] = apiResponse.as[ComplicatedUser]

// Ok, but my weird API has weird fields that Circe, and Circe doesn't have a function to deal with that!

"""{
  "FIRST_NAME" : "John",
  "LAST_NAME" : "Doe",
  "AGE" : 34,
  "NUMBER_OF_KIDS" : 2,
  "LIKES_DOGS" : true,
  "MARRIED" : false,
  "SPOUSE_NAME" : null,
  "EATS_BURRITOS" : false
}"""

