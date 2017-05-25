package ru.kpfu.itis.elvira.viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.elvira.configurations.ConfigurationController;
import ru.kpfu.itis.elvira.entity.Reservation;
import ru.kpfu.itis.elvira.services.CotService;
import ru.kpfu.itis.elvira.services.ReservationService;

import javax.annotation.PostConstruct;

public class RentController {

    @Autowired
    CotService cotService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    @Qualifier("userView")
    ConfigurationController.View userHomeView;

    @FXML
    TextField name;
    @FXML
    TextField number;
    @FXML
    TextField car;
    @FXML
    TextField issueDate;
    @FXML
    TextField returnDate;
    @FXML
    Button rentCarBtn;
    @FXML
    Button cancelBtn;

    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
    }

    public void rentCar(){
        if(cotService.getCar(car.getText())!=null){
            Reservation reservation = new Reservation(
                    name.getText(),
                    number.getText(),
                    cotService.getCar(car.getText()),
                    issueDate.getText(),
                    returnDate.getText()
            );
            reservationService.save(reservation);
            UserController controller = (UserController) userHomeView.getController();
            controller.refresh();
            cancel();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Спасибо!");
            alert.setContentText("В ближайшее время наш оператор с вами свяжется!");
            alert.showAndWait();
        }
    }

    public void cancel(){
        Stage stage = (Stage) rentCarBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }
}
