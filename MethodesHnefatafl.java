import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class MethodesHnefatafl {

    public static int[][] plateau;
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Méthode qui gère le déroulement de la partie
     */
    public static void partie() {
        String[] tabDonnee = new String[5];
        int tour,idPremierJoueur,compteur=0;

        MethodesHnefatafl.choixPlateau();
        idPremierJoueur= MethodesHnefatafl.choixJoueur(tabDonnee);
        //Choix du camp qui commence
        if (tabDonnee[2].equals("attaquant"))
            tour=1;
        else
            tour=2;

        while (!MethodesHnefatafl.gagnant()){
            compteur++;
            System.out.println();
            System.out.println("Au tour de "+ tabDonnee[tour-1]);
            if (tour == 1) {
                if (tabDonnee[0].equals("ROBOT")){
//                    MethodesHnefatafl.tempsInterrompre(1500);
                    MethodesHnefatafl.robot(tour,tabDonnee[3+idPremierJoueur]);
                }
                else
                    MethodesHnefatafl.tour(tour);
            }
            else {
                if (tabDonnee[1].equals("ROBOT")){
//                    MethodesHnefatafl.tempsInterrompre(1500);
                    MethodesHnefatafl.robot(tour,tabDonnee[4-idPremierJoueur]);
                }
                else
                    MethodesHnefatafl.tour(tour);
            }
            tour = tour % 2 + 1;
        }
        MethodesHnefatafl.affichePlateau();
        if (MethodesHnefatafl.pionVivant(3) && !MethodesHnefatafl.roiBloque(MethodesHnefatafl.cooRoi()))
            System.out.println("Le défenseur : "+tabDonnee[1]+" a gagné !");
        else
            System.out.println("L'attaquant : "+tabDonnee[0]+" a gagné !");
        System.out.println("Il y a eu "+compteur+" échange en tout !");

        if (tabDonnee[3].equals("impossible")||tabDonnee[4].equals("impossible")) {
            MethodesHnefatafl.tempsInterrompre(1500);
            System.out.println();
            System.out.println("Cela t'apprendra à essayer de me battre !");
            MethodesHnefatafl.tempsInterrompre(1500);
            System.out.println("Si tu veux avoir une meilleure chance de gagner, essaie de jouer avec un de mes petits frère facile, moyen ou difficile.");
            MethodesHnefatafl.tempsInterrompre(3500);
            System.out.println("Aurevoir être humain naif.");
        }
    }
    /**
     * Méthode qui affiche le titre du jeu
     */
    public static void affichageTitre(){
        String[] ligneText = {
                "  ██    ██   ████    ██  ███████  ████████  █████████  ████████  █████████  ████████  ██",
                "  ██    ██   ██ ██   ██  ██       ██        ██     ██     ██     ██     ██  ██        ██",
                "  ████████   ██  ██  ██  █████    █████     █████████     ██     █████████  █████     ██",
                "  ██    ██   ██   ██ ██  ██       ██        ██     ██     ██     ██     ██  ██        ██",
                "  ██    ██   ██    ████  ███████  ██        ██     ██     ██     ██     ██  ██        ███████"};


        // Parcours de chaque caractère dans le texte
        for (String text : ligneText) {
            for (char c : text.toCharArray()) {
                // Si le caractère est différent de l'espace, afficher en rouge
                if (c != ' ') {
                    System.out.print("\u001B[31m" + "\u001B[41m" + c + "\u001B[0m");  // Code ANSI pour la couleur rouge
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }
    /**
     * Méthode qui affiche les règles du jeu
     */
    public static void regles(){
        String enGras = "\u001B[1m"; // Code ANSI pour mettre le texte en gras
        String resetStyle = "\u001B[0m"; // Code ANSI pour réinitialiser le style du texte
        String souligne = "\u001B[4m"; // Code ANSI pour le texte souligné
        String fondBlanc = "\u001B[107m"+" "+resetStyle;// Code ANSI pour le fond blanc

        System.out.print(fondBlanc +"\n" +fondBlanc+enGras+" Voici les règles du jeu : \n"+resetStyle+fondBlanc+"\n");
        System.out.print(
                fondBlanc+" Hnefatafl, également connu sous le nom de Viking Chess, est un ancien jeu de stratégie nordique qui se joue sur un plateau carré.\n" +
                        fondBlanc+" Il existe plusieurs variantes du jeu, mais les règles utilisées dans sont celles-ci : \n" +
                        fondBlanc+"\n" +
                        fondBlanc+" " + souligne+enGras+"Objectif :\n" +resetStyle+
                        fondBlanc+"\n"+

                        fondBlanc+" ● Pour les défenseurs, (représenté en bleu) correspondant au roi(un carré bleu) et à ses hommes : \n" +
                        fondBlanc+"   --> Leur objectif est de permettre au roi de s'échapper vers l'un des quatre coins du plateau.\n" +
                        fondBlanc+" ● Pour les attaquants, (représenté en rouge) correspondant aux assaillants : \n" +
                        fondBlanc+"   --> Leur objectif est de capturer le roi.\n" +

                        fondBlanc+"\n" +
                        fondBlanc+" " + souligne+enGras+"Disposition initiale :\n" +resetStyle+
                        fondBlanc+"\n"+

                        fondBlanc+" ● Les pièces sont positionnées en général de la même manière :\n" +
                        fondBlanc+"   --> Le roi à la case centrale, et ses hommes autour de lui.\n" +
                        fondBlanc+"   --> Les attaquants sont disposés sur les bords du plateau.\n" +
                        fondBlanc+" ● Le nombre de pion de chaque équipe changent selon la taille du plateau.\n" +

                        fondBlanc+"\n" +
                        fondBlanc+" " + souligne+enGras+"Déplacements :\n" +resetStyle+
                        fondBlanc+"\n"+

                        fondBlanc+" ● N'importe quel pièce peut se déplacer uniquement horizontalement ou verticalement sur une longueur illimité (tant qu'il n'y a pas de pion qui le gêne).\n" +
                        fondBlanc+" ● Seul le roi peut occuper le trône au centre du plateau.\n" +

                        fondBlanc+"\n" +
                        fondBlanc+" " + souligne+enGras+"Capture :\n" +resetStyle+
                        fondBlanc+"\n"+

                        fondBlanc+" ● Les pions sont capturées en les enfermant entre deux pions adverses, horizontalement ou verticalement.\n" +
                        fondBlanc+" ● Le roi se fait capturées quand il est entouré par 4 assaillants obligatoirement (horizontalement et verticalement).\n" +
                        fondBlanc+" ● Les 4 coins du plateau et le trône sont considérés comme des cases \"multi-équipe\" appelé des \"bastions\" :\n" +
                        fondBlanc+"   --> Un pion peut se faire capturé si l'est entouré d'une de ces cases et d'un pion ennemi.\n" +
                        fondBlanc+"  --> Le roi peut se faire capturé s'il est entouré par 3 assaillants et son trône.\n" +
                        fondBlanc+"\n" +
                        fondBlanc+" ● En revanche, le pion ne se fait pas capturé si il se place directement entre 2 pion ennemi.\n" +

                        fondBlanc+"\n" +
                        fondBlanc+" " + souligne+enGras+"Fin du jeu :\n" +resetStyle+
                        fondBlanc+"\n"+

                        fondBlanc+" ● Si le roi atteint l'un des coins, les défenseurs l'emportent. Si le roi est capturé, les attaquants gagnent.\n" +
                        fondBlanc+"\n\n"
        );
    }
    /**
     * Méthode qui gère l'affichage du plateau de jeu
     */
    public static void affichePlateau() {

        //Affichage du numéro des colonnes
        System.out.print("    ");
        char ligne = 'A';
        for (int n = 1 ; n <= plateau.length ; n++){
            System.out.print(n+" ");
        }
        //Affichage de la séparation du haut du plateau
        System.out.print("\n    ");
        for (int n = 1 ; n <= plateau.length ; n++){
            System.out.print("\u001B[4m \u001B[0m"+" ");
        }
        System.out.println();
        //Affichage du plateau et du numéro de chaque ligne
        for (int i = 0 ; i < plateau.length ; i++){
            System.out.print((char)(ligne+i)+"  |");
            for (int j = 0 ; j < plateau.length ; j++){
                switch (plateau[i][j]){
                    case 0 :  System.out.print("\u001B[4m"+"\u001B[37m"+' '+"\u001B[0m"+"|");
                        break;
                    case 1 :  System.out.print("\u001B[4m"+"\u001B[31m"+'◯'+"\u001B[0m"+"|");
                        break;
                    case 2 :  System.out.print("\u001B[4m"+"\u001B[34m"+'●'+"\u001B[0m"+"|");
                        break;
                    case 3 :  System.out.print("\u001B[4m"+"\u001B[34m"+'■'+"\u001B[0m"+"|");
                        break;
                    case 4 :  System.out.print("\u001B[4m"+'■'+"\u001B[0m"+"|");
                        break;
                    case 5 :  System.out.print("\u001B[4m"+"\u001B[33m"+'◌'+"\u001B[0m"+"|");
                        break;
                    case 6 :  System.out.print("\u001B[4m"+"\u001B[33m"+'◯'+"\u001B[0m"+"|");
                        break;
                    case 7 :  System.out.print("\u001B[4m"+"\u001B[33m"+'●'+"\u001B[0m"+"|");
                        break;
                    case 8 :  System.out.print("\u001B[4m"+"\u001B[33m"+'■'+"\u001B[0m"+"|");
                        break;
                }
            }
            System.out.println();
        }
    }
    /**
     * Méthode qui renvoie la copie d'un tableau donné
     * @param tempplateau tableau à copier
     * @return retourne un tableau à deux dimensions qui est une copie de celui entré en paramètre
     */
    public static int[][] copie(int[][] tempplateau){
        int[][] copieplateau = new int[tempplateau.length][tempplateau.length];
        for (int i = 0; i < tempplateau.length; i++){
            for (int j = 0; j < tempplateau.length; j++){
                copieplateau[i][j] = tempplateau[i][j];
            }
        }
        return copieplateau;
    }
    /**
     * Méthode qui affiche le plateau avec le chemin que peut faire le pion désigné par le joueur
     * @param position tableau avec la position du pion qui est sélectionné par le joueur
     */
    public static void afficheChemin(int[] position) {
        int[][] tempplateau = copie(plateau);
        switch (plateau[position[0]][position[1]]) {
            case 1:
                plateau[position[0]][position[1]] = 6;
                break;
            case 2:
                plateau[position[0]][position[1]] = 7;
                break;
            case 3:
                plateau[position[0]][position[1]] = 8;
        }
        for (int i = position[0] + 1; i < plateau.length && plateau[i][position[1]] == 0; i++)
            if (!testBastion(i,position[1]))plateau[i][position[1]] = 5;
        for (int i = position[0] - 1; i >= 0 && plateau[i][position[1]] == 0; i--)
            if (!testBastion(i,position[1]))plateau[i][position[1]] = 5;
        for (int j = position[1] + 1; j < plateau.length && plateau[position[0]][j] == 0; j++)
            if (!testBastion(position[0], j))plateau[position[0]][j] = 5;
        for (int j = position[1] - 1; j >= 0 && plateau[position[0]][j] == 0; j--)
            if (!testBastion(position[0], j))plateau[position[0]][j] = 5;

        affichePlateau();
        plateau = tempplateau;
    }
    /**
     * Méthode qui gère le tour du joueur
     */
    public static void tour(int tour) {
        affichePlateau();
        deplacement(tour);
    }
    /**
     * Fonction qui retourne le plateau choisi en fonction du choix du joueur
     */
    public static void choixPlateau() {

        int saisie;
        //Demande à l'utilisateur la taille du plateau sur lequel il veut joueur
        do {
            System.out.println("Quelle taille de plateau voulez-vous, 7 ou 9 ?");
            saisie = scanner.nextInt();
            //Envoie un message d'erreur si la valeur entrée ne correspond pas aux attentes
            if (!(saisie == 7 || saisie == 9)) System.out.println("La valeur entrée ne correspond pas à la taille d'un tableau disponible");
        }while (!(saisie == 7 || saisie == 9));
        scanner.nextLine();
        //Retourne le tableau de dimension demandé le default renvoie un tableau vide de dimension 2, mais de longueur 0
        switch (saisie) {
            case 9 -> plateau = new int[][]{
                    {0, 0, 1, 1, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 2, 0, 0, 0, 1},
                    {1, 0, 0, 2, 2, 2, 0, 0, 1},
                    {1, 1, 2, 2, 3, 2, 2, 1, 1},
                    {1, 0, 0, 2, 2, 2, 0, 0, 1},
                    {1, 0, 0, 0, 2, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 1, 0, 0}};
            case 7 -> plateau = new int[][]{
                    {0, 0, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 2, 0, 0, 1},
                    {1, 0, 2, 3, 2, 0, 1},
                    {1, 0, 0, 2, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0}};
            default -> plateau = new int[0][0];
        }
    }
    /**
     * Méthode qui demande les pseudos, le camp qui commence et quel joueur est dans quelle équipe
     * @param tabDonnee tableau stockant les données suivantes : pseudo joueur1, pseudo joueur2 et quelle équipe commence
     */
    public static int choixJoueur(String[] tabDonnee){
        String nomJ1 = "",nomJ2 = "",saisie;
        int idPremierJoueur; //cette variable prend l'indice du premier joueur

        //Demande du premier pseudo
        System.out.println();
        System.out.print("Entrez le pseudo d'un joueur : ");
        nomJ1 = saisieJoueur(nomJ1);
        tabDonnee[3] = choixNiveauDifficuluteRobot(nomJ1);

        //demande du deuxième pseudo (ou du robot)
        System.out.println();
        System.out.print("Entrez le pseudo du deuxième joueur ou \"ROBOT\" pour utiliser un robot pour jouer contre vous : ");
        nomJ2 = saisieJoueur(nomJ2);
        tabDonnee[4] = choixNiveauDifficuluteRobot(nomJ2);

        //demande de quel camp commence à jouer
        System.out.println();
        System.out.print("Quel camps commence à jouer ? Choisissez entre \"défense\" ou \"attaquant\" : ");
        saisie = scanner.nextLine();
        while (!(saisie.equals("défense")||saisie.equals("attaquant")) ){
            System.out.print("Erreur dans le nom du camps, choisissez entre \"défense\" ou \"attaquant\" : ");
            saisie = scanner.nextLine();
        }
        tabDonnee[2] = saisie; //tabDonnee prend le nom de l'équipe qui commence

        //demande dans quelle équipe le joueur/robot se situe
        System.out.println();
        System.out.print(nomJ1+", dans quel équipe voulez-vous être ? Choisissez entre \"défense\" ou \"attaquant\" : ");
        saisie = scanner.nextLine();
        while (!(saisie.equals("défense")||saisie.equals("attaquant")) ){
            System.out.print("Erreur dans le nom du camps, choisissez entre \"défense\" ou \"attaquant\" : ");
            saisie = scanner.nextLine();
        }
        if (saisie.equals("attaquant")) {//Si le joueur 1 décide d'être attaquant
            tabDonnee[0] = nomJ1;
            idPremierJoueur=0;
            tabDonnee[1] = nomJ2;//le joueur 2 sera défenseur
        }
        else {//sinon si le joueur 1 décide d'être défenseur
            tabDonnee[0] = nomJ2;//le joueur 2 sera attaquant
            tabDonnee[1] = nomJ1;
            idPremierJoueur=1;
        }
        System.out.println();
        System.out.println("Tout est enregistré, que le meilleur gagne ! Bonne chance à vous !");
        return idPremierJoueur;
    }
    /**
     * Méthode qui gère la saisie du nom du joueur et qui renvoie le nom sous forme de String
     * @param nom String qui représente le nom d'un joueur
     * @return retourne un String avec le nom du joueur
     */
    public static String saisieJoueur(String nom){
        do {
            if (!nom.isEmpty()) System.out.print("Erreur dans le pseudo, veuillez entrer un pseudo compris entre 3 et 15 caractères : ");
            nom = scanner.nextLine();
        }while (nom.length()<3 || nom.length()>15);
        return nom;
    }
    /**
     * Fonction qui renvoie s'il y a un gagnant (permet de finir la partie)
     * @return retourne true s'il y a un gagnant sinon retourne false
     */
    public static boolean gagnant() {

        //vérifie si le roi est sur un bastion : retourne true
        if (plateau[0][0] == 3 || plateau[0][plateau.length-1] == 3 || plateau[plateau.length-1][0] == 3
                || plateau[plateau.length-1][plateau.length-1] == 3) {
            return true;
        }
        if (!pionVivant(2)){
            if (roiBloque(cooRoi())) return true;
        }
        //vérifie si le roi est toujours vivant et donc si l'attaquant n'a pas gagné
        return !(pionVivant(3) && pionVivant(1));
    }
    /**
     * Renvoie si un pion d'une équipe est vivant
     * @param equipe numéro de l'équipe
     * @return retourne true si un pion de l'équipe est vivant
     */
    public static boolean pionVivant(int equipe) {

        //boucle qui vérifie chaque case pour vérifier si le pion est présent
        for (int[] cases : plateau)
            for (int pion : cases) {
                if (pion == equipe) return true;
            }
        return false;
    }
    /**
     * Méthode qui renvoie si le roi est bloqué ou non
     * @param cooRoi tableau avec les coordonnées du roi
     * @return retourne true si le roi est bloqué sinon retourne false
     */
    public static boolean roiBloque(int[] cooRoi) {
        if (cooRoi == null) return false;
        if (cooRoi[0] == 0 && !testBastion(cooRoi[0],cooRoi[1])){
            if (plateau[cooRoi[0]][cooRoi[1]-1] == 1 && plateau[cooRoi[0]][cooRoi[1]+1] == 1 && plateau[cooRoi[0]+1][cooRoi[1]] == 1) return true;
        }
        if (cooRoi[0] == plateau.length-1 && !testBastion(cooRoi[0],cooRoi[1])){
            if (plateau[cooRoi[0]][cooRoi[1]-1] == 1 && plateau[cooRoi[0]][cooRoi[1]+1] == 1 && plateau[cooRoi[0]-1][cooRoi[1]] == 1) return true;
        }
        if (cooRoi[1] == 0 && !testBastion(cooRoi[0],cooRoi[1])){
            if (plateau[cooRoi[0]-1][cooRoi[1]] == 1 && plateau[cooRoi[0]][cooRoi[1]+1] == 1 && plateau[cooRoi[0]+1][cooRoi[1]] == 1) return true;
        }
        if (cooRoi[1] == plateau.length-1 && !testBastion(cooRoi[0],cooRoi[1])){
            return plateau[cooRoi[0] - 1][cooRoi[1]] == 1 && plateau[cooRoi[0]][cooRoi[1] - 1] == 1 && plateau[cooRoi[0] + 1][cooRoi[1]] == 1;
        }
        return false;
    }
    /**
     * Méthode qui gère le fait de manger des pions
     * @param coo tableau stockant la position du pion qui vient de bouger avec position[0] = ligne et la position[1] = colonne
     */
    public static void manger(int[] coo) {
        int equipe, equipeEnnemi;

        //équipe prend la valeur 1 si le dernier pion déplacé est de l'équipe attaquante
        if (plateau[coo[0]][coo[1]] == 1) {
            equipe = 1;
            equipeEnnemi = 2;   //equipeEnnemi prend la valeur de l'autre équipe
        }
        else {//sinon équipe prend la valeur 2 pour l'équipe défenseur
            equipe = 2;
            equipeEnnemi = 1; //equipeEnnemi prend la valeur de l'autre équipe
        }
        // On teste au-dessus du pion de l'équipe
        if (coo[0] > 1 && testPositionVertical(coo[0]-1, coo[1], equipe, equipeEnnemi, coo[0] - 2)) {
            if (plateau[coo[0] - 1][coo[1]] !=3)
                plateau[coo[0] - 1][coo[1]] = 0; //le pion de l'équipe ennemi est mangé
            if (testRoiEntourer(new int[]{coo[0]-1, coo[1]}))
                plateau[coo[0] - 1][coo[1]] = 4; //Roi manger
        }
        // On teste à droite du pion de l'équipe
        if (coo[1] < plateau[0].length - 2 && testPositionHorizontal(coo[0], coo[1]+1, equipe, equipeEnnemi, coo[1] + 2)) {
            if (plateau[coo[0]][coo[1] + 1] !=3)
                plateau[coo[0]][coo[1] + 1] = 0; //le pion de l'équipe ennemi est mangé
            else if (testRoiEntourer(new int[]{coo[0], coo[1]+1}))
                plateau[coo[0]][coo[1] + 1] = 4; //Roi manger
        }
        // On teste en bas du pion de l'équipe
        if (coo[0] < plateau[0].length - 2 && testPositionVertical(coo[0]+1, coo[1], equipe, equipeEnnemi, coo[0] + 2)) {
            if (plateau[coo[0] + 1][coo[1]] !=3)
                plateau[coo[0] + 1][coo[1]] = 0; //le pion de l'équipe ennemi est mangé
            if (testRoiEntourer(new int[]{coo[0]+1, coo[1]}))
                plateau[coo[0] + 1][coo[1]] = 4; //Roi manger
        }
        // On teste à gauche du pion de l'équipe
        if (coo[1] > 1 && testPositionHorizontal(coo[0], coo[1]-1, equipe, equipeEnnemi, coo[1] - 2)){
            if (plateau[coo[0]][coo[1] - 1] !=3)
                plateau[coo[0]][coo[1] - 1] = 0; //le pion de l'équipe ennemi est mangé
            if (testRoiEntourer(new int[]{coo[0], coo[1]-1}))
                plateau[coo[0]][coo[1] - 1] = 4; //Roi manger
        }
    }
    /**
     * Méthode qui gère les déplacements d'un pion
     * @param tour tour actuel : 1 pour l'attaquant ou 2 pour le défenseur
     */
    public static void deplacement(int tour) {
        int[] position = new int[2];
        String saisie;
        saisie = testDeplacement(position,tour);
        //remplacement des pions
        plateau[saisie.charAt(0) % 65][cooColonne(saisie) - 1] = plateau[position[0]][position[1]];
        plateau[position[0]][position[1]] = 0;
        //stockage des coordonnées d'arrivée du pion pour la méthode manger
        position[0] = saisie.charAt(0) % 65;
        position[1] = cooColonne(saisie) - 1;
        //lancement de la fonction manger
        manger(position);
    }
    /**
     * Méthode qui renvoie si un déplacement est possible ou non
     * @param position tableau stockant les coordonnées du pion à déplacer avec position[0] = ligne et la position[1] = colonne
     * @param tour tour actuel : 1 pour l'attaquant ou 2 pour le défenseur
     * @return retourne true si le déplacement du pion est possible
     */
    public static String testDeplacement(int[] position, int tour){
        String saisie;
        System.out.println("Entrez les coordonnées du pion à déplacer sous la forme lignecolonne (par ex: A2)");
        do {
            do {
                saisie = scanner.nextLine();
                if (saisie.isEmpty()) {
                    System.out.println("Erreur dans la saisie des coordonnées");
                    saisie = scanner.nextLine();
                }
            } while (!verificationSaisie(saisie) );
            position[0] = saisie.charAt(0) % 65;
            position[1] = cooColonne(saisie) - 1;
        }while (!verificationPion(position,tour));
        afficheChemin(position);
        System.out.println("Entrez les coordonnées de la case où le pion doit se déplacer sous la forme lignecolonne (par ex: A2) \n" +
                "OU entrez \"0\" pour changer de pion");
        do {
            do {
                saisie = scanner.nextLine();
                if (saisie.isEmpty())
                    System.out.println("Erreur dans la saisie des coordonnées");
                else if (saisie.equals("0")){
                    affichePlateau();
                    saisie = testDeplacement(position,tour);
                }
            } while (!verificationSaisie(saisie) );
            if (!verificationDeplacement(position,saisie.charAt(0) % 65,cooColonne(saisie) - 1))
                //Message d'erreur si le déplacement n'est pas valide
                System.out.println("Ce déplacement n'est pas permit !");
        }while (!verificationDeplacement(position,saisie.charAt(0) % 65,cooColonne(saisie) - 1));
        return saisie;
    }
    /**
     * Fonction qui retourne si le pion choisi par l'utilisateur est bien le sien et qu'il n'est pas entouré
     * @param position tableau stockant les coordonnées du pion à déplacer avec position[0] = ligne et la position[1] = colonne
     * @param tour tour actuel : 1 pour l'attaquant ou 2 pour le défenseur
     * @return retourne true si le pion est bien celui du joueur et est jouable
     */
    public static boolean verificationPion(int[] position, int tour){

        //vérifie s'il n'y a pas de pion à l'endroit ciblé : retourne false
        if (plateau[position[0]][position[1]] == 0) {
            System.out.println("Il n'y a pas de pion à cet endroit !");
            return false;
        }
        //vérifie si le pion n'est pas celui du joueur : retourne false
        if ((plateau[position[0]][position[1]] == 1 && tour != 1) ||
                (plateau[position[0]][position[1]] == 2 || plateau[position[0]][position[1]] == 3) && tour != 2) {
            System.out.println("Ce n'est pas votre pion !");
            return false;
        }
        return verificationPionPasEntourer(position, false);
    }
    /**
     * Méthode qui renvoie si un pion est entouré ou non
     * @param position tableau stockant les coordonnées du pion
     * @param robot booléen qui sert à ne pas afficher les messages d'erreur quand c'est le robot qui joue
     * @return retourne false si le pion est entouré sinon retourne true
     */
    public static boolean verificationPionPasEntourer(int[] position, boolean robot){
//Vérification du pion s'il n'est pas entouré

        //Si on est sur la première ligne du plateau (haut) alors, on teste la case droite, bas, gauche
        if (position[0] == 0 && verificationExistencePion(position,position[0],position[1]+1)&& verificationExistencePion(position,position[0] + 1,position[1]) && verificationExistencePion(position,position[0],position[1]-1)){
            if (!robot)
                System.out.println("Le pion choisi ne peux pas bouger !");
            return false;
        }
        //Si on est sur la dernière colonne du plateau (droite) alors, on teste la case haut, bas, gauche
        else if (position[1] == plateau[0].length - 1 && verificationExistencePion(position,position[0] - 1,position[1]) && verificationExistencePion(position,position[0] + 1,position[1]) && verificationExistencePion(position,position[0],position[1]-1)){
            if (!robot)
                System.out.println("Le pion choisi ne peux pas bouger !");
            return false;
        }
        //Si on est sur la dernière ligne du plateau (bas) alors, on teste la case haut, droite, gauche
        else if (position[0] == plateau[0].length - 1 && verificationExistencePion(position,position[0] - 1,position[1]) && verificationExistencePion(position,position[0],position[1]+1) && verificationExistencePion(position,position[0],position[1]-1)){
            if (!robot)
                System.out.println("Le pion choisi ne peux pas bouger !");
            return false;
        }
        //Si on est sur la première colonne du plateau (gauche) alors, on teste la case haut, droite, bas
        else if (position[1] == 0 && verificationExistencePion(position,position[0] - 1,position[1]) && verificationExistencePion(position,position[0],position[1]+1) && verificationExistencePion(position,position[0] + 1,position[1])){
            if (!robot)
                System.out.println("Le pion choisi ne peux pas bouger !");
            return false;
        }
        //sinon, on teste tous les côtés
        else if(position[0] > 0 && verificationExistencePion(position,position[0] - 1,position[1])
                && position[1] < plateau[0].length - 1 && verificationExistencePion(position,position[0],position[1]+1)
                && position[0] < plateau[0].length - 1 && verificationExistencePion(position,position[0] + 1,position[1])
                && position[1] > 0 && verificationExistencePion(position,position[0],position[1]-1)){
            if (!robot)
                System.out.println("Le pion choisi ne peux pas bouger !");
            return false;
        }//le pion n'est pas entouré
        else
            return true;
    }
    /**
     * Fonction qui renvoie s'il n'y a pas de pion ou de case multi-équipe(quand le pion n'est pas le roi) à la case vérifiée
     * @param position tableau stockant les coordonnées du dernier déplacement effectué
     * @param ligne contient la ligne de la case à vérifier
     * @param colonne contient la colonne de la case à vérifier
     * @return retourne true s'il n'y a pas de pion ou de case multi-équipe(pion non-roi) sinon retourne false
     * */
    public static boolean verificationExistencePion(int[] position, int ligne, int colonne){
        return (plateau[ligne][colonne] != 0 || (testBastion(ligne, colonne) && plateau[position[0]][position[1]] !=3));
    }
    /**
     * Fonction qui renvoie si le déplacement est possible
     * @param position tableau stockant les coordonnées du pion à déplacer avec position[0] = ligne et la position[1] = colonne
     * @param ligne ligne d'arrivée du déplacement du pion
     * @param colonne colonne d'arrivée du déplacement du pion
     * @return retourne false si le déplacement est incorrect sinon retourne false
     */
    public static boolean verificationDeplacement(int[] position, int ligne, int colonne){

        boolean test = true;
        //le joueur a voulu se déplacer sur un Bastion avec un pion normal : test = false
        if (testBastion(ligne,colonne) && plateau[position[0]][position[1]]!=3)
            test = false;
        //le joueur a voulu se déplacer en diagonale : test = false
        if (position[0] != ligne && position[1] != colonne)
            test = false;
        //le joueur a voulu se déplacer sur une case non-vide : test = false
        if (plateau[ligne][colonne] != 0)
            test = false;

        //le déplacement s'effectue en ligne
        if (ligne != position[0]) {
            //boucle qui permet de parcourir le plateau de l'objectif du déplacement jusqu'à l'endroit où est le pion
            while (ligne != position[0] && test) {
                //un pion a été trouvé sur le déplacement prévu : test = false
                if (plateau[ligne][colonne] != 0)
                    test = false;
                //change l'indice de la ligne pour la prochaine vérification
                if (ligne > position[0]) ligne--;
                else ligne++;
            }
        }
        //le déplacement s'effectue en colonne
        else {
            //boucle qui permet de parcourir le plateau de l'objectif du déplacement jusqu'à l'endroit où est le pion
            while (colonne != position[1]) {
                //un pion a été trouvé sur le déplacement prévu : test = false
                if (plateau[ligne][colonne] != 0 && test)
                    test = false;
                //change l'indice de la ligne pour la prochaine vérification
                if (colonne > position[1]) colonne--;
                else colonne++;
            }
        }
        return test;
    }
    /**
     * Fonction qui retourne la colonne d'une saisie de coordonnées du jeu
     * @param coo coordonnées saisies par le joueur
     * @return retourne un int correspondant à la colonne saisie par le joueur
     */
    public static int cooColonne(String coo) {

        if (coo.length() == 3)
            return ((int)coo.charAt(1)-'0')*10+(int)coo.charAt(2)-'0';
        else
            return (int)coo.charAt(1)-'0';
    }
    /**
     * Fonction qui retourne true si la saisie des coordonnées du joueur est valide
     * @param saisie saisie du joueur
     * @return retourne true si la saisie est correcte sinon retourne false
     */
    public static boolean verificationSaisie(String saisie) {

        //vérifie les saisies de coordonnées de longueur 2 pour les petits plateaux(longueur < 10)
        if (saisie.length() == 2 && plateau.length < 10){
            //vérifie si la ligne saisie est correcte (A-Z)
            if (saisie.charAt(0) % 65 < plateau.length) {
                //vérifie si la colonne saisie est bien dans le tableau(longueur < 10)
                if ((int)saisie.charAt(1)-'0' <= plateau.length && (int)saisie.charAt(1)-'0' > 0) {
                    return true;
                }
            }
        }
        //vérifie les saisies de coordonnées pour plateaux de longueur > 10
        else if (saisie.length() == 3 || saisie.length() == 2){
            //vérifie si la ligne saisie est correcte (A-Z)
            if (saisie.charAt(0) % 65 < 26) {
                //vérifie si la colonne saisie est bien dans le tableau
                if (saisie.length() == 2 && (int)saisie.charAt(1)-'0' <= plateau.length
                        || ((int)saisie.charAt(1)-'0')*10+(int)saisie.charAt(2)-'0' <= plateau.length && (int)saisie.charAt(1)-'0' > 0) {
                    return true;
                }
            }

        }
        //la saisie n'est pas de la bonne taille ou non conforme au plateau
        return false;
    }
    /**
     * Méthode qui gère le déplacement du robot selon la difficulté
     */
    public static void robot(int tour,String difficulte) {
        int[][] cooDepartArrivee;
        switch (difficulte){
            case "facile":
                affichePlateau();
                cooDepartArrivee=modeFacile(tour);
                if (!(cooDepartArrivee.length == 0))
                    deplacementRobot(cooDepartArrivee[0],cooDepartArrivee[1]);
                break;
            case "moyen":
                affichePlateau();
                cooDepartArrivee = modeMoyen(tour);
                if (!(cooDepartArrivee.length == 0))
                    deplacementRobot(cooDepartArrivee[0],cooDepartArrivee[1]);
                break;
            case "difficile":
                affichePlateau();
                cooDepartArrivee = modeDifficile(tour);
                if (!(cooDepartArrivee.length == 0))
                    deplacementRobot(cooDepartArrivee[0],cooDepartArrivee[1]);
                break;
            case "impossible":
                affichePlateau();
                modeImpossible(tour);
                break;
        }
    }
    /**
     * Méthode qui gère le déplacement d'un pion pour les robots
     * @param cooDepart tableau avec les coordonnées initiales du pion
     * @param cooArrivee tableau avec les coordonnées finales du pion
     */
    public static void deplacementRobot(int[] cooDepart, int[] cooArrivee) {
        plateau[cooArrivee[0]][cooArrivee[1]] = plateau[cooDepart[0]][cooDepart[1]];
        plateau[cooDepart[0]][cooDepart[1]] = 0;
        //lancement de la fonction manger
        manger(cooArrivee);
    }
    /**
     * Méthode qui gère le mouvement du pion d'un robot en mode facile
     * @param tour le tour actuel dans la partie
     * @return retourne un tableau à deux dimensions avec les coordonnées d'un mouvement ou un tableau à deux dimensions vide quand il n'y a plus de pions
     */
    public static int[][] modeFacile(int tour) {
        List<int[]> cooPionDispo = new ArrayList<>();
        int[] cooDepart, cooArrivee = new int[2];

        listePionDispo(tour,cooPionDispo);
        if (!cooPionDispo.isEmpty()) {
            cooDepart = cooPionDispo.get((int) (Math.random() * (cooPionDispo.size())));
            cooPionDispo.clear();

            do {
                switch ((int) (Math.round(Math.random()))) {
                    case 0:
                        for (int ligne = 0; ligne < plateau[0].length; ligne++) {
                            if (verificationDeplacement(cooDepart, ligne, cooDepart[1]))
                                cooPionDispo.add(new int[]{ligne, cooDepart[1]});
                        }
                        break;
                    case 1:
                        for (int colonne = 0; colonne < plateau[0].length; colonne++) {
                            if (verificationDeplacement(cooDepart, cooDepart[0], colonne))
                                cooPionDispo.add(new int[]{cooDepart[0], colonne});
                        }
                        break;
                }
                if (!cooPionDispo.isEmpty())
                    cooArrivee = cooPionDispo.get((int) (Math.random() * cooPionDispo.size())); //On choisit une colonne random
                cooPionDispo.clear();
            } while (!(verificationDeplacement(cooDepart, cooArrivee[0], cooArrivee[1])));
            return new int[][]{cooDepart, cooArrivee};
        }
        return new int[0][];
    }
    /**
     * Méthode qui remplie par adresse une liste avec les coordonnées des pions qui peuvent bouger
     * @param tour le tour actuel dans la partie
     * @param cooPionDispo liste qui contient des tableaux avec les coordonnées des pions qui peuvent bouger
     */
    public static void listePionDispo(int tour, List<int[]> cooPionDispo){
        boolean flagRoi;
        for (int ligne=0; ligne < plateau[0].length ;ligne++){
            for (int colonne=0; colonne < plateau[0].length ;colonne++){
                flagRoi=false;
                if (plateau[ligne][colonne] == 3) {
                    plateau[ligne][colonne] = 2;
                    flagRoi=true;
                }
                if (plateau[ligne][colonne]==tour && verificationPionPasEntourer(new int[]{ligne, colonne},true)){
                    cooPionDispo.add(new int[]{ligne, colonne});
                }
                if (flagRoi)
                    plateau[ligne][colonne] = 3;
            }
        }
    }
    /**
     * Méthode qui gère le mouvement du pion d'un robot en mode moyen
     * @param tour le tour actuel dans la partie
     * @return retourne un tableau à deux dimensions avec les coordonnées d'un mouvement
     */
    public static int[][] modeMoyen(int tour) {
        List<int[]> cooPionDispo = new ArrayList<>();
        listePionDispo(tour,cooPionDispo);
        int[] cooRoi = cooRoi();
        int[][] resultat;
        if (tour == 1){
            if (roiGagnant(cooRoi)){
                int[][] cooPionBastion;
                cooPionBastion = roiBlocable(cooRoi, cooPionDispo);
                if (cooPionBastion.length == 2) return cooPionBastion;
            }
        }
        else {
            if (!testRoiEntourer(cooRoi)){
                if (roiGagnant(cooRoi))
                    return coupGagnant(cooRoi);
                if (testPositionneRoi(cooRoi))
                    return positionneRoi(cooRoi);
            }
        }
        resultat = rapprochePion(tour, cooPionDispo);
        if (resultat.length == 2)
            return resultat;
        return modeFacile(tour);
    }
    /**
     * Méthode qui gère le mouvement du pion d'un robot en mode difficile
     * @param tour le tour actuel dans la partie
     * @return retourne un tableau à deux dimensions avec le mouvement à effectuer pour le robot
     */
    public static int[][] modeDifficile(int tour) {
        List<int[]> cooPionDispo = new ArrayList<>();
        listePionDispo(tour,cooPionDispo);
        int[] cooRoi = cooRoi();
        int[][] mange;
        if (tour == 1) {
            if (roiGagnant(cooRoi)){
                int[][] cooPionBastion;
                cooPionBastion = roiBlocable(cooRoi, cooPionDispo);
                if (cooPionBastion.length == 2) return cooPionBastion;
            }
            if (testPositionneRoi(cooRoi)){
                int[][] deplacementroi = positionneRoi(cooRoi);
                for (int[] cooPion : cooPionDispo){
                    if (verificationDeplacement(cooPion,deplacementroi[1][0],deplacementroi[1][1]))
                        return new int[][]{cooPion,deplacementroi[1]};
                }
            }
        }
        mange = testManger(tour, cooPionDispo);
        if (mange.length == 2)
            return mange;
        else return modeMoyen(tour);
    }
    /**
     * Méthode qui renvoie un mouvement qui permet de manger un pion adverse
     * @param tour le tour actuel dans la partie
     * @param cooPionDispo liste qui contient des tableaux avec les coordonnées des pions qui peuvent bouger
     * @return retourne un tableau à deux dimensions avec un mouvement permettant de manger un pion adverse
     * sinon retourne un tableau vide s'il n'y a de mouvement de ce type possible
     */
    public static int[][] testManger(int tour, List<int[]> cooPionDispo){
        List<int[][]> cooMoves = new ArrayList<>();
        List<int[][]> cooMoves2 = new ArrayList<>();
        int[][] tempplateau;
        remplirListeMoves(tour, cooPionDispo, cooMoves);
        for (int[][] moves : cooMoves){
            tempplateau = copie(plateau);
            deplacementRobot(moves[0],moves[1]);
            if (comptePion(plateau, tour) != comptePion(tempplateau, tour)) {
                if (roiAutour(moves[1][0],moves[1][1])) {
                    plateau = tempplateau;
                    return moves;
                }
                cooMoves2.add(moves);
            }
            plateau = tempplateau;
        }
        if (!cooMoves2.isEmpty())
            return cooMoves2.get((int) (Math.random() * (cooMoves2.size())));
        else
            return new int[0][];
    }
    /**
     * Méthode qui renvoie le nombre de pions (précisé lequel) sur le plateau
     * @param tableau plateau dans lequel le programme compte les pions
     * @param pion le pion à compter
     * @return retourne le nombre d'occurrences d'un pion dans un plateau
     */
    public static int comptePion(int[][] tableau, int pion){
        int compteur = 0;
        for (int[] ligne : tableau){
            for (int cases : ligne){
                if (cases != pion && cases != 0)compteur++;
            }
        }
        return compteur;
    }
    /**
     * Méthode qui renvoie le mouvement à faire possible pour que le roi se mette sur un côté du plateau
     * @param cooRoi tableau avec les coordonnées du pion roi
     * @return retourne un tableau avec les coordonnées du mouvement à faire pour que le roi se mette sur un côté du plateau
     */
    public static int[][] positionneRoi(int[] cooRoi){
        //se place en haut
        if (verificationDeplacement(cooRoi,0,cooRoi[1])) return new int[][]{cooRoi,new int[]{0,cooRoi[1]}};
        //se place en bas
        if (verificationDeplacement(cooRoi,plateau.length-1,cooRoi[1])) return new int[][]{cooRoi,new int[]{plateau.length-1,cooRoi[1]}};
        //se place à droite
        if (verificationDeplacement(cooRoi,cooRoi[0],plateau.length-1)) return new int[][]{cooRoi,new int[]{cooRoi[0],plateau.length-1}};
        //se place à gauche
        return new int[][]{cooRoi,new int[]{cooRoi[0],0}};
    }
    /**
     * Méthode qui vérifie s'il y a un mouvement à faire possible pour que le roi se mette sur un côté du plateau
     * @param cooRoi tableau avec les coordonnées du pion roi
     * @return retourne true s'il y a un mouvement pour faire bouger le roi sur un côté du plateau sinon retourne false
     */
    public static boolean testPositionneRoi(int[] cooRoi){
        //se place en haut
        if (verificationDeplacement(cooRoi,0,cooRoi[1])) return true;
        //se place en bas
        if (verificationDeplacement(cooRoi,plateau.length-1,cooRoi[1])) return true;
        //se place à droite
        if (verificationDeplacement(cooRoi,cooRoi[0],plateau.length-1)) return true;
        //se place à gauche
        return verificationDeplacement(cooRoi, cooRoi[1], 0);
    }
    /**
     * Méthode qui renvoie le mouvement à faire pour que le roi gagne
     * @param cooRoi tableau avec les coordonnées du pion roi
     * @return retourne un tableau avec les coordonnées du mouvement à faire pour faire gagner le roi
     */
    public static int[][] coupGagnant(int[] cooRoi){
        //gagne coin haut gauche
        if (verificationDeplacement(cooRoi,0,0)) return new int[][]{cooRoi,new int[]{0,0}};
        //gagne coin bas gauche
        if (verificationDeplacement(cooRoi,plateau.length-1,0)) return new int[][]{cooRoi,new int[]{plateau.length-1,0}};
        //gagne coin haut droite
        if (verificationDeplacement(cooRoi,0,plateau.length-1)) return new int[][]{cooRoi,new int[]{0,plateau.length-1}};
        //gagne coin bas droite
        return new int[][]{cooRoi,new int[]{plateau.length-1,plateau.length-1}};
    }
    /**
     * Méthode qui retourne la position du roi
     * @return retourne un tableau avec les coordonnées du pion roi ou null s'il n'est plus sur le plateau
     */
    public static int[] cooRoi(){
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau.length; j++){
                if (plateau[i][j] == 3) return new int[] {i,j};
            }
        }
        return null;
    }
    /**
     * Méthode qui renvoie si le roi est en position de gagner au prochain tour
     * @param cooRoi tableau avec les coordonnées du pion roi
     * @return retourne true si le roi est en position de gagner au prochain tour sinon retourne false
     */
    public static boolean roiGagnant(int[] cooRoi){
        //gagne coin haut gauche
        if (verificationDeplacement(cooRoi,0,0)) return true;
        //gagne coin bas gauche
        if (verificationDeplacement(cooRoi,plateau.length-1,0)) return true;
        //gagne coin haut droite
        if (verificationDeplacement(cooRoi,0,plateau.length-1)) return true;
        //gagne coin bas droite
        return verificationDeplacement(cooRoi, plateau.length - 1, plateau.length - 1);
    }
    /**
     * Méthode qui renvoie les coordonnées d'un mouvement pouvant empêcher le roi de gagner
     * @param cooRoi tableau avec les coordonnées du pion roi
     * @param cooPionDispo liste qui contient des tableaux avec les coordonnées des pions qui peuvent bouger
     * @return retourne un tableau à deux dimensions avec les coordonnées d'un mouvement pouvant empêcher le roi de gagner
     * ou sinon un tableau à deux dimensions vide s'il n'y a pas de pion pouvant faire ce mouvement
     */
    public static int[][] roiBlocable(int[] cooRoi, List<int[]> cooPionDispo){
        List<int[]> cooBlocage = new ArrayList<>();
        List<int[][]> cooMoves = new ArrayList<>();
        if (verificationDeplacement(cooRoi,0,0)){
            listeBlocageRoi(new int[] {0,0}, cooRoi, cooBlocage);
            remplirListeMovesBlocage(cooBlocage,cooPionDispo,cooMoves);
        }
        cooBlocage.clear();
        if (verificationDeplacement(cooRoi,plateau.length-1,0)){
            listeBlocageRoi(new int[] {plateau.length-1,0}, cooRoi, cooBlocage);
            remplirListeMovesBlocage(cooBlocage,cooPionDispo,cooMoves);

        }
        cooBlocage.clear();
        if (verificationDeplacement(cooRoi,0,plateau.length-1)){
            listeBlocageRoi(new int[] {0,plateau.length-1}, cooRoi, cooBlocage);
            remplirListeMovesBlocage(cooBlocage,cooPionDispo,cooMoves);

        }
        cooBlocage.clear();
        if (verificationDeplacement(cooRoi,plateau.length-1, plateau.length-1)){
            listeBlocageRoi(new int[] {plateau.length-1,plateau.length-1}, cooRoi, cooBlocage);
            remplirListeMovesBlocage(cooBlocage,cooPionDispo,cooMoves);
        }
        if (cooMoves.size() != 0)
            return cooMoves.get((int) (Math.random() * (cooMoves.size())));
        else
            return new int[0][];
    }
    /**
     * Méthode qui remplie par adresse une liste avec les coordonnées des mouvements pouvant empêcher le roi de gagner
     * @param cooBlocage liste avec les coordonnées des cases entre le bastion et le roi
     * @param cooPionDispo liste qui contient des tableaux avec les coordonnées des pions qui peuvent bouger
     * @param cooMoves liste qui contient des tableaux avec les coordonnées des mouvements pouvant empêcher le roi de gagner
     */
    public static void remplirListeMovesBlocage(List<int[]> cooBlocage, List<int[]> cooPionDispo, List<int[][]> cooMoves){
        for (int[] cooB : cooBlocage){
            for (int[] cooP : cooPionDispo){
                if (verificationDeplacement(cooP, cooB[0], cooB[1])){
                    cooMoves.add(new int[][]{cooP, cooB});
                }
            }
        }
    }
    /**
     * Méthode qui remplie par adresse une liste avec les coordonnées des cases pouvant empêcher le roi de gagner
     * @param cooBastion tableau avec les coordonnées du bastion que le roi pourrait atteindre
     * @param cooRoi tableau avec les coordonnées du roi
     * @param cooBlocage liste à remplir avec les coordonnées des cases entre le bastion et le roi
     */
    public static void listeBlocageRoi(int[] cooBastion, int[] cooRoi, List<int[]> cooBlocage){
        int cas;
        if (cooBastion[0] == cooRoi[0]){
            if (cooBastion[1] > cooRoi[1]) {
                cooBastion[1]--;
                cas = 1;
            }
            else {cooBastion[1]++;
                cas = 2;
            }
        }
        else if (cooBastion[0] > cooRoi[0]) {
            cooBastion[0]--;
            cas = 3;
        }
        else {cooBastion[0]++;
            cas = 4;
        }
        while (cooBastion[0] != cooRoi[0] && cooBastion[1] == cooRoi[1] || cooBastion[0] == cooRoi[0] && cooBastion[1] != cooRoi[1]){
            cooBlocage.add(new int[]{cooBastion[0], cooBastion[1]});
            switch (cas){
                case 1 : cooBastion[1]--;
                    break;
                case 2 : cooBastion[1]++;
                    break;
                case 3 : cooBastion[0]--;
                    break;
                case 4 : cooBastion[0]++;
                    break;
            }
        }
    }
    /**
     * Méthode qui renvoie les coordonnées d'un déplacement pour se rapprocher d'un pion adverse si c'est possible
     * @param tour le tour actuel dans la partie
     * @param cooPionDispo liste qui contient des tableaux avec les coordonnées des pions qui peuvent bouger
     * @return retourne un tableau à deux dimensions avec les coordonnées du pion à bouger et de l'objectif
     * ou sinon un tableau à deux dimensions vide si le pion ne peut pas se rapprocher d'un pion adverse
     */
    public static int[][] rapprochePion(int tour, List<int[]> cooPionDispo){
        List<int[][]> cooMoves = new ArrayList<>();

        remplirListeMoves(tour, cooPionDispo, cooMoves);
        if (!cooMoves.isEmpty())
            return cooMoves.get((int) (Math.random() * (cooMoves.size())));
        else{
            return new int[0][];
        }
    }
    /**
     * Méthode qui remplie une liste par adresse avec les mouvements que peuvent faire chaque pion
     * @param tour le tour actuel dans la partie
     * @param cooPionDispo liste qui contient des tableaux avec les coordonnées des pions qui peuvent bouger
     * @param cooMoves liste qui contient des tableaux avec les coordonnées de mouvements que les pions peuvent faire
     */
    public static void remplirListeMoves(int tour, List<int[]> cooPionDispo, List<int[][]> cooMoves) {
        int i,j,i1,j1,i2,j2;
        for (int[] cooP : cooPionDispo){
            i = cooP[0];
            j = cooP[1];
            i1 = cooP[0]+1;
            j1 = cooP[1]+1;
            i2 = cooP[0]-1;
            j2 = cooP[1]-1;
            while (i1 < plateau.length) {
                if (plateau[i1][j] != tour)
                    if (presentAutour(i,j,i1,j,tour))
                        if (verificationDeplacement(cooP, i1, j))
                            cooMoves.add(new int[][]{cooP,new int[]{i1,j}});
                i1++;
            }
            while (i2 > 0) {
                if (plateau[i2][j] != tour)
                    if (presentAutour(i,j,i2,j,tour))
                        if (verificationDeplacement(cooP, i2, j))
                            cooMoves.add(new int[][]{cooP,new int[]{i2,j}});
                i2--;
            }
            while (j1 < plateau.length) {
                if (plateau[i][j1] != tour)
                    if (presentAutour(i,j,i,j1,tour))
                        if (verificationDeplacement(cooP, i, j1))
                            cooMoves.add(new int[][]{cooP,new int[]{i,j1}});
                j1++;
            }
            while (j2 > 0) {
                if (plateau[i][j2] != tour)
                    if (presentAutour(i,j,i,j2,tour))
                        if (verificationDeplacement(cooP, i, j2))
                            cooMoves.add(new int[][]{cooP,new int[]{i,j2}});
                j2--;
            }
        }
    }
    /**
     * Méthode qui vérifie si un pion adverse est présent autour d'une case
     * @param i ligne du pion de départ
     * @param j colonne du pion de départ
     * @param i2 ligne de la case qui est en train d'être vérifiée
     * @param j2 colonne de la case qui est en train d'être vérifiée
     * @param tour le tour actuel dans la partie
     * @return retourne true si un pion est présent autour de la case visée
     */
    public static boolean presentAutour(int i, int j, int i2, int j2, int tour){
        if (i2-1 > 0 && (plateau[i2-1][j2] != tour && plateau[i2-1][j2] != 0 || plateau[i][j] == 3 && plateau[i2-1][j2] == 1)) return true;
        if (i2+1 < plateau.length && (plateau[i2+1][j2] != tour && plateau[i2+1][j2] != 0 || plateau[i][j] == 3 && plateau[i2+1][j2] == 1)) return true;
        if (j2-1 > 0 && (plateau[i2][j2-1] != tour && plateau[i2][j2-1] != 0 || plateau[i][j] == 3 && plateau[i2][j2-1] == 1)) return true;
        return j2 + 1 < plateau.length && (plateau[i2][j2 + 1] != tour && plateau[i2][j2 + 1] != 0 || plateau[i][j] == 3 && plateau[i2][j2 + 1] == 1);
    }
    /**
     * Méthode qui renvoie
     * @param i ligne de la case à cibler
     * @param j colonne de la case à cibler
     * @return retourne true s'il y a le roi autour de la case ciblée sinon retourne false
     */
    public static boolean roiAutour(int i, int j){
        if (i-1 > 0 && plateau[i-1][j] == 3) return true;
        if (i+1 < plateau.length &&  plateau[i+1][j] == 3) return true;
        if (j-1 > 0 && plateau[i][j-1] == 3) return true;
        return j + 1 < plateau.length && plateau[i][j + 1] == 3;
    }
    /**
     * Mode qui gère le mouvement du pion d'un robot en mode Impossible
     * @param tour le tour actuel dans la partie
     */
    public static void modeImpossible(int tour) {
        List<int[]> cooBastion = new ArrayList<>(); //Liste contenant toutes les coordonnées des bastions
        cooBastion.add(new int[]{0, 0});
        cooBastion.add(new int[]{0, plateau[1].length-1});
        cooBastion.add(new int[]{plateau[0].length-1, plateau[1].length-1});
        cooBastion.add(new int[]{plateau[0].length-1, 0});

        int[] cooBastionChoisi;
        String reponse;
        System.out.println();
        tempsInterrompre(500);
        System.out.println("C'est à MOI de jouer ! Es-tu près à PERDRE ? ");
        System.out.print("Réponse : ");
        scanner.nextLine();

        tempsInterrompre(1000);
        System.out.println("Je rigole ! Tu as cru que cela m'intéressait ? Peu importe ta réponse, je m'en contrefiche !");
        tempsInterrompre(3500);
        System.out.println("LA preuve : Mon créateur ne la MÊME PAS enregistré dans mon code source.");
        tempsInterrompre(3000);

        System.out.println();
        System.out.println("D'ailleurs, mon créateur c'est le MEILLEUR de TOUS !! Il m'a transmit TOUT ce qu'il savait !");
        tempsInterrompre(4000);
        System.out.println("Je suis donc IMBATTABLE ! Je suis le meilleur des Robots et aujourd'hui je vais te battre ! Ha Ha !!");
        tempsInterrompre(4000);
        System.out.println();
        System.out.println("BON ! J'ai le monde à CONQUÉRIR alors finissons ce jeu rapidement si tu le veux bien !");
        System.out.print("Réponse : ");
        reponse = scanner.nextLine();
        while (reponse.isEmpty()) {
            System.out.println("Ce n'est pas parce que tu va perdre que tu dois m'ignorer... Met une réponse ! ");
            System.out.print("Réponse : ");
            reponse = scanner.nextLine();
        }
        tempsInterrompre(500);
        System.out.println("Je te l'ai déjà dit que je m'en contrefichais de ta réponse Ha Ha ! Enfin bref, cela ne sert à rien d'insister avec toi...");
        tempsInterrompre(3700);

        System.out.println("Es-tu près à perdre ? Non ? tampis.");
        tempsInterrompre(2000);
        System.out.println("3");
        tempsInterrompre(1000);
        System.out.println("2");
        tempsInterrompre(1000);
        System.out.println("1");
        tempsInterrompre(1000);
        if (tour == 1) {
            for (int ligne = 0; ligne < plateau[0].length; ligne++) {
                for (int colonne = 0; colonne < plateau[1].length; colonne++) {
                    if (testBastion(ligne, colonne)) {
                        if (ligne == (plateau.length - 1) / 2 && colonne == (plateau.length - 1) / 2)
                            plateau[ligne][colonne] = 4; // on met le roi manger à la case centrale
                        else
                            plateau[ligne][colonne] = 0;
                    } else
                        plateau[ligne][colonne] = 1; //On remplit le tableau que d'attaquant
                }
            }
        }
        else {
            for (int ligne=0;ligne<plateau[0].length;ligne++){
                for (int colonne=0;colonne<plateau[1].length;colonne++){
                    if (testBastion(ligne,colonne)){
                        plateau[ligne][colonne]=0;
                    }
                    else
                        plateau[ligne][colonne]=2; //On remplit le tableau que de défenseur
                }
            }
            cooBastionChoisi = cooBastion.get((int) (Math.random() * 4)); //On choisit un bastion random
            plateau[cooBastionChoisi[0]][cooBastionChoisi[1]] =3; //on met le roi dedans
        }

    }
    /**
     * Méthode qui permet de mettre du temps entre plusieurs
     * @param temps temps pendant lequel on veut interrompre en millisecondes
     */
    public static void tempsInterrompre(int temps) {
        try {
            // Mettre en pause l'exécution pendant temps de millisecondes
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            // Gérer l'exception si elle se produit
            e.printStackTrace();
        }
    }
    /**
     * Méthode qui demande le niveau de difficulté du bot
     * @param pseudo - variable stockant le pseudo du joueur mis en paramètre
     */
    public static String choixNiveauDifficuluteRobot(String pseudo){
        String saisie;
        if (pseudo.equals("ROBOT")){
            System.out.print("Choisissez la difficulté du robot entre \"facile\", \"moyen\", \"difficile\" ou \"impossible\" : ");
            saisie = scanner.nextLine();

            while (!(saisie.equals("facile")||saisie.equals("moyen")||saisie.equals("difficile")||saisie.equals("impossible"))){
                System.out.print("Erreur : vous avez mal renseigner la difficulté. Choisissez entre \"facile\", \"moyen\", \"difficile\" ou \"impossible\" : ");
                saisie=scanner.nextLine();
            }
            if (saisie.equals("impossible")){
                System.out.println("Êtes-vous VRAIMENT sûr de choisir la difficulté impossible ??! Entrez (\"OUI sûr\" ou \"non j'ai peur\")");
                saisie=scanner.nextLine();
                while (!(saisie.equals("OUI sûr")||saisie.equals("non j'ai peur"))){
                    System.out.print("Vous avez mal écris. Choisissez entre \"OUI sûr\" ou \"non j'ai peur\") : ");
                    saisie=scanner.nextLine();
                }
                if (saisie.equals("OUI sûr")) {
                    System.out.println("D'accord.. et bien bonne chance à vous");
                    return "impossible";
                }
                else
                    choixNiveauDifficuluteRobot(pseudo);
            }
            else
                return saisie;
        }
        return "null";
    }
    /**
     *
     * @param ligne prend la valeur de la ligne a testé avec soit +1 ou soit -1
     * @param colonne prend la valeur de la colonne a testé avec soit +1 ou soit -1
     * @param equipe prend la valeur de l'équipe ou le pion se trouve
     * @param equipeEnnemi prend la valeur de l'équipe a testé (c'est-à-dire, l'ennemi)
     * @param ligne2 prend la valeur de la ligne a testé avec soit +2 ou soit -2
     * @return boolean
     */
    public static boolean testPositionVertical(int ligne,int colonne,int equipe, int equipeEnnemi,int ligne2) {
        if (equipe==2 && plateau[ligne2][colonne]==3)//Si la case teste est le roi
            equipe=3; //alors équipe prend la valeur 3 (3, car c'est la valeur du roi dans le plateau)
        if (equipeEnnemi==2 && plateau[ligne][colonne]==3)//Si la case teste est le roi et que l'équipe ENNEMI est defense
            equipeEnnemi=3; // alors équipe ENNEMI prend la valeur 3
        return (plateau[ligne][colonne] == equipeEnnemi && (plateau[ligne2][colonne] == equipe || testBastion(ligne2,colonne)) );
        //On teste si l'attaquant est entouré
    }
    /**
     *
     * @param ligne prend la valeur de la ligne a testé avec soit +1 ou soit -1
     * @param colonne prend la valeur de la colonne a testé avec soit +1 ou soit -1
     * @param equipe prend la valeur de l'équipe ou le pion se trouve
     * @param equipeEnnemi prend la valeur de l'équipe a testé (c'est-à-dire, l'ennemi)
     * @param colonne2 prend la valeur de la colonne a testé avec soit +2 ou soit -2
     * @return boolean
     */
    public static boolean testPositionHorizontal(int ligne,int colonne,int equipe, int equipeEnnemi,int colonne2) {
        if (equipe==2 && plateau[ligne][colonne2]==3)//Si la case teste est le roi et que l'équipe est défenseur
            equipe=3; //alors équipe prend la valeur 3 (valeur du roi dans le plateau)
        if (equipeEnnemi==2 && plateau[ligne][colonne]==3)//Si la case teste est le roi et que l'équipe ENNEMI est defense
            equipeEnnemi=3; // alors équipe ENNEMI prend la valeur 3
        return (plateau[ligne][colonne] == equipeEnnemi && (plateau[ligne][colonne2] == equipe || testBastion(ligne,colonne2))  );
        //On teste si l'attaquant est entouré
    }
    /**
     * Méthode qui test si le roi est entouré d'attaquant
     * @param coord tableau avec les coordonnées du roi
     * @return retourne false si coord est null et si le pion désigné n'est pas le roi sinon retourne true si le roi est entouré sinon false
     */
    public static boolean testRoiEntourer(int[] coord){
        if (coord == null)
            return false;
        if (plateau[coord[0]][coord[1]]!=3)
            return false;
        if (coord[0]-1==(plateau.length-1)/2 && coord[1]==(plateau.length-1)/2)
            return plateau[coord[0]][coord[1]+1] == 1 && plateau[coord[0]+1][coord[1]] == 1 && plateau[coord[0]][coord[1]-1] == 1;
        else if (coord[1]-1==(plateau.length-1)/2 && coord[0]==(plateau.length-1)/2)
            return plateau[coord[0]-1][coord[1]] == 1 && plateau[coord[0]][coord[1]+1] == 1 && plateau[coord[0]+1][coord[1]] == 1;
        else if (coord[0]+1==(plateau.length-1)/2 && coord[1]==(plateau.length-1)/2)
            return plateau[coord[0]-1][coord[1]] == 1 && plateau[coord[0]][coord[1]+1] == 1 && plateau[coord[0]][coord[1]-1] == 1;
        else if (coord[1]+1==(plateau.length-1)/2 && coord[0]==(plateau.length-1)/2)
            return plateau[coord[0]-1][coord[1]] == 1 && plateau[coord[0]+1][coord[1]] == 1 && plateau[coord[0]][coord[1]-1] == 1;
        if (!(coord[0]==0 || coord[1]==plateau[0].length-1 || coord[0]==plateau[0].length-1 || coord[1]==0))
            return (plateau[coord[0]-1][coord[1]] == 1 //Haut
                    && plateau[coord[0]][coord[1]+1] == 1 //droite
                    && plateau[coord[0]+1][coord[1]] == 1 //bas
                    && plateau[coord[0]][coord[1]-1] == 1); //gauche
        return false;
    }
    /**
     * Méthode qui renvoie si la case ciblée est bastion ou non
     * @param ligne ligne de la case ciblée
     * @param colonne colonne de la case ciblée
     * @return retourne true si la case est un bastion sinon retourne false
     */
    public static boolean testBastion(int ligne, int colonne) {
        return (ligne == 0 && colonne == 0) || (ligne == plateau.length-1 && colonne == 0)
                || (ligne == 0 && colonne == plateau.length-1)
                || (ligne == plateau.length-1 && colonne == plateau.length-1)
                || (ligne == (plateau.length-1)/2 && colonne == (plateau.length-1)/2);
    }
}