# Stock Buy and Sell

Application checks the price list passed at time iterval and finds
 * Stock Buy price
 * Stock Sell price
 * Max Profit
 
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
Java 8
maven 3
Junit 4
```
### Design and Assumption
* **App** class holds the entry point of application which takes input from file and throws exception in case of any glitch in the system.
* **GlassBeamStock** POJO that has buy and sell unit price and also checks if profit can be made.
* **StockMarket** sets the price for GBStock and also checks that the Buy first happens before selling the stock.
### Installing

Unzip the file to any directory. And open the project in your favourite IDE. Do the following 

```
mvn clean package
$ java -jar buy-and-sell-1.0.jar /path/to/input/file/input.txt 100000
```

if running on IDE
```
Run the App.java with 2 program arguments 
* input-file.txt // should be a comma separated price list like 1,2,3,4,5 ...
* 10000 // chunk size that you want to break it into  
```

Note :- Chunk size should be decided on basis of the data. For example:
* if data is 1 million, ideal chunk size should be 100000 i.e 1 lakh
* chunk size above is 100000 because the data is 1 million.
* A sample text file containing comma separated price list is attached.
* **ExecutorService** is being used to parallelize the execution by taking chunk by chunk and finding the max, min. And after all threads are executed, values are checked and max profit is calculated.
* Order-of-execution for this algorithms in my assumption would be O(n log n), as the size of the array is reduced to chunks and iterated over. 

## Running the tests

Tests are included to check the **StockMarketTest.java** validating price list

```
mvn clean test
```
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
