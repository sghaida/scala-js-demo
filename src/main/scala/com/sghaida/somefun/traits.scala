package com.sghaida.somefun

import scala.util.Random

object TheWayIMove extends Enumeration {
  type Action = Value
  val Walk, Fly, Crawl, Jump = Value
}

trait Animal {
  def name(why: String)
  def speak()
  def run(): Unit = println("sorry I dont Run")
}

trait Transportation {
  self: Animal =>
  def move(): TheWayIMove.Value
}

case class Human(msg: String) extends Animal with Transportation {
  override def name(why: String): Unit = println(s"$why is my name, which i dont like!!!")
  override def speak(): Unit = println(s"since I am ${this.getClass.getName} then i could say! : $msg")
  override def move(): TheWayIMove.Value = TheWayIMove.Walk
}

case class Bird(msg: String) extends Animal with Transportation {
  override def name(why: String): Unit = println(s"$why is my name, which i dont like!!!")
  override val speak: Unit = println(s"since I am ${this.getClass.getName} then i could say! : $msg")
  override def move(): TheWayIMove.Value = TheWayIMove.Fly

  override def run(): Unit = throw new Exception("I can't run you Idiot")
}

object traits extends App {

  implicit def createAnimal(msg: String): Human = Human(msg)

  val human = Human("I Know that I know Nothing")
  val bird = Bird("I am Not a Duck")

  val funnyHuman: Human = "I am Funny"

  val animalFarm  = human :: funnyHuman :: bird ::Nil

  for (a <- animalFarm) {
    a.name(Random.nextString(10))
    a.speak()
    println(s"I ${a.move()} like I ${a.move()}")
    a.run()
  }
}
