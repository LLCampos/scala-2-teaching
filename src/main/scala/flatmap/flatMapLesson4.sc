import cats.Monad
import cats.effect.IO
import cats.syntax.all._

// We can flatMap over many things
// What we can flatMap over, we call a Monad
// Examples of Monads: List, Option, Either

// Knowing this, we can write really general functions

def function1[F[_]](i: Int): F[String] = ???
def function2[F[_]](s: String): F[Boolean] = ???

def doSomethingCool[F[_] : Monad](in: F[Int]): F[Boolean] =
  for {
    i <- in
    s <- function1[F](i)
    b <- function2[F](s)
  } yield b


// It will work for every Monad
// It doesn't work if I remove the ": Monad"

doSomethingCool[Option](Some(1))
doSomethingCool[List](List(1,2,3))
doSomethingCool[IO](IO(1))

// flatMap is used to sequence computations
// first do this, then do tha (maybe using the result of the first thing) then do other, thing, etc
