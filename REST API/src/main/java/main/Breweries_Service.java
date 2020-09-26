/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.DBUtil;
import Service.Breweries;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.springframework.stereotype.Service;

@Service
public class Breweries_Service {

    public List<Breweries> getAllBreweries() {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        List<Breweries> list = null;
        try {
            list = em.createNamedQuery("Breweries.findAll")
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


    public void addBrewerie(Breweries brewerie) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(brewerie);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
    }

    public Breweries getBrewerieById(int id) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        Breweries brewerie = null;
        try {
            brewerie = em.createNamedQuery("Breweries.findById", Breweries.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
        return brewerie;
    }

    public void editBrewerie(Breweries brewerie) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(brewerie);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
    }

    public void deleteBrewerie(Breweries brewerie) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(brewerie));
            trans.commit();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
    }
}
