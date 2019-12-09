package telran.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.book.model.Book;

public interface BookRepository extends JpaRepository<Book	, Long>{
//Book findBookByIsbn(Long isbn);
}
