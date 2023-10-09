package control;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import view.ui;
import dao.CurrencyDao;
import entity.Currency;

public class MainController {

    CurrencyDao dao = new CurrencyDao();

    // Main window
        @FXML
        TextField inputFrom, inputTo;
        @FXML
        ComboBox<String> fromISO;
        @FXML
        ComboBox<String> toISO;
        @FXML
        Button submit, info, addCurr, addFromApi;

        @FXML
        private void convert() {
            if(fromISO.getValue() == null || toISO.getValue() == null) {
                System.out.println("No currency selected");
            } else if(validateNumber(inputFrom.getText())) {
                double amount = Double.parseDouble(inputFrom.getText());
                String from = fromISO.getValue();
                String to = toISO.getValue();

                // Conversion
                double fromRate = dao.find(from).getRate();
                double toRate = dao.find(to).getRate();
                double result = Math.round((amount/fromRate)*toRate*100.0)/100.0;

                inputTo.setText(String.valueOf(result));
            } else {
                System.out.println("Invalid input");
            }
        }

        @FXML
        private void addCurrApi() {
            System.out.println("Add currency");
        }

        @FXML
        private void openInfo() {
            ui.openInfo();
        }

        @FXML
        private void addCurrManual() throws IOException {
            ui.openDb();
        }

        // Init
        @FXML
        void initialize() {
            updateCurrList();
            System.out.println("Init");
        }

        private boolean validateNumber(String input) {
            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public void addCurr(Currency currency) {
            fromISO.getItems().add(currency.getCode());
            toISO.getItems().add(currency.getCode());
        }

        private void updateCurrList() {
            List<Currency> currList = dao.findAll();
            fromISO.getItems().clear();
            toISO.getItems().clear();
            String[] currCodes = new String[currList.size()];
            for(Currency curr : currList) {
                currCodes[currList.indexOf(curr)] = curr.getCode();
            }
            fromISO.getItems().addAll(currCodes);
            toISO.getItems().addAll(currCodes);
        }
}
