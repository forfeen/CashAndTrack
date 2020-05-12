package cashandtrack.customer;

import cashandtrack.cart.*;
import cashandtrack.menu.Menu;
import cashandtrack.payment.PaymentScreen;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CustomerScreen {

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    private static Customer customerObj = new Customer("Name","Normal",0);
    private TableView<Customer> customerTableView = new TableView<>();
    private static TableView<Menu> cartTableView = new TableView<>();
    private static ObservableList<Menu> customerCartObservableList;
    private ObservableList<Customer> customersObservableList;
    private static int index = 0;
    private TextField customerName;
    private String memberChoice[] = {"Normal", "Silver", "Gold", "Premium"};
    private final String[] defaultChoice = {memberChoice[0]};


    public Scene initComponents() {
        ScrollPane pane = new ScrollPane();
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
//        Button menuButton = new Button("Go to Menu");
        addButton.setOnAction(this::addCustomerScreen);
        deleteButton.setOnAction(this::deleteCustomer);
        checkoutButton.setOnAction(this::paymentScreen);
//        menuButton.setOnAction(CashAndTrack.handler(eventHandler));

        root.getChildren().addAll(customerText, getCustomerTableView(), addButton, deleteButton, checkoutButton);
        pane.setPrefViewportHeight(1000);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setContent(root);
        return new Scene( new VBox(pane));
    }

    public TableView<Customer> getCustomerTableView() {
        return customerTableView();
    }

    public static TableView<Menu> getCartTableView() {
        return cartTableView();
    }

    private TableView<Customer> customerTableView() {
        TableColumn nameColumn = new TableColumn("Customer Name");
        TableColumn memberColumn = new TableColumn("Member");
        TableColumn costColumn = new TableColumn("Total Cost");

        nameColumn.setPrefWidth(130);
        memberColumn.setPrefWidth(100);
        costColumn.setPrefWidth(100);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        memberColumn.setCellValueFactory(new PropertyValueFactory<>("member"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        customersObservableList = getCustomerList();
        customerTableView.getColumns().clear();
        customerTableView.setItems(customersObservableList);
        customerTableView.getColumns().addAll(nameColumn, memberColumn, costColumn);
        customerTableView.setRowFactory(this::clickRow);
        return customerTableView;
    }

    private static TableView<Menu> cartTableView () {
        TableColumn nameColumn = new TableColumn("Menu Name");
        //TableColumn priceColumn = new TableColumn("Price");
        nameColumn.setPrefWidth(170);
        //priceColumn.setPrefWidth(120);
        nameColumn.setCellValueFactory( new PropertyValueFactory<>("menuName"));
        //priceColumn.setCellValueFactory( new PropertyValueFactory<>("price"));
        cartTableView.getColumns().clear();
        cartTableView.setItems(getCustomerCartList());
//        cartTableView.getColumns().addAll(nameColumn, priceColumn);
        cartTableView.getColumns().addAll(nameColumn);

        return cartTableView;
    }

    private TableRow<Customer> clickRow(TableView<Customer> customerTableView) {
        TableRow<Customer> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                customerObj = row.getItem();
                index = storeSingleton.getAllCustomer().indexOf(customerObj);
                customerCartObservableList = FXCollections.observableList(storeSingleton.getAllCustomer().get(index).getOrder()) ;
                try {
                    CartScreen.addCartScene();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return row ;
    }

    public static ObservableList<Menu> getCustomerCartList() {
        return customerCartObservableList;
    }

    private ObservableList<Customer> getCustomerList() {
        return FXCollections.observableList(storeSingleton.getAllCustomer());
    }

    public static int getIndexCustomer() {
        return index;
    }

    public void paymentScreen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (getCustomerCartList() == null) {
            alert.setTitle("Check Out");
            alert.setContentText("Cart is Empty");
            alert.showAndWait();
        } else {
            try {
                PaymentScreen.checkoutScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCustomer(ActionEvent event){
        Customer deleteCustomer = customerTableView.getSelectionModel().getSelectedItem();
        customerTableView.getItems().remove(deleteCustomer);
        storeSingleton.getAllCustomer().remove(deleteCustomer);
    }

    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        defaultChoice[0] = memberChoice[t1.intValue()];
    }

    private void addCustomerScreen(ActionEvent event) {
        String memberChoice[] = {"Normal", "Silver", "Gold", "Premium"};
        final String[] c = {memberChoice[0]};
        try {
            FlowPane pane = new FlowPane();
            VBox boxAll = new VBox();
            HBox nameBox = new HBox();
            HBox memberBox = new HBox();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding( new Insets(20));

            Label name = new Label("Customer : ");
            name.setStyle("-fx-line-spacing: 5;");
            customerName = new TextField();
            customerName.setMaxWidth(Double.MAX_VALUE);

            Label member = new Label("Member : ");
            ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(memberChoice));
            choiceBox.setValue(c[0]);
            choiceBox.getSelectionModel().selectedIndexProperty().addListener(this::changed);

            Button enterButton = new Button("ENTER");
            enterButton.setOnAction(this::enterHandler);

            nameBox.setSpacing(10);
            nameBox.getChildren().addAll(name, customerName);
            memberBox.setSpacing(10);
            memberBox.getChildren().addAll(member, choiceBox);
            boxAll.setSpacing(20);
            boxAll.setAlignment(Pos.CENTER);
            boxAll.setPadding(new Insets(10, 20, 10, 20));
            boxAll.getChildren().addAll(nameBox, memberBox, enterButton);

            pane.getChildren().addAll(boxAll);

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