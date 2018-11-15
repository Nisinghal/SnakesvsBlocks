package game;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

import java.util.Random;

public class Block {

    Label _block;
    int value;
    int frequency;
    String color;
    String[] hexcodes = {"#CFF", "#CCC", "#9CF", "#99F", "#99C", "#39C", "#36C", "#63C", "#93C", "#936", "#C33", "#C03", "#C00"};

    Block(AnchorPane _blockPane, int value, double[] xs, int xsi) {
        _block = new Label();
        Random rand = new Random();
        int hex = assignHex(rand, value);
        _block.setTextAlignment(TextAlignment.CENTER);
        _block.setStyle("-fx-background-color:" + hexcodes[hex] + "; -fx-background-radius: 4px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-family:'CENTURY GOTHIC'; -fx-font-size: 30; -fx-text-fill: WHITE;  -fx-alignment: CENTER;  -fx-border-radius: 4px");
        _block.setText(String.valueOf(value));
        _block.setMinSize(52.5, 52.5);
        _block.setMaxSize(52.5, 52.5);
        _block.setPrefSize(52.5, 52.5);
        _block.setLayoutX(xs[xsi]);
        _block.setLayoutY(-52.5);
        _blockPane.getChildren().add(_block);
    }

public int assignHex(Random rand, int value){

    return ((value)/4);

    }


        public boolean checkCollision(Snake _snake) {
//            System.out.println(_snake._snakeHead.getLayoutX()+" "+_block.getLayoutX());
        return (_snake._snakeHead.getLayoutX() >= _block.getLayoutX() && _snake._snakeHead.getLayoutX() <= _block.getLayoutX() + 52.5 && 240 <= _block.getLayoutY() && 310 >= _block.getLayoutY());
    }

    public void proceed() {
        _block.setLayoutY(_block.getLayoutY() + 3);
    }

    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_block);
    }
}