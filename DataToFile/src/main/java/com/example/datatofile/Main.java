package com.example.datatofile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;

public class Main extends Application {

    private double xOffset;
    private double yOffset;

private void onMousePressed(MouseEvent event) {
    xOffset = event.getSceneX();
    yOffset = event.getSceneY();
}

private void onMouseDragged(MouseEvent event) {
    Stage stage = (Stage) ((Scene) event.getSource()).getWindow();
    stage.setX(event.getScreenX() - xOffset);
    stage.setY(event.getScreenY() - yOffset);
}

@Override
public void start(Stage primaryStage) throws Exception {

Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
Scene scene = new Scene(root,870,510);
scene.setOnMousePressed(this::onMousePressed);
scene.setOnMouseDragged(this::onMouseDragged);
root.getStylesheets().add((getClass().getResource("appStyle.css")).toExternalForm());
root.setStyle("-fx-background-color:#161616");

    Button minimizeButton = (Button) root.lookup("#minimizeButton");
    Button closeButton = (Button) root.lookup("#closeButton");

    minimizeButton.setOnAction(event -> {
    primaryStage.setIconified(true);
});

    closeButton.setOnAction(event -> {
    primaryStage.close();
});

Image icon = new Image("com/example/datatofile/objldIco.png");

    primaryStage.getIcons().add(icon);
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setResizable(false);
    primaryStage.setOpacity(0.98);
    primaryStage.setTitle("Object Loader");
    primaryStage.setScene(scene);
    primaryStage.show();

}
    public static void main(String[] args) {
    launch(args);
    }
}
