package game;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Class playScreenController.
 *
 * @param <timer> the generic type
 */
public class playScreenController<timer> {

    /** The file 1. */
    //magnet icon
    @FXML
    File file1 = new File("src/resources/images/magnet.png");
    
    /** The mag. */
    Image mag = new Image(file1.toURI().toString());
    
    /** The file 2. */
    //destroy all blocks icon
    @FXML
    File file2 = new File("src/resources/images/bomb.png");
    
    /** The destroy. */
    Image destroy = new Image(file2.toURI().toString());
    
    /** The file 3. */
    //coin icon
    @FXML
    File file3 = new File("src/resources/images/diamond.png");
    
    /** The coin. */
    Image coin = new Image(file3.toURI().toString());
    
    /** The file 4. */
    //shield icon
    @FXML
    File file4 = new File("src/resources/images/magic-potion.png");
    
    /** The magic. */
    Image magic = new Image(file4.toURI().toString());
    
    /** The xs. */
    double[] xs = {2.5, 57.5, 112.5, 167.5, 222.5, 277.5};
//    @FXML
/** The snake length. */
//    Label _snakeHead, _snakeHead1, _snakeHead11;
    @FXML
    private Text _snakeLength;
    
    /** The block pane. */
    @FXML
    private AnchorPane _blockPane;
    
    /** The game over. */
    @FXML
    private AnchorPane _gameOver;
    
    /** The current coins. */
    @FXML
    Text _currentCoins;
    
    /** The end score. */
    @FXML
    Label _endScore;
    
    /** The top score. */
    @FXML
    Label _topScore;
    
    /** The current score. */
    @FXML
    private  Text _currentScore;
    
    /** The magnet timer. */
    @FXML
    Text _magnetTimer;
    
    /** The shield timer. */
    @FXML
    Text _shieldTimer;
    
    /** The snake. */
    Snake _snake;

    /** The paused. */
    static Boolean _paused;
//    Label _wall1;
//    Label _particle1;
/** The blocks. */
// ArrayList<Ball> _balls;
     static ArrayList<Block> _blocks;
    
    /** The coins. */
    static ArrayList<Coin> _coins;
    
    /** The destroys. */
    static ArrayList<DestroyAllBlocks> _destroys;
    
    /** The magnets. */
    static ArrayList<Magnet> _magnets;
    
    /** The shields. */
    static ArrayList<Shield> _shields;
    
    /** The walls. */
    static ArrayList<Wall> _walls;
    
