package com.example.datatofile;
import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.IOException;
import javafx.scene.control.ListView;
import javafx.beans.property.BooleanProperty;
import javafx.stage.Stage;

public class Controller {

    private Parent root;
    public ListView<Studenti> transferItems;
    public ListView<Studenti> transferToLoad;
    private Stage decisionStage;

    Boolean checkCraft = false;
    Boolean enableLoad = true;
    Boolean enableModify = true;

@FXML
    Button minimizeButton;
@FXML
    Button closeButton;
@FXML
    BorderPane border;
@FXML
    Button createButton;
@FXML
    Button mainButton;
@FXML
    Button modifyButton;
@FXML
    Button loadButton;

public void initialize(){
    modifyButton.setDisable(true);
    loadButton.setDisable(true);
}

//This is a function for main scene,acutally this is a vbox with centered information about this app...
@FXML
public void mainScene() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
    root = loader.load();
    border.setCenter(root);

    modifyButton.disableProperty().unbind();
    mainButton.setDisable(true);
    createButton.setDisable(checkCraft);
    modifyButton.setDisable(enableModify);
    loadButton.setDisable(enableLoad);
}

//This is a function for create scene ,being Vbox where user will create objects with data...
@FXML
public void createObjects() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("create-view.fxml"));
    root = loader.load();
    border.setCenter(root);

    CreateController createController = loader.getController();
    transferItems = createController.getList();

    BooleanProperty craftButtonProperty = createController.craftButtonPressed();
    BooleanBinding modifyButtonDisableBinding = craftButtonProperty.not();
    modifyButton.disableProperty().bind(modifyButtonDisableBinding);

    createButton.setDisable(true);
    mainButton.setDisable(false);
    loadButton.setDisable(true);
}

//This is a function for the modify scene where the user will see all created data...
@FXML
public void modifyData() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-view.fxml"));
    root = loader.load();
    border.setCenter(root);

    checkCraft = true;
    enableModify = false;
    enableLoad = false;

    ModifyController modifyController = loader.getController();
    modifyController.setList(transferItems);
    transferToLoad = modifyController.getList();

    modifyButton.disableProperty().unbind();

    modifyButton.setDisable(true);
    mainButton.setDisable(false);
    createButton.setDisable(true);
    loadButton.setDisable(false);
}

//This is the last function where user can load all created objects to a txt file...
@FXML
public void loadData() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("load-view.fxml"));
    root = loader.load();
    border.setCenter(root);

    LoadController loadController = loader.getController();
    loadController.setList(transferToLoad);

    modifyButton.setDisable(false);
    mainButton.setDisable(false);
    createButton.setDisable(true);
    loadButton.setDisable(true);
    }
}
