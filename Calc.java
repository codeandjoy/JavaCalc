import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.swing.*;

public class Calc{
    private static void createAndShowGUI(){
        // ** Style constants **
        Color BASE_CLEAR_BTN_COLOR = new Color(58, 45, 50);
        Color HOVER_CLEAR_BTN_COLOR = new Color(37, 29, 32);
        Color BASE_EQ_BTN_COLOR = new Color(240, 128, 60);
        Color HOVER_EQ_BTN_COLOR = new Color(241, 99, 14);
        Color BASE_NUM_BTN_COLOR = new Color(85, 91, 110);
        Color HOVER_NUM_BTN_COLOR = new Color(52, 56, 70);
        Color BASE_OPERATION_BTN_COLOR = new Color(125, 205, 133);
        Color HOVER_OPERATION_BTN_COLOR = new Color(70, 161, 79);

        JFrame frame = new JFrame("Calc 3000");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 590);
        frame.setResizable(false);
        frame.setLayout(null);
        // ** Styles **
        frame.getContentPane().setBackground(new Color(232, 233, 243));
        
        JTextField textField = new JTextField();
        textField.setBounds(30, 30, 340, 100);
        textField.setFont(new Font("SansSerif", Font.PLAIN , 16));
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder(25, 25, 25, 25)));

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(null);
        panelButtons.setBounds(55, 160, 290, 370);

        //
        // Panel row 1
        //
        ImageIcon iconBtnClear = new ImageIcon(Paths.get("icons/backspace.png").toAbsolutePath().toString());
        CustomButton btnClear = new CustomButton(iconBtnClear, 0, 0, BASE_CLEAR_BTN_COLOR, HOVER_CLEAR_BTN_COLOR);
        CustomButton btnEquals = new CustomButton("=", 240, 0, BASE_EQ_BTN_COLOR, HOVER_EQ_BTN_COLOR, Color.WHITE);

        //
        // Panel row 2
        //
        CustomButton btn7 = new CustomButton("7", 0, 80, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btn8 = new CustomButton("8", 80, 80, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btn9 = new CustomButton("9", 160, 80, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btnAdd = new CustomButton("+", 240, 80, BASE_OPERATION_BTN_COLOR, HOVER_OPERATION_BTN_COLOR, Color.WHITE);
        
        //
        // Panel row 3
        //
        CustomButton btn4 = new CustomButton("4", 0, 160, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btn5 = new CustomButton("5", 80, 160, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btn6 = new CustomButton("6", 160, 160, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btnSubtract = new CustomButton("-", 240, 160, BASE_OPERATION_BTN_COLOR, HOVER_OPERATION_BTN_COLOR, Color.WHITE);

        //
        // Panel row 4
        //
        CustomButton btn1 = new CustomButton("1", 0, 240, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btn2 = new CustomButton("2", 80, 240, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btn3 = new CustomButton("3", 160, 240, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);
        CustomButton btnMultiply = new CustomButton("*", 240, 240, BASE_OPERATION_BTN_COLOR, HOVER_OPERATION_BTN_COLOR, Color.WHITE);

        //
        // Panel row 5
        //
        CustomButton btn0 = new CustomButton("0", 80, 320, BASE_NUM_BTN_COLOR, HOVER_NUM_BTN_COLOR, Color.WHITE);       
        CustomButton btnDivide = new CustomButton("/", 240, 320, BASE_OPERATION_BTN_COLOR, HOVER_OPERATION_BTN_COLOR, Color.WHITE);

        panelButtons.add(btnClear);
        panelButtons.add(btnEquals);
        panelButtons.add(btn7);
        panelButtons.add(btn8);
        panelButtons.add(btn9);
        panelButtons.add(btnAdd);
        panelButtons.add(btn4);
        panelButtons.add(btn5);
        panelButtons.add(btn6);
        panelButtons.add(btnSubtract);
        panelButtons.add(btn1);
        panelButtons.add(btn2);
        panelButtons.add(btn3);
        panelButtons.add(btnMultiply);
        panelButtons.add(btn0);
        panelButtons.add(btnDivide);

        frame.add(textField);
        frame.add(panelButtons);

        frame.toFront();
        frame.setVisible(true);

        //
        // Text field actions
        //
        textField.addKeyListener(new KeyAdapter() {
            Pattern p = Pattern.compile("[\\d+\\-*\\/ ]");

            public void keyTyped(KeyEvent evt){
                char keyChar = evt.getKeyChar();

                // Filter symbols
                if ( !p.matcher(Character.toString(keyChar)).matches() &&
                    (keyChar != KeyEvent.VK_BACK_SPACE) &&
                    (keyChar != KeyEvent.VK_ENTER)){
                    evt.consume();
                }
                else if (keyChar == KeyEvent.VK_ENTER){
                    int calcResult = 0;
                    try{
                        if (textField.getText() != ""){
                            calcResult = StringToMathResult.calc(textField.getText());
                            textField.setText(textField.getText() + " = " + Integer.toString(calcResult));
                        }
                    }
                    catch(InvalidMathInputString ex){
                        // ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Invalid operation");
                    }
                }
            }
        });

        //
        // Button actions
        //
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText("");
            }
        });
        btnEquals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int calcResult = 0;
                try{
                    if (textField.getText() != ""){
                        calcResult = StringToMathResult.calc(textField.getText());
                        textField.setText(textField.getText() + " = " + Integer.toString(calcResult));
                    }
                }
                catch(InvalidMathInputString ex){
                    // ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid operation");
                }
            }
        });
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "7");
            }
        });
        btn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "8");
            }
        });
        btn9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "9");
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "+");
            }
        });
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "4");
            }
        });
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "5");
            }
        });
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "6");
            }
        });
        btnSubtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "-");
            }
        });
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "1");
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "2");
            }
        });
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "3");
            }
        });
        btnMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "*");
            }
        });
        btn0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "0");
            }
        });
        btnDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textField.setText(textField.getText() + "/");
            }
        });

    }

    public static int calculate(JTextField textField) throws InvalidMathInputString{
        int calcResult = 0;
        try{
            if (textField.getText() != ""){
                calcResult = StringToMathResult.calc(textField.getText());
            }
        }
        catch(InvalidMathInputString ex){
            // ex.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Invalid operation");
        }

        return calcResult;
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
    }
}