package com.example.datatofile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Collections;

public class LoadController {

@FXML
    Button loadButton;
@FXML
    TextField fileName;
@FXML
    Button pathSelector;
@FXML
    ImageView fileImage;
@FXML
    BorderPane loadBorder;
@FXML
    ListView <Studenti> loadList;
@FXML
    ImageView selected;


public void initialize(){
    loadList.setFocusTraversable(false);
    loadList.getSelectionModel().setSelectionMode(null);
    fileName.setEditable(false);
    loadButton.setDisable(true);
}

private void scheduleLabelDisappearance(Label label) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
        label.setVisible(false);
        label.setManaged(false);
    }));
    timeline.play();
}

public static void writeToFile(ObservableList<Studenti> list, String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
        for (Studenti item : list) {
            writer.write("Object...");
            writer.newLine();
            writer.write("Name/Surname: "+ item.getNume()+ " "+ item.getPrenume());
            writer.newLine();
            writer.write("Speciality: " + item.getSpecialitatea());
            writer.newLine();
            writer.write("Number: ");
            writer.write(String.valueOf(item.getTelefon()));
            writer.newLine();
            writer.write("Year: ");
            writer.write(String.valueOf(item.getAn_studiu()));
            writer.newLine();
            writer.write("Mediate: ");
            writer.write(String.valueOf(item.getMedia()));
                writer.newLine();
                writer.newLine();
        }
    } catch (IOException e) {
            e.printStackTrace();
    }
}

@FXML
public void createSelectData(){

    ObservableList<Studenti> loadItems = loadList.getItems();

    FileChooser file = new FileChooser();
    file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt File","*txt"));
    file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json File","*json"));
    Stage opeStage = new Stage();
    File openFile = file.showOpenDialog(opeStage);
    fileName.setText(String.valueOf(openFile));

    if(openFile != null){
        changeImage(selected);
        loadButton.setDisable(false);
    }else{
        fileName.setText(" ");
    }

    loadButton.setOnAction(event1 -> {
        writeToFile(loadItems, String.valueOf(openFile));
        showWorkDone();
        loadButton.setDisable(true);
    });
}

public void changeImage(ImageView name){
    Image newImage = new Image("com/example/datatofile/selectedPathImage.png");
    name.setImage(newImage);

}

public void showWorkDone(){
    Label doneMsg = new Label("Objects are loaded");
    doneMsg.setStyle("-fx-text-fill: white;-fx-font-size: 15");
        loadBorder.setCenter(doneMsg);
        scheduleLabelDisappearance(doneMsg);
}

public void setList(ListView<Studenti> ListItems){
    ObservableList<Studenti> items = ListItems.getItems();
    Collections.sort(items,new Compare());
    loadList.setItems(items);
    }
}
