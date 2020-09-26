/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Breweries;
import Service.DBUtil;
import Service.Categories;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brendan
 */
@Service
public class Categories_Service {
      public List<Categories> getAllCategories() {
        EntityManager em = DBUtil.getEMF().createEntityManager();

        List<Categories> list = null;

        try {
            list = em.createNamedQuery("Categories.findAll", Categories.class)
                    .getResultList();
            if (list == null || list.isEmpty()) {
                list = null;
            }

        } finally {
            em.close();
        }
        return list;

    }
         public void addCategorie(Categories categorie) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(categorie);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
    }
           public Categories getCategoryById(int id) {
        EntityManager em = DBUtil.getEMF().createEntityManager();
        Categories category = null;
        try {
            category = em.createNamedQuery("Categories.findById", Categories.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            System.out.println("DB: " + ex);
        } finally {
            em.close();
        }
        return category;
    }
}
