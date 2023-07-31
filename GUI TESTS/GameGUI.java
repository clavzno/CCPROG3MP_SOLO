import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameGUI extends JFrame {
    public GameGUI() {
        this.setTitle("Vending Sim");
        this.setSize(400, 400);
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("Sprites\\gold plus.png");
        this.setIconImage(icon.getImage());
    }
}
