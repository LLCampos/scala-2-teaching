// One common use is to compose functions for which the results are wrapped in some context.
// In this example, the context is option.

def function1(i: Int): Option[Int] = Some(i)

def function2(i: Int): Option[String] = {
  if (i == 1) Some("true")
  else if (i == 0) Some("false")
  else if (i == -1) Some("maybe")
  else None
}


def function3(s: String): Option[Boolean] =
  if (s == "true") Some(true)
  else if (s == "false") Some(false)
  else None

// Let's try to predict what these will return
function1(1).flatMap(function2).flatMap(function3) // Some(true)
function1(0).flatMap(function2).flatMap(function3) // Some(false)
function1(-1).flatMap(function2).flatMap(function3) // None
function1(100).flatMap(function2).flatMap(function3) // None

// Note that we could have written
function1(1).flatMap(i => function2(i)).flatMap(s => function3(s))