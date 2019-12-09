package telran.book.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.book.model.Author;
import telran.book.model.Book;
import telran.book.model.Publisher;

public interface BookRepository extends JpaRepository<Book	, Long>{
Set<Book> findBookByAuthors(Author author);
Set<Book> findBookByPublisher(Publisher publisher);
}
