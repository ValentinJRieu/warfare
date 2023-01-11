package wargame.affichage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * La minimap et ses fonctionnalités
 */
public class MiniMap extends JPanel {
    public GameSideBar parent;
    public GameDisplay gameDisplay;

    public BufferedImage background;

    public int w,h;

    /**
     * Initialisation de la minimap
     * @param parent le menu des information relative aux cases
     */
    MiniMap(GameSideBar parent) {
        super();
        Dimension dim = new Dimension(parent.getWidth(), parent.getHeight()*3/10);
        setSize(dim);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setBackground(Color.DARK_GRAY);
        this.parent = parent;
        gameDisplay = parent.parent.gameDisplay;
        MiniMapManager mmm = new MiniMapManager(this);
        addMouseListener(mmm);
        addMouseMotionListener(mmm);
        /*try {
            background = ImageIO.read(Objects.requireNonNull(InputStream.class.getResourceAsStream("/wargame/resources/textures/gui/minimap.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /**
     * Rafraichissement de la minimap
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        CaseTable ct = gameDisplay.ct;
        //System.out.println(getWidth()+"x"+getHeight());

        //g2.drawImage(background.getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST),0,0,null);
        w = getWidth()/(ct.columns) > 0 ? getWidth()/(ct.columns) : 1;
        h = getHeight()/(ct.rows)>0 ? getHeight()/(ct.rows) : 1;
        for(int i = 0;i < ct.rows;i++) {
            for (int j = 0; j < ct.columns; j++) {
                Rectangle r = new Rectangle();
                r.setRect((double)j*w+(double)(getWidth()/2)-(double)(w*ct.columns/2),(double)i*h+(double)(getHeight()/2)-(double)(h* ct.rows/2),w,h);
                if(((i == gameDisplay.getiStart() || i == gameDisplay.getiEnd()) && j >= gameDisplay.getjStart() && j <= gameDisplay.getjEnd()) || ((i >= gameDisplay.getiStart() && i <= gameDisplay.getiEnd() && (j == gameDisplay.getjStart() || j == gameDisplay.getjEnd())))){
                    g2.setColor(Color.WHITE);
                }else{
                    g2.setColor(ct.getTerrain(j,i).getImage());
                }
                g2.fill(r);
            }
        }

    }
}
