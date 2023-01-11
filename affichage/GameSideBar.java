package wargame.affichage;

import wargame.event.GameEvent;
import wargame.event.GameEventManager;
import wargame.event.GameListener;
import wargame.event.events.UpdateInfoEvent;
import wargame.carte.Carte;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

/**
 * La classe permettant d'afficher les informations relative à la case et la minimap
 */
public class GameSideBar extends JPanel {
    public GameWindow parent;

    public BufferedImage background;

    public Image bgImage;

    private String[] terrainInfo,unitInfo;

    private JLabel terrainInfoLabel,unitInfoLabel;

    private Font font;

    /**
     * Initialise le panneau des informations relative à la case et la minimap
     * @param parent La fenêtre de jeu
     */
    public GameSideBar(GameWindow parent){
        super();
        Dimension dim = new Dimension(parent.getWidth() * 2/10, parent.getHeight() * 9/10);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(new Color(30,60,90));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        this.parent = parent;
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            background = ImageIO.read(Objects.requireNonNull(InputStream.class.getResourceAsStream("/wargame/resources/textures/gui/side_bar.png")));
            bgImage = background.getScaledInstance(parent.getWidth() * 2/10,parent.getHeight() * 9/10,Image.SCALE_FAST);
            InputStream myStream = new BufferedInputStream(Objects.requireNonNull(InputStream.class.getResourceAsStream("/wargame/resources/fonts/medieval2.ttf")));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 30);
            genv.registerFont(font);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (FontFormatException e)
        {
            throw new RuntimeException(e);
        }
        addComponentListener(new ComponentAdapter() {
            /**
             * Adapte la taille de l'image à l'écran
             * @param e L'event
             */
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                bgImage = background.getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST);
            }
        });
        terrainInfo = new String[5];
        unitInfo = new String[5];
        terrainInfoLabel = new JLabel();
        terrainInfoLabel.setFont(font);
        terrainInfoLabel.setForeground(Color.WHITE);
        terrainInfoLabel.setHorizontalTextPosition(JLabel.CENTER);
        unitInfoLabel = new JLabel();
        unitInfoLabel.setFont(font);
        unitInfoLabel.setForeground(Color.WHITE);
        unitInfoLabel.setHorizontalTextPosition(JLabel.CENTER);
        setTerrainInfo("");
        setUnitInfo("");
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.5;
        c.weightx = 1;
        add(terrainInfoLabel,c);
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.5;
        c.weightx = 1;
        add(unitInfoLabel,c);
        GameEventManager.addListener(new GameListener() {
            @Override
            public void triggered(GameEvent e) {
                Carte c = ((UpdateInfoEvent) e).carte;
                if(c.hasActif())
                {
                    String t = c.returnActif()[1];
                    String u = c.returnActif()[0];
                    if(t == null)t = "";
                    if(u == null)u = "";
                    setTerrainInfo(t);
                    setUnitInfo(u);
                }
            }
        },"RefreshSideBar");
    }

    public void setUnitInfo(String unitInfo) {
        String test[] = unitInfo.split("\n");
        if(test.length > 5)
        {
            throw new Error("Les informations des unités doivent faire 5 lignes maximum");
        }
        this.unitInfo = test;
        unitInfoLabel.setText(getUnitInfo());
        repaint();
    }

    public void setTerrainInfo(String terrainInfo) {
        String test[] = terrainInfo.split("\n");
        if(test.length > 5)
        {
            throw new Error("Les informations du Terrain doivent faire 5 lignes maximum");
        }
        this.terrainInfo = test;
        terrainInfoLabel.setText(getTerrainInfo());
        repaint();
    }

    public String getTerrainInfo() {
        return "<html>" + String.join("<br>",terrainInfo) + "</html>";
    }

    public String getUnitInfo() {
        return "<html>" + String.join("<br>",unitInfo) + "</html>";
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
