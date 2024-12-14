package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test extends Application {
    private TomasuloAlgorithmLogic tomasulo = new TomasuloAlgorithmLogic();  // Assume this is your backend logic class

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        initializeUI(root);
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Tomasulo Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeUI(Pane root) {
        // Dummy data, replace with actual method calls to get real data
        ArrayList<ReservationStationE> stations = new ArrayList<>();
        stations.add(new ReservationStationE("RS1"));
        stations.get(0).setOp(InstructionType.ADD_D);  // Dummy operation type
        stations.get(0).setBusy(true);
        stations.get(0).setVj(5);
        stations.get(0).setVk(3);
        stations.get(0).setQj("Q1");
        stations.get(0).setQk("Q2");

        drawReservationStations(root, stations, 100, 100);  // Draw at position (100, 100)
    }

    private void drawReservationStations(Pane pane, ArrayList<ReservationStationE> stations, double x, double y) {
        double spacingY = 100;
        for (int i = 0; i < stations.size(); i++) {
            ReservationStationE station = stations.get(i);
            double posY = y + i * spacingY;

            Rectangle rect = new Rectangle(x, posY, 200, 90);
            rect.setStroke(javafx.scene.paint.Color.BLACK);
            rect.setFill(javafx.scene.paint.Color.LIGHTGRAY);
            pane.getChildren().add(rect);

            Text text = new Text(x + 5, posY + 20, "Tag: " + station.getTag() + ", Op: " + station.getOp() +
                "\nBusy: " + station.isBusy() + ", Vj: " + station.getVj() + ", Vk: " + station.getVk() +
                "\nQj: " + station.getQj() + ", Qk: " + station.getQk());
            pane.getChildren().add(text);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
