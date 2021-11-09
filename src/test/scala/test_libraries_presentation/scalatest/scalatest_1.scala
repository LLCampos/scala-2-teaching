package test_libraries_presentation.scalatest

import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers.should._
import test_libraries_presentation.House

class ExampleSpec extends AnyFlatSpec with Matchers {

  "A Stack" should "be true" in {
    true should be (true)
  }

  it should "fail" in {
    false should be (false)
  }

  // Nice error messages
  it should "maps should be the same" in {
    Map("a" -> 1, "b" -> 2) should be (Map("a" -> 1, "b" -> 3))
  }

  it should "case classes should be the same" in {
    House(3, "red") should be (House(3, "yellow"))
  }
}