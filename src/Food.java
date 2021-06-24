import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class Food {
    public Rect background,rect;
    public Snake snake;
    public int width,height;
    public Color color;
    public String type = "apple";
    public boolean isSpawned = true;
    public BufferedImage java,apple;

    public Food(Rect background, Snake snake,int width, int height, Color color){
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rect=new Rect(0,0,width,height);
        try {
            java = ImageIO.read(new File("assets/java.png"));
            apple = ImageIO.read(new File("assets/apple.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void spawn(){
        do {
            double randX = (int)(Math.random()*(int)(background.height/Constants.TILE_WIDTH))*Constants.TILE_WIDTH + background.x;
            double randY =  (int)(Math.random() * (int)(background.width / Constants.TILE_WIDTH))*Constants.TILE_WIDTH + background.y;
            this.rect.x = randX;
            this.rect.y = randY;
        }while (snake.intersectingWithRect(this.rect));
        if((Math.random()*10)<5){
            this.type = "apple";
        } else {this.type = "java";}
    }
    public void update(double dt){
        if(snake.intersectingWithRect(this.rect)){
            snake.grow(this.type);
            if(this.type=="java"){
                if (Constants.EASY == true) {
                    snake.ogWaitBetweenUpdates = Constants.hardV;
                }else{
                    snake.ogWaitBetweenUpdates = 0.03f;
                }
                Constants.snakeColorActualy = Constants.snakeColor_y;
            }else{
                if (Constants.EASY == true) {
                    snake.ogWaitBetweenUpdates = Constants.easyV;
                }else{
                    snake.ogWaitBetweenUpdates = Constants.hardV;
                }
                Constants.snakeColorActualy = Constants.snakeColor_b;
            }
            this.rect.x = -100;
            this.rect.y = -50;
            isSpawned=false;
        }else{isSpawned = true;}


    }
    public void draw(Graphics2D g2){

        if(this.type == "apple") {
            g2.drawImage(apple, (int)this.rect.x,(int)this.rect.y ,width, height, null);
        }else if(this.type=="java"){
            g2.drawImage(java, (int)this.rect.x,(int)this.rect.y ,width, height, null);

        }
    }
}
