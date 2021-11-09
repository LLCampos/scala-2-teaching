import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.parse
import io.circe.{Decoder, HCursor, Json}

import scala.language.implicitConversions

val apiResponseVariation1: Json =
  parse("""{"name": "John", "age": 2}""").getOrElse(Json.Null)

val apiResponseVariation2: Json =
  parse("""{"user_name": "John", "age": 2}""").getOrElse(Json.Null)

case class User(name: String, age: Int)

implicit val userDecoder: Decoder[User] = (c: HCursor) =>
  deriveDecoder[User].apply(c) match {
    case Right(v) => Right(v)
    case Left(_) =>
      implicit val config: Configuration = Configuration.default.copy(
        transformMemberNames = {
          case "name" => "user_name"
          case other  => other
        }
      )
      deriveConfiguredDecoder[User].apply(c)
  }

apiResponseVariation1.as[User]
apiResponseVariation2.as[User]
