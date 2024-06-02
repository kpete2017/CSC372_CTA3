import java.awt.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class CreateUserInterface {
    private static JFrame frame = new JFrame();
    private static JTextArea textArea = new JTextArea();
    private static JPanel contentPanel = new JPanel();
    private static Color currentColor = getRandomGreenHueColor();

    public static void main(String[] args) {
        JPanel topPanel = new JPanel();
    
        createTopPanel(topPanel);
        createContentPanel(contentPanel);
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);
    
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private static void createTopPanel(JPanel panel) {
        panel.setLayout(new FlowLayout());
        JButton dateTimeButton = createStyledButton("Date/Time");
        JButton textLogButton = createStyledButton("Text Log");
        JButton changeFrameColorButton = createStyledButton("Change Color to Green Hue");
        changeFrameColorButton.setBackground(currentColor);
        JButton exiButton = createStyledButton("Exit");
        panel.add(dateTimeButton);
        panel.add(textLogButton);
        panel.add(changeFrameColorButton);
        panel.add(exiButton);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add action listeners
        dateTimeButton.addActionListener(e -> {
            textArea.setText("Current date and time: " + new java.util.Date());
        });

        textLogButton.addActionListener(e -> {
            try {
                FileWriter writer = new FileWriter("log.txt", true);
                writer.write(textArea.getText() + "\n");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        changeFrameColorButton.addActionListener(e -> {
            contentPanel.setBackground(currentColor);
            currentColor = getRandomGreenHueColor();
        });

        exiButton.addActionListener(e -> {
            frame.dispose();
        });
    }
    private static void createContentPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
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