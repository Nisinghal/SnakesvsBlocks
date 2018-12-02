 package game;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Class Ball.
 */
public class Ball implements Token {
    
    /** The ball. */
    Label _ball;
    
    /** The value. */
    int _value;
    
    /** The balls. */
    static ArrayList<Ball> _balls=new ArrayList<game.Ball>();
//    int frequency;

    /**
 * Instantiates a new ball.
 *
 * @param _blockPane the block pane
 */
Ball(/*ArrayList<Ball> _balls,*/ AnchorPane _blockPane) {
        _ball = new Label();
        _ball.setStyle("-fx-background-color: WHITE; -fx-background-radius: 20px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-border-radius: 20px");
        _ball.setMinSize(20, 20);
        _ball.setMaxSize(20, 20);
        _ball.setPrefSize(20, 20);
        _blockPane.getChildren().add(_ball);
        _balls.add(this);
//        this._balls=_balls;
    }

    /**
     * Instantiates a new ball.
     *
     * @param rand the rand
     * @param xsi the xsi
     * @param xs the xs
     * @param _blockPane the block pane
     */
    Ball(/*ArrayList<Ball> _balls, */Random rand, int xsi, double[] xs, AnchorPane _blockPane) {
        _value = rand.nextInt(15) + 1;
        _ball = new Label();
        _ball.setTextAlignment(TextAlignment.CENTER);
        _ball.setStyle("-fx-background-color: WHITE; -fx-background-radius: 20px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-family:'CENTURY GOTHIC'; -fx-font-size: 14; -fx-text-fill: BLACK;  -fx-alignment: CENTER; -fx-font-weight: bold; -fx-border-radius: 20px");
        _ball.setText(String.valueOf(_value));
        _ball.setMinSize(20, 20);
        _ball.setMaxSize(20, 20);
        _ball.setPrefSize(20, 20);
        _ball.setLayoutX(xs[xsi]);
        _ball.setLayoutY(-52.5);
        _blockPane.getChildren().add(_ball);
        _balls.add(this);
        this._balls=_balls;
    }

    /* (non-Javadoc)
     * @see game.Token#disappear(javafx.scene.layout.AnchorPane)
     */
    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_ball);
    }

    /* (non-Javadoc)
     * @see game.Token#proceed()
     */
    public void proceed() {
        _ball.setLayoutY(_ball.getLayoutY() + 3);
    }

    /* (non-Javadoc)
     * @see game.Token#checkCollision(game.Snake)
     */
    public boolean checkCollision(Snake _snake) {
        return (_snake._snakeHead.getLayoutX() >= _ball.getLayoutX() && _snake._snakeHead.getLayoutX() <= _ball.getLayoutX() + 52.5 && 280 <= _ball.getLayoutY() && 310 >= _ball.getLayoutY());
    }

    /* (non-Javadoc)
     * @see game.Token#getLayoutY()
     */
    public double getLayoutY() {
        return _ball.getLayoutY();
    }

    /**
     * Sets the layout X.
     *
     * @param x the new layout X
     */
    public void setLayoutX(double x) {
        _ball.setLayoutX(x);
    }

    /**
     * Sets the layout Y.
     *
     * @param x the new layout Y
     */
    public void setLayoutY(double x) {
        _ball.setLayoutY(x);
    }

    /**
     * Extend snake.
     *
     * @param _snake the snake
     */
    public void extendSnake(Snake _snake) {
        _snake.extendSnake(_ball);
    }

    /* (non-Javadoc)
     * @see game.Token#collide(game.Snake, javafx.scene.layout.AnchorPane, javafx.scene.text.Text)
     */
    public void collide( Snake _snake, AnchorPane _blockPane, Text _currentCoins) {
        if(_snake.checkMagnetOn()) System.out.println("MAGNETTball");
//        Random rand = new Random();
        int sl = Integer.parseInt(_snake._snakeLength.getText());
        disappear(_blockPane);
        _snake._incLength(/*_balls,*/_value, _blockPane);
////        _ball.setLayoutX(_snake._snakeTailX);
////        _ball.setLayoutY(_snake._snakeTailY);
//        _snake._snakeLength.setText("" + (sl + _value));
////        _ball.setText("");
////        _snake._snakeTailY = _snake._snakeTailY + 20;
////        _snake._snake.add(_ball);
//        for (int i = 1; i <= _value; i++) {
//            _snake._snakeLength.setText("" + (sl + 1));
//            _snake._snakeTailY = _snake._snakeTailY + 20;
//            double[] x= new double[1];
//            x[0]=0;
//            Ball _newball = new Ball(_blockPane);
//            _newball.setLayoutX(_snake._snakeTailX);
//            _newball.setLayoutY(_snake._snakeTailY);
//            _newball.extendSnake(_snake);
    }

}
