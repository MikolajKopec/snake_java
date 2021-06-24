import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene{
    int i =0;
    public KListener keyListener;
    public MListener mouseListener;
    public BufferedImage main, play,play_presed,options,options_presed,exit,exit_presed,options2,options2_pressed;
    public Rect playRect,optionsRect,exitRect,options2Rect;

    public BufferedImage play_current,options_current,exit_current,options2_current;

    public MenuScene(KListener keyListener, MListener mouseListener){
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try{
//            Główny obrazek
            main = ImageIO.read(new File("assets/menu_main_image.png"));
//            Opcje gry
            play = ImageIO.read(new File("assets/PlayButton.png"));
            play_presed = ImageIO.read(new File("assets/PlayButtonPressed.png"));
            options = ImageIO.read(new File("assets/OptionsButton.png"));
            options_presed = ImageIO.read(new File("assets/OptionsButtonPressed.png"));
            options2 = ImageIO.read(new File("assets/Options2Button.png"));
            options2_pressed = ImageIO.read(new File("assets/Options2ButtonPressed.png"));
            exit = ImageIO.read(new File("assets/ExitButton.png"));
            exit_presed = ImageIO.read(new File("assets/ExitButtonPressed.png"));

        }   catch (Exception e){
            e.printStackTrace();
        }

        play_current = play;
        options_current = options;
        options2_current = options2;
        exit_current = exit;

        playRect = new Rect(700,150,192,128);
        optionsRect = new Rect(700,290,192,128);
        options2Rect = new Rect(700, 430,192,128);
        exitRect = new Rect(700,570,192,128);
    }

    @Override
    public void update(double dt) {
        if (mouseListener.getX()>=playRect.x && mouseListener.getX() <= playRect.x+playRect.height &&
                mouseListener.getY()>= playRect.y && mouseListener.getY() <= playRect.y + playRect.width){
            play_current = play_presed;
            if (mouseListener.isPresed()){
                Window.getWindow().changeState(1);
            }
        }
        else {
            play_current = play;
        }
        if (mouseListener.getX()>=options2Rect.x && mouseListener.getX() <= options2Rect.x+options2Rect.height &&
                mouseListener.getY()>= options2Rect.y && mouseListener.getY() <=options2Rect.y + options2Rect.width){
            if (mouseListener.isPresed()){
                Constants.wsad = true;
            }else{options2_current = options2_pressed;}
        }
        else {
            options2_current = options2;
        }
        if (mouseListener.getX()>=exitRect.x && mouseListener.getX() <= exitRect.x+exitRect.height &&
                mouseListener.getY()>= exitRect.y && mouseListener.getY() <=exitRect.y + exitRect.width){
            exit_current = exit_presed;
            if (mouseListener.isPresed()){
                Window.getWindow().close();
            }
        }
        else {
            exit_current = exit;
        }
        if (mouseListener.getX()>=optionsRect.x && mouseListener.getX() <= optionsRect.x+optionsRect.height &&
                mouseListener.getY()>= optionsRect.y && mouseListener.getY() <=optionsRect.y + optionsRect.width){
            if (mouseListener.isPresed()){
                Constants.wsad = true;
            }else{options_current = options_presed;}
        }
        else {
            options_current = options;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.decode("#f6db94"));
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        // python
        g.drawImage(main, 150,250 ,200, 200, null);
        // options
        g.drawImage(play_current, (int)playRect.x,(int)playRect.y ,(int)playRect.height,(int)playRect.width, null);
        g.drawImage(options_current, (int)optionsRect.x,(int)optionsRect.y ,(int)optionsRect.height,(int)optionsRect.width, null);
        g.drawImage(options2_current, (int)options2Rect.x,(int)options2Rect.y ,(int)options2Rect.height,(int)options2Rect.width, null);
        g.drawImage(exit_current, (int)exitRect.x,(int)exitRect.y ,(int)exitRect.height,(int)exitRect.width, null);

    }
}
