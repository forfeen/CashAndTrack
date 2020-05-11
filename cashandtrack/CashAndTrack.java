package cashandtrack;

import cashandtrack.customer.*;
import cashandtrack.menu.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class CashAndTrack extends Application{

   public static List<Customer> allCustomer = new ArrayList<>();
   public static List<Menu> allMenu = new ArrayList<Menu>();
   public static Stage stage = new Stage();


    public Stage componentsMainStage() throws Exception {
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding( new Insets(55));
        root.setHgap(50);

        Image menuPic = new Image("/images/menu.png");
        ImageView menuC = new ImageView();
        menuC.setImage(menuPic);
        menuC.setFitWidth(100);
        menuC.setFitHeight(100);

        Image customerPic = new Image("/images/people.png");
        ImageView customerC = new ImageView();
        customerC.setImage(customerPic);
        customerC.setFitHeight(100);
        customerC.setFitWidth(100);

        Button menu = new Button("", menuC);
        menu.setStyle("-fx-focus-color: white;");
        menu.setOnAction(this::handlerMenu);

        Button customer = new Button("", customerC);
        customer.setStyle("-fx-focus-color: white;");
        customer.setOnAction(this::handlerCustomer);

        root.getChildren().addAll(menu, customer);
        Scene scene = new Scene(new VBox(root));
        stage.setScene(scene);
        stage.setHeight(250);
        stage.setWidth(400);
        stage.show();
        stage.setTitle("Cash & Track");
        return stage;
    }

    private void handlerCustomer (ActionEvent event) {
        stage.setWidth(440);
        stage.setHeight(580);
        stage.setScene( new CustomerScreen().initComponents());
    }

    private void handlerMenu(ActionEvent event) {
        stage.setHeight(570);
        stage.setScene( new MenuScreen().initComponents());
    }

    public void start(Stage stage) throws Exception {
        componentsMainStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
