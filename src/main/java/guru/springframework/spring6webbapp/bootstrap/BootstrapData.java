package guru.springframework.spring6webbapp.bootstrap;

import guru.springframework.spring6webbapp.domain.Author;
import guru.springframework.spring6webbapp.domain.Book;
import guru.springframework.spring6webbapp.domain.Publisher;
import guru.springframework.spring6webbapp.repositories.AuthorRepository;
import guru.springframework.spring6webbapp.repositories.BookRepository;
import guru.springframework.spring6webbapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEjb = new Book();
        noEjb.setTitle("J2EE development without EJB");
        noEjb.setIsbn("789012");

        Author rodSaved = authorRepository.save(rod);
        Book noEjbSaved = bookRepository.save(noEjb);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEjbSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEjbSaved.getAuthors().add(rodSaved);

        Publisher awPublisher = new Publisher();
        awPublisher.setPublisherName("Addison-Wesley");
        awPublisher.setAddress("Florida ave 123");
        awPublisher.setCity("Florida");
        awPublisher.setState("Florida");
        awPublisher.setZip(1236547L);

        Publisher savedPublisher = publisherRepository.save(awPublisher);

        dddSaved.setPublisher(savedPublisher);
        noEjbSaved.setPublisher(savedPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEjbSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("---");
        System.out.println("Books published by "+ ericSaved.getFirstName() + " " + ericSaved.getLastName() + ":");
        ericSaved.getBooks().stream().forEach( book -> System.out.println("-> "+ book.getTitle()));
        System.out.println("---");
        System.out.println("Books published by "+ rodSaved.getFirstName() + " " + rodSaved.getLastName() + ":");
        rodSaved.getBooks().stream().forEach( book -> System.out.println("-> "+ book.getTitle()));
        System.out.println("---");
        System.out.println("Authors of book "+ dddSaved.getTitle() + ":");
        dddSaved.getAuthors().stream().forEach( author -> System.out.println("-> " + author.getFirstName() + " " + author.getLastName()));
        System.out.println("---");
        System.out.println("Authors of book "+ noEjbSaved.getTitle() + ":");
        noEjbSaved.getAuthors().stream().forEach( author -> System.out.println("-> " + author.getFirstName() + " " + author.getLastName()));
    }
}
