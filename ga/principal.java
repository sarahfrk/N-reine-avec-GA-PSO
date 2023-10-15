//GA

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;




public class principal extends JFrame implements ActionListener {
	private static int n;
	private static Double m;
	private static Integer k;
	private static int nbrNoeudsGeneres;
	private static int nbrNoeudsDevelopes;
	private static double time2;
	private static double popSize;
	private static double iteration;
	private static int suc;
	private static int fail;
	
	private static long startTime,endTime,time;

    private static int[] tab = new int[n];
	private static int[] count = new int[2];
	private static List<Chromosome> ma;
	private static Chromosome maliste;
	private static String nomMethod;
    private JTextField textField;
    private JTextArea textArea;
    private JButton btnChoix1, btnChoix2, btnChoix3, btnChoix4;
    private JTextField textField2;
    private JTextArea textArea2;
    private JComboBox<Double> comboBox;
    private JComboBox<Integer> comboBox2;
    
    

    public principal() {
        super("Jeux des n-reine");

        // création des composants de l'interface graphique
        textField = new JTextField(5);
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
       // Ajout des marges au JTextArea
        Insets insets = new Insets(60, 80, 20, 20);
        textArea.setMargin(insets);
        textArea.setBackground(new Color(139, 69, 19));
        Font font = new Font("Arial", Font.BOLD, 14);
        textArea.setFont(font);
        textArea.setForeground(Color.WHITE);
        //textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        String texte = "Bonjour à tous !\n\nVous etes la bien venu au jeux des N-reine.\n\nVous pouvez choisir n'importe quelle méthode pour afficher la solution a votre Taille préfére!\n\n Enjoy Your Time!!";
        // Afficher le texte dans le JTextArea
        textArea.setText(texte);
        
        
        btnChoix1 = new JButton("GA");
        btnChoix1.addActionListener(this);
        btnChoix1.setBackground(new Color(139, 69, 19));
        btnChoix1.setForeground(Color.WHITE);
        btnChoix2 = new JButton("PSO");
        btnChoix2.addActionListener(this);
        btnChoix2.setBackground(new Color(139, 69, 19));
        btnChoix2.setForeground(Color.WHITE);
        
        Double[] options = {0.001, 0.005, 0.01, 0.05, 0.1, 4.0, 8.0, 12.0, 16.0, 20.0};
        comboBox = new JComboBox<>(options);
        Integer[] options2 = {50, 1000, 5000, 10000, 50000};
        comboBox2 = new JComboBox<>(options2);
       
        //
        // ajout des composants à la fenêtre
        JPanel panel = new JPanel();
        panel.add(new JLabel("mutationRate / maxVelocity"));
        panel.add(comboBox);
        panel.add(new JLabel("MaxEpoch"));
        panel.add(comboBox2);
        
        panel.add(new JLabel("Taille d'échiquier"));
        Border line = BorderFactory.createLineBorder(new Color(139, 69, 19), 2);
        Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border compound = BorderFactory.createCompoundBorder(line, margin);
        textField.setBorder(compound);
        panel.add(textField);
        panel.setBackground(new Color(245, 245, 220));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        
        
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        panel.add(new JLabel("Choisissez une Methode"));
        panel.add(btnChoix1);
        panel.add(btnChoix2);
        

        // configuration de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        
     
        
        
    }
    
    
    //Appel au fonctions
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	//GA
        if (e.getSource() == btnChoix1) {
        	
            // récupération de la taille de la matrice
            n = Integer.parseInt(textField.getText());
            
            // Récupération de la valeur sélectionnée et conversion en entier
            Double selectedValue = (Double) comboBox.getSelectedItem();
            // Utilisation de la valeur sélectionnée
            m = selectedValue;
            System.out.println("mutation : " + selectedValue);
            
            
         // Récupération de la valeur sélectionnée et conversion en entier
            Integer selectedValue2 = (Integer) comboBox2.getSelectedItem();
            // Utilisation de la valeur sélectionnée
            k = selectedValue2;
            System.out.println("epoc : " + selectedValue2);
            
            if (n < 4) {
                JOptionPane.showMessageDialog(null, "Erreur : La Taille doit être entre 4 et 14", "Erreur", JOptionPane.ERROR_MESSAGE);
            }else {
            	TesterGA T = new TesterGA();
            	maliste = T.main(maliste, n, m, k);
            	time2 = T.getT();
            	popSize = T.getPopSize();
            	iteration = T.getIter();
            	suc = T.getSucc();
            	fail = T.getFail();
            	
            	System.out.println("Voila "+maliste.getMaxLength());
            	for(int j=0; j<maliste.getMaxLength(); j++) {
            		System.out.println(maliste.getGene(j));
            	}
                nomMethod="GA";
                
                secondaire maFenetre = new secondaire();
            	maFenetre.setVisible(true); 
            	JolieInterface maFenetre2 = new JolieInterface();
    	        maFenetre2.setVisible(true); 
            	
    	        dispose();
            }  
        }
        
        
        
        
        
   
    }
    
    
    
    
    

    public static void main(String[] args) {
        new principal();
    }
    
    public static Chromosome getList() {
        return maliste;
    }
    
    public static String getNom() {
    	return nomMethod;
    }
    
    public static int getNbGen() {
    	return count[0];
    }
    
    public static int getNbDev() {
    	return count[1];
    }
    
    
    public static int[] getSol() {
    	return tab;
    }
    
    public static double getTime() {
    	return time2;
    }
    
    public static double getS() {
    	return popSize;
    }
    
    public static double getI() {
    	return iteration;
    }
    
    public static int getSuc() {
    	return suc;
    }
    
    public static int getFail() {
    	return fail;
    }
    
    
    
    
    
    
    
}
