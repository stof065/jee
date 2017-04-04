package org.elasticsearch.jpa.repo;

import java.util.List;

import org.elasticsearch.jpa.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, String> {
	
	List<Book> findByTitle(String title);

}
