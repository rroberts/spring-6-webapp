package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author ericSaved = persistAuthor("Eric", "Evans");
        Book dddSaved = persistBook("Domain Driven Design", "123456");

        Author rodSaved = persistAuthor("Rod", "Johnson");
        Book noEJBSaved = persistBook("J2EE Development without EJB", "54757585");

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        Publisher mitSaved = persistPublisher("MIT Press", "111 Brainiac Way", "Cambridge",
                "MA", "12345");

        dddSaved.setPublisher(mitSaved);
        noEJBSaved.setPublisher(mitSaved);
        mitSaved.getBooks().add(dddSaved);
        mitSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);
        publisherRepository.save(mitSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }

    private Author persistAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return authorRepository.save(author);
    }

    private Book persistBook(String title, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);

        return bookRepository.save(book);
    }

    private Publisher persistPublisher(String publisherName, String address, String city, String state, String zip) {
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherName);
        publisher.setAddress(address);
        publisher.setCity(city);
        publisher.setState(state);
        publisher.setZip(zip);

        return publisherRepository.save(publisher);
    }
}
