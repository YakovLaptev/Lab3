package Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yakov
 */
@Entity
@Table(name = "Order", schema = "advag")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer number;
    @JoinColumn(name = "advertising_id")
    @ManyToOne
    private Advertising advertising;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    void orderPrePersist() {
        System.out.println("Производится добавление заказа номер " + number);
    }

    @PostPersist
    void orderPostPersist() {
        System.out.println("Заказ номер " + number + " добавлен");
    }

    @PreRemove
    void orderPreRemove() {
        System.out.println("Производится ужадение заказа номер " + number);
    }

    @PostRemove
    void orderPostRemove() {
        System.out.println("Заказ номер " + number + " удален");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id != null ? id.equals(order.id) : order.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", advertising=" + advertising +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Advertising getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }

}
