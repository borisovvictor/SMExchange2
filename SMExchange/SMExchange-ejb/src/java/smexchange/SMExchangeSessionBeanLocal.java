/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Victor
 */
@Local
public interface SMExchangeSessionBeanLocal {

    int authUser(String username, String password);

    int createNewAgency(String name, String username, String password);
    
    int createNewClient(String name, String username, String password, String phoneNumber);
    
    int createNewPerformer(String name, String username, String password,
        String phoneNumber, int agencyId);

    int getUserIdByUsername(String username);
    
    Object getUserById(int userId);
    
    int getAgencyIdByUserId(int userId);
    
    int getClientId(int userId);
    
    int getPerformerId(int userId);

    int addUser(String name, String username, String password);

    void addAgency(int userId);
    
    void addClient(int userId, String phoneNumber);
    
    void addPerformer(int userId, String phoneNumber, int agencyID);
    
    List<Orders> getOrdersByUserId(int userID);
    
    List<Offer> getOffersByUserId(int userID);
    
}
