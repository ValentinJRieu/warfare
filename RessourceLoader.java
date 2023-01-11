package wargame;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import wargame.affichage.GameDisplay;
import wargame.affichage.MainMenu;
import wargame.affichage.Option;
import wargame.carte.Carte;
import wargame.soldats.Heros;
import wargame.soldats.Monstres;

import javax.swing.*;

public class RessourceLoader {
	public static InputStream load(String path)
	{
		return InputStream.class.getResourceAsStream(path);
	}


}
