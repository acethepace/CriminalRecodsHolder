package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea crime_description_report;

    @FXML
    private ComboBox<?> crime_type_cb_query;

    @FXML
    private TextArea suspect_description_report;

    @FXML
    private Button submit_crime_button;

    @FXML
    private ComboBox<?> status_cb_query;

    @FXML
    private Button cancel_crime_button;

    @FXML
    private RadioButton criminal_query;

    @FXML
    private TextField category_report;

    @FXML
    private TextArea addn_information_report;

    @FXML
    private Button add_criminal_button;

    @FXML
    private TextField offr_new_criminal;

    @FXML
    private DatePicker date_report;

    @FXML
    private RadioButton status_query;

    @FXML
    private TextField location_report;

    @FXML
    private TextField crimeId_new_criminal;

    @FXML
    private RadioButton crime_type_query;

    @FXML
    private Label message_new_criminal;

    @FXML
    private Button submit_query;

    @FXML
    private ComboBox<?> area_cb_query;

    @FXML
    private TextField department_new_criminal1;

    @FXML
    private ComboBox<?> criminal_cb_query;

    @FXML
    private TextField name_new_criminal;

    @FXML
    private Label message_report_crime;

    @FXML
    private ComboBox<?> status_new_criminal;

    @FXML
    private RadioButton area_query;

    @FXML
    void initialize() {
        assert crime_description_report != null : "fx:id=\"crime_description_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert crime_type_cb_query != null : "fx:id=\"crime_type_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert suspect_description_report != null : "fx:id=\"suspect_description_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert submit_crime_button != null : "fx:id=\"submit_crime_button\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert status_cb_query != null : "fx:id=\"status_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert cancel_crime_button != null : "fx:id=\"cancel_crime_button\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert criminal_query != null : "fx:id=\"criminal_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert category_report != null : "fx:id=\"category_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert addn_information_report != null : "fx:id=\"addn_information_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert add_criminal_button != null : "fx:id=\"add_criminal_button\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert offr_new_criminal != null : "fx:id=\"offr_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert date_report != null : "fx:id=\"date_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert status_query != null : "fx:id=\"status_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert location_report != null : "fx:id=\"location_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert crimeId_new_criminal != null : "fx:id=\"crimeId_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert crime_type_query != null : "fx:id=\"crime_type_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert message_new_criminal != null : "fx:id=\"message_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert submit_query != null : "fx:id=\"submit_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert area_cb_query != null : "fx:id=\"area_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert department_new_criminal1 != null : "fx:id=\"department_new_criminal1\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert criminal_cb_query != null : "fx:id=\"criminal_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert name_new_criminal != null : "fx:id=\"name_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert message_report_crime != null : "fx:id=\"message_report_crime\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert status_new_criminal != null : "fx:id=\"status_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert area_query != null : "fx:id=\"area_query\" was not injected: check your FXML file 'mainUI.fxml'.";

    }
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
    			if(!sanity(area,crime_type,criminal,status))
    			{
    				//flash error message
    				
    			}
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
    			if(!sanity(location,category,crime_desc,suspect_desc,addn_info))
    			{
    				//flash error
    				message_report_crime.setText("Special characters not allowed");
    				message_report_crime.setVisible(true);
    			}
    			if(!checkNull(location,category,crime_desc))
    			{
    				//flash error
    				message_report_crime.setVisible(true);
    				message_report_crime.setText("Please fill all required fields");
    			}

				System.out.println(checkNull(location,category,crime_desc));
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
    			String status= status_new_criminal.getValue().toString();
    			//TODO: add criminal with these details
    			if(!sanity(name,crimeId,officerIncharge,policeDept,status))
    			{
    				//flash error
    				message_new_criminal.setText("Don't include special characters");
    			}else if(!checkNull(name,crimeId,officerIncharge,policeDept,status))
    			{
    				//flash error
    				message_new_criminal.setText("Please fill all required fields");
    			}else
    			{
    				System.out.println("I'm here");
    				message_new_criminal.setText("");
    			}
    		}
		});
    }
	
	private boolean sanity(String... args)
	{
		for(String string : args)
		{
			try{
				if(string.matches(".*[;()'\"].*"))
					return false;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return true;
	}
	
	private boolean checkNull(String...strings)
	{
		System.out.println(strings.length);
		for(String string : strings)
		{
			if(string==null || string.equals(""))
			{
				return false;
			}else
			{
				System.out.println(string+" is good. ");
			}
		}
		return true;
	}
}
