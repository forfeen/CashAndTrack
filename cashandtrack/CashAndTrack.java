package cashandtrack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import strategy.GoldPayment;
import strategy.PremiumPayment;
import strategy.SilverPayment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashAndTrack extends Application {


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


//    public static void main(String[] args) {
//
//        while (true) {
//            System.out.print("(C)ustomer , (M)enu : ");
//
//            Scanner input = new Scanner(System.in);
//            String choose =  input.next();
//
//            if (choose.equals("M".toLowerCase())) {
//                Scanner menuInput = new Scanner(System.in);
//
//                while (true) {
//
//                    System.out.print("(A)dd , (D)elete , (S)how menu , (Q)uit : ");
//                    String chooseMenu = menuInput.next().toUpperCase();
//
//                    if (chooseMenu.equals("A")) {
//                        Scanner addMenu = new Scanner(System.in);
//                        System.out.print("Menu : ");
//                        String menuName = addMenu.next();
//
//
//                        Scanner addPrice = new Scanner(System.in);
//                        System.out.print("Price : ");
//                        double menuPrice = addPrice.nextDouble();
//                        Menu newMenu = new Menu(menuName, menuPrice);
//                        allMenu.add(newMenu);
//                        showAllMenu(allMenu);
//
//                    }
//
//                    if (chooseMenu.equals("S")) {
//                        showAllMenu(allMenu);
//
//                    }if (chooseMenu.equals("D")) {
//                        if (allMenu.size() == 0 ) {
//                            System.out.println("No Menu");
//                        } else {
//                            showAllMenu(allMenu);
//                            Scanner numDelete = new Scanner(System.in);
//                            System.out.print("Input the number to delete : ");
//                            int index = numDelete.nextInt();
//                            deleteMenu(index, allMenu);
//                        }
//                    } if (chooseMenu.equals("Q")) {
//                        break;
//                    }
//                }
//
//            } if (choose.equals("C".toLowerCase())) {
//
//                while (true) {
//                    Scanner choiceMenu = new Scanner(System.in);
//                    System.out.print("(A)dd new Customer , (D)elete Customer , Add (O)rder , (S)how customer's order :  ");
//                    String customerChoice = choiceMenu.next().toUpperCase();
//
//                    if (customerChoice.equals("A")) {
//                        Scanner addCustomer = new Scanner(System.in);
//                        System.out.print("Customer : ");
//                        String customerName = addCustomer.next();
//
//                        Customer newCustomer = new Customer(customerName, 0.0);
//
//                        Scanner addLevel = new Scanner(System.in);
//                        System.out.println("(P)remium , (G)old, (S)ilver, (N)ormal \n Member : ");
//                        String customerLevel = addLevel.next().toUpperCase();
//                        if (customerLevel.equals("P")) {
//                            newCustomer.setPaymentStrategy(new PremiumPayment());
//                        } if (customerLevel.equals("G")) {
//                            newCustomer.setPaymentStrategy( new GoldPayment());
//                        } if (customerLevel.equals("S")) {
//                            newCustomer.setPaymentStrategy( new SilverPayment());
//                        }
//
//                        allCustomer.add(newCustomer);
//                    }if (customerChoice.equals("O")) {
//                        if (allCustomer.size() == 0 || allMenu.size() == 0) {
//                            System.out.println("Can not add order.");
//                        } else {
//                            showAllCustomer(allCustomer);
//                            Scanner inputCustomer = new Scanner(System.in);
//                            System.out.print("Input the number of customer  : ");
//                            int indexCustomer = inputCustomer.nextInt();
//                            showAllMenu(allMenu);
//
//                            Scanner addOrder = new Scanner(System.in);
//                            System.out.print("Input the number of menu  : ");
//                            int indexOrder = addOrder.nextInt();
//
//                            Menu selectedOrder = allMenu.get(indexOrder - 1);
//                            Customer selectCustomer = allCustomer.get(indexCustomer -1);
//                            selectCustomer.addOrder( selectedOrder );
//
//                        }
//
//                    } if (customerChoice.equals("S")){
//                        if (allCustomer.size() == 0 ){
//                            System.out.println("No customer");
//                        } else {
//                            showAllCustomer(allCustomer);
//                            Scanner inputCustomer = new Scanner(System.in);
//                            System.out.print("Input the number of customer  : ");
//                            int indexCustomer = inputCustomer.nextInt();
//                            System.out.println(allCustomer.get( indexCustomer - 1).toString());
//                            allCustomer.get(indexCustomer - 1).showOrder();
//
//                            while (true) {
//                                Scanner choice = new Scanner(System.in);
//                                System.out.print("(D)elete order , (C)heck out  , (Q)uit : ");
//                                String inputChoice = choice.next().toUpperCase();
//                                if ( inputChoice.equals("D")) {
//                                    allCustomer.get(indexCustomer - 1).showOrder();
//                                    Scanner inputIndex = new Scanner(System.in);
//                                    System.out.print("Input the number to delete  : ");
//                                    int indexDelete = inputIndex.nextInt();
//                                    allCustomer.get( indexCustomer - 1).deleteOrder(indexDelete - 1);
//                                    allCustomer.get( indexCustomer - 1).showOrder();
//
//                                } if (inputChoice.equals("C")) {
//                                    System.out.print("\nTotal cost : " + allCustomer.get(indexCustomer - 1).getCost());
//                                    while (true) {
//                                        Scanner askInput = new Scanner(System.in);
//                                        System.out.print("(C)onfirm , (Q)uit : ");
//                                        String  ask = askInput.next();
//                                        if (ask.equals("C")) {
//                                            Scanner receive = new Scanner(System.in);
//                                            System.out.print("Receive : ");
//                                            double receiveMoney = receive.nextDouble();
//                                            System.out.println("Total cost : " + allCustomer.get(indexCustomer - 1).getCost());
//
//                                            // เงินที่ลดแล้ว
//                                            System.out.println("Net. Cost : " + allCustomer.get( indexCustomer - 1).netCost());
//                                            // เงินทอน
//                                            double change = receiveMoney - allCustomer.get(indexCustomer - 1).netCost();
//                                            System.out.println("Change : " +  change);
//
//                                        } if (ask.equals("Q")) {
//                                            break;
//                                        }
//                                    }
//                                } if (inputChoice.equals("Q")) {
//                                    break;
//                                }
//                            }
//                        }
//                    }  if (customerChoice.equals("D")) {
//                        if (allCustomer.size() == 0 ) {
//                            System.out.println("No Menu");
//                        } else {
//                            showAllCustomer(allCustomer);
//                            Scanner numDelete = new Scanner(System.in);
//                            System.out.print("Input the number to delete : ");
//                            int index = numDelete.nextInt();
//                            deleteCustomer(index, allCustomer);
//                        }
//                    }
//
//                    if (customerChoice.equals("Q")) {
//                        break;
//                    }
//                }
//
//            }
//        }
//
//    }


    public void start(Stage stage) throws Exception {
        stage.setTitle("Cash & Track");
        stage.setScene(initComponents());
        stage.show();
    }

    private Scene initComponents() {
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding( new Insets(10.0));
        root.setPrefWrapLength(1000);
        root.setHgap(10.0);

        Image customerPic = new Image("/images/people.png");
        ImageView customer = new ImageView();
        customer.setImage(customerPic);
        customer.setFitHeight(100);
        customer.setFitWidth(100);
        customer.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleCustomerClicked );


        Image menuPic = new Image("/images/burger.png");
        ImageView menu = new ImageView();
        menu.setImage(menuPic);
        menu.setFitWidth(100);
        menu.setFitHeight(100);
        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleMenuClicked );

        root.getChildren().addAll(customer, menu);
        VBox VB = new VBox(root);
        Scene scene = new Scene(VB);
        return scene;
    }

    public void customerScene(Stage stage) throws Exception {


    }


    private void handleCustomerClicked(MouseEvent mouseEvent) {
        System.out.println("d");
        //start();
    }

    private void  handleMenuClicked(MouseEvent mouseEvent) {
        System.out.println("s");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
