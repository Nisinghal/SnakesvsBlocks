package game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * The Class Snake.
 */
public class Snake {
    
    /** The shield. */
    boolean _shield;
    
    /** The magnet. */
    boolean _magnet;
    
    /** The speed. */
    int _speed;
    
    /** The snake. */
    ArrayList<Label> _snake;
    
    /** The snake tail Y. */
    double _snakeTailX, _snakeTailY;
    
    /** The snake head. */
    Label _snakeHead/*, _snakeHead1, _snakeHead11*/;
    
    /** The snake length. */
    Text _snakeLength;

/**
 * Instantiates a new snake.
 *
 * @param _sl the sl
 * @param _blockPane the block pane
 * @param len the len
 */
Snake(/*Label s1, Label s2, Label s3, ArrayList<Ball> _balls,*/  Text _sl, AnchorPane _blockPane, String len) {
        _shield = false;
        _snake = new ArrayList<Label>();
        _snakeTailY = 300;
        _snakeTailX = 120;
        for(int i=0;i<9;i++){
            Ball _new = new Ball(/*_balls,*/ _blockPane);
            _new.setLayoutX(_snakeTailX);
            _new.setLayoutY(_snakeTailY);
            _new.extendSnake(this);
            _snakeTailY=_snakeTailY+20;
        }
//        _snake.add(s1);
//        _snake.add(s2);
//        _snake.add(s3);

        _snakeLength = _sl;
        _snakeLength.setText(len);
        _snakeHead = _snake.get(0);
//        _snakeHead1 = s2;
//        _snakeHead11 = s3;
    }

    /**
     * Dec length.
     *
     * @param _block the block
     * @param value the value
     * @param _blockPane the block pane
     * @param _currentScore the current score
     * @throws Exception the exception
     */
    public void _decLength(Block _block, int value, AnchorPane _blockPane, String _currentScore) throws Exception{
//        System.out.println("collide"+value);

        int sl = Integer.parseInt(_snakeLength.getText());
        while (_snake.size() > (sl - value)  && _snake.size()>0) {
//            System.out.println(_snake.size()+" "+_snakeLength.getText()+" "+ value);
           if (_snake.size() <= 9) {
               _blockPane.getChildren().remove(_snake.get(_snake.size() - 1));
               _snakeTailY=_snakeTailY-20;
           }
//            _snakeLength.setText(""+(Integer.parseInt(_snakeLength.getText())-1));
//            value--;
            _snake.remove(_snake.size() - 1);

        }
        _snakeLength.setText("" + (sl - value));
        sl = Integer.parseInt(_snakeLength.getText());
        value=0;
        if (sl - value <= 0 || sl<=0){
            _snakeLength.setText("0");
            Main._sg.currentCoins = "0";
            Main._sg.currentScore = "0";
            Main._sg.snakeLength = "0";
            Main._sg.lastScore = _currentScore;
            Main._sg.allScores.add(Integer.parseInt(_currentScore));
            Main.save();

            Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 320, 480);
            Main.mainStage.setScene(s);
        }

    }

    /**
     * Inc length.
     *
     * @param increment the increment
     * @param _blockPane the block pane
     */
    public void _incLength(/*ArrayList<Ball> _balls,*/ int increment, AnchorPane _blockPane) {
        for(int i=0;i<increment;i++){
            if(_snake.size()<9) {
                Ball _new = new Ball(/*Ball._balls, */_blockPane);

//            System.out.println(_snakeTailY+ " "+_snake.size()+" "+_snakeLength.getText());
                _new.setLayoutX(_snakeTailX);
                _new.setLayoutY(_snakeTailY);
                _new.extendSnake(this);
                _snakeTailY = _snakeTailY + 20;
            }
            _snakeLength.setText(""+(Integer.parseInt(_snakeLength.getText())+1));
        }
    }

    /**
     * Left boundary check.
     *
     * @return true, if successful
     */
    public boolean leftBoundaryCheck() {
        return (_snakeHead.getLayoutX() >= 52.5);
    }

    /**
     * Right boundary check.
     *
     * @return true, if successful
     */
    public boolean rightBoundaryCheck() {
        return (_snakeHead.getLayoutX() <= 270);
    }

    /**
     * Check shield off.
     *
     * @return true, if successful
     */
    public boolean checkShieldOff() {
        return !_shield;
    }

    /**
     * Check magnet on.
     *
     * @return true, if successful
     */
    public boolean checkMagnetOn() {
        return _magnet;
    }

/**
 * Extend snake.
 *
 * @param _ball the ball
 */
public void extendSnake(Label _ball){
        _snake.add(_ball);
}
    
    /**
     * Move left.
     */
    public void moveLeft() {
        _snakeLength.setLayoutX(_snakeLength.getLayoutX() - 52.5);
        for (Label _snakeBody : _snake) {
            _snakeBody.setLayoutX(_snakeBody.getLayoutX() - 52.5);
        }
        if (_snake.size() > 0) _snakeTailX = _snake.get(_snake.size() - 1).getLayoutX();
    }

    /**
     * Move right.
     */
    public void moveRight() {
        _snakeLength.setLayoutX(_snakeLength.getLayoutX() + 52.5);
        for (Label _snakeBody : _snake) {
            _snakeBody.setLayoutX(_snakeBody.getLayoutX() + 52.5);
        }
        if (_snake.size() > 0) _snakeTailX = _snake.get(_snake.size() - 1).getLayoutX();
    }
}
