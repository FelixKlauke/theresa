<p align="center">
<img src=".github/logo.png" alt="Theresa" title="Theresa" />
</p>

[![Quality Gate Status](https://sonar.klauke-enterprises.com/api/project_badges/measure?project=theresa&metric=alert_status)](https://sonar.klauke-enterprises.com/dashboard?id=theresa)

# theresa
Blazingly intelligent and dynamic dependency injection framework built on google guice inspired by netflix governator providing lifecycle management and other fancy stuff we miss in guice.

# Build Status
|             	| Build Status                                                                                                                                              	| Test Code Coverage                                                                                                                                               	|
|-------------	|-----------------------------------------------------------------------------------------------------------------------------------------------------------	|------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| Master      	| [![Build Status](https://travis-ci.org/FelixKlauke/theresa.svg?branch=master)](https://travis-ci.org/FelixKlauke/theresa) 	| [![codecov](https://codecov.io/gh/FelixKlauke/theresa/branch/master/graph/badge.svg)](https://codecov.io/gh/FelixKlauke/theresa) 	|
| Development 	| [![Build Status](https://travis-ci.org/FelixKlauke/theresa.svg?branch=dev)](https://travis-ci.org/FelixKlauke/theresa)    	| [![codecov](https://codecov.io/gh/FelixKlauke/theresa/branch/dev/graph/badge.svg)](https://codecov.io/gh/FelixKlauke/theresa)    	|

# Installation / Usage

**Maven repositories**
```xml
<repositories>
    <!-- Klauke Enterprises Releases -->
    <repository>
        <id>klauke-enterprises-maven-releases</id>
        <name>Klauke Enterprises Maven Releases</name>
        <url>https://repository.klauke-enterprises.com/repository/maven-releases/</url>
    </repository>
	
    <!-- Klauke Enterprises Snapshots -->
    <repository>
        <id>klauke-enterprises-maven-snapshots</id>
        <name>Klauke Enterprises Maven Snapshots</name>
        <url>https://repository.klauke-enterprises.com/repository/maven-snapshots/</url>
    </repository>
</repositories>
```

**Maven dependencies**
```xml
<dependency>
  <groupId>de.d3adspace.theresa</groupId>
  <artifactId>theresa-core</artifactId>
  <version>1.1.0</version>
</dependency>
```

# Example

**Main Application**
```java
Theresa theresa = TheresaFactory.create();

theresa.startLifeCycle();

ExampleApplication instance = theresa.getInstance(ExampleApplication.class);
instance.action();

theresa.stopLifeCycle();
```

**Managed Instance Interface**

```java
/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
@ImplementedBy(ExampleApplicationImpl.class)
public interface ExampleApplication {

    void action();
}
```

**Managed instance implementation with life cycle annotations**
```java
@Singleton
public class ExampleApplicationImpl implements ExampleApplication {

    private final SomethingManager somethingManager;

    @Inject
    public ExampleApplicationImpl(SomethingManager somethingManager) {
        this.somethingManager = somethingManager;
    }

    @PostConstruct
    public void onPostConstruct() {

        System.out.println(ExampleApplicationImpl.class.getSimpleName() + "#" + "onPostConstruct()");
    }

    @Override
    public void action() {

        somethingManager.doSomething();
    }

    @PreDestroy
    public void onPreDestroy() {

        System.out.println(ExampleApplicationImpl.class.getSimpleName() + "#" + "onPreDestroy()");
    }
}
```

You can find the full example [here](https://github.com/FelixKlauke/theresa/tree/dev/example)

# Goals
Some time ago I discovered [Netflix Governator](https://github.com/Netflix/governator) and I really loved the idea of
having life cycle management support alongside with dependency injection. Guice was my favourite dependency injection
framework and some time Ago Jordan Zimmerman (mailto:jzimmerman@netflix.com) built a library on top of guice bringing
some nice features like configuration mapping and life cycle management. The goal of this project is to maintain
this idea and functionality in a clean and minimal rewrite.
