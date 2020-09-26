/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.BreweriesGeocode;
import Service.DBUtil;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class Breweries_Geocode_Service {

    public BreweriesGeocode getGeocode(int id) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        BreweriesGeocode geocode = null;
        try {
            geocode = em.createNamedQuery("BreweriesGeocode.findByBreweryId", BreweriesGeocode.class)
                    .setParameter("breweryId", id)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
        return geocode;
    }
    
        public List<BreweriesGeocode> getAllGeocodes() {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        List<BreweriesGeocode> list = null;
        try {
            list = em.createNamedQuery("BreweriesGeocode.findAll")
                    .getResultList();
            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
        return list;
    }
}
