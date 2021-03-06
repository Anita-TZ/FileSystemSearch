# FileSystemSearch
This is a file search GUI. 

By typing the name of file or the folder, it could list all the filee or foldere path which contains the indicated word in the name.

Feel free to play with this.

## MUST NEED BEFORE USED
- [x] Docker
- [x] Java 17
- [x] JavaFx
- [x] maven

## How to use-By command line
* Switch to the FileSystem folder
* Open MySQL database in docker
 ``` docker
docker-compose -f docker-compose.yaml up --build -d
```

* Install necessary maven dependency( when running the first time)
``` 
mvn install
```
* Compile Java file
``` 
mvn clean compile
```
* Execute Java file
 ``` docker
mvn exec:java
```
**Step 1 : The initial screen**

<img src="demo-1.JPG" width="400">

**Step 2 : Type the name of the file and folder in the search bar and press the search botton**

<img src="demo-2.JPG" width="400">

**Step 3 : The result show below** 

<img src="demo-3.JPG" width="400">

**Hips:**
If add new file in the root folder, just re-run java file without restart docker.
The new file would be added to MySQL.

* Close MySQL in docker
``` docker
docker-compose -f docker-compose.yaml down
```
* Clear memory space
 ``` docker
docker system prune
```

## Tech in the FileSystemSearch
* Java 17
* JavaFX ( for GUI )
* Maven 
* Docker 
* MySQL 5.7
