package example

import scala.math.random

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkFiles

object SparkRustSimpleLine {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark Rust Simple Line")
      .getOrCreate()

    // Get sqlContext from spark
    val sqlContext = spark.sqlContext
    import sqlContext.implicits._

    // Construct simple dataframe with three column: id, a, b
    val df = spark.createDataFrame(Seq(
      ("id_1", 100, 200),
      ("id_2", 612, 223),
      ("id_3", 112, 912),
      ("id_4", 241, 235),
      ("id_5", 125, 914)
    )).toDF("id", "a", "b")

    df.show

    // Pipe the dataframe to a rust program
    // Then convert to Dataset
    val ds = df.toJSON.rdd.pipe(SparkFiles.get("process-simple-line")).toDS
    ds.collect.foreach { println }

    // Convert ds back to DataFrame
    spark.read.json(ds).show

    spark.stop()
  }
}

