package tests

import org.scalatest.FunSuite
import recommendations.Recommendations

class TestCorrelation extends FunSuite {

  class TwoDoubles(val a: Double, val b: Double) {

  }

  val EPSILON: Double = 0.01

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  test("Test Correlation") {
    val x: List[Double] = List(2, 8, 11)
    val y: List[Double] = List(7, 12, 17)

    val pairs: List[(Double, Double)] = List((x.head, y.head),(x(1), y(1)), (x(2), y(2)))

    val corre: Double = Recommendations.correlation(pairs, (pairs: (Double, Double)) => (pairs._1), (pairs: (Double, Double)) => (pairs._2))
    val result: Double = 0.981980506

    assert(equalDoubles(corre, result))
  }

}
