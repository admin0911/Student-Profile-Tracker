import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Login frame */

public class oops implements ActionListener {
    private static JLabel userlabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;             //hides actual text when typed into
    private static JButton button;
    private static JLabel success;

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(650, 600);
        frame.setTitle("Login page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        userlabel = new JLabel("Username");
        userlabel.setFont(new Font("copperplate", Font.BOLD, 30));
        userlabel.setBounds(85, 200, 180, 55);
        panel.add(userlabel);

        userText = new JTextField();
        userText.setFont(new Font("copperplate", Font.BOLD, 30));
        userText.setBounds(350, 200, 165, 55);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("copperplate", Font.BOLD, 30));
        passwordLabel.setBounds(85, 300, 180, 55);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setFont(new Font("copperplate", Font.BOLD, 30));
        passwordText.setBounds(350, 300, 165, 55);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setFont(new Font("copperplate", Font.BOLD, 30));
        button.setBounds(200, 400, 190, 40);
        button.addActionListener(new oops());
        panel.add(button);

        success = new JLabel("");
        success.setFont(new Font("copperplate", Font.BOLD, 30));
        success.setBounds(50, 450, 350, 40);
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = userText.getText();
        String password = passwordText.getText();
        int type;
        if(username.contains("00"))
        {
            type = 2;
        }
        else
        {
            type = 1;
        }
        login l=new login(username,password,type);

    }
}
