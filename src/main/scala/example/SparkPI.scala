package example

import scala.math.random

import org.apache.spark.sql.SparkSession

object SparkPI {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark Pi")
      .getOrCreate()

    val result = calculate(spark, args)
    println(s"Pi is roughly ${result}")

    spark.stop()
  }

  def calculate(spark: SparkSession, args: Array[String]): Double = {
    val slices = if (args.length > 0) args(0).toInt else 2
    val n = math.min(100000L * slices, Int.MaxValue).toInt // avoid overflow
    val count = spark.sparkContext.parallelize(1 until n, slices).map { i =>
      val x = random * 2 - 1
      val y = random * 2 - 1
      if (x*x + y*y <= 1) 1 else 0
    }.reduce(_ + _)

    4.0 * count / (n - 1)
  }
}

