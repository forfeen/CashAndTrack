package cashandtrack.customer;

import cashandtrack.CashAndTrack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerScreen {

    public Scene menu() {

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding( new Insets(100));
        pane.setHgap(50);

        ImageView addCustomer = new ImageView( new Image("/images/add-customer.png"));
        addCustomer.setFitWidth(100);
        addCustomer.setFitHeight(100);
        Button addButton = new Button("",addCustomer);
        addButton.setStyle("-fx-focus-color: white;");
        addButton.setOnAction( this::addCustomerScreen);

        ImageView deleteCustomer = new ImageView( new Image("/images/delete-customer.png"));
        deleteCustomer.setFitWidth(100);
        deleteCustomer.setFitHeight(100);
        Button deleteButton = new Button("",deleteCustomer);
        deleteButton.setStyle("-fx-focus-color: white;");

        ImageView orderMenu = new ImageView( new Image("/images/cooking.png"));
        orderMenu.setFitHeight(100);
        orderMenu.setFitWidth(100);
        Button orderButton = new Button("", orderMenu);
        orderButton.setStyle("-fx-focus-color: white;");

        ImageView showCustomer = new ImageView( new Image("/images/all-customer.png"));
        showCustomer.setFitWidth(100);
        showCustomer.setFitHeight(100);
        Button showButton = new Button("", showCustomer);
        showButton.setStyle("-fx-focus-color: white");

        pane.getChildren().addAll(addButton , deleteButton, orderButton, showButton);

        VBox vb = new VBox(pane);
        Scene scene = new Scene(vb);

        return scene;
    }

    private  void  addCustomerScreen(ActionEvent event) {
        try {

            FlowPane pane = new FlowPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding( new Insets(40));
            pane.setHgap(50);
            pane.setVgap(20);

            Label name = new Label("Menu : ");
            name.setStyle("-fx-line-spacing: 5;");
            TextField customerName = new TextField();

            pane.getChildren().addAll(name, customerName);
            Scene scene = new Scene( new VBox(pane));

            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(scene);
            stage.setHeight(200);
            stage.setWidth(350);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}