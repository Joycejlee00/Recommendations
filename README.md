# Recommendations
This recommendation project was done utilizing scala.

The project was done last year during my cse 116 class at the University at Buffalo.
This recommendation list consists of variety of items by using an onlineRetail.csv to calucate the top correlated items within the list and recommend additional items to the customer for additional purchase based on the item of liking.


# Recommendations.scala
The recommendations.scala takes in three main functions:
1. StandardDeviation: This function extracts the property from the data set and calculates the sample standard deviation of the property.
2. Correlation: This function takes two properties from the data set and computes the sample correlation value between both properties. 
3. TopKCorrelations: This function returns a list of the top (K) correlated items (products) to the customer; the list is in descending order.

