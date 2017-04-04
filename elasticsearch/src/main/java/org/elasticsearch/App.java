package org.elasticsearch;

import java.lang.instrument.Instrumentation;
import java.util.Date;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.elasticsearch.jpa.repo.BookRepo;
import org.elasticsearch.model.Book;
import org.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class App.
 */
/**
 * @author mabourouh
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	/** The book service. */
	@Autowired
	BookService bookService;

	/** The es. */
	@Autowired
	ElasticsearchOperations es;

	@Autowired
	BookRepo repo;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */

	
	public void run(String... args) throws Exception {

		long l = 5000000L;
		for (Long j = 100001L; j < l; j++) {

			org.elasticsearch.jpa.model.Book bk = new org.elasticsearch.jpa.model.Book("test" + j, "title" + j,
					"author", new Date().toString());
			repo.save(bk);
			System.out.println("saved " + j);
			bk = null;

		}

		printElasticSearchInfo();

		bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
		bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
		bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));

		// fuzzey search
		Page<Book> books = bookService.findByAuthor("Rambabu", new PageRequest(0, 10));

		// List<Book> books = bookService.findByTitle("Elasticsearch Basics");

		books.forEach(x -> System.out.println(x));

	}



	/**
	 * Prints the elastic search info.
	 */
	// useful for debug, print elastic search details
	private void printElasticSearchInfo() {

		System.out.println("--ElasticSearch--");

		Client client = es.getClient();
		Map<String, String> asMap = client.settings().getAsMap();

		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("--ElasticSearch--");
	}
}
