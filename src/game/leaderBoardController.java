package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The Class leaderBoardController.
 */
public class leaderBoardController {
    
    /**
     * Back home.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void backHome(ActionEvent actionEvent) throws Exception{
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 480, 800);
        Main.mainStage.setScene(s);
    }
}
