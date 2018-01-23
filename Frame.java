/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

/**
 *
 * @author mina
 */
import Organisms.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.io.FileNotFoundException;
 
public class Frame extends JFrame {
    private JTextPane Action;
    private JPanel Map;
    private JButton zapisz;
    private JButton wczytaj;
    private JPopupMenu lista;
    private int click_x;
    private int click_y;
    Graphics gr;
    PaintPanel panel;
    class PaintPanel extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	}
}
    public Frame(World world) {
        super("Mikolaj Naderza 165337");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 250, 1000, 800);
        setUndecorated(false);
        setVisible(true);
        String[] names={"Wilk","Owca","Lis","Zolw","Antylopa","Trawa","Mlecz","Jagoda","Barszcz","Guarana"};
        
        Action = new JTextPane();
        Action.setEditable(false);
        Action.setFocusable(false);
        JScrollPane scrollkomunikat = new JScrollPane(Action); 
        scrollkomunikat.setBounds(500, 100, 400, 500);
        scrollkomunikat.setFocusable(false);
        add(scrollkomunikat);
        
        JLabel fill = new JLabel("");
        
        Map = new JPanel();
        Map.setBounds(50, 100, 400, 400);
        Map.setBackground(Color.WHITE);
        Map.setLayout(null);
        
        for(int i=0;i<world.GetWidth()*world.GetHeight()-1;i++){
                JPanel org = new JPanel();
                if(world.OrgList[i] != null){
                    char m=world.OrgList[i].GetMark();
                    switch(m){
                        case'C': org.setBackground(Color.BLACK);
                        break;
                        case'W': org.setBackground(Color.RED);
                        break;
                        case'O': org.setBackground(Color.LIGHT_GRAY);
                        break;
                        case'L': org.setBackground(Color.ORANGE);
                        break;
                        case'A': org.setBackground(Color.YELLOW);
                        break;
                        case'Z':org.setBackground(Color.BLUE);
                        break;
                
                        case'T': org.setBackground(Color.GREEN);
                        break;
                        case'B': org.setBackground(Color.PINK);
                        break;
                        case'J': org.setBackground(new Color(138,43,226));
                        break;
                        case'G': org.setBackground(Color.CYAN);
                        break;
                        case'M': org.setBackground(Color.GRAY);
                        break;
                    }
                    org.setBounds(world.OrgList[i].GetX()*10, world.OrgList[i].GetY()*10, 10, 10);
                    Map.add(org);
                }
                
        }
        
        Map.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        lista = new JPopupMenu("Stwórz organizm");
        lista.add(new JLabel("Stwórz organizm"));
        lista.addSeparator();
        for (int i=0;i<10;i++) {
            String clas=names[i];
            JMenuItem MenuItem = new JMenuItem(clas);
            MenuItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                            if(world.GetOrg(click_x/10, click_y/10) == null){
                           Organism tmp=new Sheep(1,1,world);
                            switch(clas){
                                case "Wilk":tmp=new Wolf(click_x/10, click_y/10,world); break;
                                case "Owca":tmp=new Sheep(click_x/10, click_y/10,world); break;
                                case "Lis":tmp=new Foxx(click_x/10, click_y/10,world); break;
                                case "Zolw":tmp=new Turtle(click_x/10, click_y/10,world); break;
                                case "Antylopa":tmp=new Antilope(click_x/10, click_y/10,world); break;
                                case "Trawa":tmp=new Grass(click_x/10, click_y/10,world); break;
                                case "Guarana":tmp=new Guarana(click_x/10, click_y/10,world); break;
                                case "Jagoda":tmp=new NightShade(click_x/10, click_y/10,world); break;
                                case "Mlecz":tmp=new Dandelion(click_x/10, click_y/10,world); break;
                                case "Barszcz":tmp=new Hogweed(click_x/10, click_y/10,world); break;
                            }
                            world.Ad(tmp);
                            }
                            else{
                                world.AdCom("Pole " + click_x/10 + "," + click_y/10 + " jest zajęte\n");
                            }
                            refresh(world);
                }
            });
            lista.add(MenuItem);
        }
        lista.addSeparator();
        JMenuItem Anuluj = new JMenuItem("Anuluj");
        Anuluj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.setVisible(false);
            }
        });
        lista.add(Anuluj);
                
        Map.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lista.show(Map, e.getX(), e.getY());
                click_x = e.getX() ;
                
                click_y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        zapisz = new JButton("Zapisz grę");
        zapisz.setBounds(50, 30, 400, 50);
        zapisz.setFocusable(false);
        zapisz.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try
                {
                    world.Save();
                }
                catch(FileNotFoundException ex){};
                refresh(world);
            }
        });
        
        wczytaj = new JButton("Wczytaj grę");
        wczytaj.setBounds(500, 30, 400, 50);
        wczytaj.setFocusable(false);
        wczytaj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refresh(world);
                world.load();
                refresh(world);
            }
        });
        
        
        
        add(zapisz);
        add(wczytaj);
        add(Map);
        add(fill);
        addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                world.ExecuteTurn(e);
                refresh(world);
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        setVisible(true);
    }
    
    public void repaint(World world){
        Map.removeAll();
        for(int i=0;i<world.GetWidth()*world.GetHeight()-1;i++){
                JPanel org = new JPanel();
                if(world.OrgList[i] != null){
                    char m=world.OrgList[i].GetMark();
                    switch(m){
                        case'C': org.setBackground(Color.BLACK);
                        break;
                        case'W': org.setBackground(Color.RED);
                        break;
                        case'O': org.setBackground(Color.LIGHT_GRAY);
                        break;
                        case'L': org.setBackground(Color.ORANGE);
                        break;
                        case'A': org.setBackground(Color.YELLOW);
                        break;
                        case'Z':org.setBackground(Color.BLUE);
                        break;
                
                        case'T': org.setBackground(Color.GREEN);
                        break;
                        case'B': org.setBackground(Color.PINK);
                        break;
                        case'J': org.setBackground(new Color(138,43,226));
                        break;
                        case'G': org.setBackground(Color.CYAN);
                        break;
                        case'M': org.setBackground(Color.GRAY);
                        break;
                    }
                    org.setBounds(world.OrgList[i].GetX()*10, world.OrgList[i].GetY()*10, 10, 10);
                    Map.add(org);
            }
        Map.repaint();
        
        }
        
        setVisible(true);
    }
    public void refresh(World world){
        repaint(world);
        Action.setText(world.GetCom());
        requestFocus();
    }
    
}