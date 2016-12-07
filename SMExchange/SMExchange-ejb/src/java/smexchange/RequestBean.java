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
public class RequestBean implements RequestBeanRemote {

    Request request;

    public RequestBean() {
        this.request = new Request();
    }

    public RequestBean(Request request) {
        this.request = request;
    }
    
    @Override
    public Integer getId() {
        return this.request.getId();
    }

    @Override
    public int getClientid() {
        return this.request.getClientid();
    }

    @Override
    public Float getPrice() {
        return this.request.getPrice();
    }

    @Override
    public Integer getPeriod() {
        return this.request.getPeriod();
    }

    @Override
    public String getSocialmedia() {
        return this.request.getSocialmedia();
    }

    @Override
    public String getDescription() {
        return this.request.getDescription();
    }

    @Override
    public String getStatus() {
        return this.request.getStatus();
    }

    @Override
    public String getOffersids() {
        return this.request.getOffersids();
    }

}
