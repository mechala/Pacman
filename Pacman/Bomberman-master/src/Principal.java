
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author examen
 */
public class Principal extends JFrame {

    public Thread movieLoop;
    public Canvas c;
    public Pacman J1;
    public Fantasma E1;
    public int[][] mundo = {{1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1}
    };
    public Principal(int w, int h)throws Exception{
        c= new Canvas();
        this.setSize(w, h);
        c.setSize(w, h);
        this.add(c);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
              
            }

            @Override
            public void keyPressed(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:{J1.currentDirection=Pacman.UP; break;}
                   case KeyEvent.VK_DOWN:{J1.currentDirection=Pacman.DOWN; break;}
                   case KeyEvent.VK_LEFT:{J1.currentDirection=Pacman.LEFT; break;}
                   case KeyEvent.VK_RIGHT:{J1.currentDirection=Pacman.RIGTH; break;}
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:{J1.currentDirection=Pacman.NONE; break;}
                   case KeyEvent.VK_DOWN:{J1.currentDirection=Pacman.NONE; break;}
                   case KeyEvent.VK_LEFT:{J1.currentDirection=Pacman.NONE; break;}
                   case KeyEvent.VK_RIGHT:{J1.currentDirection=Pacman.NONE; break;}
               }
            }
        });
         E1=new Fantasma(100,100,2,2, "Fantasma");
        J1=new Pacman(50, 50, 2, 2, "Pacman");
        String []names={"Arriba","Derecha","Abajo","Izquierda"};
        E1.loadPics(names);
        J1.loadPics(names);
        movieLoop=new Thread(new Runnable() {

            @Override
            public void run() {
                c.createBufferStrategy(2);
                Graphics g=c.getBufferStrategy().getDrawGraphics();
                long startTime=System.currentTimeMillis();
                long currentTime=0;
                while(true){
                    try{
                        g.setColor(Color.BLACK);
                        g.fillRect(0,0, c.getWidth(), c.getHeight());                        
                        /*for (int i = 0; i < 6; i++) {
                            for (int j = 0; j < 8; j++) {
                                if(mundo[i][j]==1){
                                    g.setColor(Color.BLUE);
                                    g.fillRect(100*j,100*i, 100, 100);
                                }else{
                                    g.setColor(Color.BLACK);
                                    g.fillRect(100*j,100*i, 100, 100);
                                }
                            }
                        }*/
                        currentTime=System.currentTimeMillis()-startTime;
                        switch(J1.currentDirection){
                            case Pacman.RIGTH:{ J1.moveRigth(currentTime); break;}
                            case Pacman.DOWN:{J1.moveDown(currentTime); break;}
                            case Pacman.LEFT:{ J1.moveLeft(currentTime); break;}
                            case Pacman.UP:{J1.moveUp(currentTime); break;}
                        }
                        J1.draw(g);
                        E1.draw(g);
                        Thread.sleep(30);
                        c.getBufferStrategy().show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    
    public static void main(String[] args) {
        try{
            Principal p= new Principal(500,500);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setVisible(true);
            p.movieLoop.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
