Use glassfish 4.1.1 at least.

To compile and install, execute the following command:
```
$ mvn clean install
```


To run the code, execute the following commands:
```$ asadmin start-domain domain1; asadmin start-database; 
asadmin deploy DirectoryManager/directoryManager.api/target/directoryManager.api-1.0-SNAPSHOT.jar;
asadmin deploy AuctionManager/auctionManager.bean/target/auctionManager.bean-1.0-SNAPSHOT.jar;
```

To execute the client of the DirectoryManager
```
$cd /DirectoryManager/administration.Client;mvn exec:java;
```

To execute the client of the AuctionManager
```
$cd ../../AuctionManager/auctionManager.client;mvn exec:java;
```
