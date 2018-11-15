package game;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Magnet implements Token {
    static int _value;
    int frequency;
    int position;
    ImageView _magnet;

    Magnet(Image tok, int xsi, double[] xs, AnchorPane _blockPane) {
        _value = 0;
        _magnet = new ImageView();
        _magnet.setImage(tok);
        _magnet.setFitHeight(60);
        _magnet.setFitWidth(60);
        _magnet.setLayoutX(xs[xsi]);
        _magnet.setLayoutY(-52.5);
        _blockPane.getChildren().add(_magnet);
    }

    public boolean checkCollision(Snake _snake) {
        return (_snake._snakeHead.getLayoutX() >= _magnet.getLayoutX() && _snake._snakeHead.getLayoutX() <= _magnet.getLayoutX() + 52.5 && 240 <= _magnet.getLayoutY() && 310 >= _magnet.getLayoutY());
    }

    public void proceed() {
        _magnet.setLayoutY(_magnet.getLayoutY() + 3);
    }

    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_magnet);
    }

    public double getLayoutY() {
        return _magnet.getLayoutY();
    }

    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins) {
        disappear(_blockPane);
        _snake._magnet = true;
        _value = 10;

        _currentCoins.setDisable(false);
        _currentCoins.setVisible(true);
        _currentCoins.setText("Magnet On: 10");

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                for (Object _node : _blockPane.getChildren()) {
                    if (_node instanceof Coin) {
                        Coin node = (Coin) _node;
                        node.collide(_snake, _blockPane, _currentCoins);
                    } else if (_node instanceof Ball) {
                        Ball node = (Ball) _node;
                        node.collide(_snake, _blockPane, _currentCoins);
                    }
                    if (now - lastUpdate >= 1000_000_000l) {
                        lastUpdate = now;
                        _currentCoins.setText("Magnet On: " + _value);
                        _value--;
                        if (_value <= 0) {
                            _snake._magnet = false;
                            _currentCoins.setVisible(false);
                            _currentCoins.setDisable(true);
                            this.stop();
                        }
                    }

                }

            }
        };
        timer.start();
    }

}

