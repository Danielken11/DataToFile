package com.example.datatofile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.TextFormatter;

public class ModifyController {

    private String nameObject;
    private String surnameObject;
    private String fullnameObject;
    private String specialityObject;
    private int numberObject;
    private int yearObject;
    private double mediateObject;
    private Stage decisionStage;

    private Boolean wasChanged = false;

    String patternOfLetters = "[a-zA-Z\\s]*";
    String patternOfNumbers = "[0-9]*";
    String mediatePattern = "[0-9.]*";

@FXML
    ListView<Studenti> modifyList;
@FXML
    BorderPane modifyBorder;
@FXML
    Button changeButton;
@FXML
    Button deleteButton;

private void scheduleLabelDisappearance(Label label) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
    label.setVisible(false);
    label.setManaged(false);
    }));
        timeline.play();
}

public void initialize(){
    modifyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    changeButton.setDisable(true);
    deleteButton.setDisable(true);

    modifyList.setOnMouseClicked(mouseEvent -> {
        Studenti items = modifyList.getSelectionModel().getSelectedItem();

if(items != null){
    changeButton.setDisable(false);
    deleteButton.setDisable(false);
    GridPane gridPane = new GridPane();
    gridPane.setStyle("-fx-background-color: #161616");
    gridPane.setPadding(new Insets(0, 0, 0, 50));

    Label nameLabel = new Label("Name,Surname:");
    nameLabel.setStyle("-fx-text-fill: white");
    gridPane.add(nameLabel, 0, 0);

    TextField nameInfoLabel = new TextField();
    nameInfoLabel.setStyle("-fx-text-fill: white;-fx-background-color: black");
    gridPane.add(nameInfoLabel, 0, 1);

    Label specialtyLabel = new Label("Specialty:");
    specialtyLabel.setStyle("-fx-text-fill: white;-fx-background-color: black");
    gridPane.add(specialtyLabel, 0, 2);

    TextField specialtyInfoLabel = new TextField();
    specialtyInfoLabel.setStyle("-fx-text-fill: white;-fx-background-color: black");
    gridPane.add(specialtyInfoLabel, 0, 3);

    Label phoneLabel = new Label("Phone:");
    phoneLabel.setStyle("-fx-text-fill: white");
    gridPane.add(phoneLabel, 0, 4);

    TextField phoneInfoLabel = new TextField();
    phoneInfoLabel.setStyle("-fx-text-fill: white;-fx-background-color: black");
    gridPane.add(phoneInfoLabel, 0, 5);

    Label yearLabel = new Label("Year:");
    yearLabel.setStyle("-fx-text-fill: white");
    gridPane.add(yearLabel, 0, 6);

    TextField yearInfoLabel = new TextField();
    yearInfoLabel.setStyle("-fx-text-fill: white;-fx-background-color: black");
    gridPane.add(yearInfoLabel, 0, 7);

    Label mediateLabel = new Label("Mediate:");
    mediateLabel.setStyle("-fx-text-fill: white");
    gridPane.add(mediateLabel, 0, 8);

    TextField mediateInfoLabel = new TextField();
    mediateInfoLabel.setStyle("-fx-text-fill: white;-fx-background-color: black");
    gridPane.add(mediateInfoLabel, 0, 9);

    Button modifyButton = new Button("Modify");
    modifyButton.setPrefSize(99, 34);
    modifyButton.setStyle("-fx-background-color: black; -fx-text-fill: white");
    gridPane.add(modifyButton, 0, 10);

    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setPercentWidth(70);
    gridPane.getColumnConstraints().add(columnConstraints);

    for (int i = 0; i < 11; i++) {
        RowConstraints rowConstraints = new RowConstraints();
            if (i == 10) {
                rowConstraints.setPercentHeight(15);
            } else {
                rowConstraints.setPercentHeight(10);
            }
            gridPane.getRowConstraints().add(rowConstraints);
    }

    TextFormatter<String> allLettersNameTextField = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches(patternOfLetters)) {
            return change;
        }
        return null;
    });

    TextFormatter<String> allLettersSpecialityTextField = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches(patternOfLetters)) {
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

    nameInfoLabel.setTextFormatter(allLettersNameTextField);
    specialtyInfoLabel.setTextFormatter(allLettersSpecialityTextField);
    phoneInfoLabel.setTextFormatter(allNumbersPhoneTextField);
    yearInfoLabel.setTextFormatter(allNumbersYearTextField);
    mediateInfoLabel.setTextFormatter(allNumbersMediateTextField);

    nameObject = items.getNume();
    surnameObject = items.getPrenume();
    fullnameObject = nameObject + " " + surnameObject;
    specialityObject = items.getSpecialitatea();
    numberObject = items.getTelefon();
    yearObject = items.getAn_studiu();
    mediateObject = items.getMedia();

    nameInfoLabel.setText(fullnameObject);
    specialtyInfoLabel.setText(specialityObject);
    phoneInfoLabel.setText(String.valueOf(numberObject));
    yearInfoLabel.setText(String.valueOf(yearObject));
    mediateInfoLabel.setText(String.valueOf(mediateObject));

    nameInfoLabel.setEditable(false);
    specialtyInfoLabel.setEditable(false);
    phoneInfoLabel.setEditable(false);
    yearInfoLabel.setEditable(false);
    mediateInfoLabel.setEditable(false);

    modifyBorder.setCenter(gridPane);
    modifyButton.setDisable(true);

    changeButton.setOnAction(event -> {
        nameInfoLabel.setEditable(true);
        specialtyInfoLabel.setEditable(true);
        phoneInfoLabel.setEditable(true);
        yearInfoLabel.setEditable(true);
        mediateInfoLabel.setEditable(true);
        modifyButton.setDisable(false);
    });

    modifyButton.setOnAction(event -> {

        String fulLName = nameInfoLabel.getText();
        String speciality = specialtyInfoLabel.getText();
        int phoneNumber = Integer.parseInt(phoneInfoLabel.getText());
        int yearsOfStudy = Integer.parseInt(yearInfoLabel.getText());
        double mediate = Double.parseDouble(mediateInfoLabel.getText());

        String[] nameParts = fulLName.split("\\s+");
        String firstName = nameParts[0];
        String lastName = nameParts[nameParts.length - 1];

        items.setNume(firstName);
        items.setPrenume(lastName);
        items.setSpecialitatea(speciality);
        items.setTelefon(phoneNumber);
        items.setAn_studiu(yearsOfStudy);
        items.setMedia(mediate);

        nameObject = items.getNume();
        surnameObject = items.getPrenume();
        fullnameObject = nameObject + " " + surnameObject;
        speciality = items.getSpecialitatea();
        numberObject = items.getTelefon();
        yearObject = items.getAn_studiu();
        mediate = items.getMedia();

        nameInfoLabel.setText(fullnameObject);
        specialtyInfoLabel.setText(speciality);
        phoneInfoLabel.setText(String.valueOf(numberObject));
        yearInfoLabel.setText(String.valueOf(yearObject));
        mediateInfoLabel.setText(String.valueOf(mediate));

        modifyButton.setDisable(true);

            modifyList.refresh();
    });

    deleteButton.setOnAction(event -> {

        deleteButton.setDisable(true);
        decisionStage = new Stage();
        BorderPane decisionPane = new BorderPane();
        Label infoMsg = new Label("Are you sure?");
        infoMsg.setStyle("-fx-font-size: 18;-fx-text-fill: white");

        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.setPrefHeight(96.0);
        hbox.setPrefWidth(400.0);

        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(0, 20, 0, 20));

            Button yes = new Button("Destroy");
            yes.setPrefHeight(31.0);
            yes.setPrefWidth(84.0);
            yes.setStyle("-fx-background-color: black; -fx-text-fill: white");
            yes.setOnMouseEntered(mouseEvent1 -> {
                yes.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
            });
            yes.setOnMouseExited(mouseEvent1 -> {
                yes.setStyle("-fx-border-color: black");
                yes.setStyle("-fx-background-color: black;-fx-text-fill: white");
            });

            Button cancel = new Button("Cancel");
            cancel.setPrefHeight(31.0);
            cancel.setPrefWidth(84.0);
            cancel.setStyle("-fx-background-color: black; -fx-text-fill: white");
            cancel.setOnMouseEntered(mouseEvent1 -> {
                cancel.setStyle("-fx-background-color: #7C797D;-fx-text-fill: white");
            });
            cancel.setOnMouseExited(mouseEvent1 -> {
                cancel.setStyle("-fx-border-color:black");
                cancel.setStyle("-fx-background-color: black;-fx-text-fill: white");
            });

        stackPane.getChildren().add(yes);
        hbox.getChildren().addAll(stackPane, cancel);
        decisionPane.setCenter(infoMsg);
        decisionPane.setBottom(hbox);

        decisionPane.setStyle("-fx-background-color: black");
        Scene scene = new Scene(decisionPane,400,200);
        decisionStage.initStyle(StageStyle.UNDECORATED);
        decisionStage.setResizable(false);
        decisionStage.setOpacity(0.99);
        decisionStage.setScene(scene);
        decisionStage.show();

        cancel.setOnAction(event1 -> {
            deleteButton.setDisable(false);
            decisionStage.close();
        });

        yes.setOnAction(event1 -> {
            decisionStage.close();
            Studenti selectedItem = modifyList.getSelectionModel().getSelectedItem();
            modifyList.getItems().remove(selectedItem);
            modifyList.refresh();
            gridPane.setVisible(false);
        });
    });
        }else{
            Label msg = new Label("Create Objects first");
            msg.setStyle("-fx-text-fill: White;-fx-font-size: 16");
            modifyBorder.setCenter(msg);
            scheduleLabelDisappearance(msg);
        }
    });
}

public ListView<Studenti> getList(){
        return modifyList;
    }


public void setList(ListView<Studenti> ListItems){
    ObservableList<Studenti> items = ListItems.getItems();
    modifyList.setItems(items);
    }
}
