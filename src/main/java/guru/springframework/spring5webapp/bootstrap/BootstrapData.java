package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner{

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		Publisher pub = new Publisher("John's Pub House", "Sydney", "Sydney", "NSW", "2000");
		publisherRepository.save(pub);
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "12232");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		ddd.setPublisher(pub);
		pub.getBooks().add(ddd);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(pub);
		
		Author rod = new Author("Rod", "Johnson");
		Book newEJB = new Book("J2EE development", "123223");
		rod.getBooks().add(newEJB);
		newEJB.getAuthors().add(rod);
		newEJB.setPublisher(pub);
		pub.getBooks().add(newEJB);
		
		authorRepository.save(rod);
		bookRepository.save(newEJB);
		publisherRepository.save(pub);
		
		System.out.println("Started in bootstrap");
		System.out.println("Number of books in DB " + bookRepository.count());
		
		
		
		System.out.println("Number of publisher " + publisherRepository.count());
		System.out.println("Number of books by publisher: " + pub.getBooks().size());
		
	}

	
}
