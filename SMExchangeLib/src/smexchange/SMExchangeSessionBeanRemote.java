/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import java.util.List;
import java.util.Map;
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
    
    List<OrdersBeanRemote> getOrdersByCurrentUser();
    
    List<OfferBeanRemote> getOffersByCurrentUser();
    
    List<RequestBeanRemote> getRequestsByCurrentUser();
    
    int createOffer(String price, String period, String description, String social_media);
    
    int createRequest(String price, String period, String description, String social_media);
    
    List<OfferBeanRemote> findOffers(double price, int period, String socialMedia, String keyWords);
    
}
