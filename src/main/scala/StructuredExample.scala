import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}

/**
  * Created by patrick on 11/10/17.
  */
object StructuredExample {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(args(0), "Dataframe creation example")
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val data: List[Row] = List(
      Row.fromSeq(List(1, 2, 3)),
      Row.fromSeq(List(4, "five", 6)),
      Row.fromSeq(List("seven", "eight", "nine")))
    val rdd: RDD[Row] = sc.parallelize(data)
//    val rdd = sqlContext.sparkContext.makeRDD(data)

    // Add schema information
    val columnNames = List("c1", "c2", "c3")
    val columnStruct = columnNames.map(colName => StructField(colName, IntegerType, false))
    val schema = StructType(columnStruct)

    val df = sqlContext.createDataFrame(rdd, schema)

    // Foreach defaults to RDD.foreach --> no evaluation of the schema --> works
    // jump into foreach
    df.foreach(row => println(row(0) + " (" + row(0).getClass + ")"))

    // DataSet.filter --> considers schema --> Error if value doesn't match column type
//    df.filter("c1 > 1").foreach(println)
//    df.filter("c23 > 1")
    // --> no type saftety at compile time!!
  }
}
