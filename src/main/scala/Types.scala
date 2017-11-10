import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Types {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[4]", "app name", new SparkConf())

    val data1 = List(
      "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val stringRDD: RDD[String] = sc.parallelize(data1)
//    val stringRDD: RDD[Int] = sc.parallelize(data1)

    val data2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val intRDD: RDD[Int] = sc.parallelize(data2)
//    val intRDD: RDD[String] = sc.parallelize(data2)

    // Mixed matrix
    val data3 = List(
      List(1, 2, 3),
      List(4, "five", 6),
      List("seven", "eight", "nine")
    )

    // Inferred type is RDD[List[Any]]
    val listRDD = sc.parallelize(data3)
  }
}
