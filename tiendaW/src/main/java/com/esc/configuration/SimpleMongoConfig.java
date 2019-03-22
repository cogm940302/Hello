package com.esc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.esc.object")
public class SimpleMongoConfig {

	@Bean
	public MongoClient mongo() {
		return new MongoClient("localhost");
	}

	 @Bean
	    public MongoTemplate mongoTemplate() throws Exception {
	        return new MongoTemplate(mongo(), "tiendadb");
	    }
}



/***
 * Script Mongo
> db  // para saber que bd existen
>use tiendadb // para crear y moverte a esa bd
>db.product.insertOne({id:0,nombre:"Producto de prueba",precio:0.0})// inserta producto
>db.product.find() //encuentra todos los productos
> db.product.find({id:{$eq:0}})  // find specify product
> db.product.find({id:0}) // same thing
> db.product.updateOne({id:0},{$set:{descripcion:"Producto De Prueba Des"}}) // modificar un registro


Instrucciones de uso de mongo
1.- Abrir cmd
2.- cd C:\Program Files\MongoDB\Server\4.0\bin
3.- escribir y ejecutar mongod
4.- ejecutar mongo.exe

Nota: si esto no funciona, checa que exista la carpeta c:/data/db


https://docs.mongodb.com/manual/crud/#update-operations
https://docs.mongodb.com/manual/tutorial/query-documents/#read-operations-query-argument
https://docs.mongodb.com/manual/reference/operator/query/eq/
https://spring.io/guides/gs/accessing-mongodb-data-rest/
https://www.baeldung.com/spring-data-mongodb-tutorial
https://www.baeldung.com/queries-in-spring-data-mongodb

 * **/
