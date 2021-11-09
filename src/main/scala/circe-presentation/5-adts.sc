import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.parse
import io.circe.{Decoder, Json}

// In Scala 3, we use Enums for this!
sealed trait Animal
case class Dog(age: Int) extends Animal
case class Cat(color: String) extends Animal

implicit val complicatedUserDecoder: Decoder[Animal] = deriveDecoder

val apiResponseDog: Json = parse("""{"Dog": {"age": 2}}""".stripMargin).getOrElse(Json.Null)
val apiResponseCat: Json = parse("""{"Cat": {"color": "brown"}}""".stripMargin).getOrElse(Json.Null)

val dog = apiResponseDog.as[Animal] // Returns Dog(2)
val cat = apiResponseCat.as[Animal] // Returns Cat("brown")

