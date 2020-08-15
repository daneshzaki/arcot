# arcot
Sample Spring Boot microservices code to handle GraphQL queries and mutations

The code uses the <a href="http://graphql-java.com/"> graphql-java </a> library. The code demonstrates a query and mutation with nested objects using an example of an order with nested items.

<b>Sample query: </b>

<code>
query ($id:ID){
  
  orderById (id: $id)
  {
    id
    date
    amount
    customer
    {
      id
    }
    item
    {
      id
      price
      description
      type
    }
    status
  }
 
}
</code>

<b>Sample mutation: </b>

<code>
mutation {
	
  createOrder (
    ordering:
    {
      id: "o25"
      customerID:"c2"      
      amount:"5.80"
      date:"17/08/2020"
      status:"shipped"
      item:      
        {
        id:"i03"
        type:"condiments"
        price:"4.95"
        description:"Tea Ceylon"
      }
    }
  )
}
</code>
