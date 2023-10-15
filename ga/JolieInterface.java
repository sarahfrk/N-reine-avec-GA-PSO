//ici
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JolieInterface extends JFrame {
    private static String nomMethod = principal.getNom();
    private static int nbrNoeudsGeneres = principal.getNbGen();
    private static int nbrNoeudsDevelopes = principal.getNbDev();
    private static Chromosome sol = principal.getList();
    private static List<Integer> sol2;
    private static double time = principal.getTime();
    private static double s = principal.getS();
    private static double i = principal.getI();
    private static double suc = principal.getSuc();
    private static double fail = principal.getFail();
    private JButton button;
    
    public JolieInterface() {
        super("Mesures de performance");
        // Création du JLabel pour le titre
        JLabel labelTitre = new JLabel("Mesures de performance de la méthode: " + nomMethod);
        labelTitre.setForeground(new Color(153,153,0));

        sol2 = new ArrayList<Integer>();
        for (int i = 0; i < sol.getMaxLength(); i++) {
            sol2.add(sol.getGene(i));
        }
        
        // Création des labels pour les informations
        JLabel labelInfo3 = new JLabel("La solution : "+sol2);
        JLabel labelInfo2 = new JLabel("La taille de population : "+s);
        JLabel labelInfo1 = new JLabel("Le nombre d'iteration : "+i);
        JLabel labelInfo4 = new JLabel("Le temps d'execution : "+time+" ms");
        JLabel labelInfo5 = new JLabel("Le nombre de success : "+suc);
        JLabel labelInfo6 = new JLabel("Le nombre de faillures : "+fail);

     // Création du JPanel contenant les labels d'information
        JPanel panelInfo = new JPanel(new GridLayout(4, 1));
        panelInfo.add(labelInfo3);
        panelInfo.add(labelInfo2);
        panelInfo.add(labelInfo1);
        panelInfo.add(labelInfo4);
        panelInfo.add(labelInfo5);
        panelInfo.add(labelInfo6);
        
        
        
        
        // Centrer les labels d'information
        labelInfo3.setHorizontalAlignment(JLabel.CENTER);
        labelInfo5.setHorizontalAlignment(JLabel.CENTER);
        labelInfo6.setHorizontalAlignment(JLabel.CENTER);
        labelInfo2.setHorizontalAlignment(JLabel.CENTER);
        labelInfo4.setHorizontalAlignment(JLabel.CENTER);
        labelInfo1.setHorizontalAlignment(JLabel.CENTER);
        
        // Ajouter un peu de coloration aux labels d'information
        labelInfo3.setForeground(Color.black);
        labelInfo4.setForeground(Color.black);
        labelInfo5.setForeground(Color.black);
        labelInfo6.setForeground(Color.black);
        labelInfo2.setForeground(Color.black);
        panelInfo.setBackground(Color.WHITE);
        labelInfo1.setForeground(Color.black);

        // Ajout du JPanel contenant les labels d'information à la région centre de la fenêtre
        add(panelInfo, BorderLayout.CENTER);

        // Création du JPanel contenant le titre et le bouton
        JPanel panelTitre = new JPanel(new BorderLayout());
        panelTitre.add(labelTitre, BorderLayout.NORTH);
       // panelTitre.add(panelButton, BorderLayout.SOUTH); // Ajout du panelButton en bas du panelTitre
        labelTitre.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitre.setHorizontalAlignment(JLabel.CENTER);

        // Ajout du JPanel contenant le titre à la région nord de la fenêtre
        add(panelTitre, BorderLayout.NORTH);

        // Configuration de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        //setBackground(Color.BLACK);
        panelInfo.setBackground(new Color(210, 180, 140));

        setVisible(true);
    }

    public static void main(String[] args) {
        new JolieInterface();
    }
}

