# QA_Assignment
## How to Run the project

## Pre-required
```
1) Install JDK 1.8 
2) Download selenium jar file selenium-server-standalone-3.141.59.jar (Its available in src/test/resource folder in project"
   Go to cmd and move to the directory where jar file is available and follow below steps
   ex:cd /Users/ravi.kne/Downloads then run below cmd to start hub
   java -jar selenium-server-standalone-3.141.59.jar -role hub

   Once the hub starts running, open the another cmd window and follow below steps to run node
   java -Dwebdriver.chrome.driver="/Users/ravi.kone/Documents/mobile/chromedriver" -jar selenium-server-standalone-3.141.59.jar -role  node -hub    http://192.168.1.4:4444/grid/register -port 5555 
  (192.168.1.4:4444/ this needs to be updated to your path which can seen in hub cmd window, so that hub can be rigistered with node)

3) Install the Zalenium by entering the cmds
    # Pull docker-selenium
    docker pull elgalu/selenium
    
    # Pull Zalenium
    docker pull dosel/zalenium
    
    # Run it!
    docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start
      
    # Point your tests to http://localhost:4444/wd/hub and run them

    # Stop
    docker stop zalenium
    
```

Check if both installed properly in browser by entering below URLS
```
http://localhost:4444/grid/console  		[To check if Grib is running properly]
http://localhost:4444/grid/admin/live 		[To check if Zalenium is running properly]
http://localhost:4444/dashboard                 [To check if Zalenium dashboard]
```

Git Repository URL: https://github.com/ravidkone/SAP_Assignment.git

## Steps to execute:
Step 1: Go to your cmd/terminal and and create a folder
        inside that folder clone the git repository and move to the new folder
```
  $ mkdir “run_test” 
  $ cd run_test
  $ git clone https://github.com/ravidkone/SAP_Assignment.git
``` 
 cd “project folder name” [change directory to project folder]

Once we are inside the project folder enter below maven commands to execute the project
```
$ mvn clean     // [it will scan the project and do a clean install]
$ mvn test      // [this will start executing the test cases]


Note: for the first time it will download the all the required jars(it may take little time) 
and then execute the test cases, from second time onward it will be faster.
```

## How to open the project in Eclipse IDE
Step 1: Open Eclipse IDE, click on File Tab and select "Open Projects from File System.." 
#
Step 2: It will open dialogue box, click on "directory" and choose the project folder and click on Finish

## Project Details
    - Under "src/main/java" we can find base,config,POMs,report and utility packages
    - Under "src/main/test" we can find the testcases.
    - Under "src/main/resource" we can find the drivers, test data and jar files files
    - Under "Test Reports" folder, we can find the test reports for each execution
            its a custom report created, which will have logs.
    - Under "screenshots", we can find the screenshot of error occured during the excution.
    - Under "pom.xml" we can find all the dependedancies and plugins
