call mvn clean package
call mvn dependency:copy-dependencies -DoutputDirectory=lib   -DincludeScope=compile
pause