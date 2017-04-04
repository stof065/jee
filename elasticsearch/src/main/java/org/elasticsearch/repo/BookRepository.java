package org.elasticsearch.repo;

import java.util.List;

import org.elasticsearch.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book, String> {


	Page<Book> findByAuthor(String author, Pageable pageRequest);

	List<Book> findByTitle(String title);

}
