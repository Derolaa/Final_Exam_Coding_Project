package pkgApp.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private TextField txtYearsToWork;
	@FXML
	private TextField txtAnnualReturnWork;
	@FXML
	private TextField txtYearsRetired;
	@FXML
	private TextField txtAnnualReturnRetired;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;
	@FXML
	private TextField txtSaveEachMonth;
	@FXML
	private TextField txtNeedToSave;

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");
		
		txtYearsToWork.clear();
		txtAnnualReturnWork.clear();
		txtYearsRetired.clear();
		txtAnnualReturnRetired.clear();
		txtRequiredIncome.clear();
		txtMonthlySSI.clear();
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		this.format();

		if (isInputValid()) {
			Retirement r = new Retirement();
			r.setdAnnualReturnWorking(Double.parseDouble(txtAnnualReturnWork.getText()));
			r.setdAnnualReturnRetired(Double.parseDouble(txtAnnualReturnRetired.getText()));
			r.setdRequiredIncome(Double.parseDouble(txtRequiredIncome.getText()));
			r.setdMonthlySSI(Double.parseDouble(txtMonthlySSI.getText()));
			r.setiYearsToWork(Integer.parseInt(txtYearsToWork.getText()));
			r.setiYearsRetired(Integer.parseInt(txtYearsRetired.getText()));
			
			double needToSave = r.TotalAmountSaved();
			double saveEachMonth = r.AmountToSave();
			
			txtSaveEachMonth.setText(NumberFormat.getCurrencyInstance().format(saveEachMonth));
			txtNeedToSave.setText(NumberFormat.getCurrencyInstance().format(needToSave));
		}
	}

	private void format() {
		if (txtAnnualReturnWork.getText().contains("%")) {
			txtAnnualReturnWork.setText(txtAnnualReturnWork.getText().replace("%",""));
		}

		if (txtAnnualReturnRetired.getText().contains("%")) {
			txtAnnualReturnRetired.setText(txtAnnualReturnRetired.getText().replace("%",""));
		}
		
		if (txtRequiredIncome.getText().contains(",") || txtRequiredIncome.getText().contains("$")) {
			txtRequiredIncome.setText(txtRequiredIncome.getText().replace(",",""));
			txtRequiredIncome.setText(txtRequiredIncome.getText().replace("$",""));
		}

		if (txtMonthlySSI.getText().contains(",") || txtMonthlySSI.getText().contains("$")) {
			txtMonthlySSI.setText(txtMonthlySSI.getText().replace(",",""));
			txtMonthlySSI.setText(txtMonthlySSI.getText().replace("$",""));
		}
	}

	private boolean isInputValid() {
		String errorMessage = "";
		if (txtYearsToWork.getText()==null || txtYearsToWork.getText().length()==0 || txtYearsToWork.getText().contains(".")) {
			errorMessage += "Please enter a whole number greater than or equal to 0.\n";
		
		}else {
			
			try { 
				Integer.parseInt( txtYearsToWork.getText() ); 
				
			}catch(NumberFormatException e) {
				errorMessage += "Please enter a whole number greater than or equal to 0.\n";
			}
		}

		if (txtAnnualReturnWork.getText()==null || txtAnnualReturnWork.getText().length()==0) {
			errorMessage += "Please enter working annual return.\n";
			
		}else {
			
			try { 
				Double.parseDouble(txtAnnualReturnWork.getText());
				
			}catch(NumberFormatException e) {
				errorMessage += "Please enter a valid number for working annual return.\n";
			}
		}

		if(Double.parseDouble(txtAnnualReturnWork.getText())>20.0 || Double.parseDouble(txtAnnualReturnWork.getText()) <0.0) {
			errorMessage += "Please enter a working annual return between 0 and 20.\n";
	}

		if (txtYearsRetired.getText()==null || txtYearsRetired.getText().length()==0 || txtYearsRetired.getText().contains(".")) {
			errorMessage += "Please enter expected years retired without decimals.\n";

		}else {

			try { 
				Integer.parseInt( txtYearsRetired.getText() ); 
				
			}catch(NumberFormatException e) {

				errorMessage += "Please enter a whole number greater than or equal to 0.\n";
			}
		}
		
		if (txtAnnualReturnRetired.getText()==null || txtAnnualReturnRetired.getText().length()==0) {
			errorMessage += "Please enter retired annual return.\n";

		}else {

			try { 

				Double.parseDouble(txtAnnualReturnRetired.getText());

			}catch(NumberFormatException e) {
				errorMessage += "Please enter a retired annual return. Number must be between 0 and 3.\n";
			}
		}

		if(Double.parseDouble(txtAnnualReturnRetired.getText())>3.0 || Double.parseDouble(txtAnnualReturnRetired.getText()) <0.0) {
				errorMessage += "Please enter a retired annual return. Number must be between 0 and 3.\n";
		}

		if (txtRequiredIncome.getText()==null || txtRequiredIncome.getText().length()==0) {
			errorMessage += "Please enter required monthly income while retired. Number must be greater than or equal to 0.\n";
			
		}else {

			try { 

				Double.parseDouble(txtRequiredIncome.getText());

			}catch(NumberFormatException e) {
				
				errorMessage += "Please enter a valid number for required income. Number must be greater than or equal to 0.\n";
			}
		}

		if (txtMonthlySSI.getText()==null || txtMonthlySSI.getText().length()==0) {
			errorMessage += "Please enter monthly SSI payment you will recieve during retirement. Number must be greater than or equal to 0.\n";

		}else {

			try { 

				Double.parseDouble(txtMonthlySSI.getText()); 

			}catch(NumberFormatException e) {

				errorMessage += "Please enter a valid number for monthly SSI. Number must be greater than or equal to 0.\n";
			}
		}

		if(errorMessage.length() ==0) {

			return true;

		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please check correct invalid field(s).");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		} 	
	}	
}