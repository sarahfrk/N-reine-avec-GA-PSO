import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class secondaire extends JFrame {
    //récupérer les valeurs envoyées par l'autre fenêtre
    private static Chromosome maList = principal.getList();
    // Dimensions de l'échiquier
    private static final int DIMENSION = maList.getMaxLength();
    int[] tableau =  new int[DIMENSION];
    
    

    public secondaire() {
        super("Echiquier");

        for (int i = 0; i < DIMENSION; i++) {
            tableau[i] = maList.getGene(i);
          }
        
        // Création du plateau
        JPanel plateau = new JPanel(new GridLayout(DIMENSION, DIMENSION));

        // Parcours des cases
        for (int ligne = 0; ligne < DIMENSION; ligne++) {
            for (int colonne = 0; colonne < DIMENSION; colonne++) {
                // Création de la case
                JPanel caseEchiquier = new JPanel();

                // Coloration de la case en noir ou blanc
                if ((ligne + colonne) % 2 == 0) {
                    caseEchiquier.setBackground(new Color(210, 180, 140));
                } else {
                    caseEchiquier.setBackground(new Color(139, 69, 19));
                }

                // Ajout de la case sur le plateau
                plateau.add(caseEchiquier);

                // Si la case contient une reine, ajout de l'image de la reine
                if (tableau[ligne] == colonne) {
                	
                    ImageIcon iconeReine = new ImageIcon(getClass().getResource("crown(1).png"));
                    //setIconImage(Toolkit.getDefaultToolkit().getImage("./img/cown(1)"));
            		
                    Image imageReine = iconeReine.getImage();
                    Image imageRedimensionnee = imageReine.getScaledInstance(65, 55, Image.SCALE_SMOOTH);
                    ImageIcon iconeReineRedimensionnee = new ImageIcon(imageRedimensionnee);
                    JLabel labelReine = new JLabel(iconeReineRedimensionnee);
                    //JLabel labelReine2 = new JLabel("Reine");
                    caseEchiquier.add(labelReine);
                }
            }
        }

        // Création du JScrollPane contenant le plateau
        JScrollPane scrollPane = new JScrollPane(plateau);

        // Ajout du JScrollPane à la fenêtre
        add(scrollPane);

        // Configuration de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new secondaire();
        System.out.println("fzefez");
    }
}
