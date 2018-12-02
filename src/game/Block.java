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

/**
 * The Class Block.
 */
public class Block {

    /** The block. */
    Label _block;
    
    /** The value. */
    int _value;
    
    /** The frequency. */
    int frequency;
    
    /** The color. */
    String color;
    
    /** The hexcodes. */
    static String[] hexcodes = {"#CFF", "#CCC", "#9CF", "#99F", "#99C", "#39C", "#36C", "#63C", "#93C", "#936", "#C33", "#C03", "#C00"};
    
    /** The hex. */
    static int hex;

    /**
     * Instantiates a new block.
     *
     * @param _blocks the blocks
     * @param _blockPane the block pane
     * @param value the value
     * @param xs the xs
     * @param xsi the xsi
     */
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

    /**
     * Assign hex.
     *
     * @return the int
     */
    public int assignHex() {

        return ((_value) / 4);

    }


    /**
     * Check collision.
     *
     * @param _snake the snake
     * @return true, if successful
     */
    public boolean checkCollision(Snake _snake) {
//            System.out.println(_snake._snakeHead.getLayoutX()+" "+_block.getLayoutX());
        return (_snake._snakeHead.getLayoutX() >= _block.getLayoutX() && _snake._snakeHead.getLayoutX() <= _block.getLayoutX() + 52.5 && 240 <= _block.getLayoutY() && 310 >= _block.getLayoutY());
    }

    /**
     * Collide.
     *
     * @param _blockPane the block pane
     * @param _snake the snake
     * @param _currentScore the current score
     * @param _blocks the blocks
     * @throws Exception the exception
     */
    public void collide(AnchorPane _blockPane, Snake _snake, Text _currentScore, ArrayList<Block> _blocks) throws Exception {
        if (_block.isDisabled() != true) {
            if (_snake.checkShieldOff()) _snake._decLength(this, _value, _blockPane, _currentScore.getText());
            _currentScore.setText("" + (Integer.parseInt(_currentScore.getText()) + _value));
            Burst _burst = new Burst(_blockPane, _block.getLayoutX(), _block.getLayoutY(), hex, hexcodes);

            disappear(_blockPane, _blocks);
        }
        _blockPane.getChildren().remove(_block);
    }

    /**
     * Collide.
     *
     * @param _blockPane the block pane
     * @param _snake the snake
     * @param _currentScore the current score
     */
    public void collide(AnchorPane _blockPane, Snake _snake, Text _currentScore) {
        _currentScore.setText("" + (Integer.parseInt(_currentScore.getText()) + _value));
        _blockPane.getChildren().remove(_block);
        playScreenController._blocks.remove(_block);
        _blockPane.getChildren().remove(_block);
    }

    /**
     * Proceed.
     */
    public void proceed() {
        _block.setLayoutY(_block.getLayoutY() + 3);
    }

    /**
     * Disappear.
     *
     * @param _blockPane the block pane
     * @param _blocks the blocks
     */
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