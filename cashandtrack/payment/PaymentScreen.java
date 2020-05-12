package cashandtrack.payment;

import cashandtrack.cart.StoreSingleton;
import cashandtrack.customer.CustomerScreen;
import cashandtrack.menu.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.List;

public class PaymentScreen {

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    private static String member =  storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getMember();
    private static String discount = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getDiscount();
    private static double totalCost = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getTotalCost();
    private static double netCost = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).netCost();
    private static List<Menu> order = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getOrder();
    private static TextField receive;

    public static void checkoutScene() throws Exception {
        FlowPane root = new FlowPane();
        VBox vBox =  new VBox();
        HBox hBox = new HBox();
        HBox hBoxReceive = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0));
        root.setPrefWrapLength(350);
        root.setHgap(10.0);
        root.setVgap(30);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));

        String txt = String.format("Total Cost : %.2f", totalCost );
        String txt2 = String.format("Member : %s (%s)", member, discount);
        String txt3 = String.format("Net Cost : %.2f ", netCost);
        Label paymentText = new Label("Check out");
        Label costText = new Label(txt);
        Label memberTxt = new Label(txt2);
        Label netText = new Label(txt3);
        paymentText.setFont( new Font("Arial", 30));

        costText.setFont( new Font("Arial", 15));
        memberTxt.setFont( new Font("Arial", 15));
        netText.setFont( new Font("Arial", 15));
        Label txt4 = new Label("Receive : ");
        receive = new TextField();
        receive.setOnKeyPressed(PaymentScreen::handlerEnter);


        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        hBoxReceive.setAlignment(Pos.CENTER);
        hBoxReceive.getChildren().addAll(txt4, receive);
        hBox.getChildren().addAll(CustomerScreen.getCartTableView());
        vBox.getChildren().addAll(paymentText, hBox, costText, netText, memberTxt, hBoxReceive);

        root.getChildren().addAll(vBox);
        Scene scene = new Scene(new VBox(root));
        Stage stage = new Stage();
        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.setHeight(650);
        stage.setWidth(500);
        stage.show();
    }

    private static void handlerEnter(KeyEvent e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (e.getCode() == KeyCode.ENTER) {
            String receiveText = receive.getText().trim();
            if (receiveText.isEmpty()) {
                alert.setContentText("Please input the receive");
            } else {
                System.out.println(receiveText);
                double receiveValue = Double.parseDouble(receiveText);
                if (netCost > receiveValue) {
                    alert.setContentText("The receive is not enough.");
                    alert.show();
                } else {
                    double change = receiveValue - netCost;
                    String changeTxt = String.format("Change : %.2f  \"Thank you !\"", change);
                    order.clear();
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText(changeTxt);
                    alert.showAndWait();
                }
            }
        }
    }
}