package DAO;

import Entity.Campaign2;

import javax.ejb.EJBException;
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
public class CampaignDAO2Impl implements CampaignDAO2, Serializable {

    @PersistenceContext(unitName = "mavenproject2-ejbPU2")
    private EntityManager em;

    @Override
    public void addCampaign(Campaign2 cmp) {
        em.persist(cmp);
    }

    @Override
    public void editCampaign(Campaign2 cmp) {
        em.merge(cmp);
    }

    @Override
    public void editCampaignWithException(Campaign2 cmp) throws EJBException  {
        em.merge(cmp);
        throw new EJBException();
    }

    @Override
    public void deleteCampaign(Long id) {
        Campaign2 cmp = em.find(Campaign2.class, id);
        em.remove(cmp);
    }

    @Override
    public Campaign2 getById(Long id) {
        return em.find(Campaign2.class, id);
    }

    @Override
    public List<Campaign2> getAllCampaigns() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Campaign2> selectAllQuery = criteriaBuilder.createQuery(Campaign2.class);
        Root<Campaign2> root = selectAllQuery.from(Campaign2.class);
        selectAllQuery.select(root);
        TypedQuery<Campaign2> selectAllCampaignQuery = em.createQuery(selectAllQuery);
        return selectAllCampaignQuery.getResultList();
    }

}
