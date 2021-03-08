package br.com.devmedia.introducaojpql.dao;

import br.com.devmedia.introducaojpql.domain.Serie;

import javax.persistence.EntityManager;
import java.util.List;

public class SerieDAO {

    public void persist(Serie serie) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(serie);
        em.getTransaction().commit();
    }

    public void remove(Serie serie) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(serie);
        em.getTransaction().commit();
    }

    public void update(Serie serie) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(serie);
        em.getTransaction().commit();
    }

    public List<Serie> list() {
        EntityManager em = JPAUtil.getEntityManager();

        return em.createQuery("SELECT s FROM Serie s", Serie.class)
                .getResultList();
    }

    public Serie get(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.getReference(Serie.class, id);
    }
}
