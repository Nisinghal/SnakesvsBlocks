package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

/**
 * The Class frontScreenController.
 */
public class frontScreenController {

    /** The resume. */
    @FXML Button _resume;


    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        if (!Main._sg.currentScore.equals("0")) {
            _resume.setDisable(true);
        }
        else {
            _resume.setDisable(false);
        }
    }

/**
 * Tap to start.
 *
 * @param event the event
 * @throws Exception the exception
 */
@FXML
    protected void tapToStart(ActionEvent event) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/playscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    /**
     * Tap to resume.
     *
     * @param event the event
     * @throws Exception the exception
     */
    @FXML
    protected void tapToResume(ActionEvent event) throws Exception {
        Main.resumed = true;
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/playscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    /**
     * Go leader.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    @FXML
    public void goLeader(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/leaderboard.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    /**
     * Back home.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    @FXML
    public void backHome(ActionEvent actionEvent) throws Exception{
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }
}
