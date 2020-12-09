package app;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import model.Block;
import view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay {

    private static final int SIZE = 50;
    private Block block;
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(), getHeight());
        int m = Block.MAX * SIZE;
        g.setColor(Color.BLACK);
        for (int i = 0; i <= 7; i++) {
            int d = i * SIZE;
            g.drawLine(0, d, m, d);
            g.drawLine(d, 0, d, m);
        }
        if (block == null) return;
        g.setColor(Color.RED);
        g.fillRect((block.x()-1)*SIZE, (Block.MAX - block.y())*SIZE, SIZE, SIZE);
    }
    
    @Override
    public void display(Block block) {
        this.block = block;
        this.repaint();
    }
    @Override
    public void changed() {
        this.repaint();
    }
}
