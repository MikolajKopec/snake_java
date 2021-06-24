import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene{
    Rect background,foreground;
    Snake snake;
    KListener keyListener;

    public Food food;

    public GameScene(KListener keyListener){
        background = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        foreground = new Rect(24,96,Constants.TILE_WIDTH*51,Constants.TILE_WIDTH*24);
        snake = new Snake(2,48,384,24,24,foreground);
        this.keyListener = keyListener;
        food = new Food(foreground,snake,24,24,Color.RED);
        food.spawn();
    }


    @Override
    public void update(double dt) {
        if(Constants.wsad == false){
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)){
            snake.changeDirection(Direction.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
            snake.changeDirection(Direction.DOWN);
        }else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)){
            snake.changeDirection(Direction.LEFT);
        }else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            snake.changeDirection(Direction.RIGHT);
        }}
        else{
            if (keyListener.isKeyPressed(KeyEvent.VK_W)){
                snake.changeDirection(Direction.UP);
            } else if (keyListener.isKeyPressed(KeyEvent.VK_S)){
                snake.changeDirection(Direction.DOWN);
            }else if (keyListener.isKeyPressed(KeyEvent.VK_A)){
                snake.changeDirection(Direction.LEFT);
            }else if (keyListener.isKeyPressed(KeyEvent.VK_D)){
                snake.changeDirection(Direction.RIGHT);
            }
        }

        if(!food.isSpawned)food.spawn();
        food.update(dt);
        snake.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.fill(new Rectangle2D.Double(background.x,background.y,background.height,background.width));
        g2.setColor(Color.white);
        g2.fill(new Rectangle2D.Double(foreground.x,foreground.y,foreground.height,foreground.width));
        snake.draw(g2);
        food.draw(g2);

    }
}
