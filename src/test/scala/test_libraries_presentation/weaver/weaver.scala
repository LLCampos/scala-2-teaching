package test_libraries_presentation.weaver

import cats.effect.IO
import weaver.SimpleIOSuite
import weaver.specs2compat.IOMatchers

object WeaverSuite extends SimpleIOSuite with IOMatchers {

  def effectful: IO[Int] =
    IO(2)

  /** Treats effect types as first-class citizens */

  // No need to run unsafeRunSync
  test("effectful test") {
    for {
      a <- effectful
      b <- effectful
    } yield expect(a == b) // test has to return an IO[Expectations]
  }

  // Also supports Monix, Monix BIO and ZIO

  /** Don't worry, you can also run pure tests */

  pureTest("pure test") {
    expect("bananas" == "bananas")
  }
  
  
  /** It's more "functional" */

  pureTest("more functional") {
    expect(1 == 2)
    expect(1 == 1)
  } 
  // This would fail in specs2, since it works through exceptions.
  // Here, what's relevant is the value returned.
  // Assuming you're developing with a functional mindset, let's you keep using that mindset even in tests!

  // Proper way to compose Expectations:
  pureTest("more functional (proper way)") {
    expect(1 == 2) and
    expect(1 == 1)
  }

  // This will fail and error msg only appear in the end
}