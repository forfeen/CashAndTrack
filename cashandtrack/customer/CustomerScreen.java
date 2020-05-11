package cashandtrack.customer;

import cashandtrack.cart.CartScreen;
import cashandtrack.cart.StoreSingleton;
import cashandtrack.menu.Menu;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CustomerScreen {

    private StoreSingleton storeSingleton = StoreSingleton.getInstance();
    private static Customer customerObj = new Customer("Name","Normal",0);
    private TableView<Customer> customerTableView = new TableView<>();
    private static TableView<Menu> cartTableView = new TableView<>();
    private static ObservableList<Menu> customerCart;
    private ObservableList<Customer> customers;
    private TextField customerName;
    private String memberChoice[] = {"Normal", "Silver", "Gold", "Premium"};
    private final String[] defaultChoice = {memberChoice[0]};

    public static TableView<Menu> getCartTableView() {
        return cartTableView;
    }

    public Scene initComponents() {
        ScrollPane sc = new ScrollPane();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0));
        root.setPrefWrapLength(350);
        root.setHgap(10.0);
        root.setVgap(30);
        Label customerText = new Label("Customer");
        customerText.setFont( new Font("Arial", 20));

        Button addButton = new Button("Add New Customer");
        Button deleteButton = new Button("Delete Customer");
        Button checkoutButton = new Button("Check Out");
        addButton.setOnAction(this::addCustomerScreen);
        deleteButton.setOnAction(this::deleteCustomer);

        root.getChildren().addAll(customerText, showCustomerTable(), addButton, deleteButton, checkoutButton);
        sc.setPrefViewportHeight(1000);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sc.setContent(root);
        return new Scene( new VBox(sc));
    }

    private TableView<Customer> showCustomerTable() {
        TableColumn nameColumn = new TableColumn("Customer Name");
        TableColumn memberColumn = new TableColumn("Member");
        TableColumn costColumn = new TableColumn("Total Cost");
        TableColumn countColumn = new TableColumn("Order");

        nameColumn.setPrefWidth(130);
        memberColumn.setPrefWidth(100);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        memberColumn.setCellValueFactory(new PropertyValueFactory<>("member"));
//        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
//        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        customers = getCustomerList();
        customerTableView.setItems(customers);

        customerTableView.getColumns().addAll(nameColumn, memberColumn, costColumn, countColumn);
        customerTableView.setRowFactory(this::clickRow);
        return customerTableView;
    }


    public static TableView<Menu> showCartTable() {
        TableColumn nameColumn = new TableColumn("Menu Name");
        TableColumn priceColumn = new TableColumn("Price");
        nameColumn.setCellValueFactory( new PropertyValueFactory<>("menuName"));
        priceColumn.setCellValueFactory( new PropertyValueFactory<>("price"));

        cartTableView.setItems(customerCart);
        cartTableView.getColumns().addAll(nameColumn, priceColumn);
        return cartTableView;

    }

    private TableRow<Customer> clickRow(TableView<Customer> customerTableView) {
        TableRow<Customer> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                customerObj = row.getItem();
                int index = storeSingleton.getAllCustomer().indexOf(customerObj);
                customerCart = FXCollections.observableList(storeSingleton.getAllCustomer().get(index).getOrder()) ;
                try {
                    CartScreen.addCartScene();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return row ;
    }

    private ObservableList<Customer> getCustomerList() {
        return FXCollections.observableList(storeSingleton.getAllCustomer());
    }

    public static Customer getCustomerObj() {
        return customerObj;
    }

    public void deleteCustomer(ActionEvent event){
        Customer deleteCustomer = customerTableView.getSelectionModel().getSelectedItem();
        customerTableView.getItems().remove(deleteCustomer);
        storeSingleton.getAllCustomer().remove(deleteCustomer);
    }

    private void addCustomerScreen(ActionEvent event) {
        String memberChoice[] = {"Normal", "Silver", "Gold", "Premium"};
        final String[] c = {memberChoice[0]};
        try {
            FlowPane pane = new FlowPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding( new Insets(40));
            pane.setHgap(50);
            pane.setVgap(20);

            Label name = new Label("Customer : ");
            name.setStyle("-fx-line-spacing: 5;");
            customerName = new TextField();

            Label member = new Label("Member : ");
            ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(memberChoice));
            choiceBox.setValue(c[0]);
            choiceBox.getSelectionModel().selectedIndexProperty().addListener(this::changed);

            Button enterButton = new Button("ENTER");
            enterButton.setOnAction(this::enterHandler);

            pane.getChildren().addAll(name, customerName, member, choiceBox, enterButton);
            Scene scene = new Scene( new VBox(pane));

            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(scene);
            stage.setHeight(200);
            stage.setWidth(450);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        defaultChoice[0] = memberChoice[t1.intValue()];
    }



    private void enterHandler(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String name = customerName.getText().trim();
        try {
            if (name.isEmpty()) {
                alert.setContentText("Please input name");
                alert.showAndWait();
            } else {
                Customer newCustomer = new Customer(name, defaultChoice[0], 0);
                if (customerObj.equalsTo(newCustomer)) {
                    alert.setContentText("Customer already exist.");
                    alert.showAndWait();
                } else {
                    storeSingleton.getAllCustomer().add(newCustomer);
                }
            }
        } catch (NullPointerException e) {
            alert.setContentText("Please input name");
            alert.showAndWait();
        }
    }
}