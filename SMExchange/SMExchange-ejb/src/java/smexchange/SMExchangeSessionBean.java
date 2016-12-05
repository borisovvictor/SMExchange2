/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Victor
 */
@Stateless
public class SMExchangeSessionBean implements SMExchangeSessionBeanRemote, SMExchangeSessionBeanLocal {

    @PersistenceContext(unitName = "SMExchange-ejbPU")
    private EntityManager em;
    
    // General logic
    
    public int currentUserId = -1;
    
    @Override
    public int auth(String username, String password) {
        
        currentUserId = authUser(username, password);

        if(currentUserId < 1){
            return -1; //Util.Result.USER_NOT_EXIST;
        }

        //logIn(currentUserId);

        /*if(authClientByUserId(userid) != null){
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
    
    @Override
    public Object getCurrentUser() {     
        if (currentUserId < 0)
            return new Object();
        
        return getUserById(currentUserId);       
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    // User logic
    
    @Override
    public int authUser(String username, String password) {     
        if(username.isEmpty() || password.isEmpty()){
            return -1;
        }
        
        Query query = em.createNamedQuery("Users.findByUsername");
        query.setParameter("username", username);
        List result = query.getResultList();
        if(result.size() > 0)
        {
            Users user = (Users)result.get(0);
            if (user.getPassword().equals(password))
                return user.getId();
        }
        return -1;     
    }

    @Override
    public int createNewUser(String type, String name, String username, String password, String phone, String agencyId) {
        
        int result = -1;
        
        if (type.contains("client")) {
            result = createNewClient(name, username, password, phone);
        } else if (type.contains("performer")) {
            result = createNewPerformer(name, username, password, phone, Integer.parseInt(agencyId));
        } else if (type.contains("agency")) {
            result = createNewAgency(name, username, password);
        }
        
        return result;
    }

    @Override
    public int createNewAgency(String name, String username, String password) {      
        if(name.isEmpty() || username.isEmpty() || password.isEmpty()){
            return -1; //Util.Result.INVALID_PARAMS;
        }

        
        
        if(getUserIdByUsername(username) > 0){
            return -1; //Util.Result.USER_ALREADY_EXIST;
        }     

        Users user = new Users();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        em.persist(user);
        
        Agency agency = new Agency();
        agency.setAgencyid(user.getId());
        agency.setUser(user);
        em.persist(agency);
        
        /*
        addUser(name, username, password);

        int userid = getUserIdByUsername(username);
        if(userid < 1){
            return -1; //Util.Result.USER_NOT_EXIST;
        }

        addAgency(userid);
        int agencyid = getAgencyIdByUserId(userid);
        if(agencyid < 1){
            return -1; //Util.Result.INTERNAL_ERROR;
        }*/

        return 0; //Util.Result.SUCCEED;
    }
    
    @Override
    public int createNewClient(String name, String username, String password, String phoneNumber) {      
        if(name.isEmpty() || username.isEmpty() || password.isEmpty()){
            return -1; //Util.Result.INVALID_PARAMS;
        }

        if(getUserIdByUsername(username) > 0){
            return -1; //Util.Result.USER_ALREADY_EXIST;
        }     

        addUser(name, username, password);

        int userid = getUserIdByUsername(username);
        if(userid < 1){
            return -1; //Util.Result.USER_NOT_EXIST;
        }

        addClient(userid, phoneNumber);
        int clientid = getClientId(userid);
        if(clientid < 1) {
            return -1; //Util.Result.INTERNAL_ERROR;
        }

        return 0; //Util.Result.SUCCEED;
    }
    
    @Override
    public int createNewPerformer(String name, String username, String password,
            String phoneNumber, int agencyId) {      
        if(name.isEmpty() || username.isEmpty() || password.isEmpty()){
            return -1; //Util.Result.INVALID_PARAMS;
        }

        if(getUserIdByUsername(username) > 0){
            return -1; //Util.Result.USER_ALREADY_EXIST;
        }     

        addUser(name, username, password);

        int userid = getUserIdByUsername(username);
        if(userid < 1){
            return -1; //Util.Result.USER_NOT_EXIST;
        }

        addPerformer(userid, phoneNumber, agencyId);
        int performerid = getPerformerId(userid);
        if(performerid < 1){
            return -1; //Util.Result.INTERNAL_ERROR;
        }

        return 0; //Util.Result.SUCCEED;
    }

    @Override
    public int getUserIdByUsername(String username) {
        Query query = em.createNamedQuery("Users.findByUsername");
        query.setParameter("username", username);
        List result = query.getResultList();
        if(result.size() > 0)
            return ((Users)result.get(0)).getId();
        else
            return -1;
    } 
    
    @Override
    public Object getUserById(int userId) {
        Query query = em.createNamedQuery("Agency.findByAgencyid");
        query.setParameter("agencyid", userId);
        List result = query.getResultList();
        if(result.size() > 0)
            return (Agency)result.get(0);
        
        query = em.createNamedQuery("Client.findByClientid");
        query.setParameter("clientid", userId);
        result = query.getResultList();
        if(result.size() > 0)
            return (Client)result.get(0);
        
        query = em.createNamedQuery("Performer.findByPerformerid");
        query.setParameter("performerid", userId);
        result = query.getResultList();
        if(result.size() > 0)
            return (Performer)result.get(0);
        
        return new Object();
    } 
    
    @Override
    public int getAgencyIdByUserId(int userId) {
        Query query = em.createNamedQuery("Agency.findByAgencyid");
        query.setParameter("agencyid", userId);
        List result = query.getResultList();
        if(result.size() > 0)
            return ((Agency)result.get(0)).getId();
        else
            return -1;
    }
    
    @Override
    public int getClientId(int userId) {
        Query query = em.createNamedQuery("Client.findByClientid");
        query.setParameter("clientid", userId);
        List result = query.getResultList();
        if(result.size() > 0)
            return ((Client)result.get(0)).getId();
        else
            return -1;
    }
    
    @Override
    public int getPerformerId(int userId) {
        Query query = em.createNamedQuery("Performer.findByPerformerid");
        query.setParameter("performerid", userId);
        List result = query.getResultList();
        if(result.size() > 0)
            return ((Performer)result.get(0)).getId();
        else
            return -1;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public int addUser(String name, String username, String password) {
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return -1; // wrong parameters
        }

        Users user = new Users();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        em.persist(user);
        
        return 0;
    }

    @Override
    public void addAgency(int userId) {
        if(userId < 1){
            return;
        }
        
        Agency agency = new Agency();
        agency.setAgencyid(userId);
        em.persist(agency);       
    }
    
    @Override
    public void addClient(int userId, String phoneNumber) {
        if(userId < 1){
            return;
        }
        
        Client client = new Client();
        client.setClientid(userId);
        client.setPhonenum(phoneNumber);
        em.persist(client);       
    }
    
    @Override
    public void addPerformer(int userId, String phoneNumber, int agencyID) {
        if(userId < 1){
            return;
        }
        
        Performer performer = new Performer();
        performer.setPerformerid(userId);
        performer.setAgencyid(agencyID);
        performer.setPhonenumber(phoneNumber);
        performer.setMoneyamount(0F);
        em.persist(performer);       
    }

    
    
    
}

    
