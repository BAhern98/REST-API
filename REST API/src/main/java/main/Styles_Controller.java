/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Styles;
import java.util.List;
import javax.persistence.OrderBy;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Brendan
 */
@RestController
@RequestMapping("/styles")
public class Styles_Controller {

    @Autowired
    Styles_Service service;

    //@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   // @OrderBy("Styles.styleName DESC")
    public List<Styles> GetAllStyles() {
       
        return service.getAllStylesByName();
    }
}
