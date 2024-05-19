import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorApplet extends JApplet implements ActionListener {
    private JTextField display;
    private double result;
    private String operator;
    private boolean start;

    public void init() {
        createGUI();
    }

    private void createGUI() {
        // Create display
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Create buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        // Initialize calculator state
        result = 0;
        operator = "=";
        start = true;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
            if (start) {
                display.setText(cmd);
            } else {
                display.setText(display.getText() + cmd);
            }
            start = false;
        } else {
            if (start) {
                if (cmd.equals("-")) {
                    display.setText(cmd);
                    start = false;
                } else {
                    operator = cmd;
                }
            } else {
                double x = Double.parseDouble(display.getText());
                calculate(x);
                operator = cmd;
                start = true;
            }
        }
    }

    private void calculate(double n) {
        if (operator.equals("+")) {
            result += n;
        } else if (operator.equals("-")) {
            result -= n;
        } else if (operator.equals("*")) {
            result *= n;
        } else if (operator.equals("/")) {
            result /= n;
        } else if (operator.equals("=")) {
            result = n;
        }
        display.setText("" + result);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        SimpleCalculatorApplet applet = new SimpleCalculatorApplet();
        applet.init(); // Initialize the applet
        frame.add(applet);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
