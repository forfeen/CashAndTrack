package cashandtrack.screen;
import cashandtrack.StoreSingleton;
import cashandtrack.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**The stage of cart */
public class CartScreen {

    /** create an object of StoreSingleton */
    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();

    /** the stage of cart scene */
    public static void addCartScene() throws Exception {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);

        Button addButton = new Button("<------  Add to cart");
        addButton.setStyle("-fx-focus-color: white;");
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction( e->{
            Menu addMenu = MenuScreen.getMenuTableView().getSelectionModel().getSelectedItem();
            storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getOrder().add(addMenu);
        });

        Button deleteButton = new Button("Delete order ------->");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setOnAction( event -> {
            Menu deleteMenu = MenuScreen.getMenuTableView().getSelectionModel().getSelectedItem();
            storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getOrder().remove(deleteMenu);
        });

        VBox vbButtons = new VBox();
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