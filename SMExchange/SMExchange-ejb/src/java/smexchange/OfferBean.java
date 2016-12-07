/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import javax.ejb.Stateless;

/**
 *
 * @author Victor
 */
@Stateless
public class OfferBean implements OfferBeanRemote {

    private Offer offer; 
    
    public OfferBean() {
        offer = new Offer();
    }

    public OfferBean(Offer offer) {
        this.offer = offer;
    }
    
    @Override
    public Integer getId() {
        return this.offer.getId();
    }

    @Override
    public int getPerformerid() {
        return this.offer.getPerformerid();
    }

    @Override
    public Integer getPeriod() {
        return this.offer.getPeriod();
    }

    @Override
    public float getPrice() {
        return this.offer.getPrice();
    }

    @Override
    public String getDescription() {
        return this.offer.getDescription(); 
    }
    
    @Override
    public String getSocialmedia() {
        return this.offer.getSocialmedia(); 
    }
}
