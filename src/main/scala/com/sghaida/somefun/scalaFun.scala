package com.sghaida.somefun

import scala.util.Random


case class AnotherFunStuff()
case object AnotherFunStuff {
  def apply(age: Int): User = age match {
    case a if a > 18 => User("Adult", Some(a))
    case _ => User("child", None)
  }
}

case class User(name: String, age: Option[Int])

object scalaFun extends App {

  println(AnotherFunStuff(20))
  println(AnotherFunStuff(10))

  val users = for (
    i <- 1 to 40
    if i %2 == 0
  ) yield {
    User(Random.nextString(5), Some(Random between(10, 100)))
  }

  println(users.size)

  val updatedUsers = users.map{
    case u if u.age.isDefined && u.age.get %5 == 0 => u
    case u => u.copy(age = None)
  }.filter(item => item.getClass.getCanonicalName != "scala.runtime.BoxedUnit")

  println(updatedUsers.size)


  val ageSum = updatedUsers.foldLeft[Int](0) {
    (acc, value) => value.age match {
      case Some(age) => acc + age
      case None => acc
    }
  }

  println(s"folding sum res: $ageSum")

  /* some nice functional features*/

  val sum1 = (first: Int, second: Int) => first + second
  println(s"nice functional sum res: ${sum1(10, 20)}")

  val sum2 = (first: Int) => (second: Int) => first + second
  val fSum2 = sum2(10)
  println(s"amazing functional sum res: ${fSum2(100)}")

  def sum3(first: Int)(second: Int) = first + second
  val fSum3 = sum3(19)_
  println(s"yet another amazing some: ${fSum3(22)}")
  val sum4 = sum1.curried
  println(sum4(19)(1))
}
