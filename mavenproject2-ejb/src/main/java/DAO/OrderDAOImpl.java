package DAO;

import Entities.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class OrderDAOImpl implements OrderDAO, Serializable {

    @PersistenceContext(unitName = "mavenproject2-ejbPU")
    private EntityManager em;

    @Override
    public void addOrder(Order ord) {
        em.persist(ord);
    }

    @Override
    public void deleteOrder(Long id) {
        Order ord = em.find(Order.class, id);
        em.remove(ord);
    }

    @Override
    public Order getById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> getAllOrders() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Order> selectAllQuery = criteriaBuilder.createQuery(Order.class); // ??
        Root<Order> root = selectAllQuery.from(Order.class); // ??
        selectAllQuery.select(root);
        TypedQuery<Order> selectAllOrderQuery = em.createQuery(selectAllQuery);
        return selectAllOrderQuery.getResultList();
    }

    @Override
    public List<Order> getOrdersByAdvertisingId(Long id) {
        Query query = em.createQuery("Select o FROM Order o JOIN o.advertising adv WHERE adv.id=?1", Order.class);
        query.setParameter(1, id);
        return (List<Order>) query.getResultList();
    }
}
