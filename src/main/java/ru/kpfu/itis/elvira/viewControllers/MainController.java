package ru.kpfu.itis.elvira.viewControllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.elvira.configurations.ConfigurationController;
import ru.kpfu.itis.elvira.services.RoleService;
import ru.kpfu.itis.elvira.services.UserService;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции JavaFX
   // @FXML private TableView<> table;

    @Autowired
    @Qualifier("regitrationView")
    private ConfigurationController.View registrationView;

    @Autowired
    @Qualifier("adminView")
    private ConfigurationController.View adminHomeView;

    @Autowired
    @Qualifier("userView")
    private ConfigurationController.View userHomeView;

    @Autowired
    AuthenticationProvider authProvider;

    @FXML
    Label label;

    @FXML
    Button loginbtn;

    @FXML
    Button registrationbtn;

    @FXML
    TextField login;

    @FXML
    TextField password;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
    }

    @FXML
    public void login(){
        Authentication authentication = new UsernamePasswordAuthenticationToken(login.getText(), password.getText());
        try {
            authentication = authProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                login.getScene().setRoot(adminHomeView.getView());
            } else login.getScene().setRoot(userHomeView.getView());
        }catch (AuthenticationException e) {
            password.clear();
            label.setText("Wrong user or password");
        }
    }

    @FXML
    public void registration(){
        login.clear();
        password.clear();
        registrationbtn.getScene().setRoot(registrationView.getView());
    }
}