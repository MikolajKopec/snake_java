import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    //długość tablicy - bug
    public Rect[] body = new Rect[100000];
    public double bodyWidth,bodyHeight;

    public int size,head = 0,tail = 0;
    // prędkość węża
    public double ogWaitBetweenUpdates;
    public double waitTimeLeft = ogWaitBetweenUpdates;
    public Rect background;
    public Direction direction = Direction.RIGHT;

    public Snake(int size,double startX, double startY,double bodyWidth, double bodyHeight,Rect background){
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;

        for (int i = 0; i<=size;i++){
            Rect bodyPiece = new Rect(startX+i*bodyWidth,startY,bodyWidth,bodyHeight);
            body[i] = bodyPiece;
            head++;
        }
        head--;
        if(Constants.EASY==true){
            ogWaitBetweenUpdates = Constants.easyV;
        }else{
            ogWaitBetweenUpdates = Constants.hardV;
        }
        Constants.snakeColorActualy = Constants.snakeColor_b;
    }
    public void grow(String type){
        int num;
        if(type == "java"){
            num = 1;
        }else{num = 0;}
        for(int i = 0;i<=num;i++) {
            double newX = 0;
            double newY = 0;

            if (direction == Direction.RIGHT) {
                newX = body[tail].x - bodyWidth;
                newY = body[tail].y;
            } else if (direction == Direction.LEFT) {
                newX = body[tail].x + bodyWidth;
                newY = body[tail].y;
            } else if (direction == Direction.UP) {
                newX = body[tail].x;
                newY = body[tail].y + bodyHeight;
            } else if (direction == Direction.DOWN) {
                newX = body[tail].x;
                newY = body[tail].y - bodyHeight;
            }

            Rect newBodyPiece = new Rect(newX, newY, bodyWidth, bodyHeight);

            tail = (tail - 1) % body.length;
            body[tail] = newBodyPiece;
        }
    }
    public void draw(Graphics2D g2){
        for (int i =tail; i!=head; i = (i+1)%body.length){
            Rect piece = body[i];
            double subWidth = 2,subHight;

            g2.setColor(Constants.snakeColorActualy);
            g2.fill(new Rectangle2D.Double(piece.x,piece.y,piece.width,piece.height));
            g2.setColor(Constants.snakeBorder);
            g2.draw(new Rectangle2D.Double(piece.x,piece.y,piece.width,piece.height));
        }
    }
    public boolean intersectingWithSelf(){
        Rect headR = body[this.head];
        return intersectingWithRect(headR) || intesectingWithWall(headR);
    }

    public boolean intersectingWithRect(Rect rect){
        for (int i =tail; i!=head-1; i = (i+1)%body.length){
            if(intersecting(rect,body[i])){
                return true;
            }
        }
        return false;
    }

    public boolean intersecting(Rect r1,Rect r2){
        return(r1.x>= r2.x && r1.x+r1.width<=r2.x+r2.width &&
                r1.y>= r2.y && r1.y+r1.height<=r2.y+r2.height);
    }
    public boolean intesectingWithWall(Rect head){
        return(head.x<background.x || head.x+ head.width>background.x + background.height||
                head.y<background.y || head.y+head.height>background.y + background.width);
    }

    public void changeDirection(Direction newDirection){
        if(newDirection == Direction.RIGHT && direction !=Direction.LEFT){
            direction = newDirection;
        }else if(newDirection == Direction.LEFT && direction !=Direction.RIGHT){
            direction = newDirection;
        }else if(newDirection == Direction.UP && direction !=Direction.DOWN){
            direction = newDirection;
        }else if(newDirection == Direction.DOWN && direction !=Direction.UP){
            direction = newDirection;
        }

    }
    public void update(double dt){
        if (intersectingWithSelf()){
            Window.getWindow().changeState(0);
        }
        if(waitTimeLeft>0){
            waitTimeLeft -= dt;
            return;
        }
        waitTimeLeft = ogWaitBetweenUpdates;
        double newX = 0;
        double newY = 0;
        if (direction == Direction.RIGHT){
            newX = body[head].x+bodyWidth;
            newY = body[head].y;
        } else if(direction == Direction.LEFT){
            newX = body[head].x-bodyWidth;
            newY = body[head].y;
        }else if(direction == Direction.UP){
            newX = body[head].x;
            newY = body[head].y-bodyHeight;
        }
        else if(direction == Direction.DOWN){
            newX = body[head].x;
            newY = body[head].y+bodyHeight;
        }

        body[(head+1)%body.length] = body[tail];
        body[tail] = null;
        head = (head+1)%body.length;
        tail = (tail+1)%body.length;

        body[head].x = newX;
        body[head].y = newY;

    }
}
