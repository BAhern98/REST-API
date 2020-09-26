/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Service.Styles;
import Service.DBUtil;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brendan
 */
@Service
public class Styles_Service {
    
     public List<Styles> getAllStyles() {
        EntityManager em = DBUtil.getEMF().createEntityManager();

        List<Styles> list = null;

        try {
            list = em.createNamedQuery("Styles.findAll", Styles.class)
                    .getResultList();
            if (list == null || list.isEmpty()) {
                list = null;
            }

        } finally {
            em.close();
        }
        return list;

    }
       public List<Styles> getAllStylesByName() {
        EntityManager em = DBUtil.getEMF().createEntityManager();

        List<Styles> list = null;

        try {
            list = em.createNamedQuery("Styles.findAllOrderBy", Styles.class)
                    .getResultList();
            if (list == null || list.isEmpty()) {
                list = null;
            }

        } finally {
            em.close();
        }
        return list;

    }
    
}
