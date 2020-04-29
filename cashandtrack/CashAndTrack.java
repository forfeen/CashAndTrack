package cashandtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashAndTrack {

    private static List<Customer> allCustomer = new ArrayList<>();
    private  static List<Menu> allMenu = new ArrayList<Menu>();

    public static void showAllCustomer(List<Customer> allCustomer) {
        if (allCustomer.size() == 0){
            System.out.println("No Customer");
        } else {
            int count = 0;
            for (int i = 0; i <= allCustomer.size() - 1; i++){
                count ++ ;
                String customer = String.format(count + ". " + allCustomer.get(i).getCustomerName());
                System.out.println(customer);
            }
        }
    }

    public static void deleteCustomer(int index, List<Customer> customer) {
        if (index > customer.size()) {
            System.out.println("Out of index.");
        }
        else {
            customer.remove(index-1);
            showAllCustomer(customer);
        }
    }


    public static void showAllMenu(List<Menu> allMenu) {
        if (allMenu.size() == 0){
            System.out.println("No Menu");
        } else {
            int count = 0;
            for (int i = 0; i <= allMenu.size() - 1; i++){
                count ++ ;
                String menu = String.format(count + ". " +allMenu.get(i).toString());
                System.out.println(menu);
            }
        }
    }

    public static void deleteMenu(int index, List<Menu> menu) {
        if (index > menu.size()) {
            System.out.println("Out of index.");
        }
        else {
            menu.remove(index-1);
            showAllMenu(menu);
        }
    }


    public static void main(String[] args) {

        while (true) {
            System.out.print("(C)ustomer , (M)enu : ");

            Scanner input = new Scanner(System.in);
            String choose =  input.next();

            if (choose.equals("M".toLowerCase())) {
                Scanner menuInput = new Scanner(System.in);

                while (true) {

                    System.out.print("(A)dd , (D)elete , (S)how menu , (Q)uit : ");
                    String chooseMenu = menuInput.next().toUpperCase();

                    if (chooseMenu.equals("A")) {
                        Scanner addMenu = new Scanner(System.in);
                        System.out.print("Menu : ");
                        String menuName = addMenu.next();


                        Scanner addPrice = new Scanner(System.in);
                        System.out.print("Price : ");
                        double menuPrice = addPrice.nextDouble();
                        Menu newMenu = new Menu(menuName, menuPrice);
                        allMenu.add(newMenu);
                        showAllMenu(allMenu);

                    }

                    if (chooseMenu.equals("S")) {
                        showAllMenu(allMenu);

                    }if (chooseMenu.equals("D")) {
                        if (allMenu.size() == 0 ) {
                            System.out.println("No Menu");
                        } else {
                            showAllMenu(allMenu);
                            Scanner numDelete = new Scanner(System.in);
                            System.out.print("Input the number to delete : ");
                            int index = numDelete.nextInt();
                            deleteMenu(index, allMenu);
                        }
                    } if (chooseMenu.equals("Q")) {
                        break;
                    }
                }

            } if (choose.equals("C".toLowerCase())) {

                while (true) {
                    Scanner choiceMenu = new Scanner(System.in);
                    System.out.print("(A)dd new Customer , (D)elete Customer , Add (O)rder , (S)how customer's order :  ");
                    String customerChoice = choiceMenu.next().toUpperCase();

                    if (customerChoice.equals("A")) {
                        Scanner addCustomer = new Scanner(System.in);
                        System.out.print("Customer : ");
                        String customerName = addCustomer.next();
                        Customer newCustomer = new Customer(customerName);
                        allCustomer.add(newCustomer);
                    }if (customerChoice.equals("O")) {
                        if (allCustomer.size() == 0 || allMenu.size() == 0) {
                            System.out.println("Can not add order.");
                        } else {
                            showAllCustomer(allCustomer);
                            Scanner inputCustomer = new Scanner(System.in);
                            System.out.print("Input the number of customer  : ");
                            int indexCustomer = inputCustomer.nextInt();
                            showAllMenu(allMenu);

                            Scanner addOrder = new Scanner(System.in);
                            System.out.print("Input the number of menu  : ");
                            int indexOrder = addOrder.nextInt();

                            Menu selectedOrder = allMenu.get(indexOrder - 1);
                            Customer selectCustomer = allCustomer.get(indexCustomer -1);
                            selectCustomer.addOrder( selectedOrder );

                        }

                    } if (customerChoice.equals("S")){
                        if (allCustomer.size() == 0 ){
                            System.out.println("No customer");
                        } else {
                            showAllCustomer(allCustomer);
                            Scanner inputCustomer = new Scanner(System.in);
                            System.out.print("Input the number of customer  : ");
                            int indexCustomer = inputCustomer.nextInt();
                            System.out.println(allCustomer.get( indexCustomer - 1).toString());
                            allCustomer.get(indexCustomer - 1).showOrder();

                            while (true) {
                                Scanner choice = new Scanner(System.in);
                                System.out.print("(D)elete order , (C)heck out  , (Q)uiz : ");
                                String inputChoice = choice.next().toUpperCase();
                                if ( inputChoice.equals("D")) {
                                    //showAllCustomer(allCustomer);
                                    allCustomer.get(indexCustomer - 1).showOrder();
                                    Scanner inputIndex = new Scanner(System.in);
                                    System.out.print("Input the number to delete  : ");
                                    int indexDelete = inputIndex.nextInt();
                                    allCustomer.get( indexCustomer - 1).deleteOrder(indexDelete - 1);

                                } if (inputChoice.equals("C")) {
                                    System.out.println("f");
                                } if (inputChoice.equals("Q")) {
                                    break;
                                }
                            }
                        }
                    }  if (customerChoice.equals("D")) {
                        if (allCustomer.size() == 0 ) {
                            System.out.println("No Menu");
                        } else {
                            showAllCustomer(allCustomer);
                            Scanner numDelete = new Scanner(System.in);
                            System.out.print("Input the number to delete : ");
                            int index = numDelete.nextInt();
                            deleteCustomer(index, allCustomer);
                        }
                    }

                    if (customerChoice.equals("Q")) {
                        break;
                    }
                }

            }
        }

    }







}
