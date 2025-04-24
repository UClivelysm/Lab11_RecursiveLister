import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DirListerFrame extends JFrame {

    // Global component declarations
     JPanel northPanel;
     JPanel centerPanel;
     JPanel southPanel;

     JPanel centerMainPanel;
    JPanel northMainPanel;
    JPanel southMainPanel;

     JButton loadDirButton;
     JButton quitButton;
     JButton startButton;
     JButton toggleHiddenButton;
     Boolean showHidden = true;

     JTextArea logTextArea;
     JScrollPane logScrollPane;


     JLabel northLabel;

     JLabel fCountLabel;
     JLabel dCountLabel;
     JLabel dirLabel;

     DirLister dirLister;

     File initialDir = null;

    public DirListerFrame() {
        dirLister = new DirLister(this);
        dirLister.enableSkipHidden();

        setTitle("DirListerFrame");
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
        northMainPanel = new JPanel(new GridLayout(4, 1));
        northLabel = new JLabel("Recursive Directory Lister.");
        dirLabel = new JLabel("Current Directory: ");
        fCountLabel = new JLabel("Number of Files Found: 0");
        dCountLabel = new JLabel("Number of Directories Found: 0");




        northMainPanel.add(dCountLabel, SwingConstants.CENTER);
        northMainPanel.add(fCountLabel, SwingConstants.CENTER);
        northMainPanel.add(dirLabel, SwingConstants.CENTER);
        northMainPanel.add(northLabel, SwingConstants.CENTER);
        return northMainPanel;
    }

    private JPanel createCenterPanel() {
        centerMainPanel = new JPanel(new BorderLayout());
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        logScrollPane = new JScrollPane(logTextArea);
        centerMainPanel.add(logScrollPane, BorderLayout.CENTER);


        return centerMainPanel;
    }

    private JPanel createSouthPanel() {
        southMainPanel = new JPanel(new GridLayout(1, 4));
        loadDirButton = new JButton("Select Directory");
        quitButton = new JButton("Quit");

        loadDirButton.addActionListener(e -> {
            initialDir = null;
            initialDir = FilePicker.getDir();
            if (initialDir != null) {
                dirLabel.setText("Current Directory: " + initialDir.getAbsolutePath());
            }
        });
        toggleHiddenButton = new JButton("Show Hidden Items");
        toggleHiddenButton.addActionListener(e -> {
            if (showHidden == true) {
                showHidden = false;
                toggleHiddenButton.setText("Hide Hidden Items");
                dirLister.disableSkipHidden();

            } else {
                showHidden = true;
                toggleHiddenButton.setText("Show Hidden Items");
                dirLister.enableSkipHidden();

            }
        });
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            if (initialDir != null) {
                logTextArea.setText("");
                dirLister.setFirstDir(initialDir);
            }
        });
        quitButton.addActionListener(e -> System.exit(0));

        southMainPanel.add(loadDirButton);
        southMainPanel.add(toggleHiddenButton);
        southMainPanel.add(startButton);
        southMainPanel.add(quitButton);
        return southMainPanel;
    }

    public void updateValues(String logs, int fCount, int dCount) {
        logTextArea.append(logs + "\n");
        fCountLabel.setText("Number of Files Found: " + fCount);
        dCountLabel.setText("Number of Directories Found: " + dCount);
        System.out.println(logs + " " + fCount + " " + dCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DirListerFrame frame = new DirListerFrame();
            frame.setVisible(true);
        });
    }
}
