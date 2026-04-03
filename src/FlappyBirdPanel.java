import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class FlappyBirdPanel extends JPanel implements ActionListener,KeyListener{

    final static int SCREEN_WIDTH=320;
    final static int SCREEN_HEIGHT=512;
    Timer gameloop;
    int birdx=SCREEN_WIDTH/12;
    int birdy=SCREEN_HEIGHT/2;
    final static int DELAY=75;
    boolean running=false;
    //sha venkagtsai aganepublic int velocity=0;
    public int gravity=2;
    public ArrayList<Pipes> pipes=new ArrayList<>(); 
    Random random=new Random();
    Timer pipesloop;
    Pipes firstPipe=new Pipes();
    int score =0;

    public FlappyBirdPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLUE);
        // gameloop=new Timer(DELAY, this);
        this.setFocusable(true);
        this.addKeyListener(this);
        //pipes.add(firstPipe);
        // pipesloop=new Timer(2700,new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Pipes pipe=new Pipes();
        //         pipes.add(pipe);
        //         repaint();
        //     }
        // });
        start();
    }
    public void start(){
        pipes.clear();
        birdx=SCREEN_WIDTH/12;
        firstPipe=new Pipes();
        birdy=SCREEN_HEIGHT/2;
        gravity=2;
        score=0;
        pipes.add(firstPipe);
        //System.err.println(pipes.toString());
        running=true;
        gameloop=new Timer(DELAY, this);
        pipesloop=new Timer(2700,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipes pipe=new Pipes();
                pipes.add(pipe);
                repaint();
            }
        });
        gameloop.start();
        pipesloop.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
        //bird
        g.setColor(Color.red);
        g.fillOval(birdx, birdy, 20, 20);

        //pipes
        for(int i=0;i<pipes.size();i++){
            g.setColor(Color.green);
            g.fillRect(/*pipes.get(i).pipex*/pipes.get(i).pipex, 0, 40,pipes.get(i).pipeheight);
            g.setColor(Color.green);
            g.fillRect(/*pipes.get(i).pipex*/pipes.get(i).pipex, pipes.get(i).pipeheight+80, 40,SCREEN_HEIGHT+10-pipes.get(i).pipeheight+50);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        g.drawString("Score:"+score, 10 , 20);
    }
    else{
        try {
            gameloop.stop();
            pipesloop.stop();
            Thread.sleep(100);
        } catch (Exception e) {
            // TODO: handle exception
        }
        gameover(g);
    }
    }

    public void gameover(Graphics g){
        //pipes.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, SCREEN_WIDTH,SCREEN_HEIGHT );
        g.setColor(Color.red);
        g.setFont(new Font("Ink free", Font.BOLD, 35));
        FontMetrics fontMetrics=getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH-fontMetrics.stringWidth("Game Over"))/2 ,SCREEN_HEIGHT/2);
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        fontMetrics=getFontMetrics(g.getFont());
        g.drawString("Score:"+score, (SCREEN_WIDTH-fontMetrics.stringWidth("Game Over"))/2 , SCREEN_HEIGHT/2 +50);
        g.setColor(Color.green);
        g.setFont(new Font("Ink Free", Font.BOLD, 18));
        fontMetrics=getFontMetrics(g.getFont());
        g.drawString("Press Space to restart", (SCREEN_WIDTH-fontMetrics.stringWidth("Press Space to restart"))/2 , SCREEN_HEIGHT/2 +100);
    }

    class Pipes{
        int pipex=SCREEN_WIDTH;
        int pipey=SCREEN_HEIGHT-SCREEN_HEIGHT/2;
        int pipeheight=random.nextInt(pipey/3,pipey);
        int pipevelocity=4;
    }

    public void checkCollisions(){
        if(birdx+15>=pipes.get(0).pipex && birdx+15<=pipes.get(0).pipex+40){
            if(pipes.get(0).pipeheight-2>=birdy || pipes.get(0).pipeheight+70<=birdy){
                running=false;
            }
        }
        if(birdy<=0 || birdy>=SCREEN_HEIGHT){
            running=false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(running==true){
        birdy+=gravity;
        gravity+=1;
        //gravity=Math.min(gravity,6);
        birdy=Math.max(birdy,0);
         for(int i=0;i<pipes.size();i++){
                        pipes.get(i).pipex-=pipes.get(i).pipevelocity;
                        if(pipes.get(i).pipex<-40){
                            pipes.remove(i);
                        }
                }
        if(pipes.get(0).pipex+40==birdx || pipes.get(0).pipex+40<birdx && pipes.get(0).pipex+44>birdx){
            score++;
        }
        checkCollisions();
        repaint();
       }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_SPACE){
        gravity=-6;
       }
       if(e.getKeyCode()==KeyEvent.VK_SPACE && running==false){
        start();
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }


}
