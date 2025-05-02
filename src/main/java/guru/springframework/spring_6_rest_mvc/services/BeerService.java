package guru.springframework.spring_6_rest_mvc.services;

import guru.springframework.spring_6_rest_mvc.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<Beer> listBeers();
    Optional<Beer> getBeerById(UUID uuid);
    Beer create(Beer beer);
    void update(UUID id, Beer beer);
    void deleteById(UUID id);
}
