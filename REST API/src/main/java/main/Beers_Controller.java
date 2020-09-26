/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Beers;
import java.util.Date;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/beers")
public class Beers_Controller {

    @Autowired
    Beers_Service service;
//
////    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
////    public Resources<Beers> getAllBeers(@QueryParam("year") int year) {
////
////        List<Beers> allBeers = service.getAllBeers();
////
////       
////        List<Beers> paginatedList = allBeers.subList(offset, offset + limit);
////
////        for (Beers b : allBeers) {
////            int id = b.getResourceId();
////            Link self = linkTo(this.getClass()).slash(id).withSelfRel();
////            b.add(self); 
////            linkTo(methodOn(this.getClass()).getBeer(id));
////        }
////        Link link = linkTo(this.getClass()).withSelfRel();
////        Resources<Beers> result = new Resources<Beers>(paginatedList, link);
////
////        return result;
////
////    }
////      @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
////    public Resource<Beers> getBeer(@PathVariable("id") int id) {
////        Resource<Beers> beer = new Resource<Beers>(service.GetBeerByID(id));
////        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllBeers(2020, 5, 50));
////      
////        beer.add(linkTo.withRel("all-beers"));
////        return beer;
////    }
    
       @GetMapping()
    public List<Beers> GetAllBeers(@QueryParam("year") int year)
    {
        if(year > 0){
            return service.getAllBeersByYear(year); 
        }
        return service.getAllBeers();
    }
}
