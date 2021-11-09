package test_libraries_presentation.utest

import utest.TestSuite
import utest._


object UtestSuite extends TestSuite {
  val tests = Tests {

    test("test1") {
      assert(1 == 1)
    }

    test("test2") {
      assert(1 == 2)
    }

    // Error messages not that great
    test("test3") {
      assert(1 == 1, 2 == 3)
    }

    test("test4") {
      assert(
        Map("a" -> 1, "b" -> 1) == Map("a" -> 1, "b" -> 3)
      )
    }

    // Smart asserts
    test("smart asserts") {
      val a = Map("a" -> 1, "b" -> 1)
      val b = Map("a" -> 1, "b" -> 3)
      assert(
        a == b
      )
    }
  }
}