import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MListener extends MouseAdapter implements MouseMotionListener {
    public boolean isPresed = false;
    public double x = 0, y = 0;

    @Override
    public void mousePressed(MouseEvent e){
        isPresed = true;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        isPresed = false;
    }
    @Override
    public  void mouseMoved(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public boolean isPresed(){
        return this.isPresed;
    }
}
