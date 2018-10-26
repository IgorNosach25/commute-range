# commute-range test app.

Simple application for getting reachable cities from some city.

Application run on localhost:8080

Tools necessasry for running:

-JDK 1.7+

-Maven 3.2.2+

##Build and run project

#Go to project directory

$ mvn package

$ mvn spring-boot:run


the using example below

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
