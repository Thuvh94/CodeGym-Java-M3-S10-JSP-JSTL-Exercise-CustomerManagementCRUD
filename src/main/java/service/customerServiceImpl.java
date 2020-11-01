package service;



import model.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class customerServiceImpl implements customerService<customer>{
    private static HashMap<Integer,customer> customers = new HashMap<>();
    static{
//        public customer(int id, String name, String address, int age, boolean isMale)
        customers.put(1,new customer(1,"Xuan","Ha Noi", 26,false));
        customers.put(2,new customer(2,"Ha","Nghe An", 25,false));
        customers.put(3,new customer(3,"Thu","Quang Nam", 25,false));
        customers.put(4,new customer(4,"Dong","Da Nang", 21,true));
        customers.put(5,new customer(5,"Tay","TP HCM", 20,true));
        customers.put(6,new customer(6,"Nam","Can Tho", 33,true));
        customers.put(7,new customer(7,"Bac","Tien Giang", 17,true));
    }

    @Override
    public List<customer> findAll() {
      return new ArrayList<>(customers.values());
    }

    @Override
    public void add(customer object) {
        customers.put(object.getId(),object);
    }

    @Override
    public void update(int id, customer object) {
        customers.put(id,object);
    }

    @Override
    public void delete(int id) {
        customers.remove(id);
    }
}
