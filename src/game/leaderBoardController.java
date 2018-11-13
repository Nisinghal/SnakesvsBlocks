package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class leaderBoardController {
    public void backHome(ActionEvent actionEvent) throws Exception{
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 480, 800);
        Main.mainStage.setScene(s);
    }
}
