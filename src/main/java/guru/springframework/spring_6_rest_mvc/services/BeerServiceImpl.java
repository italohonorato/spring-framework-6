package guru.springframework.spring_6_rest_mvc.services;

import guru.springframework.spring_6_rest_mvc.controllers.NotFoundException;
import guru.springframework.spring_6_rest_mvc.model.Beer;
import guru.springframework.spring_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer paleAle = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("1111")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer stout = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.STOUT)
                .upc("2222")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer ipa = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.IPA)
                .upc("2222")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        this.beerMap.put(paleAle.getId(), paleAle);
        this.beerMap.put(stout.getId(), stout);
        this.beerMap.put(ipa.getId(), ipa);
    }

    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(this.beerMap.values());
    }

    @Override
    public Optional<Beer> getBeerById(UUID uuid) {
        log.debug("Get Beer by Id - in service. Id: " + uuid.toString());

        return Optional.of(this.beerMap.get(uuid));
    }

    @Override
    public Beer create(Beer beer) {
        final Beer newBeer = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(newBeer.getId(), newBeer);

        return newBeer;
    }

    @Override
    public void update(UUID id, Beer beer) {

        Beer targetBeer =  getBeerById(id).orElseThrow(NotFoundException::new);
        targetBeer.setBeerName(beer.getBeerName());
        targetBeer.setBeerStyle(beer.getBeerStyle());
        targetBeer.setPrice(beer.getPrice());
        targetBeer.setUpdateDate(LocalDateTime.now());

        beerMap.put(id, targetBeer);
    }

    @Override
    public void deleteById(UUID id) {
        beerMap.remove(id);
    }
}