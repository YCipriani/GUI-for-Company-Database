

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button goButton;

    @FXML
    private TextField bobbyShmurda;
    
    public void hey()
    {
    	System.out.println(bobbyShmurda.getText());
    }

}
