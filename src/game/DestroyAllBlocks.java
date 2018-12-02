package game;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class DestroyAllBlocks implements Token {
    int value;
    int frequency;
    int position;
    ImageView _destroyAll;

    DestroyAllBlocks(ArrayList<DestroyAllBlocks> _destroys, Image tok, int xsi, double[] xs, AnchorPane _blockPane) {
        _destroyAll = new ImageView();
        _destroyAll.setImage(tok);
        _destroyAll.setFitHeight(60);
        _destroyAll.setFitWidth(60);
        _destroyAll.setLayoutX(xs[xsi]);
        _destroyAll.setLayoutY(-52.5);
        _blockPane.getChildren().add(_destroyAll);
        _destroys.add(this);
    }

    public boolean checkCollision(Snake _snake) {
        return (_snake._snakeHead.getLayoutX() >= _destroyAll.getLayoutX() && _snake._snakeHead.getLayoutX() <= _destroyAll.getLayoutX() + 52.5 && 240 <= _destroyAll.getLayoutY() && 310 >= _destroyAll.getLayoutY());
    }

    public double getLayoutY() {
        return _destroyAll.getLayoutY();
    }

    public void disappear(AnchorPane _blockPane) {
        _blockPane.getChildren().remove(_destroyAll);
    }

    public void proceed() {
        _destroyAll.setLayoutY(_destroyAll.getLayoutY() + 3);
    }

    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentScore) {
        disappear(_blockPane);
       for (Block _block:playScreenController._blocks){
           if(_block._block.getLayoutY()<400) {
               _block._block.setDisable(true);
               System.out.println(_block._value);
               _block.collide(_blockPane, _snake, _currentScore);
           }
//        System.out.println(_blockPane.getChildren().size());
//        ObservableList<Node> nodes = _blockPane.getChildren();
//        System.out.println("start");
//        for (int i = 0; i < nodes.size(); i++) {

//            System.out.println(nodes.get(i).getClass());
//            if (nodes.get(i).getClass().toString().equals("class javafx.scene.control.Label")) {
//
//                Label _node = (Label) nodes.get(i);
//                System.out.println(_node.getText());
//                String[] _nodecss = _node.getStyle().split("; ");
//                if (_nodecss[1].equals("-fx-background-radius: 4px") && _node.getLayoutY() <= 420) {
//                    _n
//                    System.out.println(_node.getText()+" del");
////                System.out.println("Removed"+_node.getText());
//                    _currentCoins.setText("" + (Integer.parseInt(_currentCoins.getText()) + Integer.parseInt(_node.getText())));
//
////                Burst _burst=new Burst(_blockPane, nodes.get(i).getLayoutX(),nodes.get(i).getLayoutY(), Block.hex, Block.hexcodes);
//                    nodes.get(i).setVisible(false);
//                    _node.setDisable(true);
//                    _blockPane.getChildren().remove(_node);
//
////                Burst _burst = new
////
//                }
//            }
        }
//        nodes = null;
    }
}




