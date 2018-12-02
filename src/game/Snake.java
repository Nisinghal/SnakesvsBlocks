package game;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Snake {
    boolean _shield;
    boolean _magnet;
    int _speed;
    ArrayList<Label> _snake;
    double _snakeTailX, _snakeTailY;
    Label _snakeHead/*, _snakeHead1, _snakeHead11*/;
    Text _snakeLength;

Snake(/*Label s1, Label s2, Label s3, ArrayList<Ball> _balls,*/  Text _sl, AnchorPane _blockPane) {
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
        _snakeLength.setText("10");
        _snakeHead = _snake.get(0);
//        _snakeHead1 = s2;
//        _snakeHead11 = s3;
    }

    public void _decLength(Block _block, int value, AnchorPane _blockPane) {
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
            System.exit(0);
        }

    }

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

    public boolean leftBoundaryCheck() {
        return (_snakeHead.getLayoutX() >= 52.5);
    }

    public boolean rightBoundaryCheck() {
        return (_snakeHead.getLayoutX() <= 270);
    }

    public boolean checkShieldOff() {
        return !_shield;
    }

    public boolean checkMagnetOn() {
        return _magnet;
    }

public void extendSnake(Label _ball){
        _snake.add(_ball);
}
    public void moveLeft() {
        _snakeLength.setLayoutX(_snakeLength.getLayoutX() - 52.5);
        for (Label _snakeBody : _snake) {
            _snakeBody.setLayoutX(_snakeBody.getLayoutX() - 52.5);
        }
        if (_snake.size() > 0) _snakeTailX = _snake.get(_snake.size() - 1).getLayoutX();
    }

    public void moveRight() {
        _snakeLength.setLayoutX(_snakeLength.getLayoutX() + 52.5);
        for (Label _snakeBody : _snake) {
            _snakeBody.setLayoutX(_snakeBody.getLayoutX() + 52.5);
        }
        if (_snake.size() > 0) _snakeTailX = _snake.get(_snake.size() - 1).getLayoutX();
    }
}
