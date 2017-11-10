import org.apache.spark.sql.SparkSession

object DataSetExamples {

  /**
    * Case class for sales.csv:
    *
    * transactionId,customerId,itemId,amountPaid
    * 111,1,1,100.0
    * 112,2,2,505.0
    * 113,3,3,510.0
    * 114,4,4,600.0
    * 115,1,2,500.0
    * ...
    */
  case class Sales(transactionId: Int, customerId: Int, amountPaid: Double)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]")
      .appName("DataSet Examples").getOrCreate()

    val sqlContext = spark.sqlContext

    // See ImplicitExample to see how this works
    import spark.implicits._

    // from collection
    val ds = Seq(1, 2, 3).toDS()
    ds.map(_ + 1).foreach(println(_))

    // from file
    val salesDS = sqlContext.read.format("com.databricks.spark.csv")
      .option("header", "true").option("inferSchema", "true")
      .load("src/main/resources/sales.csv").as[Sales]

//    salesDS.collect().foreach(println)
    salesDS.filter(sales => sales.amountPaid > 23)
      .filter(sales => sales.customerId < 3).collect().foreach(println)
  }
}