    /**
     * Key released.
     *
     * @param event the event
     * @throws Exception the exception
     */
    @FXML
    protected void keyReleased(KeyEvent event) throws Exception {
        if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) && _snake.leftBoundaryCheck()) {
            _snake.moveLeft();
        }
        if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) && _snake.rightBoundaryCheck()) {
            _snake.moveRight();
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        if (!Main.resumed) {
            System.out.println("New Game");
            _paused = false;
            //        _balls=new ArrayList<Ball>();
            _blocks = new ArrayList<Block>();
            _coins = new ArrayList<Coin>();
            _destroys = new ArrayList<DestroyAllBlocks>();
            _magnets = new ArrayList<Magnet>();
            _shields = new ArrayList<Shield>();
            _walls = new ArrayList<Wall>();
            //        Wall _wall= new Wall();
            _snake = new Snake(/*_snakeHead, _snakeHead1, _snakeHead11,*/ /*_balls, */_snakeLength, _blockPane, "10");
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
        }
        else {
            System.out.println("Resume Game");
            Main.resumed = false;
            _paused = false;
            _blocks = new ArrayList<Block>();
            _coins = new ArrayList<Coin>();
            _destroys = new ArrayList<DestroyAllBlocks>();
            _magnets = new ArrayList<Magnet>();
            _shields = new ArrayList<Shield>();
            _walls = new ArrayList<Wall>();
            _snake = new Snake(_snakeLength, _blockPane, Main._sg.snakeLength);
            _shieldTimer.setVisible(false);
            _shieldTimer.setStyle("-fx-blend-mode: difference ");
            _shieldTimer.setDisable(true);
            _magnetTimer.setVisible(false);
            _magnetTimer.setStyle("-fx-blend-mode: difference ");
            _magnetTimer.setDisable(true);
            _gameOver.setVisible(false);
            _gameOver.setDisable(true);
            System.out.println(Main._sg.currentScore);
            _currentCoins.setText(Main._sg.currentCoins);
            _currentScore.setText(Main._sg.currentScore);

        }

        if (!_paused) {
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
                                    if (Integer.parseInt(_snakeLength.getText()) < 50)
                                        xxxx = rand.nextInt(Integer.parseInt(_snakeLength.getText())) + 1;
                                }
                                int value = xxxx;
                                lastUpdate = now;
                                Block _block = new Block(_blocks, _blockPane, value, xs, xsi);
                                AnimationTimer timer2 = new AnimationTimer() {
                                    private long lastUpdate2 = 0;

                                    @Override
                                    public void handle(long now2) {
                                        try {
                                            if (now2 - lastUpdate2 >= 8_000_000) {
                                                lastUpdate2 = now2;
                                                _block.proceed();
                                                if (_block.checkCollision(_snake)) {
                                                    _block.collide(_blockPane, _snake, _currentScore, _blocks);
                                                    if (_block._value > 5) {
                                                        SequentialTransition seqTransition = new SequentialTransition(new PauseTransition(Duration.millis(_block._value * 4000)));// wait a second);
                                                    }
                                                    this.stop();
                                                }
                                                if (_block._block.getLayoutY() > 480) {
                                                    this.stop();
                                                }
                                            }
                                        } catch (Exception InterruptedException) {
//                                        this.stop();
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
        }



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
                        Token _token;
                        if (_snake.checkMagnetOn() && tokeno == 1) tokeno = 0;
                        if (!_snake.checkShieldOff() && tokeno == 4) tokeno = 0;
                        final int no = tokeno;
                        if (tokeno == 1) _token = new Magnet(_magnets, mag, xsi, xs, _blockPane);
                        else if (tokeno == 2) _token = new DestroyAllBlocks(_destroys, destroy, xsi, xs, _blockPane);
                        else if (tokeno == 3) _token = new Coin(_coins, coin, xsi, xs, _blockPane, _currentCoins);
                        else if (tokeno == 4) _token = new Shield(_shields, magic, xsi, xs, _blockPane);
                        else _token = new Ball(/*_balls,*/ rand, xsi, xs, _blockPane);

                        AnimationTimer timer2 = new AnimationTimer() {
                            private long lastUpdate2 = 0;

                            @Override
                            public void handle(long now2) {
                                if (now2 - lastUpdate2 >= 8_000_000) {
                                    lastUpdate2 = now2;
                                    _token.proceed();
                                    if (_token.checkCollision(_snake)) {
                                        if (no == 3) _token.collide(_snake, _blockPane, _currentCoins);
                                        else if (no == 1) _token.collide(_snake, _blockPane, _magnetTimer);
                                        else if (no == 2) _token.collide(_snake, _blockPane, _currentScore);
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

    /**
 * Tap to start.
 *
 * @param actionEvent the action event
 * @throws Exception the exception
 */
public void tapToStart(ActionEvent actionEvent) throws Exception {
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/playscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    /**
     * Back home.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void backHome(ActionEvent actionEvent) throws Exception {
        Main._sg.currentCoins = _currentCoins.getText();
        Main._sg.currentScore = _currentScore.getText();
        Main._sg.snakeLength = _snakeLength.getText();
        Main.save();
        Scene s = new Scene(FXMLLoader.load(getClass().getResource("../resources/fxml/frontscreen.fxml")), 320, 480);
        Main.mainStage.setScene(s);
    }

    /**
     * Save game.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
    public void saveGame(ActionEvent actionEvent) throws Exception {
        System.out.println("SAVING");
        Main._sg.currentCoins = _currentCoins.getText();
        Main._sg.currentScore = _currentScore.getText();
        Main._sg.snakeLength = _snakeLength.getText();
        Main.save();
    }
}
