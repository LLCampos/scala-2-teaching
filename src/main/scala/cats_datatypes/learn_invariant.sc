/**
 *
 * The `Invariant` type class is for functors that define an `imap` function with the following type:
 *
 * `def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]`
 *
 */

import java.util.Date

import cats._
import cats.implicits._

def longToDate: Long => Date = new Date(_)
def dateToLong: Date => Long = _.getTime

implicit val semigroupDate: Semigroup[Date] =
  Semigroup[Long].imap(longToDate)(dateToLong)


trait CustomParser[A] {
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](dec: A => B, enc: B => A): CustomParser[B] = { val self = this
    new CustomParser[B] {
      override def encode(value: B) = self.encode(enc(value))
      override def decode(value: String) = dec(self.decode(value))
    }

  }
}

def encode[A](value: A)(implicit c: CustomParser[A]): String = c.encode(value)
def decode[A](value: String)(implicit c: CustomParser[A]): A = c.decode(value)