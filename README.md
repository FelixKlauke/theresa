# theresa
Blazingly intelligent and dynamic dependency injection framework built on google guice inspired by netflix governator providing lifecycle management and other fancy stuff we miss in guice.

# Installation / Usage

**Maven repositories**
```xml
<repositories>
    <repository>
        <id>d3adspace-theresa-github-package-registry</id>
        <name>D3adspace Enterprises Theresa GitHub Package Registry</name>
        <url>https://maven.pkg.github.com/d3adspace/theresa/</url>
    </repository>
</repositories>
```

**Maven dependencies**
```xml
<dependency>
  <groupId>de.d3adspace</groupId>
  <artifactId>theresa-core</artifactId>
  <version>3.4.0</version>
</dependency>
```

# Example

**Main Application**
```java
var theresa = TheresaFactory.create();

theresa.startLifeCycle();

var instance = theresa.getInstance(ExampleApplication.class);
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