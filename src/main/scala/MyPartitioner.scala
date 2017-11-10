import org.apache.spark.Partitioner

class MyPartitioner extends Partitioner {
  override def numPartitions: Int = 4

  override def getPartition(key: Any): Int = {
    key match {
      case key: List[Int] => key.sum % numPartitions
      case _ => 0
    }
  }
}
