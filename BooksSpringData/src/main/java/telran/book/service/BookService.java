package telran.book.service;

import telran.book.dto.BookDto;

public interface BookService {
	
	boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(long isbn);
	
	BookDto deleteBookByIsbn(long isbn);

}
