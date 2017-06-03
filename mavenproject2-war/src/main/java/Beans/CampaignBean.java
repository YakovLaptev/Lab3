package Beans;

import DAO.CampaignDAO;
import Entities.Campaign;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Yakov
 */
@ManagedBean(value = "CampaignBean")
@SessionScoped
public class CampaignBean implements Serializable {
    @EJB
    private CampaignDAO campaignDAO;
    private Campaign campaign = new Campaign();

    public String addCampaing() {
        campaignDAO.addCampaign(campaign);
        return "advertising";
    }
    
    public List<Campaign> getAllCampaigns() {
        return campaignDAO.getAllCampaigns();
    }

    public String deleteCampaignById(Long id) {
        campaignDAO.deleteCampaign(id);
        return "advertising";
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
