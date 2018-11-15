package game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public interface Token {
    public void disappear(AnchorPane _blockPane);

    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins);

    public void proceed();

    public boolean checkCollision(Snake _snake);

    public double getLayoutY();
}