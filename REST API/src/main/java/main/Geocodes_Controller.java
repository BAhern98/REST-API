/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.BreweriesGeocode;
import Service.Breweries;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/map")
public class Geocodes_Controller {

    @Autowired
    Breweries_Geocode_Service geocode_service;
    @Autowired
    Breweries_Service breweries_service;
  @Autowired
    RestTemplate restTemplate;
    @GetMapping(value = "google/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getGoogleMaps(@PathVariable("id") int id) {
        BreweriesGeocode geocode = geocode_service.getGeocode(id);
        Breweries brewerie = breweries_service.getBrewerieById(id);
        String name = brewerie.getName();
        double latitude = geocode.getLatitude();
        double longitude = geocode.getLongitude();
        String output = "<html><body><h1>" + name + "</h1><div style=\"width: 100%\"><iframe width=\"100%\" height=\"600\" src=\"https://maps.google.com/maps?q=" + latitude + "," + longitude + "&hl=ie;z=3&amp;output=embed\"></iframe></div></body></html>";
        return output;
    }
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
    @GetMapping(value = "weather/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getWeather(@PathVariable("id") int id) {
        BreweriesGeocode geocode = geocode_service.getGeocode(id);
       

        double latitude = geocode.getLatitude();
        double longitude = geocode.getLongitude();
          String url = "http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid=4a1f5501b2798f409961c62d384a1c74";

        // Getting instance of Rest Template
        // Passing true becuase the url is a HTTPS url
      
     
                

        //Calling OpenWeather API
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String output = response.getBody();
        return output;
    }
}
