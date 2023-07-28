package com.example.datatofile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextFormatter;
import javafx.beans.property.BooleanProperty;

public class CreateController {

    private List<Studenti> studenti;
    private Boolean isCrafted = false;
    private BooleanProperty craftButtonPressed = new SimpleBooleanProperty(false);
    private int quantity = 0;

    String patternOfLetters = "[a-zA-Z\\s]*";
    String patternOfNumbers = "[0-9]*";
    String mediatePattern = "[0-9.]*";

@FXML
    ListView<Studenti> mylist;
@FXML
    Button craftButton;
@FXML
    TextField fieldQuantity;
@FXML
    BorderPane createBorder;


public void initialize(){

    craftButton.setDisable(true);

    fieldQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
        craftButton.setDisable(newValue.trim().isEmpty());
    });

    String quantityPattern = "[0-9]*";

    TextFormatter<String> allQuantityFieldNumbersOnly = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches(quantityPattern)){
            return change;
        }
        return null;
    });

    fieldQuantity.setTextFormatter(allQuantityFieldNumbersOnly);
}

private void scheduleLabelDisappearance(Label label) {

Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
    label.setVisible(false);
    label.setManaged(false);
    }));
    timeline.play();
}

/*This function offers the opportunity to create how many objects from 0/15 the user wants to create...
we set this function to work with only numbers...*/
@FXML
public void craftObjects(ActionEvent e) throws IOException, InterruptedException {

    if (e.getSource().equals(craftButton)) {

    quantity = Integer.parseInt(fieldQuantity.getText());
    Label errorMsg = null;

    if (quantity > 100) {
    errorMsg = new Label("Higher than 100");
    errorMsg.setStyle("-fx-text-fill: white;-fx-font-size: 16");
    createBorder.setCenter(errorMsg);
    scheduleLabelDisappearance(errorMsg);
}
    else if (quantity == 0 ){
        errorMsg = new Label("0 is not a valid quantity");
        errorMsg.setStyle("-fx-text-fill: white;-fx-font-size: 16");
        createBorder.setCenter(errorMsg);
        scheduleLabelDisappearance(errorMsg);
    }
        else {

    isCrafted = true;
    handleButtonPressed(isCrafted);
    fieldQuantity.clear();
    fieldQuantity.setDisable(true);
    craftButton.setDisable(true);

    mylist.setStyle("-fx-pref-width: 200;-fx-control-inner-background: #141414;-fx-control-inner-background-alt: #141414; -fx-text-fill: white;");
    createBorder.setLeft(mylist);

    Studenti st[] = new Studenti[quantity + 1];
    studenti = new ArrayList<Studenti>();

    for (int i = 1; i < quantity + 1; i++) {
        st[i] = new Studenti();
        st[i].setImportantData("Null", "Object");
        studenti.add(st[i]);
    }

    ObservableList<Studenti> studentiList = FXCollections.observableArrayList(studenti);
    mylist.setItems(studentiList);
    mylist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    mylist.setOnMouseClicked(mouseEvent -> {
        Studenti items = mylist.getSelectionModel().getSelectedItem();

    GridPane objectInfo = new GridPane();
    objectInfo.setPrefSize(432, 306);
    objectInfo.setStyle("-fx-background-color: #161616");
    objectInfo.setPadding(new Insets(0, 0, 0, 35));

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(45);
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(45);
    objectInfo.getColumnConstraints().addAll(col1, col2);

    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(15);
    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(15);
    RowConstraints row3 = new RowConstraints();
    row3.setPercentHeight(15);
    RowConstraints row4 = new RowConstraints();
    row4.setPercentHeight(15);
    RowConstraints row5 = new RowConstraints();
    row5.setPercentHeight(15);
    RowConstraints row6 = new RowConstraints();
    RowConstraints row7 = new RowConstraints();
    objectInfo.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);

//Text formatter for making fields to get only numbers or letters...
    TextFormatter<String> allLettersNameTextField = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
            if (newText.matches(patternOfLetters)) {
                return change;
            }
        return null;
    });

    TextFormatter<String> allLettersSpecialityTextField = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
            if (newText.matches(patternOfLetters)){
                return change;
            }
        return null;
    });

    TextFormatter<String> allNumbersPhoneTextField = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(patternOfNumbers)){
                return change;
            }
            return null;
    });

    TextFormatter<String> allNumbersYearTextField = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
            if (newText.matches(patternOfNumbers)){
                return change;
            }
        return null;
    });

    TextFormatter<String> allNumbersMediateTextField = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(mediatePattern)){
                return change;
            }
        return null;
    });

    Label nameLabel = new Label("Name,Surname:");
    nameLabel.setStyle("-fx-text-fill: white");
    TextField nameTextField = new TextField();
    nameTextField.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
    nameTextField.setTextFormatter(allLettersNameTextField);

    Label specialtyLabel = new Label("Specialty:");
    specialtyLabel.setStyle("-fx-text-fill: white");
    TextField specialtyTextField = new TextField();
    specialtyTextField.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
    specialtyTextField.setTextFormatter(allLettersSpecialityTextField);

    Label phoneLabel = new Label("Phone:");
    phoneLabel.setStyle("-fx-text-fill: white");
    TextField phoneTextField = new TextField();
    phoneTextField.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
    phoneTextField.setTextFormatter(allNumbersPhoneTextField);

    Label yearLabel = new Label("Year:");
    yearLabel.setStyle("-fx-text-fill: white");
    TextField yearTextField = new TextField();
    yearTextField.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
    yearTextField.setTextFormatter(allNumbersYearTextField);

    Label mediateLabel = new Label("Mediate:");
    mediateLabel.setStyle("-fx-text-fill: white");
    TextField mediateTextField = new TextField();
    mediateTextField.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
    mediateTextField.setTextFormatter(allNumbersMediateTextField);

    Region region = new Region();
    region.setPrefHeight(22);

    Button updateButton = new Button("Update");
    updateButton.setStyle("-fx-background-color: Black;-fx-text-fill: white");

    updateButton.setPrefWidth(Double.MAX_VALUE);
    updateButton.setPrefHeight(35);

    BooleanBinding allTextFieldsEmpty = nameTextField.textProperty().isEmpty()
        .or(specialtyTextField.textProperty().isEmpty())
        .or(phoneTextField.textProperty().isEmpty())
        .or(yearTextField.textProperty().isEmpty())
        .or(mediateTextField.textProperty().isEmpty());

    updateButton.disableProperty().bind(allTextFieldsEmpty);

    GridPane.setConstraints(nameLabel, 0, 0);
    GridPane.setConstraints(nameTextField, 1, 0);
    GridPane.setConstraints(specialtyLabel, 0, 1);
    GridPane.setConstraints(specialtyTextField, 1, 1);
    GridPane.setConstraints(phoneLabel, 0, 2);
    GridPane.setConstraints(phoneTextField, 1, 2);
    GridPane.setConstraints(yearLabel, 0, 3);
    GridPane.setConstraints(yearTextField, 1, 3);
    GridPane.setConstraints(mediateLabel, 0, 4);
    GridPane.setConstraints(mediateTextField, 1, 4);
    GridPane.setConstraints(region,1,5);
    GridPane.setConstraints(updateButton, 1, 6);

    objectInfo.getChildren().addAll(
            nameLabel, nameTextField, specialtyLabel, specialtyTextField,
            phoneLabel, phoneTextField, yearLabel, yearTextField,
            mediateLabel, mediateTextField, updateButton
    );

    createBorder.setCenter(objectInfo);

    updateButton.setOnAction(event -> {

    String fullName = nameTextField.getText();
    String speciality = specialtyTextField.getText();
    int phoneNumber = Integer.parseInt(phoneTextField.getText());
    int yearsOfStudy = Integer.parseInt(yearTextField.getText());
    double mediate = Double.parseDouble(mediateTextField.getText());

    String[] nameParts = fullName.split("\\s+");
    String firstName = nameParts[0];
    String lastName = nameParts[nameParts.length - 1];

    items.setNume(firstName);
    items.setPrenume(lastName);
    items.setSpecialitatea(speciality);
    items.setTelefon(phoneNumber);
    items.setAn_studiu(yearsOfStudy);
    items.setMedia(mediate);

    mylist.refresh();

        });
    });
            }
        } else {
            System.out.println("Error while getting the quantity!!!");
    }
}

private void handleButtonPressed(Boolean crafted) {
        craftButtonPressed.set(crafted);
}

public BooleanProperty craftButtonPressed() {
    return craftButtonPressed;
}

public ListView<Studenti> getList(){
    return mylist;
    }
}
