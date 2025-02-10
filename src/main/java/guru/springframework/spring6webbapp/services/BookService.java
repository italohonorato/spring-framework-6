package guru.springframework.spring6webbapp.services;

import guru.springframework.spring6webbapp.domain.Book;

public interface BookService {

    public Iterable<Book> findAll();
}
