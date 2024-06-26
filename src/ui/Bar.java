package ui;

import java.awt.Color;
import java.awt.Graphics;

public class Bar {

    protected int x, y, width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void draw(Graphics g) {
        g.setColor(new Color(102, 111, 63));
        g.fillRect(x, y, width, height);
    }

    protected void drawButtonFeedback(Graphics g, MyButton b) {
        //Mouse Over
            if(b.isMouseOver())
                g.setColor(Color.WHITE);
            else 
                g.setColor(Color.BLACK);
            
                        
            //Border
        
            g.drawRect(b.x, b.y, b.width, b.height);

            //Mouse Pressed
            if(b.isMousePressed()){
                g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
                g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
            }

    }

}
