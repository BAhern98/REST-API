/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Beers;
import Service.DBUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brendan
 */
@Service
public class Beers_Service {

    public List<Beers> getAllBeers() {
        EntityManager em = DBUtil.getEMF().createEntityManager();

        List<Beers> list = null;

        try {
            list = em.createNamedQuery("Beers.findAll", Beers.class)
                    .getResultList();
            if (list == null || list.isEmpty()) {
                list = null;
            }

        } finally {
            em.close();
        }
        return list;

    }

    public List<Beers> getAllBeersByYear(int year) {
        EntityManager em = DBUtil.getEMF().createEntityManager();

        List<Beers> list = null;
        List<Beers> beersToRemove = new ArrayList<>();
        try {
            list = em.createNamedQuery("Beers.findAll", Beers.class)
                    .getResultList();
            if (list == null || list.isEmpty()) {
                list = null;
            }

        } finally {
            em.close();
        }
        Calendar cal = Calendar.getInstance();
        for (Beers beer : list) {
            cal.setTime(beer.getLastMod());
            if (cal.get(Calendar.YEAR) != year) {

                beersToRemove.add(beer);
            }
        }
        list.removeAll(beersToRemove);
        return list;

    }

    public Beers GetBeerByID(int id) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        Beers beer = null;
        try {
            beer = em.createNamedQuery("Beers.findById", Beers.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }

        return beer;
    }

}
