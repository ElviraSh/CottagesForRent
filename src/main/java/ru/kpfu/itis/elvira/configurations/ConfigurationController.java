package ru.kpfu.itis.elvira.configurations;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.elvira.viewControllers.*;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ConfigurationController {

    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("login.fxml");
    }

    @Bean(name = "regitrationView")
    public View getRegistrationView() throws IOException {
        return loadView("registration.fxml");
    }

    @Bean(name = "adminView")
    public View getAdminHomeView() throws IOException {
        return loadView("admin.fxml");
    }

    @Bean(name = "userView")
    public View getUserHomeView() throws IOException {
        return loadView("user.fxml");
    }

    @Bean(name = "editView")
    public View getEditCarView() throws IOException {
        return loadView("edit.fxml");
    }

    @Bean(name = "editReservationView")
    public View getEditReservationView() throws IOException {
        return loadView("editReservation.fxml");
    }

    @Bean(name = "rentView")
    public View getRentCarView() throws IOException {
        return loadView("rent.fxml");
    }

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public RegistrationController getRegistrationController() throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }

    @Bean
    public AdminController getAdminHomeController() throws IOException {
        return (AdminController) getAdminHomeView().getController();
    }

    @Bean
    public UserController getUserHomeController() throws IOException {
        return (UserController) getUserHomeView().getController();
    }

    @Bean
    public EditController getEditCarController() throws IOException {
        return (EditController) getEditCarView().getController();
    }

    @Bean
    public EditReservationController getEditReservationController() throws IOException {
        return (EditReservationController) getEditReservationView().getController();
    }

    @Bean
    public RentController getRentCarController() throws IOException {
        return (RentController) getRentCarView().getController();
    }

    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View((Parent) loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}