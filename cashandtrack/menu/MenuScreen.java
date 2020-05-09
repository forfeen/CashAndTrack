package cashandtrack.menu;
import cashandtrack.CashAndTrack;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuScreen {

    private TableView<Menu> table = new TableView<>();
    private TextField menuName;
    private TextField menuPrice;
    private ObservableList<Menu> menu;

    public Scene initComponents() {
        ScrollPane sc = new ScrollPane();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0));
        root.setPrefWrapLength(350);
        root.setHgap(10.0);
        root.setVgap(30);

        Label menuText = new Label("Menu");
        menuText.setFont(new Font("Arial" ,20));

        TableColumn nameColumn = new TableColumn("Menu Name");
        TableColumn priceColumn = new TableColumn("Price");

        nameColumn.setPrefWidth(170);
        priceColumn.setPrefWidth(100);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuPrice"));
        menu = getMenuList();
        table.setItems(menu);

        table.getColumns().addAll(nameColumn, priceColumn);
        Button addButton = new Button("Add new menu");
        Button deleteButton = new Button("Delete menu");
        addButton.setOnAction(this::addMenuScreen);
        deleteButton.setOnAction(this::deleteMenu);

        root.getChildren().addAll(menuText,table, addButton, deleteButton);
        sc.setPrefViewportHeight(1000);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sc.setContent(root);
        return new Scene( new VBox(sc));
    }


    private ObservableList<Menu> getMenuList() {
        return FXCollections.observableList(CashAndTrack.getAllMenu());
    }


    private void deleteMenu(ActionEvent event){
        Menu deleteMenu = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(deleteMenu);
        CashAndTrack.allMenu.remove(deleteMenu);
    }


    private void addMenuScreen(ActionEvent event) {
        try {
            FlowPane pane = new FlowPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding( new Insets(40));
            pane.setHgap(50);
            pane.setVgap(20);

            Label txt = new Label("Menu : ");
            txt.setStyle("-fx-line-spacing: 5;");
            menuName = new TextField();

            Label txt2 = new Label("Price : ");
            menuPrice = new TextField();

            Button enterButton = new Button("ENTER");
            enterButton.setOnAction(this::enterHandler);

            pane.getChildren().addAll(txt, menuName, txt2, menuPrice, enterButton);
            Scene scene = new Scene( new VBox(pane));

            Stage stage = new Stage();
            stage.setTitle("Add Menu");
            stage.setScene(scene);
            stage.setWidth(350);
            stage.setHeight(200);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void enterHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String name = menuName.getText().trim();
        String price = menuPrice.getText().trim();
        try {
            if (name.isEmpty() && price.isEmpty()) {
                alert.setContentText("Please input name and price");
                alert.showAndWait();
            }
            if (name.isEmpty() && !price.isEmpty()) {
                alert.setContentText("Please input name.");
                alert.showAndWait();
            }
            if (!name.isEmpty() && price.isEmpty()) {
                alert.setContentText("Please input price");
                alert.showAndWait();
            } else {
                double priceMenu = Double.parseDouble(price);
                Menu newMenu = new Menu(name, priceMenu);
                CashAndTrack.allMenu.add(newMenu);

            }

        } catch (NumberFormatException e) {

        } catch (NullPointerException e) {

        }
    }

}