import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    public Form() throws HeadlessException {
        setTitle("Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 300);

        JButton[] jbs = new JButton[5];
        for (int i = 0; i < jbs.length; i++)
            jbs[i] = new JButton("item #" + i);
        setLayout(new BorderLayout());
        add(jbs[0], BorderLayout.WEST);
        add(jbs[1], BorderLayout.CENTER);
        add(jbs[2], BorderLayout.EAST);
        add(jbs[3], BorderLayout.NORTH);
        add(jbs[4], BorderLayout.SOUTH);

        setVisible(true);
    }
}

class Main {
    public static void main(String[]args) {
        Form form = new Form();
    }
}