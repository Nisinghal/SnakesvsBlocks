package game;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;

public class Block {

    Label _block;
    int _value;
    int frequency;
    String color;
    static String[] hexcodes = {"#CFF", "#CCC", "#9CF", "#99F", "#99C", "#39C", "#36C", "#63C", "#93C", "#936", "#C33", "#C03", "#C00"};
    static int hex;

    Block(ArrayList<Block> _blocks, AnchorPane _blockPane, int value, double[] xs, int xsi) {
        _block = new Label();
        Random rand = new Random();
        _value = value;
        hex = assignHex();
        _block.setTextAlignment(TextAlignment.CENTER);
        if (hex > 12) hex = 12;
        _block.setStyle("-fx-background-color:" + hexcodes[hex] + "; -fx-background-radius: 4px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-family:'CENTURY GOTHIC'; -fx-font-size: 30; -fx-text-fill: WHITE;  -fx-alignment: CENTER;  -fx-border-radius: 4px");
        _block.setText(String.valueOf(value));
        _block.setMinSize(52.5, 52.5);
        _block.setMaxSize(52.5, 52.5);
        _block.setPrefSize(52.5, 52.5);
        _block.setLayoutX(xs[xsi]);
        _block.setLayoutY(-52.5);
        _blockPane.getChildren().add(_block);
        _blocks.add(this);
    }

    public int assignHex() {

        return ((_value) / 4);

    }


    public boolean checkCollision(Snake _snake) {
//            System.out.println(_snake._snakeHead.getLayoutX()+" "+_block.getLayoutX());
        return (_snake._snakeHead.getLayoutX() >= _block.getLayoutX() && _snake._snakeHead.getLayoutX() <= _block.getLayoutX() + 52.5 && 240 <= _block.getLayoutY() && 310 >= _block.getLayoutY());
    }

    public void collide(AnchorPane _blockPane, Snake _snake, Text _currentScore, ArrayList<Block> _blocks) {
        if (_block.isDisabled() != true) {
            if (_snake.checkShieldOff()) _snake._decLength(this, _value, _blockPane);
            _currentScore.setText("" + (Integer.parseInt(_currentScore.getText()) + _value));
            Burst _burst = new Burst(_blockPane, _block.getLayoutX(), _block.getLayoutY(), hex, hexcodes);

            disappear(_blockPane, _blocks);
        }
        _blockPane.getChildren().remove(_block);
    }

    public void collide(AnchorPane _blockPane, Snake _snake, Text _currentScore) {
        _currentScore.setText("" + (Integer.parseInt(_currentScore.getText()) + _value));
        _blockPane.getChildren().remove(_block);
        playScreenController._blocks.remove(_block);
        _blockPane.getChildren().remove(_block);
    }

    public void proceed() {
        _block.setLayoutY(_block.getLayoutY() + 3);
    }

    public void disappear(AnchorPane _blockPane, ArrayList<Block> _blocks) {
        AnimationTimer timer2 = new AnimationTimer() {
            private long lastUpdate2 = 0;

            @Override
            public void handle(long now2) {
                if (Integer.parseInt(_block.getText()) > 5) {

                    if (now2 - lastUpdate2 > 50_000_000) {
                        System.out.println(_value);
                        _block.setText((_value - 1) + "");
                        _value--;
                    }
                    lastUpdate2 = now2;
                    playScreenController._paused=true;

                }
            }
        };
        timer2.start();
        _blockPane.getChildren().remove(_block);

        _blocks.remove(_block);
    }
}