package ru.kpfu.itis.elvira.viewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.elvira.configurations.ConfigurationController;

import ru.kpfu.itis.elvira.entity.Cottages;
import ru.kpfu.itis.elvira.entity.Reservation;
import ru.kpfu.itis.elvira.services.CotService;
import ru.kpfu.itis.elvira.services.ReservationService;

import javax.annotation.PostConstruct;
import java.util.List;

public class AdminController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CotService cotService;

    @Autowired
    @Qualifier("editView")
    private ConfigurationController.View editView;

    @Autowired
    @Qualifier("mainView")
    private ConfigurationController.View mainView;

    @Autowired
    @Qualifier("editReservationView")
    private ConfigurationController.View editReservationView;

    @FXML
    TableView<Reservation> reservationsTable;
    @FXML
    TableView<Cottages> carsTable;
    @FXML
    TextField name;
    @FXML
    TextField number;
    @FXML
    TextField car;
    @FXML
    TextField issue;
    @FXML
    TextField returndate;
    @FXML
    Button addbtn;
    @FXML
    TextField carModel;
    @FXML
    TextField carMileage;
    @FXML
    TextField carYear;
    @FXML
    TextField carPower;
    @FXML
    TextField carCost;
    @FXML
    Button addNewCarBtn;
    @FXML
    TextField carId;
    @FXML
    Button removeCarBtn;
    @FXML
    Button editCarBtn;
    @FXML
    Button logoutBtn;
    @FXML
    TextField reservationId;
    @FXML
    Button editReservationBtn;
    @FXML
    Button removeReservationBtn;

    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
        List<Reservation> reservetions = reservationService.findAll();
        ObservableList<Reservation> data = FXCollections.observableArrayList(reservetions);

        // Добавляем столбцы к таблице
        TableColumn<Reservation, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("id"));
        TableColumn<Reservation, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("client"));
        TableColumn<Reservation, String> phoneColumn = new TableColumn<>("Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("number"));
        TableColumn<Reservation, String> carColumn = new TableColumn<>("Cottage");
        carColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("car"));
        TableColumn<Reservation, String> issueColumn = new TableColumn<>("Issue");
        issueColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("issueDate"));
        TableColumn<Reservation, String> returnColumn = new TableColumn<>("Return");
        returnColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("returnDate"));
        reservationsTable.getColumns().setAll(idColumn, nameColumn, phoneColumn, carColumn, issueColumn, returnColumn);

        // Добавляем данные в таблицу
        reservationsTable.setItems(data);

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
        TableColumn<Cottages, String> carYearColumn = new TableColumn<>("Children's area");
        carYearColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("year"));
        TableColumn<Cottages, String> carCostColumn = new TableColumn<>("Cost");
        carCostColumn.setCellValueFactory(new PropertyValueFactory<Cottages, String>("cost"));
        carsTable.getColumns().setAll(carIdColumn, carModelColumn, carMileageColumn, carPowerColumn, carYearColumn, carCostColumn);
        carsTable.setItems(carData);
    }

    @FXML
    public void addnew(){
        Reservation reservation = new Reservation(name.getText(),
                number.getText(),
                cotService.getCar(car.getText()),
                issue.getText(),
                returndate.getText());
        reservationService.save(reservation);
        number.clear();
        name.clear();
        car.clear();
        issue.clear();
        returndate.clear();
        refreshReservation();
    }

    @FXML
    public void removeReservation(){
        String id = reservationId.getText();
        reservationService.remove(id);
        reservationId.clear();
        refreshReservation();
    }

    @FXML
    public void editReservation(){
        final Stage stage = new Stage();
        stage.setTitle("Edit Reservation");
        EditReservationController controller = (EditReservationController) editReservationView.getController();
        controller.setId(reservationId.getText());
        controller.init();
        stage.setScene(new Scene(editReservationView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        reservationId.clear();
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
    public void addNewCar(){
        Cottages car = new Cottages(
                carModel.getText(),
                Integer.parseInt(carYear.getText()),
                Integer.parseInt(carMileage.getText()),
                Integer.parseInt(carPower.getText()),
                Integer.parseInt(carCost.getText()));
        cotService.save(car);
        carModel.clear();
        carMileage.clear();
        carYear.clear();
        carPower.clear();
        carCost.clear();
        refreshCars();
    }

    public void removeCar(){
        String id=carId.getText();
        cotService.remove(Long.valueOf(id));
        carId.clear();
        refreshCars();
    }

    public void editCar(){
        final Stage stage = new Stage();
        stage.setTitle("Edit");
        EditController controller = (EditController) editView.getController();
        controller.setId(carId.getText());
        controller.init();
        stage.setScene(new Scene(editView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.getScene().setRoot(new Pane());
                stage.close();
            }
        });
        carId.clear();
        stage.show();
    }

    public void logout(){
        SecurityContextHolder.clearContext();
        logoutBtn.getScene().setRoot(mainView.getView());
    }

    public void refreshReservation() {
        List<Reservation> reservations = reservationService.findAll();
        ObservableList<Reservation> reservationData = FXCollections.observableArrayList(reservations);
        reservationsTable.setItems(reservationData);
    }

    public void refreshCars(){
        List<Cottages> cars = cotService.findAll();
        ObservableList<Cottages> carData = FXCollections.observableArrayList(cars);
        carsTable.setItems(carData);
    }
}
