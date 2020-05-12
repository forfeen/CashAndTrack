package cashandtrack.cart;

import cashandtrack.customer.CustomerScreen;
import cashandtrack.menu.Menu;
import cashandtrack.menu.MenuScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CartScreen {

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();

    public static void addCartScene() throws Exception {
        FlowPane pane = new FlowPane();
        VBox vbButtons = new VBox();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);

        Button addButton = new Button("<------  Add to cart");

        addButton.setStyle("-fx-focus-color: white;");
        addButton.setMaxWidth(Double.MAX_VALUE);

        Button deleteButton = new Button("Delete order ------->");
        deleteButton.setMaxWidth(Double.MAX_VALUE);

        addButton.setOnAction( e->{
            Menu addMenu = MenuScreen.getMenuTableView().getSelectionModel().getSelectedItem();
            storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getOrder().add(addMenu);
        });

        deleteButton.setOnAction( event -> {
            Menu deleteMenu = MenuScreen.getMenuTableView().getSelectionModel().getSelectedItem();
            storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getOrder().remove(deleteMenu);
        });
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(addButton,deleteButton);
        vbButtons.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(CustomerScreen.getCartTableView(), vbButtons,MenuScreen.getMenuTableView() );
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(850);
        stage.setHeight(450);
        stage.setTitle("Add to cart");
        stage.show();
    }
}