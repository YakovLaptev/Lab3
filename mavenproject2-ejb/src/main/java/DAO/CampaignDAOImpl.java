package DAO;

import Entities.Campaign;

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
 *
 * @author Yakov
 */
@Stateless
public class CampaignDAOImpl implements CampaignDAO, Serializable {

    @PersistenceContext(unitName = "mavenproject2-ejbPU")
    private EntityManager em;

    @Override
    public void addCampaign(Campaign cmp) {
        em.persist(cmp);
    }

    @Override
    public void deleteCampaign(Long id) {
        Campaign cmp = em.find(Campaign.class, id);
        em.remove(cmp);
    }

    @Override
    public Campaign getById(Long id) {
        return em.find(Campaign.class, id);
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Campaign> selectAllQuery = criteriaBuilder.createQuery(Campaign.class);
        Root<Campaign> root = selectAllQuery.from(Campaign.class);
        selectAllQuery.select(root);
        TypedQuery<Campaign> selectAllCampaignQuery = em.createQuery(selectAllQuery);
        return selectAllCampaignQuery.getResultList();
    }
}
