// Kyle Petersen
// 06-1-2024
// CSC372
// Critical Thinking 3


import java.awt.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class CreateUserInterface {

    // Globals
    private static JFrame frame = new JFrame();
    private static JTextArea textArea = new JTextArea();
    private static JPanel contentPanel = new JPanel();
    private static Color currentColor = getRandomGreenHueColor();

    public static void main(String[] args) {
        JPanel topPanel = new JPanel();
    
        // Create panels
        createTopPanel(topPanel);
        createContentPanel(contentPanel);
        
        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);
    
        // Set frame properties
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private static void createTopPanel(JPanel panel) {
        // Flylayout for reactivity
        panel.setLayout(new FlowLayout());

        // Create JButtons
        JButton dateTimeButton = createStyledButton("Date/Time");
        JButton textLogButton = createStyledButton("Text Log");
        JButton changeFrameColorButton = createStyledButton("Change Color to Green Hue");
        changeFrameColorButton.setBackground(currentColor);
        JButton exiButton = createStyledButton("Exit");
        
        // Add JButtons to panel
        panel.add(dateTimeButton);
        panel.add(textLogButton);
        panel.add(changeFrameColorButton);
        panel.add(exiButton);

        // Set panel background and border
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add action listeners
        dateTimeButton.addActionListener(e -> {
            textArea.setText("Current date and time: " + new java.util.Date());
        });


        // Logs the text area to a file
        textLogButton.addActionListener(e -> {
            // Try catch file writer incase of IOException
            try {
                // Create new log.txt file
                FileWriter writer = new FileWriter("log.txt", true);
                // Write the text area to the file
                writer.write(textArea.getText() + "\n");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        // Changes the background color of the frame to random green hue
        changeFrameColorButton.addActionListener(e -> {
            // Change the background color of the content panel to the one display by the button
            contentPanel.setBackground(currentColor);
            // Sets new random color
            currentColor = getRandomGreenHueColor();
        });

        // Exit Button Action
        exiButton.addActionListener(e -> {
            frame.dispose();
        });
    }
    private static void createContentPanel(JPanel panel) {
        // Border
        panel.setLayout(new BorderLayout());

        // Create border to display background color
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Initial background color of grey
        panel.setBackground(Color.LIGHT_GRAY);

        // Add a text area to display date and time
        panel.add(textArea, BorderLayout.CENTER);
    }


    // Make JButtons less ugly
    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        button.setBackground(new Color(70, 130, 180)); // Set background color (SteelBlue)
        button.setForeground(Color.WHITE); // Set text color
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private static Color getRandomGreenHueColor() {
        int red = 0;
        int green = (int) (Math.random() * 206) + 50; // Make sure the green isnt too dark
        int blue = 0;
        return new Color(red, green, blue);
    }
}