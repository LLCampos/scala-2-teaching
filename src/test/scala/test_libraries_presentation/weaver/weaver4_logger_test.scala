package test_libraries_presentation.weaver

import weaver._

object WeaverSuite4 extends SimpleIOSuite {
  loggedTest("just logging some stuff") { log =>
    for {
      _ <- log.info("oops!")
    } yield expect((2 + 2) == 5)
  }
}