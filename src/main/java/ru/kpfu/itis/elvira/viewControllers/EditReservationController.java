package ru.kpfu.itis.elvira.viewControllers;

import javafx.fxml.FXML;
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


public class EditReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CotService cotService;

    @Autowired
    @Qualifier("adminView")
    ConfigurationController.View adminHomeView;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

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
    Button saveReservationBtn;

    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
        if(id!=null) {
            Reservation reservation = reservationService.findById(id);
            name.setText(reservation.getClient());
            number.setText(reservation.getNumber());
            car.setText(reservation.getCar());
            issueDate.setText(reservation.getIssueDate());
            returnDate.setText(reservation.getReturnDate());
        }
    }

    @FXML
    public void saveReservation(){
        Reservation reservation = new Reservation(
                name.getText(),
                number.getText(),
                cotService.getCar(car.getText()),
                issueDate.getText(),
                returnDate.getText()
        );
        reservation.setId(Long.valueOf(id));
        name.clear();
        number.clear();
        car.clear();
        issueDate.clear();
        returnDate.clear();
        reservationService.update(reservation);
        AdminController controller = (AdminController) adminHomeView.getController();
        controller.refreshReservation();
        Stage stage = (Stage) saveReservationBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }

}
