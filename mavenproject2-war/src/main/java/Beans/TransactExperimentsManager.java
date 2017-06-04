package Beans;

import DAO.CampaignDAO;
import DAO.CampaignDAO2;
import Entities.Campaign;
import Entity.Campaign2;

import javax.annotation.Resource;
import javax.ejb.*;

/**
 * @author Yakov
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TransactExperimentsManager {

    //sb = 1
    //sdf = 2

    @EJB
    private CampaignDAO campaignDAO;
    @EJB
    private CampaignDAO2 campaignDAO2;
    @Resource
    private SessionContext sessionContext;
    private StringBuilder sb;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String firstTransactionExperiment() {
        sb = new StringBuilder();
        Campaign campaign = campaignDAO.getById(1L);
        sb.append("До: " + campaign.toString());
        campaign.setPrice(campaign.getPrice() + 3000);
        changeCampaignPrice(campaign);
        campaignDAO.editCampaign(campaign);
        sb.append(" После: " + campaignDAO.getById(campaign.getId()) + " " + campaignDAO.getById(campaign.getId()).toString());
        return sb.toString();
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    private void changeCampaignPrice(Campaign campaign) {
        Campaign2 campaign2 = campaignDAO2.getById(campaign.getId());
        campaign2.setPrice(campaign.getPrice());
        campaignDAO2.editCampaign(campaign2);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String secondTransactionExperiment() {
        Campaign campaign = campaignDAO.getById(1L);
        campaign.setPrice(campaign.getPrice() + 5000);
        campaignDAO.editCampaignWithRollback(campaign);
        return !sessionContext.getRollbackOnly() ? "Rollback was not" : "Rollback was";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String thirdTransactionExperiment() {
        try {
            Campaign campaign = campaignDAO.getById(1L);
            campaign.setPrice(campaign.getPrice() + 3000);
            Campaign2 campaign2 = campaignDAO2.getById(campaign.getId());
            campaign2.setPrice(campaign.getPrice());
            campaignDAO.editCampaign(campaign);
            campaignDAO2.editCampaignWithException(campaign2);
        } catch (EJBException ex) {
            return "Вызвано исключение.";
        }
        return "Исключение не было вызвано";

    }

//    @TransactionAttribute(TransactionAttributeType.MANDATORY)
//    private void reduceAccuracyWithException(Sportsman s) throws EJBException {
//        SportsmanDetails details = sdf.find(s.getId());
//        details.setAccuracy(s.getAccuracy());
//        sdf.edit(details);
//        throw new EJBException();
//    }
//
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public String fourthTransactionExperiment() {
//
//        Sportsman s = sb.findAllSportsmen().get(0);
//        s.setAccuracy(s.getAccuracy() - 0.01F);
//        sb.editSportsman(s);
//        reduceAccuracyNotSupported(s);
//        scontext.setRollbackOnly();
//        return "Success4";
//
//    }
//
//    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//    private void reduceAccuracyNotSupported(Sportsman s) {
//        SportsmanDetails details = sdf.find(s.getId());
//        details.setAccuracy(s.getAccuracy());
//        sdf.edit(details);
//    }
//
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public String fifthTransactionExperiment() {
//
//        Sportsman s = sb.findAllSportsmen().get(0);
//        s.setAccuracy(s.getAccuracy() - 0.01F);
//        sb.editSportsman(s);
//        reduceAccuracyRequiresNew(s);
//        sessionContext.setRollbackOnly();
//        return "Success5";
//
//    }
//
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    private void reduceAccuracyRequiresNew(Sportsman s) {
//        reduceAccuracy(s);
//    }
}
