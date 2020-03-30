package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book javaBook = new Book("Java","123456");
        eric.getBooks().add(javaBook);
        javaBook.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(javaBook);

        Author max = new Author("Max", "Smith");
        Book oop = new Book("OOP","454545");
        max.getBooks().add(oop);
        max.getBooks().add(javaBook);
        oop.getAuthors().add(max);
        authorRepository.save(max);
        bookRepository.save(oop);

        Publisher prenticeHall = new Publisher("Prentice Hall", "1st street", "2nd street", "LA", "CA", "1122");
        Publisher oreilly = new Publisher("Oreilly", "1st street", "2nd street", "NY", "DC", "1234");
        publisherRepository.save(prenticeHall);
        publisherRepository.save(oreilly);
        List<Publisher> phFromDB = (List<Publisher>) publisherRepository.findAll();

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: [" + bookRepository.count() + "], number of authors: ["+ authorRepository.count()+"].");
        phFromDB.forEach(System.out::println);
    }
}
