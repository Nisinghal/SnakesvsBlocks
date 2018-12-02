package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.util.ArrayList;

/**
 * The Class Coin.
 */
public class Coin implements Token {
    
    /** The value. */
    int value;
    
    /** The frequency. */
    int frequency;
    
    /** The coin. */
    ImageView _coin;
    
    /** The coin image. */
    static Image _coinImage;
    
    /** The current coins. */
    static Text _currentCoins;

    /**
     * Instantiates a new coin.
     *
     * @param _coins the coins
     * @param tok the tok
     * @param xsi the xsi
     * @param xs the xs
     * @param _blockPane the block pane
     * @param _cc the cc
     */
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

    /* (non-Javadoc)
     * @see game.Token#checkCollision(game.Snake)
     */
    public boolean checkCollision(Snake _snake) {
        return (_snake._snakeHead.getLayoutX() >= _coin.getLayoutX() && _snake._snakeHead.getLayoutX() <= _coin.getLayoutX() + 52.5 && 240 <= _coin.getLayoutY() && 310 >= _coin.getLayoutY());
    }

    /* (non-Javadoc)
     * @see game.Token#proceed()
     */
    public void proceed() {
        _coin.setLayoutY(_coin.getLayoutY() + 3);
    }

    /* (non-Javadoc)
     * @see game.Token#disappear(javafx.scene.layout.AnchorPane)
     */
    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_coin);
    }

    /* (non-Javadoc)
     * @see game.Token#getLayoutY()
     */
    public double getLayoutY() {
        return _coin.getLayoutY();
    }

    /* (non-Javadoc)
     * @see game.Token#collide(game.Snake, javafx.scene.layout.AnchorPane, javafx.scene.text.Text)
     */
    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins) {
        if (_snake.checkMagnetOn()) System.out.println("MAGNETTcoin");
        disappear(_blockPane);
        setCurrentCoins(getCurrentCoins() + 1);
        _blockPane.getChildren().remove(_coin);
    }
    
    /**
     * Sets the current coins.
     *
     * @param coin the new current coins
     */
    static public void setCurrentCoins(int coin){
        _currentCoins.setText(""+coin);
    }

    /**
     * Gets the current coins.
     *
     * @return the current coins
     */
    static public int getCurrentCoins(){
        return Integer.parseInt(_currentCoins.getText());
    }
}
