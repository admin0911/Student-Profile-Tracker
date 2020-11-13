import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Is not being used now. */


public class four extends JFrame {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    four frame = new four();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public four() {
        String enter_ROll_no;
        enter_ROll_no = JOptionPane.showInputDialog("ENter username");
        JOptionPane.showMessageDialog(null, enter_ROll_no);
        JOptionPane.showMessageDialog(null, "You are viweing data");
        JOptionPane.showMessageDialog(null, "L - > 100\nOops -> 0\nDiscrete structures -> 100 ");
    }
}

