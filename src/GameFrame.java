
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    GameFrame(){
        snakegamelabel snakegame=new snakegamelabel();
        FlappyBirdPanel birdgame=new FlappyBirdPanel();
        PingPong game=new PingPong();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("snake game");
        this.setResizable(false);
        this.add(birdgame);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }    
}
