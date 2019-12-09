package telran.book.service;

import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;

public interface BookService {
	
	boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(long isbn);
	
	BookDto deleteBookByIsbn(long isbn);
	
	AuthorDto deleteAuthor (String name);
	
	BookDto findBookByAuthor(String authorName);
	
	BookDto findBookByPublisher(String publisherName);

}
