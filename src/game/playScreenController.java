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
    int[] xs = {0, 80, 160, 240, 320, 400};
    String[] hexcodes = {"#B0C4DE", "#B0E0E6", "#ADD8E6", "#87CEEB", "#87CEFA", "#00BFFF", "#1E90FF", "#6495ED", "#4682B4", "#4169E1", "#0000FF", "#0000CD", "#00008B", "#000080", "#191970"};
    @FXML
    Label _snakeHead, _snakeHead1, _snakeHead11;
    @FXML
    private Text _snakeLength;
    @FXML
    private AnchorPane _blockPane;
    @FXML
    private AnchorPane _gameOver;
    @FXML
    private Text _currentCoins;
    @FXML
    Label _endScore;
    @FXML
    Label _topScore;
    @FXML
    private Text _currentScore;
    @FXML
    ArrayList<Label> _snake;
    static double _snakeTailX, _snakeTailY;

    @FXML
    protected void keyReleased(KeyEvent event) throws Exception {
        if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) && (_snakeHead.getLayoutX() >= 80)) {

            _snakeLength.setLayoutX(_snakeLength.getLayoutX() - 80);
//            _snakeHead.setLayoutX(_snakeHead.getLayoutX() - 80);
//            _snakeHead1.setLayoutX(_snakeHead1.getLayoutX() - 80);
//            _snakeHead11.setLayoutX(_snakeHead11.getLayoutX() - 80);
            for (Label _snakeBody : _snake) {
                _snakeBody.setLayoutX(_snakeBody.getLayoutX() - 80);
            }
            if (_snake.size() > 0) _snakeTailX = _snake.get(_snake.size() - 1).getLayoutX();
        }

        if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) && (_snakeHead.getLayoutX() <= 400)) {
            _snakeLength.setLayoutX(_snakeLength.getLayoutX() + 80);
//            _snakeHead.setLayoutX(_snakeHead.getLayoutX() + 80);
//            _snakeHead1.setLayoutX(_snakeHead1.getLayoutX() + 80);
//            _snakeHead11.setLayoutX(_snakeHead11.getLayoutX() + 80);
            for (Label _snakeBody : _snake) {
                _snakeBody.setLayoutX(_snakeBody.getLayoutX() + 80);
            }
            if (_snake.size() > 0) _snakeTailX = _snake.get(_snake.size() - 1).getLayoutX();
//            _snakeHead.setLayoutX(_snakeHead.getLayoutX() + 80);
//            _snakeHead1.setLayoutX(_snakeHead1.getLayoutX() + 80);
//            _snakeHead2.setLayoutX(_snakeHead2.getLayoutX() + 80);
//            _snakeLength.setLayoutX(_snakeLength.getLayoutX() + 80);
//            _snakeTailX = _snakeHead2.getLayoutX()-20;
        }
