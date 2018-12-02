package game;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.util.ArrayList;
public class Magnet implements Token {
    static int _value;
    int frequency;
    int position;
    ImageView _magnet;

    Magnet(ArrayList<Magnet> _magnets,Image tok, int xsi, double[] xs, AnchorPane _blockPane) {
        _value = 0;
        _magnet = new ImageView();
        _magnet.setImage(tok);
        _magnet.setFitHeight(60);
        _magnet.setFitWidth(60);
        _magnet.setLayoutX(xs[xsi]);
        _magnet.setLayoutY(-52.5);
        _blockPane.getChildren().add(_magnet);
        _magnets.add(this);
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

    public void collide(Snake _snake, AnchorPane _blockPane, Text _text) {
        disappear(_blockPane);
        _snake._magnet = true;
        _value = 10;

        _text.setDisable(false);
        _text.setVisible(true);
        _text.setText("Magnet On: 10");

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                    ObservableList<Node> nodes = _blockPane.getChildren();
                    for (int i = 0; i < nodes.size(); i++) {
                        if (nodes.get(i).getClass().toString().equals("class javafx.scene.control.Label")) {
                            Label _node= (Label) nodes.get(i);
                            String[] _nodecss = _node.getStyle().split("; ");
//                            System.out.println(_nodecss.length);
//                            if(_nodecss.length==1)System.out.println(_nodecss[1]);
                            if (_nodecss[1].equals("-fx-background-radius: 20px") && _nodecss[4].equals("-fx-border-radius: 20px")==false) {
//                                _node.collide(_snake, _blockPane, _currentCoins);
                                System.out.println("ballremoved");
//                                if(_node.getLayoutY()>= 230 || _node.getLayoutY()<= 320)
                                _snake._incLength(Integer.parseInt(_node.getText()), _blockPane);
                                _blockPane.getChildren().remove(_node);
                            }
                        }
                        else if (nodes.get(i).getClass().toString().equals("class javafx.scene.image.ImageView")) {
                            ImageView _node= (ImageView) nodes.get(i);
                            if (_node.getImage().equals(Coin._coinImage)){
//                                _node.collide(_snake, _blockPane, _currentCoins);
                                System.out.println("coinremoved");
//                                if(_node.getLayoutY()>= 200 || _node.getLayoutY()<= 320){
                                    Coin.setCurrentCoins(Coin.getCurrentCoins() + 1);
                                    _blockPane.getChildren().remove(_node);
//                                }
                            }
                        }
                    }
                    nodes =null;

                    if (now - lastUpdate >= 1000_000_000l) {
                        lastUpdate = now;
                        _text.setText("Magnet On: " + _value);
                        _value--;
                        if (_value <= 0) {
                            _snake._magnet = false;
                            _text.setVisible(false);
                            _text.setDisable(true);
                            this.stop();
                        }
                    }

                }


        };
        timer.start();
    }

}

