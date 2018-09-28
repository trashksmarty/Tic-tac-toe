package крестики.нолики;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Main extends JFrame {

    int step = 0;
    int[] gameGrid = new int[9];
    int o = 1;
    int x = 2;
    AI ai = new AI();

    GridLayout grd1 = new GridLayout(0, 3);
    Font font = new Font("TimesRoman", Font.BOLD, 50);

    JButton cell_1 = new JButton("");
    JButton cell_2 = new JButton("");
    JButton cell_3 = new JButton("");
    JButton cell_4 = new JButton("");
    JButton cell_5 = new JButton("");
    JButton cell_6 = new JButton("");
    JButton cell_7 = new JButton("");
    JButton cell_8 = new JButton("");
    JButton cell_9 = new JButton("");

    JButton resetGame = new JButton("Reset");

    JPanel general = new JPanel();
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    
    AbstractAction myAction = new MyAction();
    
    public static void main(String[] args) {
        new Main("Крестики нолики");
    }

    public Main(String str) {
        super(str);

        cell_1.addActionListener(myAction);
        cell_2.addActionListener(myAction);
        cell_3.addActionListener(myAction);
        cell_4.addActionListener(myAction);
        cell_5.addActionListener(myAction);
        cell_6.addActionListener(myAction);
        cell_7.addActionListener(myAction);
        cell_8.addActionListener(myAction);
        cell_9.addActionListener(myAction);

        resetGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reset();
            }
        });

        cell_1.setFont(font);
        cell_1.setMnemonic(0);
        cell_2.setFont(font);
        cell_2.setMnemonic(1);
        cell_3.setFont(font);
        cell_3.setMnemonic(2);
        cell_4.setFont(font);
        cell_4.setMnemonic(3);
        cell_5.setFont(font);
        cell_5.setMnemonic(4);
        cell_6.setFont(font);
        cell_6.setMnemonic(5);
        cell_7.setFont(font);
        cell_7.setMnemonic(6);
        cell_8.setFont(font);
        cell_8.setMnemonic(7);
        cell_9.setFont(font);
        cell_9.setMnemonic(8);;

        add(general);
        general.setLayout(new BorderLayout());
        general.setSize(300, 400);
        
        general.add(top, BorderLayout.CENTER);
        top.setLayout(grd1);
        top.add(cell_1);
        top.add(cell_2);
        top.add(cell_3);
        top.add(cell_4);
        top.add(cell_5);
        top.add(cell_6);
        top.add(cell_7);
        top.add(cell_8);
        top.add(cell_9);

        general.add(bottom, BorderLayout.SOUTH);
        bottom.add(resetGame);
        
        KeyStroke num1 = KeyStroke.getKeyStroke("NUMPAD1");
        KeyStroke num2 = KeyStroke.getKeyStroke("NUMPAD2");
        KeyStroke num3 = KeyStroke.getKeyStroke("NUMPAD3");
        KeyStroke num4 = KeyStroke.getKeyStroke("NUMPAD4");
        KeyStroke num5 = KeyStroke.getKeyStroke("NUMPAD5");
        KeyStroke num6 = KeyStroke.getKeyStroke("NUMPAD6");
        KeyStroke num7 = KeyStroke.getKeyStroke("NUMPAD7");
        KeyStroke num8 = KeyStroke.getKeyStroke("NUMPAD8");
        KeyStroke num9 = KeyStroke.getKeyStroke("NUMPAD9");
        
        InputMap inputMap = general.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        
        inputMap.put(num7, "1");
        inputMap.put(num8, "2");
        inputMap.put(num9, "3");
        inputMap.put(num4, "4");
        inputMap.put(num5, "5");
        inputMap.put(num6, "6");
        inputMap.put(num1, "7");
        inputMap.put(num2, "8");
        inputMap.put(num3, "9");
        
        ActionMap actionMap = general.getActionMap();
        
        actionMap.put("1", new MyAction(cell_1));
        actionMap.put("2", new MyAction(cell_2));
        actionMap.put("3", new MyAction(cell_3));
        actionMap.put("4", new MyAction(cell_4));
        actionMap.put("5", new MyAction(cell_5));
        actionMap.put("6", new MyAction(cell_6));
        actionMap.put("7", new MyAction(cell_7));
        actionMap.put("8", new MyAction(cell_8));
        actionMap.put("9", new MyAction(cell_9));

        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void reset() {
        step = 0;
        gameGrid = new int[9];
        cell_1.setText("");
        cell_2.setText("");
        cell_3.setText("");
        cell_4.setText("");
        cell_5.setText("");
        cell_6.setText("");
        cell_7.setText("");
        cell_8.setText("");
        cell_9.setText("");
    }

    public void setValueX(JButton jb) {
        int i = jb.getMnemonic();
        if(gameGrid[i] == 0){
            jb.setText("X");
            gameGrid[i] = x;
            step++;
            checkWin();
            setValueO();
            checkLose();
        }
    }
    
    public void setValueO(){
        if(step != 0 && step <5){
            int nextO = ai.nextStepEasy(gameGrid);
            switch (nextO){
                case 0: cell_1.setText("O");break;
                case 1: cell_2.setText("O");break;
                case 2: cell_3.setText("O");break;
                case 3: cell_4.setText("O");break;
                case 4: cell_5.setText("O");break;
                case 5: cell_6.setText("O");break;
                case 6: cell_7.setText("O");break;
                case 7: cell_8.setText("O");break;
                case 8: cell_9.setText("O");break;
            }
        gameGrid[nextO] = o;
        }
    }

    public void checkWin() {
        if (ai.checkWinX(gameGrid)) {
            JOptionPane.showMessageDialog(null, "Победа");
            reset();
        } else if(step == 5){
            JOptionPane.showMessageDialog(null, "Ничья");
            reset();
        }
            
    }
    
    public void checkLose(){
        if (ai.checkWinO(gameGrid)){
            JOptionPane.showMessageDialog(null, "Проиграл");
            reset();
        }
    }
    
    class MyAction extends AbstractAction{
        JButton jb;

        public MyAction(JButton jb) {
            this.jb = jb;
        }
        
        public MyAction() {
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(this.jb == null){
                JButton jb1 = (JButton)ae.getSource();
                setValueX(jb1);
            } else {
                setValueX(jb);
            }
        }
    }
}
