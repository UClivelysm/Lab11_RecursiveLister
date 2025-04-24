import javax.swing.*;
import java.awt.*;

public class DirListerFrame extends JFrame {

    // Global component declarations
     JPanel northPanel;
     JPanel centerPanel;
     JPanel southPanel;

     JPanel centerMainPanel;
    JPanel northMainPanel;
    JPanel southMainPanel;

     JButton printButton;
     JButton quitButton;
     JLabel northLabel;

    public DirListerFrame() {
        setTitle("Sample JFrame Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize panels
        northPanel = createNorthPanel();
        centerPanel = createCenterPanel();
        southPanel = createSouthPanel();

        // Add panels to the frame
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private JPanel createNorthPanel() {
        northMainPanel = new JPanel(new GridLayout(1, 3));
        northLabel = new JLabel("Recursive Directory Lister.");

        northMainPanel.add(northLabel);
        return northMainPanel;
    }

    private JPanel createCenterPanel() {
        centerMainPanel = new JPanel();

        return centerPanel;
    }

    private JPanel createSouthPanel() {
        southMainPanel = new JPanel();
        printButton = new JButton("Print Message");
        quitButton = new JButton("Quit");

        printButton.addActionListener(e -> System.out.println("Button clicked!"));
        quitButton.addActionListener(e -> System.exit(0));

        southMainPanel.add(printButton);
        southMainPanel.add(quitButton);
        return southMainPanel;
    }

    public void updateValues(String logs, int fCount, int dCount) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DirListerFrame frame = new DirListerFrame();
            frame.setVisible(true);
        });
    }
}
