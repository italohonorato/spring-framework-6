package guru.springframework.spring6webbapp.repositories;

import guru.springframework.spring6webbapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
