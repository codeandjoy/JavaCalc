import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;

public class CustomButton extends JButton{
    CustomButton(String btnText, int posX, int posY, Color baseColor, Color hoverColor, Color fontColor){
        super(btnText);

        this.setSize(50, 50);
        this.setLocation(posX, posY);

        // ** Styles **
        this.setFont(new Font("SansSerif", Font.BOLD, 16));
        this.setBorder(null);
        this.setBackground(baseColor);
        this.setForeground(fontColor);
        // Change color and cursor on hover
        initHover(this, baseColor, hoverColor);
    }

    CustomButton(Icon icon, int posX, int posY, Color baseColor, Color hoverColor){
        super(icon);

        this.setSize(50, 50);
        this.setLocation(posX, posY);

        // ** Styles **
        this.setBorder(null);
        this.setBackground(baseColor);
        // this.setForeground(fontColor);
        // Change color and cursor on hover
        initHover(this, baseColor, hoverColor);
    }

    private void initHover(CustomButton thisBtn, Color baseColor, Color hoverColor){
        thisBtn.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt){
                thisBtn.setBackground(hoverColor);
                thisBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                thisBtn.setBackground(baseColor);
                thisBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }
}