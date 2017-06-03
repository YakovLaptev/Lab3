package Beans;

import DAO.AdvertisingDAO;
import DAO.CampaignDAO;
import Entities.Advertising;
import Entities.Campaign;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yakov
 */
@ManagedBean(value = "AdvertisingBean")
@SessionScoped
public class AdvertisingBean implements Serializable {
    @EJB
    private AdvertisingDAO advDao;
    @EJB
    private CampaignDAO campDAO;
    private List selectedCampaigns;

    private Advertising advertising = new Advertising();

    public String addAdvertising() {
        List<Campaign> campaigns = new ArrayList<Campaign>();
        for (int i = 0; i < selectedCampaigns.size(); i++) {
            campaigns.add(campDAO.getById(Long.parseLong(selectedCampaigns.get(i).toString())));
        }
        advertising.setCampaigns(campaigns);
        advDao.addAdvertising(advertising);
        return "advertisings";
    }

    public List<Advertising> getAllAdvertisings() {
        return advDao.getAllAdvertisings();
    }

    public String deleteAdvertisingById(Long id) {
        advDao.deleteAdvertising(id);
        return "advertisings";
    }

    public Advertising getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }

    public List getSelectedCampaigns() {
        return selectedCampaigns;
    }

    public void setSelectedCampaigns(List selectedCampaigns) {
        this.selectedCampaigns = selectedCampaigns;
    }
}
