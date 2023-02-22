package example

import org.apache.spark.sql.SparkSession
import org.scalatest._

class SparkPISpec extends FlatSpec with Matchers {
  val sparkSession: SparkSession = SparkSession.builder()
    .master("local[2]")
    .appName("Spark Pi Unit Test")
    .getOrCreate()

  "SparkPI" should "compute pi with reasonable accuracy" in {
    val result = SparkPI.calculate(sparkSession, Array("2"))
    result should be > 3.0
    result should be < 3.5
  }

  it should "compute pi with default slices" in {
    val result = SparkPI.calculate(sparkSession, Array.empty[String])
    result should be > 3.0
    result should be < 3.5
  }

  after {
    sparkSession.stop()
  }
}

