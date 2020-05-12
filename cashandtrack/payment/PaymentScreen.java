package cashandtrack.payment;

import cashandtrack.cart.StoreSingleton;
import cashandtrack.customer.CustomerScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PaymentScreen {

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    private static String member =  storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getMember();
    private static String discount = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getDiscount();

    private static double totalCost = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getTotalCost();
    private static double netCost = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).netCost();

    public static void checkoutScene() throws Exception {
        FlowPane root = new FlowPane();
        VBox vBox =  new VBox();
        HBox hBox = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0));
        root.setPrefWrapLength(350);
        root.setHgap(10.0);
        root.setVgap(30);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(10);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(20));
        Label paymentText = new Label("Check out");
        paymentText.setFont( new Font("Arial", 30));
        String txt = String.format("Total Cost : %.2f", totalCost );
        String txt2 = String.format("Member : %s (%s)", member, discount);
        String txt3 = String.format("Net Cost : %.2f ", netCost);
        Label costText = new Label(txt);
        Label memberTxt = new Label(txt2);
        Label netText = new Label(txt3);
        costText.setFont( new Font("Arial", 15));
        memberTxt.setFont( new Font("Arial", 15));
        netText.setFont( new Font("Arial", 15));
        Button checkout = new Button("Check Out");

        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(CustomerScreen.getCartTableView());
        vBox.getChildren().addAll(paymentText, hBox, costText, netText, memberTxt, checkout);

        root.getChildren().addAll(vBox);
        Scene scene = new Scene(new VBox(root));
        Stage stage = new Stage();
        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.setHeight(650);
        stage.setWidth(500);
        stage.show();

    }
}