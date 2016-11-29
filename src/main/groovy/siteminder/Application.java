package siteminder;

import com.oembedler.moon.graphql.boot.EnableGraphQLServer;
import groovy.transform.CompileStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CompileStatic
@SpringBootApplication
@EnableGraphQLServer
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}