import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Student Loin frame */

public class third extends JFrame implements ActionListener {
    private static JLabel userlabel;
    private static JButton button;
    private static JButton button1;

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    third frame1 = new third();
                    frame1.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public third() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        userlabel = new JLabel("STUDENT LOGIN");
        userlabel.setFont(new Font("copperplate", Font.BOLD, 30));
        userlabel.setBounds(200, 50, 300, 40);
        panel.add(userlabel);


        button = new JButton("view student details");
        button.setFont(new Font("copperplate", Font.BOLD, 30));
        button.setBounds(70, 150, 500, 40);
        button.addActionListener(new oops());
        panel.add(button);

        button1 = new JButton("calculate CGPA");
        button1.setFont(new Font("copperplate", Font.BOLD, 30));
        button1.setBounds(70, 250, 500, 40);
        button1.addActionListener(new oops());
        panel.add(button1);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        four f = new four();
    }
}
