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
public class SMExchangeSessionBean implements SMExchangeSessionBeanRemote, SMExchangeSessionBeanLocal {
    
    // General logic
    
    public int currentUserId = -1;
    
    @Override
    public int auth(String username, String password) {
        
        currentUserId = authUser(username, password);

        /*if(userid < 1){
            return Util.Result.USER_NOT_EXIST;
        }

        logIn(userid);

        if(authClientByUserId(userid) != null){
            return authClientByUserId(userid);
        }else if(authAgencyByUserId(userid) != null){
            return authAgencyByUserId(userid);
        }else if(authPerformerByUserId(userid) != null){
            return authPerformerByUserId(userid);
        }else{
            return Util.Result.USER_NOT_EXIST; // no user in db
        }*/
        
        return currentUserId;
    }
    
    @Override
    public int getCurrentUserId() {
        return currentUserId;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    // User logic
    
    @Override
    public int authUser(String username, String password) {     
        /*if(username.isEmpty() || password.isEmpty()){
            return -1;
        }

        return ug.authUser(username, password);*/
        
        return 11;       
    }

    @Override
    public int createNewUser(String type, String name, String username, String password, String phone) {
        return 0;
    }


    

    
    
    
}
