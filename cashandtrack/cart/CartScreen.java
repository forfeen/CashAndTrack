package cashandtrack.cart;

import cashandtrack.customer.CustomerScreen;
import cashandtrack.menu.Menu;
import cashandtrack.menu.MenuScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class CartScreen {

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();

    public static void addCartScene() throws Exception {
        ScrollPane pane = new ScrollPane();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0));
        root.setPrefWrapLength(980);
        root.setVgap(30);
        root.setHgap(40);
        Label cartText = new Label("Add to cart");
        cartText.setFont( new Font("Arial", 20));
        cartText.setAlignment(Pos.TOP_CENTER);

        Button addButton = new Button("Add to cart");
        Button deleteButton = new Button("Delete order");
        int index = storeSingleton.getAllCustomer().indexOf(CustomerScreen.getCustomerObj());
        addButton.setOnAction( e->{
            Menu addMenu = MenuScreen.getTableMenu().getSelectionModel().getSelectedItem();
            storeSingleton.getAllCustomer().get(index).getOrder().add(addMenu);
        });

        deleteButton.setOnAction( event -> {
            Menu deleteMenu = CustomerScreen.getCartTableView().getSelectionModel().getSelectedItem();
            storeSingleton.getAllCustomer().get(index).getOrder().remove(deleteMenu);
        });

        root.getChildren().addAll(cartText, CustomerScreen.showCartTable(), deleteButton,addButton, MenuScreen.showTableMenu() );
        pane.setPrefViewportHeight(1000);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setContent(root);
        Scene scene = new Scene( new VBox(pane));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }

}