package test_libraries_presentation.weaver

import cats.effect.IO
import cats.effect.Resource
import weaver.IOSuite

import scala.annotation._


@nowarn
object WeaverSuite3 extends IOSuite {

  case class SharedResources(
    db: DbClient,
    httpClient: HttpClient
  )

  override type Res = SharedResources
  override def sharedResource: Resource[IO,Res] = 
    for {
      res1 <- MyResources.db
      res2 <- MyResources.httpClient
    } yield SharedResources(res1, res2)

  /** Nice syntax to share resources between tests */
  test("test using resources") { sharedResources: SharedResources =>
    ???
  }
  
  // Show example in AllSported
  // Possible to test resources across suites, but never tried that

}

trait DbClient
trait HttpClient

@nowarn
object MyResources {
  val db: Resource[IO, DbClient] = ???
  val httpClient: Resource[IO, HttpClient] = ???
}