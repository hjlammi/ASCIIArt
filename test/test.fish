cd (dirname (status -f))
javac -cp .:..:junit-4.12.jar:hamcrest-core-1.3.jar ../*.java ./*.java
and java -cp .:..:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ASCIIArtTest
