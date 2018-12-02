package game;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * The Class Shield.
 */
public class Shield implements Token {
    
    /** The value. */
    static int _value;
    
    /** The frequency. */
    int frequency;
    
    /** The shield. */
    ImageView _shield;

/**
 * Instantiates a new shield.
 *
 * @param _shields the shields
 * @param tok the tok
 * @param xsi the xsi
 * @param xs the xs
 * @param _blockPane the block pane
 */
Shield(ArrayList<Shield> _shields   , Image tok, int xsi, double[] xs, AnchorPane _blockPane) {
        _shield = new ImageView();
        _shield.setImage(tok);
        _shield.setFitHeight(60);
        _shield.setFitWidth(60);
        _shield.setLayoutX(xs[xsi]);
        _shield.setLayoutY(-52.5);
        _blockPane.getChildren().add(_shield);
        _value = 0;
_shields.add(this);
    }

    /* (non-Javadoc)
     * @see game.Token#checkCollision(game.Snake)
     */
    public boolean checkCollision(Snake _snake) {
        return (_snake._snakeHead.getLayoutX() >= _shield.getLayoutX() && _snake._snakeHead.getLayoutX() <= _shield.getLayoutX() + 52.5 && 240 <= _shield.getLayoutY() && 310 >= _shield.getLayoutY());
    }

    /* (non-Javadoc)
     * @see game.Token#disappear(javafx.scene.layout.AnchorPane)
     */
    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_shield);
    }

    /* (non-Javadoc)
     * @see game.Token#proceed()
     */
    public void proceed() {
        _shield.setLayoutY(_shield.getLayoutY() + 3);
    }

    /* (non-Javadoc)
     * @see game.Token#getLayoutY()
     */
    public double getLayoutY() {
        return _shield.getLayoutY();
    }

    /* (non-Javadoc)
     * @see game.Token#collide(game.Snake, javafx.scene.layout.AnchorPane, javafx.scene.text.Text)
     */
    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins) {
        disappear(_blockPane);
        _snake._shield = true;

        if (_value != 0) {
            _value += 10;
        } else {
            _value = 10;

            _currentCoins.setDisable(false);
            _currentCoins.setVisible(true);
            _currentCoins.setText("Shield On: 10");

            AnimationTimer timer2 = new AnimationTimer() {

                long lastUpdate = 0;

                @Override
                public void handle(long now2) {
                    if (now2 - lastUpdate >= 1000_000_000l) {
                        lastUpdate = now2;
                        _currentCoins.setText("Shield On: " + _value);
                        _value--;
                        if (_value <= 0) {
                            _snake._shield = false;
                            _currentCoins.setVisible(false);
                            _currentCoins.setDisable(true);
                            this.stop();
                        }
                    }
                }
            };
            timer2.start();
        }
    }
}

