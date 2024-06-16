package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestTaskController {
    @FXML
    public Label commission;
    @FXML
    public TextField total;
    @FXML
    public TextField percent;
    @FXML
    public TextField fix;
    @FXML
    public Label feedback;

    @FXML
    public void onCountButtonClick(ActionEvent actionEvent) {
        commission.setText("Значение комиссии: ");
      try {
            BigDecimal total = new BigDecimal(this.total.getText());
            BigDecimal percent = (new BigDecimal(this.percent.getText())).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
            BigDecimal fix = new BigDecimal(this.fix.getText());

            if(total.compareTo(BigDecimal.ZERO) == -1 || percent.compareTo(BigDecimal.ZERO) == -1 || fix.compareTo(BigDecimal.ZERO) == -1) {
               throw new NonPositiveInputException();
            }

            BigDecimal pc = total.divide(BigDecimal.valueOf(1).add(percent), 10, RoundingMode.HALF_UP).multiply(percent);
            BigDecimal fc = fix;

            commission.setText("Значение комиссии: " + pc.max(fc));
        } catch (NumberFormatException e) {
            feedback.setText("Пожалуйста, введите чило корректно");
        } catch (NonPositiveInputException e) {
            feedback.setText("Число должно быть болльше 0");
        }

    }

}

