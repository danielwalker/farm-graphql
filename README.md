GraphQL with GraphiQL on Spring Boot
====================================

A demo of graphql in spring boot. Runs the graphiql user interface on root so you can play around.

Example Queries
---------------

Find all hotels (20 per page)

```
query {
    hotel {
        uuid
	    name
	}
}
```

Find all hotels, specifying a page number.

```
query {
    hotel(page: 2) {
        uuid
	    name
	}
}
```

Find hotels named `tropi%`, drill down to show all available translations.

```
query {
    hotel(name: "tropi%") {
        uuid
        address
	    name
        translations {
            language
            name
            description
        }
	}
}
```

Show only the french translation.

```
query {
    hotel(name: "tropi%") {
        uuid
        address
	    name
        translations(language:"fr") {
            language
            name
            description
        }
	}
}
```

Gotchas
-------

In graphql-spring-boot all public attributes are exposed through graphql. This causes problems when using groovy objects because it tries to expose metaClass and all the other GroovyObject fluff.
This isn't a big problem. Schema "type" objects written in java represent the data exposed in the graph.

The graphiql starter appears to require a schema to contain at least one mutation. Again, this isn't a big deal. I've added an `echo` mutation to keep graphiql happy.

Running
-------

Create a schema using `data.sql` (in the project root). This is a subset of the farm schema that comes pre-populated with a small amount of test data.

Configure application.yml to point to your database and run **gradlew bootrun**.

The graphiql interface is available at `http://localhost:8080/`.

