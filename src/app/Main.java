package app;

import control.Command;
import control.DownCommand;
import control.LeftCommand;
import control.UpCommand;
import control.RightCommand;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import model.Block;
import view.BlockDisplay;

public class Main extends JFrame {

    private Block block;
    private BlockDisplay blockDisplay;
    private Map<String, Command> commands;
    
    public static void main(String[] args) {
        new Main().execute();
    }
    
    public Main() {
        this.setTitle("Block shifter");
        this.setSize(367,430);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
                
    public void execute() {
        this.block = new Block();
        this.blockDisplay.display(block);
        this.block.register(blockDisplay);
        this.commands = createCommands();
        
        this.setVisible(true);
    }
    
    private BlockPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.blockDisplay = blockPanel;
        return blockPanel;
    }
    
    private Map<String, Command> createCommands() {
        HashMap<String, Command> commands = new HashMap<String,Command>();
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        commands.put("U", new UpCommand(block));
        commands.put("D", new DownCommand(block));
        return commands;
    }

    private Component toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("L"));
        toolbar.add(button("R"));
        toolbar.add(button("U"));
        toolbar.add(button("D"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        return button;
    }
}
