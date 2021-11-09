package test_libraries_presentation.munit

import test_libraries_presentation.House


class MUnitSuite extends munit.FunSuite {
  test("hello") {
    assertEquals("this", "this")
  }

  test("hello2") {
    assertEquals("this", "that")
  }

  test("pretty diffs 1") {
    assertEquals(
      House(1, "red"),
      House(1, "blue")
    )
  }

  test("pretty diffs 2") {
    assertEquals(
      Map(1 -> "a", 2 -> "b"),
      Map(1 -> "a", 2 -> "c"),
    )
  }
}