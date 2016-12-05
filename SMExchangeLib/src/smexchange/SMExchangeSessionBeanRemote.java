/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import javax.ejb.Remote;

/**
 *
 * @author Victor
 */
@Remote
public interface SMExchangeSessionBeanRemote {
    
    int auth(String username, String password);

    int getCurrentUserId();
    
    Object getCurrentUser();

    int createNewUser(String type, String name, String username, String password, String phone, String agencyId);
    
}
