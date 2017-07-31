# keeper-client

Client of simple key-value store `Keeper`

## Maven
```xml
<repositories>
    <repository>
        <id>keeper-client-mvn-repository</id>
        <url>https://raw.github.com/tomekbielaszewski/keeper-client/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.grizz</groupId>
        <artifactId>keeper-client</artifactId>
        <version>0.0.1-SNAPSHOT</version>	
    </dependency>
</dependencies>
```

## Deployment
1. Create empty branch `mvn-repo`
1. run `mvn clean deploy`
