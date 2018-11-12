package game;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

public class playScreenController {

    @FXML
    private Circle _snakeHead;

    @FXML
    protected void keyReleased(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
            _snakeHead.setCenterX(_snakeHead.getCenterX() - 10);
        }
        else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            _snakeHead.setCenterX(_snakeHead.getCenterX() + 10);
        }
    }

    
}
