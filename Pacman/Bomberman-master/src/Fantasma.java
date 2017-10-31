
import java.awt.Graphics;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labcienciascomputacion8
 */
public class Fantasma {
    

    public static final int UP=0;
    public static final int RIGTH=1;
    public static final int DOWN=2;
    public static final int LEFT=3;
    public static final int NONE=-1;
    
    Animation[] animations;
    int x;
    int y;
    int vx;
    int vy;
    String path;
    int currentAnimation;
    int currentDirection;
    
    public Fantasma(int x, int y, int vx, int vy, String path){
        this.path=path;
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.currentDirection=-1;
        animations=new Animation[4];
    }
    
    public void loadPics(String[] names)throws Exception{
        for (int j=0;j<4;j++) {
            String name=names[j];
            animations[j]=new Animation();
            
                System.out.println(path+"//"+name+".png");
                animations[j].addScene(
                new ImageIcon(getClass().getResource(path+"//"+name+".png")).getImage()        
                        , 100);
            
        }
       
    }
    public void moveRigth(long time){
        x+=vx;
        currentAnimation=Pacman.RIGTH;
        animations[Pacman.RIGTH].update(time);
    }
    
    public void moveLeft(long time){
        x-=vx;
        currentAnimation=Pacman.LEFT;
        animations[Pacman.LEFT].update(time);
    }
    
     public void moveUp(long time){
        y-=vy;
        currentAnimation=Pacman.UP;
        animations[Pacman.UP].update(time);
    }
     
     public void moveDown(long time){
        y+=vy;
        currentAnimation=Pacman.DOWN;
        animations[Pacman.DOWN].update(time);
    }
     
     public void draw(Graphics g){
         g.drawImage(animations[currentAnimation].getImage(),x,y,null);
     }
}

