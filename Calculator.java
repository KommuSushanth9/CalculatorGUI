import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private static JTextField display;
    private static double num1=0;
    private static String operator= "";
    private static boolean startNewInput=true;

    public static void main(String[] args) {
        JFrame frame=new JFrame("GUI Calculator");
        frame.setSize(300,300); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display=new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setEditable(false); 
        display.setHorizontalAlignment(JTextField.RIGHT); 

        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(5,3,5,5)); 

        JButton n1=new JButton("1");
        JButton n2=new JButton("2");
        JButton n3=new JButton("3");
        JButton n4=new JButton("4");
        JButton n5=new JButton("5");
        JButton n6=new JButton("6");
        JButton n7=new JButton("7");
        JButton n8=new JButton("8");
        JButton n9=new JButton("9");
        JButton n10=new JButton("0");
        JButton button1=new JButton("+");
        JButton button2=new JButton("-");
        JButton button3=new JButton("*");
        JButton button4=new JButton("/");
        JButton button5=new JButton("C");
        JButton buttonEquals=new JButton("=");

        panel.add(n1);
        panel.add(n2);
        panel.add(n3);
        panel.add(n4);
        panel.add(n5);
        panel.add(n6);
        panel.add(n7);
        panel.add(n8);
        panel.add(n9);
        panel.add(n10);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(buttonEquals);

        n1.addActionListener(new NumberButtonClickListener());
        n2.addActionListener(new NumberButtonClickListener());
        n3.addActionListener(new NumberButtonClickListener());
        n4.addActionListener(new NumberButtonClickListener());
        n5.addActionListener(new NumberButtonClickListener());
        n6.addActionListener(new NumberButtonClickListener());
        n7.addActionListener(new NumberButtonClickListener());
        n8.addActionListener(new NumberButtonClickListener());
        n9.addActionListener(new NumberButtonClickListener());
        n10.addActionListener(new NumberButtonClickListener());
        button1.addActionListener(new OperatorButtonClickListener());
        button2.addActionListener(new OperatorButtonClickListener());
        button3.addActionListener(new OperatorButtonClickListener());
        button4.addActionListener(new OperatorButtonClickListener());
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("");
                num1=0;
                operator="";
                startNewInput = true;
            }
        });
        buttonEquals.addActionListener(new EqualsButtonClickListener());
        frame.setLayout(new BorderLayout());
        frame.add(display,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    static class NumberButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command=e.getActionCommand();
            if (startNewInput) {
                display.setText(command);
                startNewInput=false;
            } else {
                display.setText(display.getText()+command);
            }
        }
    }
    static class OperatorButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command=e.getActionCommand();
            if (operator.isEmpty() && command.equals("-") && startNewInput) {
                display.setText(command);
                startNewInput=false;
            } else if (!display.getText().isEmpty()) {
                num1=Double.parseDouble(display.getText());
                operator=command;
                startNewInput=true;
            }
        }
    }
    static class EqualsButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!display.getText().isEmpty() && !operator.isEmpty()) {
                double num2=Double.parseDouble(display.getText());
                double result=0;

                try {
                    switch (operator) {
                        case "+":
                            result=num1+num2;
                            break;
                        case "-":
                            result=num1-num2;
                            break;
                        case "*":
                            result=num1*num2;
                            break;
                        case "/":
                            if (num2==0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            result=num1/num2;
                            break;
                    }
                    display.setText(Double.toString(result));
                } catch (ArithmeticException ex) {
                    display.setText("Error");
                }

                operator="";
                startNewInput=true;
            }
        }
    }
}
