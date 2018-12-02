package game;
//
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.TextAlignment;
//
//import java.util.Random;
//
//public class Wall {
////    int frequency;
////    Point Position;
////    int Length;
//
//    Block(AnchorPane _blockPane, double[] xs, int xsi) {
//        _block = new Label();
//        Random rand = new Random();
//        int hex = rand.nextInt(15);
//        _block.setTextAlignment(TextAlignment.CENTER);
//        _block.setStyle("-fx-background-color:" + hexcodes[hex] + "; -fx-background-radius: 4px; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-family:'CENTURY GOTHIC'; -fx-font-size: 30; -fx-text-fill: WHITE;  -fx-alignment: CENTER;  -fx-border-radius: 4px");
//        _block.setText(String.valueOf(value));
//        _block.setMinSize(52.5, 52.5);
//        _block.setMaxSize(52.5, 52.5);
//        _block.setPrefSize(52.5, 52.5);
//        _block.setLayoutX(xs[xsi]);
//        _block.setLayoutY(-52.5);
//        _blockPane.getChildren().add(_block);
//    }
//
//}

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

//provide random numbers for direction of particle


public class Burst {
    Random random = new Random();
    double initialSteps = 100;//number of steps until removed
    //    double deltaX;//change in x location per step
    double deltaY;//change in y location per step
    //    @FXML
    Label _particle;
    ArrayList<Label> _particles;
    Random r;
    double x;
    double xs;
    double ys;
    double y;
    //    Timer timer;
    AnchorPane _blockPane;

//    @override
//    class task extends TimerTask {
//    public void run() {
//        System.out.println("run");
//
//        for (Label _particle : _particles) {
//            _particle.setDisable(true);
//            _blockPane.getChildren().remove(_particle);
//            timer.cancel();
//        }
//    }


    Burst(AnchorPane blockPane, double X, double Y, int hex, String[] hexcodes) {
        r = new Random();
        _particles = new ArrayList<Label>();
        AnimationTimer timer2 = new AnimationTimer() {
            private long lastUpdate2 = 0;

            @Override
            public void handle(long now2) {
//                System.out.println("HII");
                for (int i = 0; i < 20; i++) {
                    lastUpdate2 = now2;
//                    System.out.println(1);
                    _particle = new Label();
                    _particles.add(_particle);
                    int h = r.nextInt(hex + 1);
                    _particle.setStyle("-fx-background-color: " + hexcodes[h] +/*; -fx-background-radius: 10*/"; -fx-background-size: 10; -fx-pref-width: 5; -fx-pref-height: 5; -fx-font-size: 1");
//          System.out.println(X + " " + Y);
                    _particle.setLayoutX(X + 26);
                    _particle.setLayoutY(Y + 26);
                    blockPane.getChildren().add(_particle);
                    _blockPane = blockPane;
                    TranslateTransition translate = new TranslateTransition();

                    //shifting the X coordinate of the centre of the circle by 400

                    x = r.nextDouble();
                    y = r.nextDouble();
                    xs = r.nextInt(2);

                    xs = x * 30 * (double) (Math.pow(-1, (int) xs));
                    ys = r.nextInt(2);
                    ys = y * 30 * (double) (Math.pow(-1, (int) ys));
//            System.out.println(xs + " " + ys);
                    translate.setByX(xs);
                    translate.setByY(ys);

                    //setting the duration for the Translate transition
                    translate.setDuration(Duration.millis(500));

                    //setting cycle count for the Translate transition
//            translate.setCycleCount(50);

                    //setting Circle as the node onto which the transition will be applied
                    translate.setNode(_particle);

                    //playing the transition
                    translate.play();
                }
                System.out.println("done");
                AnimationTimer timer = new AnimationTimer() {
                    private long lastUpdate = 0;

                    @Override
                    public void handle(long now) {
//                        System.out.println(now2 - now);

                        if (now - now2 >=20_000_0000) {

                            for (Label _particle : _particles) {
//                                System.out.println("say hi");
                                _particle.setDisable(true);
//                                _blockPane.getChildren().remove(_particle);
                            }
//                            lastUpdate=now;
//                            this.stop();
                        }

                        if (now - now2 >= 40_000_0000) {

                            for (Label _particle : _particles) {
//                                System.out.println("say hi");
                                _particle.setDisable(true);
                                    _blockPane.getChildren().remove(_particle);
                            }
                            lastUpdate=now;
                            this.stop();
                        }

//                    this.stop();
                    }
                };timer.start();
                this.stop();
            }
                };
        timer2.start();
    }
}

//            System.out.println(translate.getStatus());
//            while (translate.getStatus().equals(toString().equals("RUNNING"))) {
//
//            }
//            timer = new Timer();
//
//            timer.schedule(new task(), 100);


//
//
//            translate.setOnFinished(_blockPane.getChildren().remove(_particle));
//        _particle.setTranslateX(25);
//        _particle.setTranslateY(25);
        //Set radnom direction, squere technique.
//        deltaX = 1.0 - random.nextFloat()*2.0;
//        deltaY = 1.0 - random.nextFloat()*2.0;
