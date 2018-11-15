package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    public double getLayoutY(){
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
        for (Object _node : _blockPane.getChildren()) {
            if (_node instanceof Block) _blockPane.getChildren().remove(_node);
        }


    }
}
