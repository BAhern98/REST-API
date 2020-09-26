/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Breweries;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/breweries")
public class Breweries_Controller {

    @Autowired
    Breweries_Service service;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public Resources<Breweries> getAllBreweries(@RequestParam(name = "limit", required = false) Integer limit , @RequestParam(name = "offset", required = false) Integer offset) {

        List<Breweries> allBreweries = service.getAllBreweries();
        
        if(limit == null && offset == null){
            limit = 20;
            offset = 0;
        }
        List<Breweries> paginatedList = allBreweries.subList(offset, offset + limit);
        
        for (Breweries b : allBreweries) {
            int id = b.getResourceId();
            Link self = linkTo(this.getClass()).slash(id).withSelfRel();
            b.add(self);
            linkTo(methodOn(this.getClass()).getBrewerie(id));
        }
        Link link = linkTo(this.getClass()).withSelfRel();
        Resources<Breweries> result = new Resources<Breweries>(paginatedList, link);

        return result;
        
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<Breweries> getBrewerie(@PathVariable("id") int id) {
        Resource<Breweries> brewerie = new Resource<Breweries>(service.getBrewerieById(id));
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllBreweries(5, 50));
    
        brewerie.add(linkTo.withRel("all-breweries"));
        return brewerie;
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        Breweries brewerie = service.getBrewerieById(id);
        service.deleteBrewerie(brewerie);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Breweries b) {
        b.setResourceId(0);
        b.setAddUser(0);
        b.setLastMod(new Date());
        service.addBrewerie(b);
    }
    
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Breweries b) {
        b.setResourceId(id);
        b.setAddUser(0);
        b.setLastMod(new Date());
        service.editBrewerie(b);
    }
}
