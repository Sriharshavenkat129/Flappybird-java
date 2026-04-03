
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    GameFrame(){
        FlappyBirdPanel birdgame=new FlappyBirdPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("snake game");
        this.setResizable(false);
        this.add(birdgame);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }    
}
