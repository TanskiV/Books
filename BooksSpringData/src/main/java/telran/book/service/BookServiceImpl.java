package telran.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.book.dao.AuthorRepository;
import telran.book.dao.BookRepository;
import telran.book.dao.PublisherRepository;
import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;
import telran.book.model.Author;
import telran.book.model.Book;
import telran.book.model.Publisher;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository ;
	
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if(bookRepository.existsById(bookDto.getIsbn())) {
		return false;
		}
		//Publisher
		String publisherName = bookDto.getPublisher();
		Publisher publisher = publisherRepository.findById(publisherName)
				.orElse(publisherRepository.save(new Publisher(publisherName)));
		//Set<Auhtor>
		Set<AuthorDto> authorDtos= bookDto.getAuthors();
		Set<Author> authors = authorDtos.stream()
				.map(a -> authorRepository.findById(a.getName()).orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	@Transactional
	public BookDto findBookByIsbn(long isbn) {
	Book book =	bookRepository.findById(isbn).orElseThrow();
		return convertBooktoBookDto(book);
	}



	@Override
	public BookDto deleteBookByIsbn(long isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow();
		BookDto bookDto = convertBooktoBookDto(book);
		bookRepository.deleteById(isbn);
		return bookDto;
	}
	


	@Override
	public AuthorDto deleteAuthor(String name) {
		Author author = authorRepository.findById(name).orElseThrow();
		AuthorDto authorDto = authorToAuthorDto(author);
		authorRepository.deleteById(name);
		return authorDto;
	}


	@Override
	public Set<BookDto> findBooksByAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElseThrow();
		Set<Book> books = bookRepository.findBookByAuthors(author);
		return books.stream().map(this::convertBooktoBookDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<BookDto> findBooksByPublisher(String publisherName) {
		Publisher publisher = publisherRepository.findById(publisherName).orElseThrow();
		Set<Book> books = bookRepository.findBookByPublisher(publisher);
		return books.stream().map(this::convertBooktoBookDto)
				.collect(Collectors.toSet());
	}


	private BookDto convertBooktoBookDto(Book book) {
		
		Set<AuthorDto> authorDtos = book.getAuthors().stream()
				.map(a -> new AuthorDto(a.getName(), a.getBirthDate()))
				.collect(Collectors.toSet());
				
		return new BookDto(book.getIsbn(), book.getTitle(), authorDtos, book.getPublisher().getPublisherName());

	}

	private AuthorDto authorToAuthorDto(Author author) {
		AuthorDto authorDto = new AuthorDto(author.getName(), author.getBirthDate());
		return authorDto;
	}


}
