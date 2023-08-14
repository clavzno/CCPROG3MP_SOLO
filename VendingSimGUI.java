
/* MAIN WINDOW */
//already imported as it extends JFrame
/* WINDOW LAYOUT */
/* WINDOW CONTENT */
import javax.swing.*; //ImageIcon, JButton, JLabel
import java.awt.*; //Color, BorderLayout, FlowLayout, Dimension
//import javax.swing.border.Border;
/* WINDOW EVENTS */
import java.awt.event.ActionListener;

/*
 * Mint Green BBE6E4
 * Anti-Flash White F0F6F6
 */

public class VendingSimGUI extends JFrame {

    VendingSimGUI() { // this is the JFrame
        createStartMenu(); // start menu goes on top of the window
        createWindow();
    }

    public void createWindow() {
        this.setLayout(new BorderLayout());
        this.setTitle("Vending Machine Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 600);
    
        // Setting window title image icon
        ImageIcon image = new ImageIcon("Sprites\\filledRegular.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0xBBE6E4));
    
        // Create JPanel to hold the image and start button
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xBBE6E4));
    
        // Create JPanel to hold the image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(0xBBE6E4));
        imagePanel.setLayout(new BorderLayout());
    
        // Import titleCard image and scale it - to be used in JLabel
        ImageIcon VendingSimImage = new ImageIcon("Sprites\\titleCard.png");
        int width = VendingSimImage.getIconWidth() * 5;
        int height = VendingSimImage.getIconHeight() * 5;
        Image scaledVendingSimImage = VendingSimImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon newVendingSimImage = new ImageIcon(scaledVendingSimImage);
    
        // Importing VendingSimImage into a JLabel
        JLabel titleCardLabel = new JLabel(newVendingSimImage);
        titleCardLabel.setText("Vending Machine Simulator");
        titleCardLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleCardLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleCardLabel.setHorizontalAlignment(JLabel.CENTER);
        titleCardLabel.setVerticalAlignment(JLabel.CENTER);
        imagePanel.add(titleCardLabel, BorderLayout.CENTER);
    
        // Create JPanel for the input field and start button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(new Color(0xBBE6E4));
    
        // Create JLabel for factory name
        JLabel factoryLabel = new JLabel("Enter Factory Name:");
        factoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(factoryLabel);
    
        // Create JTextField for user input
        JTextField factoryTextField = new JTextField();
        factoryTextField.setMaximumSize(new Dimension(200, 30));
        factoryTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(factoryTextField);
    
        // Create JPanel for the startButton
        JPanel startMenuPanel2 = new JPanel();
        startMenuPanel2.setBackground(new Color(0xBBE6E4));
        JButton startButton = createStartButton(startMenuPanel2);
        startMenuPanel2.add(startButton);
    
        // Add components to mainPanel
        mainPanel.add(imagePanel);
        mainPanel.add(inputPanel);
        mainPanel.add(startMenuPanel2);
    
        // Add mainPanel to the JFrame
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
    

    public void createStartMenu() {
        // creating JPanel to hold the titleCard
        JPanel startMenuPanel = new JPanel();
        startMenuPanel.setLayout(new BoxLayout(startMenuPanel, BoxLayout.Y_AXIS));
        // startMenuPanel.setBackground(Color.CYAN);
        startMenuPanel.setBounds(0, 0, 400, 400);
        startMenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // center horizontally
        startMenuPanel.setAlignmentY(Component.CENTER_ALIGNMENT); // center vertically

        // import titleCard image and scale it - to be used in JLabel
        ImageIcon VendingSimImage = new ImageIcon("Sprites\\titleCard.png");
        int width = VendingSimImage.getIconWidth() * 5;
        int height = VendingSimImage.getIconHeight() * 5;
        Image scaledVendingSimImage = VendingSimImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon newVendingSimImage = new ImageIcon(scaledVendingSimImage);

        // importing VendingSimImage into a JLabel
        JLabel titleCardLabel = new JLabel(newVendingSimImage);
        titleCardLabel.setText("Vending Machine Simulator");
        titleCardLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleCardLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleCardLabel.setHorizontalAlignment(JLabel.CENTER);
        titleCardLabel.setVerticalAlignment(JLabel.CENTER);
        startMenuPanel.add(titleCardLabel); // adding titleCard to the startMenuPanel
        this.add(startMenuPanel, BorderLayout.CENTER);

        // creating JPanel for the startButton
        JPanel startMenuPanel2 = new JPanel();
        startMenuPanel2.setLayout(new BoxLayout(startMenuPanel2, BoxLayout.Y_AXIS));
        startMenuPanel2.setBackground(Color.RED);
        JButton startButton = createStartButton(startMenuPanel2);
        startMenuPanel2.add(startButton); // adding JButton to the startMenuPanel2
        this.add(startMenuPanel2); // adding Jlabel for startButton to the startMenuPanel holding the Button
        // startMenuPanel2.setBounds(300, 300, 400, 100);
        // startMenuPanel2.setPreferredSize(new Dimension(400, 100));
        this.add(startMenuPanel2);
        this.revalidate();
        this.repaint();
    }

    public JButton createStartButton(JPanel startMenuPanel2) {
        JButton startButton = new JButton("Start");
        startButton.setBounds(0, 0, 100, 50);

        // adding actionListener
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // startButtonActionPerformed(evt);
                System.out.println("Start button pressed");
            }
        });

        return startButton;
    }

}
