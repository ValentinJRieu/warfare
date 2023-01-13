package wargame.affichage;

import wargame.carte.Carte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MenuEchap extends JPanel {
    public Font titleFont,font;

    /**
     * Initialise le menu principal a l'aide de la fenêtre
     * @param frame la fenêtre
     */
    public MenuEchap(JFrame frame, Carte carte) {
        frame.setContentPane(this);
        setPreferredSize(new Dimension(Option.WIDTH,Option.HEIGHT));
        setBackground(new Color(100,100,100));
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            InputStream myStream = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/wargame/resources/fonts/MainMenu.ttf")));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 72);
            genv.registerFont(font);
            myStream = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/wargame/resources/fonts/Title.ttf")));
            titleFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 128);
            genv.registerFont(font);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        JLabel Title = new JLabel("Menu Pause");
        Title.setFont(titleFont);
        Title.setForeground(new Color(194, 187, 143));


        JButton save1 = new JButton("Menu Principal");
        save1.setFont(font);
        save1.setBackground(new Color(50,50,50));
        save1.setForeground(Color.WHITE);

        JButton save2 = new JButton("Savegarder");
        save2.setFont(font);
        save2.setBackground(new Color(50,50,50));
        save2.setForeground(Color.WHITE);

        JButton save3 = new JButton("Charger");
        save3.setFont(font);
        save3.setBackground(new Color(50,50,50));
        save3.setForeground(Color.WHITE);

        JButton save4 = new JButton("Retour au jeu");
        save4.setFont(font);
        save4.setBackground(new Color(50,50,50));
        save4.setForeground(Color.WHITE);

        setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0,10,0,10);
        c.gridwidth = 2;
        this.add(Title,c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        this.add(save1,c);

        c.gridy = 2;
        this.add(save2,c);

        c.gridy = 3;
        this.add(save3,c);

        c.gridy = 4;
        this.add(save4,c);

        frame.pack();

        save1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new MainMenu(frame);
            }
        });

        save2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new MenuSave(frame,carte);
                Option.oldScreen = 1;
            }
        });

        save3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new LoadMenu(frame);
                Option.oldScreen = 1;
            }
        });

        save4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new GameWindow(frame,"auto-save");
            }
        });
    }
}

