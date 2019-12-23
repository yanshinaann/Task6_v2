import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        JFrame frame = new JFrame();
      //  Plane plane = new Plane();

        frame.add(view);
        frame.addKeyListener(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        view.requestFocus();
    }
}
