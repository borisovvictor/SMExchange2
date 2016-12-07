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
public interface RequestBeanRemote {
    
    Integer getId();

    int getClientid();

    Float getPrice();

    Integer getPeriod();

    String getSocialmedia();

    String getDescription();

    String getStatus();

    String getOffersids();
}
