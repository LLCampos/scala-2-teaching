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

implicit val config: Configuration =
  Configuration.default.copy(transformMemberNames =
    Configuration.snakeCaseTransformation andThen (s => s.toUpperCase)
  )
implicit val complicatedUserDecoder: Decoder[ComplicatedUser] =
  deriveConfiguredDecoder

val apiResponse: Json = parse("""{
  "FIRST_NAME" : "John",
  "LAST_NAME" : "Doe",
  "AGE" : 34,
  "NUMBER_OF_KIDS" : 2,
  "LIKES_DOGS" : true,
  "MARRIED" : false,
  "SPOUSE_NAME" : null,
  "EATS_BURRITOS" : false
}""".stripMargin).getOrElse(Json.Null)

val complicatedUser: Result[ComplicatedUser] = apiResponse.as[ComplicatedUser]
