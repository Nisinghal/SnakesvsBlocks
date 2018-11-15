package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DestroyAllBlocks implements Token {
    int value;
    int frequency;
    int position;
    ImageView _destroyAll;

    DestroyAllBlocks(Image tok, int xsi, double[] xs, AnchorPane _blockPane) {
        _destroyAll = new ImageView();
        _destroyAll.setImage(tok);
        _destroyAll.setFitHeight(60);
        _destroyAll.setFitWidth(60);
        _destroyAll.setLayoutX(xs[xsi]);
        _destroyAll.setLayoutY(-52.5);
        _blockPane.getChildren().add(_destroyAll);

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

    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins) {
        disappear(_blockPane);
//        System.out.println(_blockPane.getChildren().size());
        ObservableList<Node> nodes = _blockPane.getChildren();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getClass().toString().equals("class javafx.scene.control.Label")) {
                Label _node= (Label) nodes.get(i);
//                System.out.println(_node.getStyle());
                String[] _nodecss = _node.getStyle().split("; ");
            if (_nodecss[1].equals("-fx-background-radius: 4px")) {
//                System.out.println("Removed"+_node.getText());
                _currentCoins.setText("" + (Integer.parseInt(_currentCoins.getText()) + Integer.parseInt(_node.getText())));
                _blockPane.getChildren().remove(nodes.get(i));
            }
            }
        }
    nodes =null;
}
}




