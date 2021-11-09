package test_libraries_presentation.weaver

import cats.effect.IO
import test_libraries_presentation.House
import weaver.SimpleIOSuite
import weaver.specs2compat.IOMatchers

object WeaverSuite2 extends SimpleIOSuite with IOMatchers {

  def effectful: IO[Int] =
    IO(2)

  /** Nice diffs on failure */
  pureTest("pretty diffs 1") {
    expect.same(
      House(1, "red"),
      House(1, "blue")
    )
  }

  pureTest("pretty diffs 2") {
    expect.same(
      Map(1 -> "a", 2 -> "b"),
      Map(1 -> "a", 2 -> "c"),
    )
  }

  /** Integrates with specs2, if you want the fancy matchers */ 

  // Note that integration is (yet?) not perfect

  pureTest("specs2 integration") {
    expect(
      House(1, "red") must be equalTo House(1, "blue")
    )
  }

  // Also get pretty diff, but less nice
}