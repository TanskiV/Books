package telran.book.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;
import telran.book.service.BookService;

@RestController
public class BookController {
	@Autowired
	BookService bookService;
	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
		
	}
	
	@GetMapping("/book/{isbn}")
	public BookDto findBook(@PathVariable Long isbn) {
		return bookService.findBookByIsbn(isbn);
	}
	
	@DeleteMapping("/book/{isbn}")
	public BookDto deleteBook (@PathVariable long isbn) {
		return bookService.deleteBookByIsbn(isbn);
	}
	
	@DeleteMapping("/book/author/{authorName}")
	public AuthorDto deleteAuthor(@PathVariable String authorName) {
		
		return bookService.deleteAuthor(authorName);
	}
	
	@GetMapping("/books/author/{authorName}")
	public Set<BookDto> findBooksByAuthor(@PathVariable String authorName) {
		return bookService.findBooksByAuthor(authorName);
	}
	
	@GetMapping("/books/publisher/{publisherName}")
	public Set<BookDto> findBooksByPublisher(@PathVariable String publisherName) {
		return bookService.findBooksByPublisher(publisherName);
	}

}
