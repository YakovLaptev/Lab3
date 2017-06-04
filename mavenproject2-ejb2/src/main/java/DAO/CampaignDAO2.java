package DAO;

import Entity.Campaign2;

import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author Yakov
 */
@Local
public interface CampaignDAO2 {
    void addCampaign(Campaign2 cmp);
    void editCampaign(Campaign2 cmp);
    void editCampaignWithException(Campaign2 cmp);
    void deleteCampaign(Long id);
    Campaign2 getById(Long id);
    List<Campaign2> getAllCampaigns();
}
