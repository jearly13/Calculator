/**
 * @author Jordan Early
 * @date October 12, 2020
 * @description Calculator that does simple calculations and
 *              trigonometry
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Driver extends JFrame{

    //fields for components
    private JPanel buttonPanel;
    private JPanel operatorPanel;
    private JPanel mainPanel;
    private JTextField questionText;
    private JButton button;
    private boolean number = true;
    private String operator = "=";
    private CalculatorOperation problem = new CalculatorOperation();

    //fields for window size and font settings
    private final int WINDOW_HEIGHT = 200;
    private final int WINDOW_WIDTH = 360;
    private final Font FONT = new Font("dialog",Font.PLAIN, 14);

    public Driver(){
        questionText = new JTextField("", 12);
        questionText.setHorizontalAlignment(JTextField.RIGHT);
        questionText.setFont(FONT);
        ActionListener numberListener = new NumberListener();
        String buttonOrder = "1234567890. ()";
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3, 4, 4));
        for (int i = 0; i < buttonOrder.length(); i++) {
            String key = buttonOrder.substring(i, i+1);
            if (key.equals(" ")) {
                buttonPanel.add(new JLabel(""));
            } else {
                JButton button = new JButton(key);
                button.addActionListener(numberListener);
                button.setFont(FONT);
                buttonPanel.add(button);
            }
        }
        ActionListener operatorListener = new OperatorListener();
        operatorPanel = new JPanel();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        operatorPanel.setLayout(new GridLayout(4, 4, 4, 4));
        String[] operators = {"+", "-", "*", "/","log","ln","cot","sin","cos","tan","C","-/+","="};
        for (int i = 0; i < operators.length; i++) {
            button = new JButton(operators[i]);
            button.addActionListener(operatorListener);
            button.setFont(FONT);
            operatorPanel.add(button);
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(4, 4));
        mainPanel.add(questionText, BorderLayout.NORTH );
        mainPanel.add(buttonPanel , BorderLayout.CENTER);
        mainPanel.add(operatorPanel , BorderLayout.EAST);
        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Calculator");
        this.setResizable(false);
    }
    private void action(){
        number = true;
        questionText.setText("");
        operator = "=";
        problem.setTotal("");
    }
    private class OperatorListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String textBoxValue = questionText.getText();
            if (event.getActionCommand().equals("sin")) {

                questionText.setText("" + Math.sin(Double.valueOf(textBoxValue).doubleValue()));

            } else if (event.getActionCommand().equals("cos")) {

                questionText.setText("" + Math.cos(Double.valueOf(textBoxValue).doubleValue()));

            } else if (event.getActionCommand().equals("ln")) {

                questionText.setText("" + Math.log(Double.valueOf(textBoxValue).doubleValue()));

            } else if (event.getActionCommand().equals("log")) {

                questionText.setText("" + Math.log10(Double.valueOf(textBoxValue).doubleValue()));

            } else if (event.getActionCommand().equals("tan")) {

                questionText.setText("" + Math.tan(Double.valueOf(textBoxValue).doubleValue()));

            } else if (event.getActionCommand().equals("cot")) {

                questionText.setText("" + ( 1 / (Math.tan(Double.valueOf(textBoxValue).doubleValue()))));

            } else if (event.getActionCommand().equals("C")) {

                questionText.setText("");

            } else if (event.getActionCommand().equals("-/+")) {

                questionText.setText("" + (Double.valueOf(textBoxValue).doubleValue() * -1));

            } else {

                if (number) {

                    action();
                    questionText.setText("");

                } else {
                    number = true;
                    if (operator.equals("=")) {
                        problem.setTotal(textBoxValue);
                    } else if (operator.equals("+")) {
                        problem.add(textBoxValue);
                    } else if (operator.equals("-")) {
                        problem.subtract(textBoxValue);
                    } else if (operator.equals("*")) {
                        problem.multiply(textBoxValue);
                    } else if (operator.equals("/")) {
                        problem.divide(textBoxValue);
                    }

                    questionText.setText("" + problem.getTotalString());
                    operator = event.getActionCommand();
                }
            }
        }
    }
    private class NumberListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String digit = event.getActionCommand();
            if (number) {
                questionText.setText(digit);
                number = false;
            } else {
                questionText.setText(questionText.getText() + digit);
            }
        }
    }
    public class CalculatorOperation {
        private double solution;
        public CalculatorOperation() {
            solution = 0;
        }
        public String getTotalString() {
            return "" + solution;
        }
        public void setTotal(String num) {
            solution = convertToNumber(num);
        }
        public void add(String num) {
            solution += convertToNumber(num);
        }
        public void subtract(String num) {
            solution -= convertToNumber(num);
        }
        public void multiply(String num) {
            solution *= convertToNumber(num);
        }
        public void divide(String num) {
            solution /= convertToNumber(num);
        }
        private double convertToNumber(String num) {
            return Double.parseDouble(num);
        }
    }
    public static void main(String[] args){
        try{
            JFrame app = new Driver();
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        } catch (Exception e){
            e.getMessage();
        }    
    }
}

