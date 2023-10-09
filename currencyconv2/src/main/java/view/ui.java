package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ui extends Application {
    
    private static Dialog<String> noteDialog = new Dialog<>();
    private static Stage dbStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        dbStage = new Stage();
        dbStage.setTitle("Add currency");
        dbStage.setScene(new Scene(FXMLLoader.load(ui.class.getClassLoader().getResource("dbgui.fxml"))));
        dbStage.initModality(Modality.APPLICATION_MODAL);
    }

    public static void openDb() {
        dbStage.showAndWait();
    }

    public static void closeDb() {
        dbStage.close();
    }

    public static void openInfo() {
        noteDialog.setTitle("Info");
        noteDialog.setContentText(
        "This is a currency converter."
        +"\n\nYou can add currencies manually."
        + "\nAdding currencies from an API is not yet implemented."
        + "\nThe conversion is done by multiplying the amount with the rate of the currency you want to convert from and dividing it by the rate of the currency you want to convert to."
        + "\nThe initial table is retrieved from the National Bank of Poland API.");
        noteDialog.getDialogPane().getButtonTypes().clear();
        noteDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        noteDialog.showAndWait();
    }

}
