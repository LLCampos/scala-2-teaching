/**
 *
 * Writer[L, A] represents  a computation that produces a tuple containing a value of type L and one of type A.
 * Usually, the value L represents a description of the computation.
 * A typical example of an L value could be a logging.
 *
 */

import cats.Id
import cats.data.{Writer, WriterT}

def reporter(w: Writer[String, Int]): Unit =
  println(s"Value: ${w.value}\nDescription: ${w.written}")


val writerInit = Writer("Initial value of 0. ", 0)
val add2 = (i: Int) => Writer("Added 2. ", i + 2)
val multiply3 = (i: Int) => Writer("Multiplied by 3. ", i * 3)

val result1 = writerInit
  .flatMap(add2)
  .flatMap(multiply3)

reporter(result1)

//////////

/**
 * Could we make it more general?
 */

val writerInit = Writer("Initial value of 0. ", 0)
def add(a: Int): Int => WriterT[Id, String, Int] = (b: Int) => Writer(s"Added $a. ", b + a)
def multiply(a: Int): Int => WriterT[Id, String, Int] = (b: Int) => Writer(s"Multiplied by $a. ", b * a)

val result2 = writerInit
  .flatMap(add(6))
  .flatMap(multiply(4))

reporter(result2)


/**
 * Could we use Writer monad to build the formula for a calculation along with the result of the calculation?
 *
 * (Synthetics could make use of this?)
 *
 * e.g. for the above example, we would get
 *
 * Value: 24
 * Formula: (0 + 6) * 4
 *
 * Not sure if possible to do this because, if Writer[L, V], L has to be a Monoid
 *
 * Anyone has ideas?
 *
 * Unsure how useful this is IRL?
 *
 * https://discord.com/channels/632277896739946517/632278570512678923/878926486378258442
 *
 * Mr. SystemFw thinks not really.
 */

