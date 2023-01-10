package wargame.affichage;

import wargame.event.GameEvent;
import wargame.event.GameEventManager;
import wargame.event.GameListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class GameDownBar extends JPanel {
    private JLabel gameInfo;
    private JLabel TourInfo;
    public  GameWindow parent;



    private Font font;

    private BufferedImage background;
    private Image bgImage;

    public GameDownBar(GameWindow parent) {
        super();
        this.parent = parent;
        Dimension dim = new Dimension(parent.getWidth(), parent.getHeight() / 10);
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
        try {
            background = ImageIO.read(new File("resources/textures/gui/side_bar.png"));
            bgImage = background.getScaledInstance(dim.width,dim.height,Image.SCALE_FAST);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                bgImage = background.getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST);
            }
        });
        font = new Font("Arial",Font.BOLD,26);
        JButton b1 = new JButton("Finir le tour (t)");
        b1.setBackground(new Color(70, 58, 42));
        b1.setForeground(Color.WHITE);
        b1.setFont(font);
        gameInfo = new JLabel("<html>Nombre de tours : 0<br>Nombre de Hero restant : 0<br>Nombre d'ennemis restants : 0</html>");
        gameInfo.setFont(font);
        gameInfo.setHorizontalTextPosition(JLabel.CENTER);
        gameInfo.setForeground(Color.WHITE);

        TourInfo = new JLabel("Heros à vous de jouer !");
        TourInfo.setFont(font);
        TourInfo.setHorizontalTextPosition(JLabel.CENTER);
        TourInfo.setForeground(Color.WHITE);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(0,5,0,5);
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = parent.getHeight() / 20;
        add(b1,c);
        c.gridx = 1;
        add(gameInfo,c);
        c.gridx = 2;
        add(TourInfo,c);
        c.ipady = 0;



        //Event Listeners

        b1.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GameEventManager.FireEvent("FinTour", null);
            }
        });
        GameEventManager.addListener(new GameListener() {
            @Override
            public void triggered(GameEvent e) {
                update();
            }
        }, "UpdateGameInfo");

        GameEventManager.addListener(new GameListener() {
            @Override
            public void triggered(GameEvent e) {
                if(parent.gameDisplay.getCarte().isTourHeros())
                    TourInfo.setText("Heros à vous de jouer !");
                else
                    TourInfo.setText("Monstres à vous de jouer !");
            }
        }, "FinTour");


    }
    public void update()
    {
        gameInfo.setText("<html>Nombre de tours : "+ parent.gameDisplay.getCarte().getTour() +"<br>Nombre de Hero restant : "+ parent.gameDisplay.getCarte().getListeHeros().size() +"<br>Nombre de monstres restants : "+ parent.gameDisplay.getCarte().getListeMonstres().size() +"</html>");
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(bgImage,0,0,null);
        g2.setColor(Color.BLACK);
        g2.setFont(font);
    }
}
