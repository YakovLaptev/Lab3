package Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Yakov
 */

@Entity
@Table(name = "Advertising", schema = "advag")
public class Advertising implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    @Size(min = 1, max = 20)
    private String category;
    @NotNull
    private int price;
    @NotNull
    @Size(min = 1, max = 40)
    private String briefDescription;
    @Size(min = 1, max = 100)
    private String fullDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertising", fetch = FetchType.LAZY)
    private List<Order> ordersList;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "advertising_campaign",
            joinColumns = @JoinColumn(name = "advertising_id"),
            inverseJoinColumns = @JoinColumn(name = "campaign_id"))
    private List<Campaign> campaigns;

    @PrePersist
    void advertisingPrePersist() {
        System.out.println("Производится добавление рекламы " + name);
    }

    @PostPersist
    void advertisingPostPersist() {
        System.out.println("Реклама " + name + " добавлена");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertising that = (Advertising) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Advertising{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", briefDescription='" + briefDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", ordersList=" + ordersList +
                '}';
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
