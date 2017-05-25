package ru.kpfu.itis.elvira.viewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.elvira.configurations.ConfigurationController;
import ru.kpfu.itis.elvira.entity.Cottages;
import ru.kpfu.itis.elvira.services.CotService;

import javax.annotation.PostConstruct;
import java.util.List;



public class UserController {

    @Autowired
    CotService cotService;

    @Autowired
    @Qualifier("mainView")
    ConfigurationController.View mainView;

    @Autowired
    @Qualifier("rentView")
    ConfigurationController.View rentCarView;


    @FXML
    TableView<Cottages> carTable;

    @FXML
    Button rentCarBtn;

    @FXML
    Button logoutBtn;

    @FXML
    public void initialize() {

    }

    @PostConstruct
    public void init() {
        List<Cottages> cars = cotService.findAll();
        ObservableList<Cottages> carData = FXCollections.observableArrayList(cars);

        TableColumn<Cottages, String> carIdColumn = new TableColumn<>("ID");
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("id"));
        TableColumn<Cottages, String> carModelColumn = new TableColumn<>("House number");
        carModelColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("model"));
        TableColumn<Cottages, String> carMileageColumn = new TableColumn<>("Number of beds");
        carMileageColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("mileage"));
        TableColumn<Cottages, String> carPowerColumn = new TableColumn<>("Parking place");
        carPowerColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("power"));
        TableColumn<Cottages, String> carYearColumn = new TableColumn<>("Cadminhildren's area");
        carYearColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("year"));
        TableColumn<Cottages, String> carCostColumn = new TableColumn<>("Cost");
        carCostColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("cost"));
        carTable.getColumns().setAll(carIdColumn, carModelColumn, carMileageColumn, carPowerColumn, carYearColumn, carCostColumn);
        carTable.setItems(carData);


    }

    @FXML
    public void rentCar(){
        final Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setScene(new Scene(rentCarView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.getScene().setRoot(new Pane());
                stage.close();
            }
        });


        stage.show();
    }

    @FXML
    public void logout(){
        SecurityContextHolder.clearContext();
        logoutBtn.getScene().setRoot(mainView.getView());
    }

    public void refresh(){
        List<Cottages> cars = cotService.findAll();
        ObservableList<Cottages> carData = FXCollections.observableArrayList(cars);
        carTable.setItems(carData);
    }
}
