package cashandtrack;
import cashandtrack.screen.CustomerScreen;
import cashandtrack.screen.MenuScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.*;

/** The application to improve the performance
 * of the ordering, billing order and billing payment.*/
public class CashAndTrack extends Application{

    /** create the stage */
   public static Stage stage = new Stage();

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();

    /** the main stage of the application */
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

    /** to handle when clicked Customer button*/
    private void handlerCustomer(ActionEvent event) {
        stage.setTitle("Customer");
        stage.setWidth(400);
        stage.setHeight(580);
        stage.setScene( new CustomerScreen().initComponents());
    }

    /** to handle when clicked menu button*/
    private void handlerMenu(ActionEvent event) {
        stage.setTitle("Menu");
        stage.setHeight(570);
        stage.setScene( new MenuScreen().initComponents());
    }

    /** to show the stage */
    public void start(Stage stage) throws Exception {
        componentsMainStage().show();
        componentsMainStage().setOnCloseRequest(this::writeData);
    }

    private void writeData(WindowEvent windowEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String separator = ",";
        File customerFile = new File("txt/customer.csv");
        try {
            BufferedWriter customerWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(customerFile)));
            customerWriter.write("#Customer");
            customerWriter.newLine();
            for (Customer customer : storeSingleton.getAllCustomer()) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(customer.getCustomerName());
                oneLine.append(separator);
                oneLine.append(customer.getMember());
                oneLine.append(separator);
                oneLine.append(customer.getTotalCost());
                customerWriter.write(oneLine.toString());
                customerWriter.newLine();
            }
            customerWriter.close();

            File menuFile = new File("txt/menu.csv");
            BufferedWriter menuWriter = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(menuFile)));
            menuWriter.write("#Menu and Price");
            menuWriter.newLine();
            for (Menu menu : storeSingleton.getAllMenu()) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(menu.getMenuName());
                oneLine.append(separator);
                oneLine.append(menu.getMenuPrice());
                menuWriter.write(oneLine.toString());
                menuWriter.newLine();
            }
            menuWriter.close();
        } catch (IOException e) {
            alert.setContentText("Error");
            alert.show();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
