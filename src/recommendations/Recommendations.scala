package recommendations

object Recommendations {

  //calculating standard deviation -> mathisfun (piazza post @420)

  //n to count whats in the list
  //example : 3, 2, 1 <- size of 3
  //sum stores next number
  def addition[T](elements:List[Double],property: T => Double, n: Int, sum:Double ): Double ={
    val add : Double = elements(n).toString.toDouble + sum
    println(elements(n).toString.toDouble) //using to make sure it prints out
    //make sure it does go out of bounds
    if(n < elements.size - 1)
    {
      addition(elements, property,n+1,add)
    }
    else
      add
  }

  //subtracts + square it
  //a variable that counters the elements
  // square that stores the double

  //example: 3, 2, 1
  def subtraction[T](elements:List[Double], property: T => Double, n: Int, mean: Double, square :Double ): Double = {
    val sub = elements(n).toString.toDouble - mean
    //square it and have it stored
    //+ adds the next number
    val store = math.pow(sub, 2) + square

    //within the number of elements stated in the list
    if(n < elements.size - 1) {
      //recursion -> subtract each element
      subtraction(elements, property, n+1, mean, store)
    }
    else {
      //returns end value
      store
    }
  }

  //divide it by the length-1
  //list[T] is the list of all the customers
  //property is a function that takes a customer and returns a DOUBLE
  //the DOUBLE represents the amount of times a customer purchased an item
  def standardDeviation[T](elements: List[T], property: T => Double): Double = {
    val pro :List[Double] = elements.map(property)

    val mean = addition(pro, property,0,0.0)/elements.size
    println(mean)
    val squared = subtraction(pro, property,0,mean,0.0)
    println(squared)
    //divide by the amount of elements
    val variance = squared / (elements.size - 1)
    println(4)
    //square root the variance
    math.sqrt(variance)
  }

  //subtract by the mean and then multiple by it
  //add the results and return it
  //mapx & mapy is the mapping of both elements
  //also takes in property 1 & 2 that takes a type T
  //n to iterate through
  //have individual meanx and y respectively
  //store to store the value
  def minusxy[T](mapx: List[Double], mapy: List[Double],property1 :T, property2 :T, n : Int, meanx: Double, meany: Double, store : Double): Double = {
    val x = mapx(n).toString.toDouble - meanx
    val y = mapy(n).toString.toDouble - meany
    //multiply x & y values to get value
    val times = x * y
    //add the result of both values
    val add = store + times

    //doesn't go out of bounds
    if(n < mapx.size - 1)
    {
      minusxy(mapx, mapy, property1, property2, n+1, meanx, meany, add)
    }
    else{
      add
    }
  }

  def correlation[T](elements: List[T], property1: T => Double, property2: T => Double): Double = {
    //
    //    //summation:
    //sigma i=1 till n -> x(i) summation of x1 till xn
    //summation / n-1 (sd of x) ( sd of y)

    //convert
    val mapx = elements.map(property1)
    val mapy = elements.map(property2)

    //the mean of both values
    val meanx = addition(mapx, property1, 0, 0.0)/elements.size
    val meany = addition(mapy, property2, 0, 0.0)/elements.size

    //standard dev takes elements and a property
    val stdDevOfX = standardDeviation(elements, property1)
    val stdDevofY = standardDeviation(elements, property2)

    //the result / n-1(sdx)(sdy)
    val subx = minusxy(mapx, mapy, property1, property2, 0, meanx, meany, 0)/((elements.size-1)*(stdDevOfX)*(stdDevofY))
    subx
  }



  //test property name = is the item that is being compared
  //elements is the list w al the data
  //Map -> keys are the names (product item) -> the values are the functions (property 1 & 2) to double
  //k it has number of top correlated properties/products <- RETURN
  //use correlation to see if two items are correlate


  def topKCorrelations[T](elements: List[T], allProperties: Map[String, T => Double], testPropertyName: String, k: Int): List[String] = {
    //compare it
    val prop = allProperties(testPropertyName)

    //nums which is a map that contains the string and double
    val nums: Map[String, Double] = (for(n <- allProperties) yield {
      val getString = allProperties(n._1)
      //make sure that its not testing against itself
      if(n._1 == testPropertyName){
        ("",0.0)
      }
      else{
        (n._1,correlation(elements, getString, prop))
      }
    })

    //sort by the highest correlation
    val sortList = nums.toList.sortWith(_._2 > _._2)

    val newList : List[String] = (for(x <- sortList) yield{
      x._1
    })

    //takes the k number and returns the k number of properties
    newList.take(k)
  }

}

