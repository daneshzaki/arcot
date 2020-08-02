package in.pleb.orders;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() { 
        return graphQL;
    }

    @PostConstruct
    public void init() {
        //read the schema file using Guava methods
        URL url = Resources.getResource("schema.graphqls");
            
        String sdl="";
        try
        {
            sdl = Resources.toString(url, Charsets.UTF_8);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        } 

        System.out.println("GraphQLProvider init schema read \n"+sdl);
        
        //call method to build schema
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        System.out.println("GraphQLProvider schema built");
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /*private RuntimeWiring buildWiring() {
        System.out.println("GraphQLProvider building schema");
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("orderById", graphQLDataFetchers.getOrderByIdDataFetcher()))
                .type(newTypeWiring("Order")
                        .dataFetcher("item", graphQLDataFetchers.getItemDataFetcher()))
                .type(newTypeWiring("Order")
                        .dataFetcher("customer",graphQLDataFetchers.getCustomerDataFetcher())
                .build();
    }*/

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
            .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("orderById", graphQLDataFetchers.getOrderByIdDataFetcher()))
            .type(TypeRuntimeWiring.newTypeWiring("Order").dataFetcher("item", graphQLDataFetchers.getItemDataFetcher()))
            .type(TypeRuntimeWiring.newTypeWiring("Order").dataFetcher("customer", graphQLDataFetchers.getCustomerDataFetcher()))
            .build();
    }
}