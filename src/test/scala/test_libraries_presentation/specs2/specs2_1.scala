package test_libraries_presentation.specs2

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import org.specs2.mutable.Specification
import test_libraries_presentation.House


class specs2_1 extends Specification {

  "my class" should {
    "return true" in {
      "bananas" must be equalTo "bananas"
    }
  }

  // Possible to have nested tests for better organization
  "nested example" should {
    "nested1" should {
      "return something" in {
        ok
      }
    }

    "nested2" should {
      "return other" in {
        ok
      }
    }
  }

  // No out-of-the box support for effects, have to run .unsafeRunSync
  "effect" should {
    "return true" in {
      IO("bananas" must be equalTo "bananas").unsafeRunSync()
    }
  }

  // So many matchers. Here's one I really like
  "many matchers" in {
    House.getHouse("1") must beSome((house: House) => {
      house.color == "red"
    })
  }

  // Not so nice error messages
  "maps should be the same" in {
    Map("a" -> 1, "b" -> 2) should be (Map("a" -> 1, "b" -> 3))
  }

  "case classes should be the same" in {
    House(3, "red") should be (House(3, "yellow"))
  }
}