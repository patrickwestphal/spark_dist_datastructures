import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}

/**
  * CSV file and taken from
  * https://github.com/phatak-dev/anatomy_of_spark_dataframe_api
  *
  * CSV:
  * transactionId,customerId,itemId,amountPaid
  * 111,1,1,100.0
  * 112,2,2,505.0
  * 113,3,3,510.0
  * 114,4,4,600.0
  * 115,1,2,500.0
  * 116,1,2,500.0
  * 117,1,2,500.0
  * 118,1,2,500.0
  * 119,2,3,500.0
  * 120,1,2,500.0
  * 121,1,4,500.0
  * 122,1,2,500.0
  * 123,1,4,500.0
  * 124,1,2,500.0
  */
object SQLExamples {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]")
      .appName("SQL Example").getOrCreate()
    val sqlContext = spark.sqlContext

    val df = sqlContext.read.format("com.databricks.spark.csv")
      .option("header", "true").option("inferSchema", "true")
      .load("src/main/resources/sales.csv")
    // Register file as table (for querying)
    df.createOrReplaceTempView("sales")

    sqlContext.sql("SELECT * FROM sales").collect().foreach(println)
//    val rdd: RDD[Row] = df.rdd

//    val resultDf = sqlContext.sql("SELECT a.customerId FROM (" +
//      "SELECT customerId, amountPaid as amount FROM sales WHERE 1 = '1') a " +
//      "WHERE amount=500.0")

    // --> WHERE 1 = '1' query part removed by optimizer
//    resultDf.explain()
//    resultDf.collect().foreach(println)

  }
}
