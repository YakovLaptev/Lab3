package DAO;

import Entities.Campaign;

import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author Yakov
 */
@Local
public interface CampaignDAO {
    void addCampaign(Campaign adv);
    void deleteCampaign(Long id);
    Campaign getById(Long id);
    List<Campaign> getAllCampaigns();
}
