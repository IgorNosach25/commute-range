# commute-range test app.

Simple application for getting reachable cities from some city.

Application run on localhost:8080

Tools necessasry for running:

-JDK 1.7+

-Maven 3.2.2+

-Windows 8+

##Build and run project

#Go to project directory

$ mvn package

$ mvn spring-boot:run


the using example below

http://localhost:8080/city-range/reachable-cities?cityName=Kiev&range=400

Next cities have been saved to DB already.

 From      To

"Kiev", "London"     250
"Kiev", "Moscow"     200
"Kiev", "Minsk"      100
"Minsk", "Brest"     250
"Minsk", "Vitebsk"   300
"Minsk", "Madrid"    150
"Moscow", "Vitebsk"  700
"London", "Moscow"   500
"London", "Madrid"   400
"London", "Doha"     800
"Brest", "Vitebsk"   300
"Madrid", "Doha"    1200
"Madrid", "Barcelona"100
"Barcelona", "Doha" 1300


All unit tests in src\test directory.
