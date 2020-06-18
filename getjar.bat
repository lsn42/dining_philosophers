@set ver=1.8
javac -sourcepath src -d bin src\main\Main.java -source %ver% -target %ver% -encoding utf-8
xcopy "resource" "bin\resource\" /e
jar -cvfm dining_philosophers.jar manifest -C bin .