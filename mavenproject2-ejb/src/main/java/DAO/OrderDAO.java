package DAO;

import Entities.Order;

import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author Yakov
 */
@Local
public interface OrderDAO {
    void addOrder(Order adv);
    void deleteOrder(Long id);
    Order getById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByAdvertisingId(Long id);
}
