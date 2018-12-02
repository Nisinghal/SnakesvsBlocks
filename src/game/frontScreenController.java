package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class frontScreenController {

@FXML
    protected void tapToStart(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/playscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    public void goLeader(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/leaderboard.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }
}
