package game;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class playScreenController<timer> {

    //magnet icon
    @FXML
    File file1 = new File("src/resources/images/magnet.png");
    Image mag = new Image(file1.toURI().toString());
    //destroy all blocks icon
    @FXML
    File file2 = new File("src/resources/images/bomb.png");
    Image destroy = new Image(file2.toURI().toString());
    //coin icon
    @FXML
    File file3 = new File("src/resources/images/diamond.png");
    Image coin = new Image(file3.toURI().toString());
    //shield icon
    @FXML
    File file4 = new File("src/resources/images/magic-potion.png");
    Image magic = new Image(file4.toURI().toString());
    double[] xs = {2.5, 57.5, 112.5, 167.5, 222.5, 277.5};
//    @FXML
//    Label _snakeHead, _snakeHead1, _snakeHead11;
    @FXML
    private Text _snakeLength;
    @FXML
    private AnchorPane _blockPane;
    @FXML
    private AnchorPane _gameOver;
    @FXML
    Text _currentCoins;
    @FXML
    Label _endScore;
    @FXML
    Label _topScore;
    @FXML
    private  Text _currentScore;
    @FXML
    Text _magnetTimer;
    @FXML
    Text _shieldTimer;
    Snake _snake;

    @FXML
    protected void keyReleased(KeyEvent event) throws Exception {
        if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) && _snake.leftBoundaryCheck()) {
            _snake.moveLeft();
        }
        if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) && _snake.rightBoundaryCheck()) {
            _snake.moveRight();
        }
    }

    @FXML
    public void initialize() {
        _snake = new Snake(/*_snakeHead, _snakeHead1, _snakeHead11,*/ _snakeLength, _blockPane);
        _shieldTimer.setVisible(false);
        _shieldTimer.setStyle("-fx-blend-mode: difference ");
        _shieldTimer.setDisable(true);
        _magnetTimer.setVisible(false);
        _magnetTimer.setStyle("-fx-blend-mode: difference ");
        _magnetTimer.setDisable(true);
        _gameOver.setVisible(false);
        _gameOver.setDisable(true);
        _currentCoins.setText("0");
        _currentScore.setText("0");

        AnimationTimer timerx1 = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1500_000_000) {
                    for (int xsi = 0; xsi < 6; xsi++) {
                        Random rand = new Random();
                        if (rand.nextInt(100) < 50) {
                            int xxxx = rand.nextInt(50) + 1;
                            if (rand.nextInt(100) < 70) {
                                xxxx = rand.nextInt(Integer.parseInt(_snakeLength.getText())) + 1;
                            }
                            int value = xxxx;
                            lastUpdate = now;
                            Block _block = new Block(_blockPane, value, xs, xsi);
                            AnimationTimer timer2 = new AnimationTimer() {
                                private long lastUpdate2 = 0;
                                @Override
                                public void handle(long now2) {
                                    if (now2 - lastUpdate2 >= 8_000_000) {
                                        lastUpdate2 = now2;
                                        _block.proceed();
                                        if (_block.checkCollision(_snake)) {
                                            if(_snake.checkShieldOff()) _snake._decLength(_block, value, _blockPane);
                                            _currentScore.setText("" + (Integer.parseInt(_currentScore.getText()) + value));
                                            _blockPane.getChildren().remove(_block._block);
                                            this.stop();
                                        }
                                        if (_block._block.getLayoutY() > 480) {
                                            this.stop();
                                        }
                                    }
                                }
                            };
                            timer2.start();
                        }
                    }
                }
            }
        };
        timerx1.start();


        AnimationTimer timerx2 = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                Random rand = new Random();
                if (now - lastUpdate >= 1500_000_000l) {
                    int xsi = rand.nextInt(6);
                    int tokeno = rand.nextInt(5);
                    if (rand.nextInt(100) < 5) {
                        lastUpdate = now;
                        Token _token ;
                        if(_snake.checkMagnetOn() && tokeno==1) tokeno=0;
                        if(!_snake.checkShieldOff() && tokeno==4) tokeno=0;
                        final int no=tokeno;
                        if (tokeno == 1) _token= new Magnet( mag, xsi, xs, _blockPane);
                        else if (tokeno == 2) _token = new DestroyAllBlocks(destroy, xsi, xs, _blockPane);
                        else if (tokeno == 3) _token= new Coin(coin, xsi, xs, _blockPane, _currentCoins);
                        else if (tokeno == 4) _token = new Shield(magic, xsi, xs, _blockPane);
                        else  _token = new Ball(rand, xsi, xs, _blockPane);

                        AnimationTimer timer2 = new AnimationTimer() {
                            private long lastUpdate2 = 0;

                            @Override
                            public void handle(long now2) {
                                if (now2 - lastUpdate2 >= 8_000_000) {
                                    lastUpdate2 = now2;
                                    _token.proceed();
                                    if (_token.checkCollision(_snake)) {
                                       if(no==3) _token.collide(_snake, _blockPane, _currentCoins);
                                       else if(no==1) _token.collide(_snake, _blockPane, _magnetTimer);
                                       else if (no==2) _token.collide(_snake,_blockPane, _currentScore);
                                       else _token.collide(_snake, _blockPane, _shieldTimer);
                                        this.stop();
                                    }
                                    if (_token.getLayoutY() > 480) {
                                        this.stop();
                                    }
                                }
                            }
                        };
                        timer2.start();
                    }
                }
            }
        };
        timerx2.start();
    }

//    public void spawnToken(int tokeno, Image tok, int xsi) {
//        ImageView _token = new ImageView();
//        _token.setImage(tok);
//        _token.setFitHeight(60);
//        _token.setFitWidth(60);
//        _token.setLayoutX(xs[xsi]);
//        _token.setLayoutY(-52.5);
//        _blockPane.getChildren().add(_token);
//        AnimationTimer timer2 = new AnimationTimer() {
//            private long lastUpdate2 = 0;
//
//            @Override
//            public void handle(long now2) {
//                if (now2 - lastUpdate2 >= 8_000_000) {
//                    lastUpdate2 = now2;
//                    _token.setLayoutY(_token.getLayoutY() + 3);
//                    if (_snake._snakeHead.getLayoutX() > _token.getLayoutX() && _snake._snakeHead.getLayoutX() <= _token.getLayoutX() + 52.5 && 240 <= _token.getLayoutY() && 310 > _token.getLayoutY()) {
//                        if(tokeno==3) {
//                            _currentCoins.setText((Integer.parseInt(_currentCoins.getText())+ 1)+"");
//                        }_blockPane.getChildren().remove(_token);
//
//                        this.stop();
//                    }
//                }
//                if (_token.getLayoutY() > 480) {
//                    this.stop();
//                }
//            }
//        };
//        timer2.start();
//
//    }

    public void tapToStart(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/playscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    public void backHome(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }
}
