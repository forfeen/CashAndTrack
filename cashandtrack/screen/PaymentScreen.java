package cashandtrack.screen;
import cashandtrack.StoreSingleton;
import cashandtrack.Menu;
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

/** The stage of Payment feature*/
public class PaymentScreen {

    /** create an object of StoreSingleton */
    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    /** the membership levels of customer */
    private static String member;
    /** the discount message */
    private static String discount;
    /** the total cost of order */
    private static double totalCost;
    /** the net cost after discount */
    private static double netCost;
    /** the list of order */
    private static List<Menu> order;
   /** the text field of receive money */
    private static TextField receive;

    /** the check out scene */
    public static void checkoutScene() throws Exception {
        totalCost = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getTotalCost();
        netCost = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).netCost();
        discount = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getDiscount();
        member =  storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getMember();

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0));
        root.setPrefWrapLength(350);
        root.setHgap(10.0);
        root.setVgap(30);

        VBox vBox =  new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        HBox hBoxTable = new HBox();
        hBoxTable.setSpacing(10);
        hBoxTable.setPadding(new Insets(10));

        HBox hBoxReceive = new HBox();
        hBoxReceive.setAlignment(Pos.CENTER);

        String totalTexT = String.format("Total Cost : %.2f", totalCost );
        Label costLabel = new Label(totalTexT);
        costLabel.setFont( new Font("Arial", 15));

        String memberText = String.format("Member : %s (%s)", member, discount);
        Label memberLabel = new Label(memberText);
        memberLabel.setFont( new Font("Arial", 15));

        String netCostText = String.format("Net Cost : %.2f ", netCost);
        Label netLabel = new Label(netCostText);
        netLabel.setFont( new Font("Arial", 15));

        Label paymentText = new Label("Check out");
        paymentText.setFont( new Font("Arial", 30));

        Label receiveLabel = new Label("Receive : ");
        receive = new TextField();
        receive.setOnKeyPressed(PaymentScreen::handlerEnter);

        vBox.setAlignment(Pos.CENTER);
        hBoxTable.setAlignment(Pos.CENTER);

        hBoxReceive.getChildren().addAll(receiveLabel, receive);
        hBoxTable.getChildren().addAll(CustomerScreen.getCartTableView());
        vBox.getChildren().addAll(paymentText, hBoxTable, costLabel, netLabel, memberLabel, hBoxReceive);

        root.getChildren().addAll(vBox);
        Scene scene = new Scene(new VBox(root));
        Stage stage = new Stage();
        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.setHeight(650);
        stage.setWidth(500);
        stage.show();
    }

    /** the handle when press ENTER key */
    private static void handlerEnter(KeyEvent e) {
        order = storeSingleton.getAllCustomer().get(CustomerScreen.getIndexCustomer()).getOrder();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (e.getCode() == KeyCode.ENTER) {
            String receiveText = receive.getText().trim();
            if (receiveText.isEmpty()) {
                alert.setContentText("Please input the receive");
            }  else {
                double receiveValue = Double.parseDouble(receiveText);
                if (netCost > receiveValue) {
                    alert.setContentText("The receive is not enough.");
                    alert.show();
                } if (receiveValue > netCost) {
                    double change = receiveValue - netCost;
                    String changeTxt = String.format("Change : %.2f  Thank you !", change);
                    order.clear();
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText(changeTxt);
                    alert.showAndWait();
                }
            }
        }
    }
}