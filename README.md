# commute-range app.



# Goal
The application for getting reachable cities from some city.


# Summary
Input data are a cityName - from start searching and the range - defines a distance from this city.
The resulst should be a list that contains all reachable cities.

# Problem, 
The problem is to find out all reachable cities from another one. 

# Solution
The algorithm to find all reachable cities is Depth-first search (DFS) with defining shortest way that helps to avoid a big count recursive functions executing. 

The algoritm has a Map<String,Integer> as a local variable that helps to define the shortest way which has already been found. It helps to avoid to execute a lot of recursive functions with big range. 

There is also a way to avoid unnecessary functions executions if we have range more then all sum distance. It just returns all cities list. 

# Main entities
The main enitites are City(name) and CitiesDirection(cityFrom, cityTo, distance);

# Performance results

For GET request: 
1) For Kiev with range 3000 ~ 0,04 ms;
2) For Kiev with range 400 ~ 0,02 ms;
3) For Kiev with range 700 ~ 0,03 ms;
4) For Kiev with range 8000 - 0,03 ms;
5) For Brest with range 700 ~ 0,02 ms;
6) For Doha with range 2000 ~ 0,02 ms;
7) For Minsk with range 0 ~ 0,03 ms;
8) For Vitebsk with range 3000 - 0,04 ms;
9) For Vitebsk with range -1: The first time ~ 0,1; The second time ~ 0,01.

(All performance results may vary on different machines)

# Future improvements
The way to improve perfomance is to add a second level and a query cache to Hibernate. 


# Starting 
Application run on localhost:8080

Tools necessasry for running:

-JDK 1.7+

-Maven 3.2.2+

##Build and run project

#Go to project directory

$ mvn package

$ mvn spring-boot:run

# Example
The using example below

http://localhost:8080/city-range/reachable-cities?cityName=Kiev&range=400

Next cities have been saved to DB already.

 From      To

1)"Kiev" ->"London"-250||
2)"Kiev" -> "Moscow"-200||
3)"Kiev"-> "Minsk"-100||
4)"Minsk"-> "Brest"-250||
5)"Minsk"-> "Vitebsk"-300||
6)"Minsk"-> "Madrid"-150||
7)"Moscow"-> "Vitebsk"-700||
8)"London"-> "Moscow"-500||
9)"London"-> "Madrid"-400||
10)"London"-> "Doha"-800||
11)"Brest"-> "Vitebsk"-300||
12)"Madrid"-> "Doha"-1200||
13)"Madrid"-> "Barcelona"-100||
14)"Barcelona"-> "Doha"-1300


All unit tests in src\test directory.
