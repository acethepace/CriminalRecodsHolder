package application;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class TableController {
    @FXML private TableView tableView;

    @FXML
    private void initialize() {
    	List<String> columns = new ArrayList<String>();
        columns.add("col1");
        columns.add("col2");
        TableColumn [] tableColumns = new TableColumn[columns.size()];     
        int columnIndex = 0;
        for(int i=0 ; i<columns.size(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(columns.get(i));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                             
                    return new SimpleStringProperty(param.getValue().get(j).toString());                       
                }

			public ObservableValue<String> call1(CellDataFeatures<ObservableList, String> arg0) {
				// TODO Auto-generated method stub
				return null;
			}                   
            });
            tableView.getColumns().addAll(col);
        }       
        ObservableList<String> row = FXCollections.observableArrayList();
        ObservableList<String> row1 = FXCollections.observableArrayList();
        row.addAll("d1");
        row.addAll("d11");
        row1.addAll("d2");
        row1.addAll("d22");
        tableView.getItems().add(row);
        tableView.getItems().add(row1);
    }

    }