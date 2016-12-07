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
public interface OfferBeanRemote {
    
    Integer getId();

    int getPerformerid();

    Integer getPeriod();

    float getPrice();

    String getDescription();
    
    String getSocialmedia();
    
}
