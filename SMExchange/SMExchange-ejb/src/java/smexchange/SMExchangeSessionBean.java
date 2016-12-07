/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    
    public enum RequestStatus {
        NONE("NONE"),
        CREATED("CREATED"),
        ASSIGNED("ASSIGNED"),
        COMPLETED("COMPLETED");

        private final String text;

        /**
         * @param text
         */
        private RequestStatus(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }
    
    public enum OrderStatus {
        
        NONE("NONE"),                // отсутствует
        CREATED("CREATED"),             // создан
        COND_OFFERED("COND_OFFERED"),        // условия предложены
        APPROVED("APPROVED"),            // условия согласованы
        REJECTED("REJECTED"),            // условия отклонены
        PAID("PAID"),                // оплачен
        PAYMENT_CONFIRMED("PAYMENT_CONFIRMED"),   // платеж подтвержден
        PERFORMED("PERFORMED"),           // выполнен
        CONFIRMED("CONFIRMED"),           // выполнение подтверждено
        COMPLETED("COMPLETED");           // завершен

        private final String text;

        /**
         * @param text
         */
        private OrderStatus(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }
    
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

        Users user = new Users();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        em.persist(user);
        
        Client client = new Client();
        client.setClientid(user.getId());
        client.setUser(user);
        client.setPhonenum(phoneNumber);
        em.persist(client);
        
        /*
        addUser(name, username, password);

        int userid = getUserIdByUsername(username);
        if(userid < 1){
            return -1; //Util.Result.USER_NOT_EXIST;
        }

        addClient(userid, phoneNumber);
        int clientid = getClientId(userid);
        if(clientid < 1) {
            return -1; //Util.Result.INTERNAL_ERROR;
        }*/

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

        /*addUser(name, username, password);

        int userid = getUserIdByUsername(username);
        if(userid < 1){
            return -1; //Util.Result.USER_NOT_EXIST;
        }

        addPerformer(userid, phoneNumber, agencyId);
        int performerid = getPerformerId(userid);
        if(performerid < 1){
            return -1; //Util.Result.INTERNAL_ERROR;
        }*/
        
        
        Users user = new Users();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        em.persist(user);
        
        Performer performer = new Performer();
        performer.setPerformerid(user.getId());
        performer.setUser(user);
        performer.setPhonenumber(phoneNumber);
        performer.setMoneyamount(0F);
        performer.setAgencyid(agencyId);
        em.persist(performer);

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
    
    @Override
    public List<OrdersBeanRemote> getOrdersByCurrentUser()
    {
        /*Orders order = new Orders();
        order.setClientid(11);
        order.setId(11);
        order.setOfferid(11);
        order.setOrderstatus("status");
        order.setSpeccond("speccond");*/
        
        List<OrdersBeanRemote> result =  new ArrayList<OrdersBeanRemote>();
        //result.add(new OrdersBean(order));

        for (Orders order : getOrdersByUserId(currentUserId))
        {
            result.add(new OrdersBean(order));
        }
        
        return result;
    }
    
    @Override
    public List<Orders> getOrdersByUserId(int userID)
    {
        Object currentUser = getCurrentUser();
        
        if (currentUser instanceof Performer) {
            return getOrdersByPerformerId(userID);
        }

        if (currentUser instanceof Client) {
            return getOrdersByClientId(userID);
        }
        
        if (currentUser instanceof Agency) {
            return getOrdersByAgencyId(userID);
        }
        return new ArrayList<Orders>();
    }

     public List<Orders> getOrdersByClientId(int clientid){
        if(clientid < 1){
            return new ArrayList<Orders>();
        }
                
        Query query = em.createNamedQuery("Orders.findByClientid");
        query.setParameter("clientid", clientid);
        List result = query.getResultList();
        if(result.size() > 0)
        {
            return result;
        }        
        
        return new ArrayList<Orders>();
        //return DataGateway.request(
        //        "select * from orders where orders.client_id=" + clientid);
    }

    public List<Orders> getOrdersByPerformerId(int performerid) {
        List<Orders> orders = new ArrayList<Orders>();
        
        if (performerid < 1) {
            return orders; 
        }
        
        Query query = em.createNamedQuery("Orders.findAll");
        //query.setParameter("clientid", performerid);
        List<Orders> result = query.getResultList();
        for (Orders order : result)
        {
            if (order.getOffer().getPerformerid() == performerid)
                orders.add(order);
        }        
        
        return orders;
        //return DataGateway.request(
        //        "select orders.id,orders.client_id,orders.offer_id,orders.status,orders.conditions "
        //                + "from (orders join offers on orders.offer_id = offers.id and offers.performer_id = " + performerid + ")");
    }

    public List<Orders> getOrdersByAgencyId(int agencyid) {
        List<Orders> orders = new ArrayList<Orders>();       
        
        if (agencyid < 1) {
            return orders;
        }
        
        Query query1 = em.createNamedQuery("Orders.findAll");
        List<Orders> result1 = query1.getResultList();
        for (Orders order : result1)
        {
            Integer performerid = order.getOffer().getPerformerid();
            Query query2 = em.createNamedQuery("Performer.findById");
            query2.setParameter("id", performerid);
            List result2 = query2.getResultList();
            if(result2.size() > 0)
            {
                if (((Performer)result2.get(0)).getAgencyid() == agencyid)
                    orders.add(order);
            }          
        }    

        return orders;

//        return DataGateway.request(
//                "select orders.id,orders.client_id,orders.offer_id,orders.status,orders.conditions"
//                + " from ((orders join offers on orders.offer_id = offers.id)"
//                + " join performers on offers.performer_id = performers.id and performers.agency_id =" + agencyid + ")");
    }
    
    public List<Offer> getOffersByAgencyId(int agencyid) {
        List<Offer> offers = new ArrayList<Offer>();       
        
        if (agencyid < 1) {
            return offers;
        }
        
        Query query1 = em.createNamedQuery("Offer.findAll");
        List<Offer> result1 = query1.getResultList();
        for (Offer offer : result1)
        {
            Integer performerid = offer.getPerformerid();
            Query query2 = em.createNamedQuery("Performer.findById");
            query2.setParameter("id", performerid);
            List result2 = query2.getResultList();
            if(result2.size() > 0)
            {
                if (((Performer)result2.get(0)).getAgencyid() == agencyid)
                    offers.add(offer);
            }          
        }    

        return offers;

//        return DataGateway.request(
//                "select orders.id,orders.client_id,orders.offer_id,orders.status,orders.conditions"
//                + " from ((orders join offers on orders.offer_id = offers.id)"
//                + " join performers on offers.performer_id = performers.id and performers.agency_id =" + agencyid + ")");
    }
    
    @Override
    public List<OfferBeanRemote> getOffersByCurrentUser()
    {
        List<OfferBeanRemote> result =  new ArrayList<OfferBeanRemote>();
        for (Offer offer : getOffersByUserId(currentUserId))
        {
            //result.add(new OfferBean(offer)); 
            Offer _offer = new Offer();
            _offer.setPerformerid(offer.getPerformerid());
            _offer.setId(offer.getId());
            _offer.setPrice(offer.getPrice());
            _offer.setPeriod(offer.getPeriod());
            _offer.setDescription(offer.getDescription());
            _offer.setSocialmedia(offer.getSocialmedia());
            result.add(new OfferBean(_offer));
        }
        return result;
    }

    @Override
    public List<Offer> getOffersByUserId(int userID)
    {
        // only for performers
        Object user = getUserById(userID);     
        if (user instanceof Performer)
        {
            Query query = em.createNamedQuery("Offer.findByPerformerid");
            query.setParameter("performerid", userID);
            List result = query.getResultList();
            if(result.size() > 0)
            {
                return result;
            }     
        }
        return new ArrayList<>();
    }
    
    @Override
    public List<RequestBeanRemote> getRequestsByCurrentUser()
    {
        List<RequestBeanRemote> result =  new ArrayList<RequestBeanRemote>();
        for (Request request : getRequestsByUserId(currentUserId))
        {
            //result.add(new OfferBean(offer)); 
            Request _request = new Request();
            _request.setClientid(request.getClientid());
            _request.setId(request.getId());
            _request.setPrice(request.getPrice());
            _request.setPeriod(request.getPeriod());
            _request.setDescription(request.getDescription());
            _request.setSocialmedia(request.getSocialmedia());
            _request.setStatus(request.getStatus());            
            _request.setOffersids(request.getOffersids());
            result.add(new RequestBean(_request));
        }
        return result;
    }
    
    public List<Request> getRequestsByUserId(int userID)
    {
        Object user = getUserById(userID);
        
        if (user instanceof Client) {
            //Client client = (Client)user;
            Query query = em.createNamedQuery("Request.findByClientid");
            query.setParameter("clientid", userID/*client.getId()*/);
            List result = query.getResultList();
            if(result.size() > 0)
            {
                return result;
            }  
        }
        
        if (user instanceof Agency) {
            Query query = em.createNamedQuery("Request.findAll");
            List result = query.getResultList();
            if(result.size() > 0)
            {
                return result;
            }  
            //return getAllRequestsParsed();
            //return requestLogic.getUnassignedRequests();
        }
        return new ArrayList<>();
    }
    
    @Override
    public int createOffer(String price, String period, String description, String social_media)
    { 
        if (price.isEmpty() || period.isEmpty() || description.isEmpty() || social_media.isEmpty())
            return -1;
        // for performer only
        Object user = getCurrentUser();        
        if (user instanceof Performer) {
            Offer offer = new Offer();
            offer.setPrice(Float.parseFloat(price));
            offer.setPeriod(Integer.parseInt(period));
            offer.setDescription(description);
            offer.setSocialmedia(social_media);
            offer.setPerformerid(currentUserId);
            em.persist(offer);
            return 0;
        }

        return -1;
    }
    
    
    @Override
    public int createRequest(String price, String period, String description, String social_media)
    {
        if (price.isEmpty() || period.isEmpty() || description.isEmpty() || social_media.isEmpty())
            return -1;
        // for performer only
        Object user = getCurrentUser();        
        if (user instanceof Client) {
            Request request = new Request();
            request.setPrice(Float.parseFloat(price));
            request.setPeriod(Integer.parseInt(period));
            request.setDescription(description);
            request.setSocialmedia(social_media);
            request.setClientid(currentUserId);
            request.setStatus(RequestStatus.CREATED.toString());
            em.persist(request);
            return 0;
        }

        return -1;
    }
    
    @Override
    public /*Map<Integer,*/ List<OfferBeanRemote> /*> */findOffers(double price, int period, String socialMedia, String keyWords)
    {
        List<OfferBeanRemote> allOffers = new ArrayList<>();
        
        Object user = getCurrentUser();
        if (!(user instanceof Agency))
            return allOffers;
        
        Agency agency = (Agency)user;

        Integer user_id = agency.getUser().getId();
        
        List<OfferBeanRemote> offers = findOffersByAgency(user_id, price, period, socialMedia, keyWords);

        if (offers.size() > 0)
            allOffers.addAll(offers);

        return allOffers;
    }
    
    private List<OfferBeanRemote> findOffersByAgency(int agencyId, double price, int period, String socialMedia, String keyWords)
    {
        Query query1 = em.createNamedQuery("Offer.findAll");

        List<Offer> allOffers = query1.getResultList();//getOffersByAgencyId(agencyId);
        List<OfferBeanRemote> offers = new ArrayList<>();

        for (Offer offer : allOffers)
        {
            //if (isOfferSatisfyRequest(o, price, period, socialMedia, keyWords))
            {
                Offer _offer = new Offer();
                _offer.setPerformerid(offer.getPerformerid());
                _offer.setId(offer.getId());
                _offer.setPrice(offer.getPrice());
                _offer.setPeriod(offer.getPeriod());
                _offer.setDescription(offer.getDescription());
                _offer.setSocialmedia(offer.getSocialmedia());
                offers.add(new OfferBean(_offer));
                //offers.add(new OfferBean(offer));
            }
        }
        return offers;
    }

    private boolean isOfferSatisfyRequest(Offer offer, double price, int period, String socialMedia, String keyWords)
    {
        if (offer.getPrice() > price)
            return false;
        if (!offer.getSocialmedia().contains(socialMedia))
            return false;
        if (offer.getPeriod() < period)
            return false;

        boolean containKeyWords = false;
        for (String keyWord : keyWords.split(":| |,"))
        {
            if (offer.getDescription().contains(keyWord))
            {
                containKeyWords = true;
                break;
            }
        }

        if (!containKeyWords)
            return false;

        return true;
    }
    
    
    
}

    
