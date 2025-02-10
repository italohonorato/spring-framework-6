package guru.springframework.spring6webbapp.services;

import guru.springframework.spring6webbapp.domain.Author;

public interface AuthorService {

    public Iterable<Author> findAll();
}
