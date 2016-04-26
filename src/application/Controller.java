package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable{

    @FXML
    private TextArea crime_description_report;

    @FXML
    private TextField crimeId_new_criminal;

    @FXML
    private ComboBox<?> crime_type_cb_query;

    @FXML
    private TextArea suspect_description_report;

    @FXML
    private RadioButton crime_type_query;

    @FXML
    private ComboBox<?> status_cb_query;

    @FXML
    private RadioButton criminal_query;

    @FXML
    private Button submit_query;

    @FXML
    private TextField category_report;

    @FXML
    private ComboBox<?> area_cb_query;

    @FXML
    private TextField department_new_criminal1;

    @FXML
    private TextArea addn_information_report;

    @FXML
    private Button add_criminal_button;

    @FXML
    private ComboBox<?> criminal_cb_query;

    @FXML
    private TextField name_new_criminal;

    @FXML
    private TextField offr_new_criminal;

    @FXML
    private DatePicker date_report;

    @FXML
    private ComboBox<CheckBox> status_new_criminal;

    @FXML
    private RadioButton area_query;

    @FXML
    private RadioButton status_query;

    @FXML
    private TextField location_report;
    
    @FXML
    private Button submit_crime_button;

    @FXML
    private Button cancel_crime_button;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
    	initializeButtons();
    	initializeRadioButtons();
    }
    
    void initializeRadioButtons() {
    	area_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent w)
    		{
    			area_cb_query.setDisable(!area_cb_query.isDisabled());
    		}
		});
    
    	crime_type_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent w)
    		{
    			crime_type_cb_query.setDisable(!crime_type_cb_query.isDisabled());
    		}
		});
    	
    	criminal_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e)
    		{
    			criminal_cb_query.setDisable(!criminal_cb_query.isDisabled());
    		}
    	});
    	
    	status_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e)
    		{
    			status_cb_query.setDisable(!status_cb_query.isDisabled());	
    		}
    	});
	}

	void initializeButtons(){
    	submit_query.setOnAction(new EventHandler<ActionEvent>(){
    		public void handle(ActionEvent t)
    		{
    			String area=null;
    			String crime_type=null;
    			String criminal=null;
    			String status=null;
    			if(!area_cb_query.isDisable())
    			{
    				area=area_cb_query.getValue().toString();
    			}
    			if(!crime_type_cb_query.isDisable())
    			{
    				crime_type=crime_type_cb_query.getValue().toString();
    			}
    			if(!criminal_cb_query.isDisable())
    			{
    				criminal=criminal_cb_query.getValue().toString();
    			}
    			if(!status_cb_query.isDisable())
    			{
    				status=status_cb_query.getValue().toString();
    			}
    			//TODO submit query, create table
    		}
   		});
    	
    	submit_crime_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			LocalDate date=date_report.getValue();
    			String location = location_report.getText();
    			String category = category_report.getText();
    			String crime_desc= crime_description_report.getText();
    			String suspect_desc=suspect_description_report.getText();
    			String addn_info= addn_information_report.getText();
    			
    			//TODO: ADD CRIME TO SQL DATABASE.
    		}
		});
    	
    	cancel_crime_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			date_report.setValue(null);
    			category_report.setText("");
    			crime_description_report.setText("");
    			suspect_description_report.setText("");
    			addn_information_report.setText("");
    		}
		});
    	
    	add_criminal_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			String name = name_new_criminal.getText();
    			String crimeId=crimeId_new_criminal.getText();
    			String officerIncharge=offr_new_criminal.getText();
    			String policeDept= department_new_criminal1.getText();
    			String status= status_new_criminal.getValue().getText();
    			
    			//TODO: add criminal with these details
    		}
		});
    }
}
