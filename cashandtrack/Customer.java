package cashandtrack;
import java.util.*;
import java.lang.String;


public class Customer {

    private String customerName;

    private List<String> allCustomer = new ArrayList<String>();

    public Customer(String name) {
        this.customerName = name;
    }

    public int count()  {
        return allCustomer.size();
    }

    public boolean addCustomerName(String name) {
        allCustomer.add(name);
        return true;
    }

    /**
     *  To delete the customer's name
     * @param index
     * @return false if cannot delete the customer's name
     */
    public boolean deleteCustomerName(int index) {
        if (index > allCustomer.size()) {
            return false; }
        else {
            allCustomer.remove(index-1);
        }return true;
    }

    /**
     *  toString
     * @return the customer's name
     */
    public String toString() {
        String name = " ";
        if (allCustomer.size() == 0){
            name = "No Customer's name";
        } else {
            for (int i = 0; i < allCustomer.size()-1; i++){
                name += allCustomer.get(i).toString() + ",";
            } name += allCustomer.get(allCustomer.size()-1).toString();
        } return name;
    }
}