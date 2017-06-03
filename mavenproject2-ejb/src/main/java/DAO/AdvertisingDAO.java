package DAO;

import Entities.Advertising;

import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author Yakov
 */
@Local
public interface AdvertisingDAO {
    void addAdvertising(Advertising adv);
    void deleteAdvertising(Long id);
    Advertising getById(Long id);
    List<Advertising> getAllAdvertisings();
}
