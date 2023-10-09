package control;

import dao.CurrencyDao;
import entity.Currency;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.ui;

public class DbController {

    MainController main = new MainController();
    CurrencyDao dao = new CurrencyDao();

    // Database window
        @FXML
        TextField currISO, currRate;

        @FXML
        Button dbSubmit;

        @FXML
        private void submitDatabase() {
            String iso = currISO.getText();
            String rate = currRate.getText();

            if(validateIso(iso) && validateNumber(rate)) {
                double rateDouble = Double.parseDouble(rate);
                Currency curr = new Currency(iso , rateDouble);
                if(dao.find(iso) == null) {
                    dao.persist(curr);
                } else {
                    dao.update(curr);
                }
                main.addCurr(curr);
            } else {
                System.out.println("Invalid input");
            }
            ui.closeDb();
        }

        private boolean validateNumber(String input) {
            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean validateIso(String input) {
            if(input.length() == 3) {
                return true;
            } else {
                return false;
            }
        }
}
