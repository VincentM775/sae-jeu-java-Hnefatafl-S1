import java.util.Scanner;

public class GestionnaireMethodesHnefatafl {
    public static void menu(){
        String souligne = "\u001B[4m"; // Code ANSI pour le texte souligné
        String enGras = "\u001B[1m"; // Code ANSI pour mettre le texte en gras
        String resetStyle = "\u001B[0m"; // Code ANSI pour réinitialiser le style du texte
        int saisie;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        MethodesHnefatafl.affichageTitre();
        System.out.println();
        do {
            System.out.print("-------------------------------------------------\n" +
                    souligne+enGras +"Choisissez un numéro pour accéder à l'option :\n\n" +resetStyle+
                    "1 - Lire les règles du jeu\n" +
                    "2 - Démarrer une partie\n" +
                    "3 - Quitter\n\n" +
                    enGras+ "Réponse : "+resetStyle);
            saisie = scanner.nextInt();

            while (saisie < 1 || saisie > 3) {
                System.out.println();
                System.out.print("Erreur dans la saisie, veuillez saisir une valeur comprise entre 1 et 3 inclus.\n" +
                        "Réponse : ");
                saisie = scanner.nextInt();
            }
            System.out.println("-------------------------------------------------");
            System.out.println();
            switch (saisie) {
                case 1:
                    MethodesHnefatafl.regles();
                    break;
                case 2:
                    MethodesHnefatafl.partie();
                    break;
            }
        }while (saisie!=3);
    }
}