//        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
//            _snakeHead.setCenterY(_snakeHead.getCenterY() - 80);
//        }
//        if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
//            _snakeHead.setCenterY(_snakeHead.getCenterY() + 80);
//        }
    }

    @FXML
    public void initialize() {
        _snake = new ArrayList<Label>();
//        System.out.println(_snakeHead.getLayoutY());

        _gameOver.setVisible(false);
        _gameOver.setDisable(true);
        _snake.add(_snakeHead);
        _snake.add(_snakeHead1);
        _snake.add(_snakeHead11);
        _snakeTailY = 580;
        _snakeTailX = 180;
        _snakeLength.setText("10");
        _currentCoins.setText("0");
        _currentScore.setText("0");
        AnimationTimer timerx1 = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1500_000_000) {
//                    System.out.println(now);
                    for (int xsi = 0; xsi < 6; xsi++) {
                        Random rand = new Random();

                        if (rand.nextInt(100) < 50) {
                            int value = rand.nextInt(50) + 1;
                            int hex = rand.nextInt(15);
                            lastUpdate = now;
                            Label _block = new Label();
                            _block.setTextAlignment(TextAlignment.CENTER);
//                            System.out.println(hexcodes[hex]);
                            _block.setStyle("-fx-background-color:" + hexcodes[hex] + "; -fx-background-radius: 4px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-family:'CENTURY GOTHIC'; -fx-font-size: 40; -fx-text-fill: WHITE;  -fx-alignment: CENTER;  -fx-border-radius: 4px");
                            _block.setText(String.valueOf(value));
                            _block.setMinSize(80, 80);
                            _block.setMaxSize(80, 80);
                            _block.setPrefSize(80, 80);
                            _block.setLayoutX(xs[xsi]);
                            _block.setLayoutY(-80);
                            _blockPane.getChildren().add(_block);
                            AnimationTimer timer2 = new AnimationTimer() {
                                private long lastUpdate2 = 0;

                                @Override
                                public void handle(long now2) {
                                    if (now2 - lastUpdate2 >= 8_000_000) {
                                        lastUpdate2 = now2;
                                        _block.setLayoutY(_block.getLayoutY() + 3);
                                        if (_snakeHead.getLayoutX() > _block.getLayoutX() && _snakeHead.getLayoutX() <= _block.getLayoutX() + 80 && 380 <= _block.getLayoutY() && 500 > _block.getLayoutY()) {
//                                            System.out.println(_block.getLayoutY() + " Collision");
                                            while (_snake.size() >= Integer.parseInt(_snakeLength.getText())-value && _snake.size()>0) {
                                                _blockPane.getChildren().remove(_snake.get(_snake.size() - 1));
                                                _snake.remove(_snake.size() - 1);
                                            }
//                                            int min = Math.min(sl, value);
                                            int sl = Integer.parseInt(_snakeLength.getText());
//long temptime=now2;
                                            if(sl-value>0) _snakeLength.setText("" + (sl - value));
                                            else {
                                                _snakeLength.setText("0");
//                                                _gameOver.setVisible(true);
//                                                _gameOver.setDisable(false);
//                                                _blockPane.setDisable(true);
//                                                _blockPane.setVisible(false);
//                                                _endScore.setText(""+_currentScore.toString());
//                                                _topScore.setText(""+_currentScore);
////                                                timerx1.sleep();
//                                                if(now2-temptime>=200_000_000l)
                                                    System.exit(0);
//                                                this.stop();
//                                                _game
//                                                return;
                                            }
                                            _currentScore.setText("" + (Integer.parseInt(_currentScore.getText()) + value));
//                                            for (int i = 0 ; i < min ; i++) {
//                                                _snakeLength.setText("" + (sl - i));
//                                                _block.setText(""+ (value - i));
//                                                _block.setLayoutY(_block.getLayoutY() - 15);
//                                                try {
//                                                    Thread.sleep(25);
//                                                }
//                                                catch (Exception e) {}
//                                                _block.setLayoutY(_block.getLayoutY() + 15);
//                                                try {
//                                                    Thread.sleep(25);
//                                                }
//                                                catch (Exception e) {}
//
//                                            }
                                            _blockPane.getChildren().remove(_block);
                                            this.stop();
                                        }
                                        if (_block.getLayoutY() > 800) {
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


//                //starts here
        AnimationTimer timerx2 = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                Random rand = new Random();
                if (now - lastUpdate >= 1500_000_000l) {
//                    Random rand = new Random();
                    int xsi = rand.nextInt(6);
                    int tokeno = rand.nextInt(5);
                    if (rand.nextInt(100) < 2) {
                        lastUpdate = now;
                        if (tokeno == 1) {
                            spawnToken(1, mag, xsi);

                        } else if (tokeno == 2) {
                            spawnToken(2, destroy, xsi);
                        } else if (tokeno == 3) {
                            spawnToken(3, coin, xsi);
                        } else if (tokeno == 4) {
                            spawnToken(4, magic, xsi);
                        } else if (tokeno == 0) {
                            System.out.println("Balll" + xsi);
                            spawnBall(rand, xsi);
                        }
                    }
                }
//                //ends here
            }
        };

        timerx2.start();
    }

    public void spawnBall(Random rand, int xsi) {
        int value = rand.nextInt(15) + 1;
        Label _ball = new Label();
        _ball.setTextAlignment(TextAlignment.CENTER);
        _ball.setStyle("-fx-background-color: WHITE; -fx-background-radius: 20px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-family:'CENTURY GOTHIC'; -fx-font-size: 22; -fx-text-fill: BLACK;  -fx-alignment: CENTER; -fx-font-weight: bold; -fx-border-radius: 20px");
        _ball.setText(String.valueOf(value));
        _ball.setMinSize(40, 40);
        _ball.setMaxSize(40, 40);
        _ball.setPrefSize(40, 40);
        _ball.setLayoutX(xs[xsi]);
        _ball.setLayoutY(-80);
        _blockPane.getChildren().add(_ball);
        AnimationTimer timer2 = new AnimationTimer() {
            private long lastUpdate2 = 0;

            @Override
            public void handle(long now2) {
                if (now2 - lastUpdate2 >= 8_000_000) {
                    lastUpdate2 = now2;
                    _ball.setLayoutY(_ball.getLayoutY() + 3);
                    if (_snakeHead.getLayoutX() > _ball.getLayoutX() && _snakeHead.getLayoutX() <= _ball.getLayoutX() + 80 && 380 <= _ball.getLayoutY() && 500 > _ball.getLayoutY()) {
//                        System.out.println(_ball.getLayoutY() + " Collision");
                        int sl = Integer.parseInt(_snakeLength.getText());
                        _snakeLength.setText("" + (sl + value));
                        _ball.setText("");
                        _ball.setLayoutX(_snakeTailX);
                        _ball.setLayoutY(_snakeTailY);
                        _snakeTailY = _snakeTailY + 40;
                        _snake.add(_ball);
                        this.stop();
                    }
                    if (_ball.getLayoutY() > 800) {
                        this.stop();
                    }
                }
            }
        };
        timer2.start();
    }

    public void spawnToken(int tokeno, Image tok, int xsi) {
        ImageView _token = new ImageView();
        _token.setImage(tok);
        _token.setFitHeight(60);
        _token.setFitWidth(60);
        _token.setLayoutX(xs[xsi]);
        _token.setLayoutY(-80);
        _blockPane.getChildren().add(_token);
        AnimationTimer timer2 = new AnimationTimer() {
            private long lastUpdate2 = 0;

            @Override
            public void handle(long now2) {
                if (now2 - lastUpdate2 >= 8_000_000) {
                    lastUpdate2 = now2;
                    _token.setLayoutY(_token.getLayoutY() + 3);
                    if (_snakeHead.getLayoutX() > _token.getLayoutX() && _snakeHead.getLayoutX() <= _token.getLayoutX() + 60 && 400 <= _token.getLayoutY() && 480 > _token.getLayoutY()) {
//                        System.out.println(_token.getLayoutY() + " Collision");
                        _blockPane.getChildren().remove(_token);
                        this.stop();
                    }
                }
                if (_token.getLayoutY() > 800) {
                    this.stop();
                }
            }
        };
        timer2.start();

    }

    public void tapToStart(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/playscreen.fxml")), 480, 800);
        Main.mainStage.setScene(s);
    }

    public void backHome(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 480, 800);
        Main.mainStage.setScene(s);
    }
}
