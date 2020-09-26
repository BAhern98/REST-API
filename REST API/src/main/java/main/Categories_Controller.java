/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.BreweriesGeocode;
import Service.Categories;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Brendan
 */

@RestController
@RequestMapping("/categories")
public class Categories_Controller {
    @Autowired
    Categories_Service service;
      @Autowired
    RestTemplate restTemplate;
    
     @Bean
public RestTemplate restTemplate2() {
    return new RestTemplate();
}
       
    
@GetMapping(value = "holiday/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getWeather(@PathVariable("id") int id) {
        Categories category = service.getCategoryById(id);
       
Date date =category.getLastMod();
Calendar calendar = Calendar.getInstance();
calendar.setTime(date);
int year = calendar.get(Calendar.YEAR);
       // Date date );
   
       
      String url = "https://calendarific.com/api/v2/holidays?&api_key=8d6843254b859ba2348bafe6d338882c5c7f9d7c&country=US&year="+year;

        // Getting instance of Rest Template
        // Passing true becuase the url is a HTTPS url
      
         

        //Calling OpenWeather API
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String output = response.getBody();
        return output;
    }
    @GetMapping
    public List<Categories> GetAllCategories() {

        return service.getAllCategories();
    }
    @PostMapping
    public void CreateCategory(@RequestBody Categories c) {

        service.addCategorie(c);
    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @Consumes({ MediaType.APPLICATION_JSON_VALUE})
//    public void CreateCategory(@RequestBody Categories c) {
//
//        service.addCategorie(c);
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @Consumes({ MediaType.APPLICATION_JSON_VALUE})
//    public void GetCategory(@RequestBody Categories c) {
//
//        service.addCategorie(c);
//    }

}
