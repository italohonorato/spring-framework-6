package guru.springframework.spring_6_rest_mvc.controllers;

import guru.springframework.spring_6_rest_mvc.model.Beer;
import guru.springframework.spring_6_rest_mvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BASE_PATH = "/api/v1/beer";
    public static final String BASE_PATH_ID = BASE_PATH + "/{beerId}";
    private final BeerService beerService;

    @GetMapping(BASE_PATH_ID)
    public Beer getBeerById(@PathVariable UUID beerId){
        log.debug("Get Beer by Id - in CONTROLLER");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(BASE_PATH)
    public List<Beer> getBeers(){
        return beerService.listBeers();
    }

    @PostMapping(BASE_PATH)
    public ResponseEntity<?> create(@RequestBody Beer beer){

        final Beer beerCreated = beerService.create(beer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "/api/v1/beer/" + beerCreated.getId().toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(BASE_PATH_ID)
    public ResponseEntity update(@PathVariable UUID beerId, @RequestBody Beer beer){

        beerService.update(beerId, beer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "/api/v1/beer/" + beerId.toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BASE_PATH_ID)
    public ResponseEntity<?> deleteBeer(@PathVariable UUID beerId){

        beerService.deleteById(beerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
