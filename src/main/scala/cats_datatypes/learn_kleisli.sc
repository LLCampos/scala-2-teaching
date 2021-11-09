import cats.data.Kleisli/**
 *
 * - Kleisli enables composition of functions that return a monadic value
 *
 *
 */

val m = Map("a" -> "1", "b" -> "2")

val nString: Option[String] = m.get("a")

def toInt(s: String): Option[Int] = ???


def getToInt(key: String): Option[Int] =
  m.get(key).flatMap(toInt)

// ----------------

val parse: String => Option[Int] =
  s => if (s.matches("-?[0-9]+")) Some(s.toInt) else None

val reciprocal: Int => Option[Double] =
  i => if (i != 0) Some(1.0 / i) else None

// TODO: Ask in Discord: why not do this instead of using Kleisi??
val parseAndReciprocal = (s: String) => parse(s).flatMap(reciprocal)

// --------------------

case class DbConfig(url: String, user: String, pass: String)
trait Db
object Db {
  val fromDbConfig: Kleisli[Option, DbConfig, Db] = ???
}

case class ServiceConfig(addr: String, port: Int)
trait Service
object Service {
  val fromServiceConfig: Kleisli[Option, ServiceConfig, Service] = ???
}

case class AppConfig(dbConfig: DbConfig, serviceConfig: ServiceConfig)
class App(db: Db, service: Service)


object App {
  val fromAppConfig: Kleisli[Option, AppConfig, App] = for {
    db <- Db.fromDbConfig.local[AppConfig](_.dbConfig)
    service <- Service.fromServiceConfig.local[AppConfig](_.serviceConfig)
  } yield new App(db, service)
}

