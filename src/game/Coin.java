package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.util.ArrayList;
public class Coin implements Token {
    int value;
    int frequency;
    ImageView _coin;
    static Image _coinImage;
    static Text _currentCoins;

    Coin(ArrayList<Coin> _coins, Image tok, int xsi, double[] xs, AnchorPane _blockPane, Text _cc) {
        _coin = new ImageView();
        _coin.setImage(tok);
        _coinImage=_coin.getImage();
        _coin.setFitHeight(60);
        _coin.setFitWidth(60);
        _coin.setLayoutX(xs[xsi]);
        _coin.setLayoutY(-52.5);
        _blockPane.getChildren().add(_coin);
        _currentCoins=_cc;
        _coins.add(this);
    }

    public boolean checkCollision(Snake _snake) {
        return (_snake._snakeHead.getLayoutX() >= _coin.getLayoutX() && _snake._snakeHead.getLayoutX() <= _coin.getLayoutX() + 52.5 && 240 <= _coin.getLayoutY() && 310 >= _coin.getLayoutY());
    }

    public void proceed() {
        _coin.setLayoutY(_coin.getLayoutY() + 3);
    }

    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_coin);
    }

    public double getLayoutY() {
        return _coin.getLayoutY();
    }

    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins) {
        if (_snake.checkMagnetOn()) System.out.println("MAGNETTcoin");
        disappear(_blockPane);
        setCurrentCoins(getCurrentCoins() + 1);
        _blockPane.getChildren().remove(_coin);
    }
    static public void setCurrentCoins(int coin){
        _currentCoins.setText(""+coin);
    }

    static public int getCurrentCoins(){
        return Integer.parseInt(_currentCoins.getText());
    }
}
