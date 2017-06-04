package Beans;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Yakov
 */
@ManagedBean(value = "TransactBean")
@SessionScoped
public class TransactBean implements Serializable {
    @EJB
    private TransactExperimentsManager experimentsManager;

    public String getFirstTransactionExperiment() {
        return experimentsManager.firstTransactionExperiment();
    }
    public String getSecondTransactionExperiment() {
        return experimentsManager.secondTransactionExperiment();
    }
    public String getThirdTransactionExperiment() {
        return experimentsManager.thirdTransactionExperiment();
    }
    public String getFourthTransactionExperiment() {
        return experimentsManager.fourthTransactionExperiment();
    }
    public String getFifthTransactionExperiment() {
        return experimentsManager.fifthTransactionExperiment();
    }


}
