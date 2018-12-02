package game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * The Interface Token.
 */
public interface Token {
    
    /**
     * Disappear.
     *
     * @param _blockPane the block pane
     */
    public void disappear(AnchorPane _blockPane);

    /**
     * Collide.
     *
     * @param _snake the snake
     * @param _blockPane the block pane
     * @param _currentCoins the current coins
     */
    public void collide(Snake _snake, AnchorPane _blockPane, Text _currentCoins);

    /**
     * Proceed.
     */
    public void proceed();

    /**
     * Check collision.
     *
     * @param _snake the snake
     * @return true, if successful
     */
    public boolean checkCollision(Snake _snake);

    /**
     * Gets the layout Y.
     *
     * @return the layout Y
     */
    public double getLayoutY();
}