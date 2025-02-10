package guru.springframework.spring6webbapp.repositories;

import guru.springframework.spring6webbapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
