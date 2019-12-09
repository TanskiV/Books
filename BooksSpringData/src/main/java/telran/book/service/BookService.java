package telran.book.service;

import java.util.Set;

import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;

public interface BookService {
	
	boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(long isbn);
	
	BookDto deleteBookByIsbn(long isbn);
	
	AuthorDto deleteAuthor (String name);
	
	Set<BookDto> findBooksByAuthor(String authorName);
	
	Set<BookDto> findBooksByPublisher(String publisherName);

}
