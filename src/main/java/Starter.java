import model.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Item;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();

        Customer customer = new Customer();
        new Customer("001","saman","panadura",75000.0);

        Item item = new Item();
        new Item("001","saman",570.0,75);
    }

    @Override
    public void start(Stage stage) throws Exception {

//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer.fxml"))));
//        stage.show();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item.fxml"))));
        stage.show();
    }
}
