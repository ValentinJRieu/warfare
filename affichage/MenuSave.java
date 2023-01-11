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

public class MenuSave extends JPanel {
    public Font titleFont,font;

    /**
     * Initialise le menu principal a l'aide de la fenêtre
     * @param frame la fenêtre
     */
    public MenuSave(JFrame frame, Carte carte) {
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
            titleFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 128);
            genv.registerFont(font);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        JLabel Title = new JLabel("Choisissez la sauvegarde");
        Title.setFont(titleFont);
        Title.setForeground(new Color(194, 187, 143));


        JButton save1 = new JButton("Emplacement de Sauvegarde 1 - VIDE");
        save1.setFont(font);
        save1.setBackground(new Color(50,50,50));
        save1.setForeground(Color.WHITE);

        JButton save2 = new JButton("Emplacement de Sauvegarde 2 - VIDE");
        save2.setFont(font);
        save2.setBackground(new Color(50,50,50));
        save2.setForeground(Color.WHITE);

        JButton save3 = new JButton("Emplacement de Sauvegarde 3 - VIDE");
        save3.setFont(font);
        save3.setBackground(new Color(50,50,50));
        save3.setForeground(Color.WHITE);

        JButton save4 = new JButton("Emplacement de Sauvegarde 4 - VIDE");
        save4.setFont(font);
        save4.setBackground(new Color(50,50,50));
        save4.setForeground(Color.WHITE);

        JButton save5 = new JButton("Emplacement de Sauvegarde 5 - VIDE");
        save5.setFont(font);
        save5.setBackground(new Color(50,50,50));
        save5.setForeground(Color.WHITE);

        JButton retour = new JButton("Retour");
        retour.setFont(font);
        retour.setBackground(new Color(50,50,50));
        retour.setForeground(Color.WHITE);

        setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10,10,10,10);
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

        c.gridy = 5;
        this.add(save5,c);

        c.gridy = 6;
        add(retour,c);

        frame.pack();

        save1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                carte.save("slot1");
            }
        });

        save2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                carte.save("slot2");
            }
        });

        save3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                carte.save("slot3");
            }
        });

        save4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                carte.save("slot4");
            }
        });

        save5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                carte.save("slot5");
            }
        });

        retour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(Option.oldScreen == 0)
                {
                    new MainMenu(frame);
                }
                else
                {
                    new MenuEchap(frame, Carte.load("auto-save"));
                }
            }
        });


        try {
            String SavePath = java.nio.file.Paths.get("").toAbsolutePath() + "/saves";
            new File(SavePath).mkdirs();
            Path dirPath = Paths.get(SavePath);
            try (DirectoryStream<Path> dirPaths = Files
                    .newDirectoryStream(dirPath)) {
                for (Path file : dirPaths) {
                    switch (file.getFileName().toString())
                    {
                        case "auto-save.save":
                            break;
                        case "slot1.save":
                            save1.setText("Emplacement de Sauvegarde 1 - PLEIN");
                            break;
                        case "slot2.save":
                            save2.setText("Emplacement de Sauvegarde 2 - PLEIN");
                            break;
                        case "slot3.save":
                            save3.setText("Emplacement de Sauvegarde 3 - PLEIN");
                            break;
                        case "slot4.save":
                            save4.setText("Emplacement de Sauvegarde 4 - PLEIN");
                            break;
                        case "slot5.save":
                            save5.setText("Emplacement de Sauvegarde 5 - PLEIN");
                            break;
                        default:
                            throw new Error("Dossier de sauvegarde Corrompu");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

