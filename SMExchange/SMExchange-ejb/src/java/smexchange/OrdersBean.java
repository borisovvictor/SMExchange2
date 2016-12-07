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
public class OrdersBean implements OrdersBeanRemote {

    private Orders order; 
    
    public OrdersBean() {
        order = new Orders();
    }

    public OrdersBean(Orders order) {
        this.order = order;
    }
    
    @Override
    public Integer getId() {
        return this.order.getId();
    }

    @Override
    public int getClientid() {
        return this.order.getClientid();
    }

    @Override
    public Integer getOfferid() {
        return this.order.getOffer().getId();
        //return this.order.getOfferid();
    }

    @Override
    public String getOrderstatus() {
        return this.order.getOrderstatus();
    }

    @Override
    public String getSpeccond() {
        return this.order.getSpeccond();
    }


    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
