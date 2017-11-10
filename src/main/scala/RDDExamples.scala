import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDExamples {
  def main(args: Array[String]): Unit = {

    /*
     * local --> run in local mode, number of partitions determined by Spark
     * local[23] --> explicitly say you want 23 partitions
     */
    val sc = new SparkContext("local", "app name", new SparkConf())

    // read in text file line by line from HDFS or local filesystem
    // sc.textFile()
    // read in hadoop file
    // sc.hadoopFile()

    val data = List(
      List(1, 2, 3),
      List(4, 5, 6),
      List(7, 8, 9),
      List(10, 11, 12),
      List(13, 14, 15)
    )

    // Immutable!
    val rdd = sc.parallelize(data)

//    println(rdd.getNumPartitions)
//
//    val newRDD = rdd.mapPartitionsWithIndex((i: Int, iter) =>
//      iter.map(list => i + " " + list.toString())
//    )
//
//    newRDD.foreach(println)
//    println(rdd.dependencies)
//    println(newRDD.dependencies)
//
//    // Repartitioning requires key-value structure
//    val kvRDD: RDD[(List[Int], String)] = rdd.map(list => (list, list.toString()))
//    // MyPartitioner partitions to four partitions
//    val repartitionedRDD: RDD[(List[Int], Iterable[String])] = kvRDD.groupByKey(new MyPartitioner)
//
//    repartitionedRDD.mapPartitionsWithIndex((partitionNo: Int, iterator: Iterator[(List[Int], Iterable[String])]) =>
//        List((partitionNo, iterator.map(_._2).toList)).iterator
//    ).foreach(println)
  }
}
