import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class game2 extends JFrame {

    private final int NUM_CARDS = 16;
    private ArrayList<String> fileNames;
    private JButton[] buttons;
    private int firstCardIndex = -1;
    private int secondCardIndex = -1;
    private int matchedPairs = 0;
    private ImageIcon scaledIcon;
     

    public game2() {
        setTitle("Memory Card Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4));


        fileNames = new ArrayList<>();
        buttons = new JButton[NUM_CARDS];
        ImageIcon originalIcon = new ImageIcon("question mark.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);


        //Adding the name of image file in array
        fileNames.add("image1.png");fileNames.add("image1.png");
        fileNames.add("image2.png");fileNames.add("image2.png");
        fileNames.add("image3.png");fileNames.add("image3.png");
        fileNames.add("image4.png");fileNames.add("image4.png");
        fileNames.add("image5.png");fileNames.add("image5.png");
        fileNames.add("image6.png");fileNames.add("image6.png");
        fileNames.add("image7.png");fileNames.add("image7.png");
        fileNames.add("image8.png");fileNames.add("image8.png");

        Collections.shuffle(fileNames); //shuffling elements of array

        // Creating buttons
        for (int i = 0; i < NUM_CARDS; i++) {
            final int index = i;
            buttons[i] = new JButton(scaledIcon);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(true);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buttons[index].getBackground().equals(Color.GREEN)) {
                        return;
                    }

                    if (firstCardIndex == -1) {
                        firstCardIndex = index;

                        ImageIcon icon = new ImageIcon(fileNames.get(index));
                        Image image = icon.getImage();
                        Image simage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon sIcon = new ImageIcon(simage);
                        
                        buttons[index].setIcon(sIcon);
                        buttons[index].setEnabled(true);
                    } else if (secondCardIndex == -1 && index != firstCardIndex) {
                        secondCardIndex = index;

                        ImageIcon icon = new ImageIcon(fileNames.get(index));
                        Image image = icon.getImage();
                        Image simage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon sIcon = new ImageIcon(simage);

                        buttons[index].setIcon(sIcon);
                        buttons[index].setEnabled(true);

                        // Check for a match
                        check();
                    }
                }
            });
            add(buttons[i]);
        }

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void check(){
        if (fileNames.get(firstCardIndex).equals(fileNames.get(secondCardIndex))) {
            matchedPairs++;
            buttons[firstCardIndex].setBackground(Color.GREEN);
            buttons[secondCardIndex].setBackground(Color.GREEN);
            if (matchedPairs == NUM_CARDS / 2) {
                JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
                System.exit(0);
            }
            firstCardIndex = -1;
            secondCardIndex = -1;
        } else {
            // If not a match, hide cards after a short delay
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttons[firstCardIndex].setIcon(scaledIcon);
                    buttons[firstCardIndex].setEnabled(true);
                    buttons[secondCardIndex].setIcon(scaledIcon);
                    buttons[secondCardIndex].setEnabled(true);
                    firstCardIndex = -1;
                    secondCardIndex = -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new game2();
            }
        });
    }
}


