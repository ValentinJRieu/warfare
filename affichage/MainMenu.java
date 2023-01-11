package wargame.affichage;

import wargame.Main;
import wargame.RessourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.net.URL;
import java.util.Objects;

/**
 * Le Menu Principal
 */
public class MainMenu extends JPanel {
    private Font font,titleFont;

    /**
     * Initialise le menu principal a l'aide de la fenêtre
     * @param frame la fenêtre
     */
    public MainMenu(JFrame frame) {
        frame.setContentPane(this);
        setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        setBackground(new Color(100,100,100));
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            InputStream myStream = new BufferedInputStream(Objects.requireNonNull(InputStream.class.getResourceAsStream("/wargame/resources/fonts/MainMenu.ttf")));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 72);
            genv.registerFont(font);
            myStream = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/wargame/resources/fonts/Title.ttf")));
            titleFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 256);
            genv.registerFont(font);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        JLabel Title = new JLabel("La Bagarre");
        Title.setFont(titleFont);
        Title.setForeground(new Color(194, 187, 143));

        JButton newButton = new JButton("Nouvelle Partie");
        newButton.setFont(font);
        newButton.setBackground(new Color(50,50,50));
        newButton.setForeground(Color.WHITE);

        JButton loadButton = new JButton("Charger Partie");
        loadButton.setFont(font);
        loadButton.setBackground(new Color(50,50,50));
        loadButton.setForeground(Color.WHITE);

        /*JButton settingsButton = new JButton("Options");
        settingsButton.setFont(font);
        settingsButton.setBackground(new Color(50,50,50));
        settingsButton.setForeground(Color.WHITE);*/
        GameStarter gs = new GameStarter(frame);
        GameLoaderOpener glo = new GameLoaderOpener(frame);
        //settingsButton.addMouseListener(sBo);
        newButton.addMouseListener(gs);
        loadButton.addMouseListener(glo);
        setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0,10,0,10);
        c.gridwidth = 2;
        this.add(Title,c);

        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 1;
        c.gridx = 0;
        c.weightx = 0.4;
        c.weighty = 1;
        c.insets = new Insets(0,10,0,10);
        this.add(newButton,c);

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = 0.4;
        c.weighty = 1;
        c.insets = new Insets(0,10,0,10);
        this.add(loadButton,c);

        /*c.gridy = 3;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0,10,0,10);
        this.add(settingsButton,c);*/

        frame.pack();
    }
    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.setClip(CaseTable.createHexagon(new Point(200,200),50));
            BufferedImage ig = ImageIO.read(new File("resources/textures/terrain/grass_1.png"));
            TexturePaint tp = new TexturePaint(ig,new Rectangle(0,0,500, 500));
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(tp);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(CaseTable.createHexagon(new Point(300,300),500));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }*/
}
