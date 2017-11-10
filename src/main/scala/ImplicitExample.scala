object ImplicitExample {
  class FlyMethods(flyingCar: Car) {
    def fly = println("Now the " + flyingCar.manufacturer + " with id " + flyingCar.id + " is flying")

    def fly(speed: Int) = println(flyingCar.manufacturer + " with id " + flyingCar.id  +
      " now is flying with " + speed + " km/h!!!!")
  }

  object FlyMethods {
    implicit def addMyCustomFunctions(car: Car) = new FlyMethods(car)
  }


  class Car (val manufacturer: String, val id: String) {
    def drive = println("Now the " + manufacturer + " with id " + id + " is driving")
  }

  def main(args: Array[String]): Unit = {
    val c = new Car("Fiat", "abcde")

//    import FlyMethods._
    c.drive
//    c.fly
//    c.fly(120)
  }
}
