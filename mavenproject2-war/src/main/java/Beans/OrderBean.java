package Beans;

import DAO.OrderDAO;
import Entities.Advertising;
import Entities.Order;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Yakov
 */
@ManagedBean(value = "OrderBean")
@RequestScoped
public class OrderBean implements Serializable {
    @EJB
    private OrderDAO orderDAO;
    private Long advId;

    public void getOrdersByAdvertisingIdRedirect(Long id) {
        advId = id;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        try {
            response.sendRedirect("ordersById.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String addOrder(Advertising advertising) {
        Random random = new Random();
        Order order = new Order();
        order.setAdvertising(advertising);
        order.setNumber(random.nextInt(100000) + advertising.getId().intValue());
        orderDAO.addOrder(order);
        return "advertisings";
    }

    public String deleteOrderById(Long id) {
        orderDAO.deleteOrder(id);
        return "orders";
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public List<Order> getOrdersByAdvertisingId() {
        return orderDAO.getOrdersByAdvertisingId(advId);
    }

}
