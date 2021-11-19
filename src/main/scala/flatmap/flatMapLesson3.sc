def function1(i: Int): Option[Int] = Some(i)
def function2(i1: Int, i2: Int): Option[String]
def function3(s1: String, s2: String): Option[Boolean]

function1(2).flatMap { i =>
  val i1 = i * 2
  val i2 = i + 42
  function2(i1, i2).flatMap { s =>
    val s1 = s.substring(3)
    function3(s, s1).map(_.toString)
  }
}

// You can use a for-comprehension as syntactic sugar of the above.
// Arguably, is slightly less confusing because of less nesting.
// There's still nesting happening, but it is hidden

for {
  i <- function1(2)
  i1 = i * 2
  i2 = i + 42
  s <- function2(i1, i2)
  s1 = s.substring(3)
  b <- function3(s, s1)
} yield b.toString
