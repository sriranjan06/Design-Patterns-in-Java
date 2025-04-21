# Gumball Machine with Proxy Pattern

To get code running on localhost: 

Compile the java files within this src by javac *.java

1. Run rmiregistry in background:
rmiregistry &

2. Run:
java src/GumballMachineTestDriveProxy localhost 300

3. In a different window, run:
java src/GumballMonitorTestDriveProxy localhost


