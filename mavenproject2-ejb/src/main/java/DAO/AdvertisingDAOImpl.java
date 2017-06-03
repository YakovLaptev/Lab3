package DAO;

import Entities.Advertising;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author Yakov
 */
@Stateless
public class AdvertisingDAOImpl implements AdvertisingDAO, Serializable {

    @PersistenceContext(unitName = "mavenproject2-ejbPU")
    private EntityManager em;

    @Override
    public void addAdvertising(Advertising adv) {
        em.persist(adv);
    }

    @Override
    public void deleteAdvertising(Long id) {
        Advertising adv = em.find(Advertising.class, id);
        em.remove(adv);
    }

    @Override
    public Advertising getById(Long id) {
        return em.find(Advertising.class, id);
    }

    @Override
    public List<Advertising> getAllAdvertisings() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Advertising> selectAllQuery = criteriaBuilder.createQuery(Advertising.class);
        Root<Advertising> root = selectAllQuery.from(Advertising.class);
        selectAllQuery.select(root);
        TypedQuery<Advertising> selectAllAdvertisingQuery = em.createQuery(selectAllQuery);
        return selectAllAdvertisingQuery.getResultList();
    }
}
