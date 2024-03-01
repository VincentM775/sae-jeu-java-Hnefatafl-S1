import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMethodesHnefatafl {
    @Test
    void gagnant(){
        MethodesHnefatafl.plateau= new int[][]{
                {3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi sur le coin Haut-Gauche");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi sur le coin Haut-Droit");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi sur le coin Bas-Droite");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi sur le coin Bas-Gauche");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi non-vivant/manger");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 1, 3, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi entouré quand il est sur la première ligne");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 3},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi entouré quand il est sur la dernière colonne");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 3, 1, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi entouré quand il est sur la dernière ligne");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {3, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi entouré quand il est sur la première colonne");
        MethodesHnefatafl.plateau= new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 4, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.gagnant(),"Test du roi entouré quand il se fait entouré par 3 attaquant et son trône");
    }
    @Test
    void copie(){
        int[][] tabegal= new int[][]{
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 2, 0, 0, 1},
                {1, 0, 2, 3, 2, 0, 1},
                {1, 0, 0, 2, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0}};
        assertArrayEquals(tabegal,MethodesHnefatafl.copie(tabegal),"Test d'un tableau égal");
    }
    @Test
    void cooRoi() {
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 3, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertArrayEquals(new int[]{3, 3}, MethodesHnefatafl.cooRoi(), "Test du renvoie de la position du roi quand il est au milieu du plateau");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertArrayEquals(null, MethodesHnefatafl.cooRoi(), "test du renvoie null quand le roi n'ets plus sur le plateau");

    }
    @Test
    void testBastion(){
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 3, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.testBastion(0,0),"Les coordonnées testés correspond au bastion haut gauche");
        assertTrue(MethodesHnefatafl.testBastion(MethodesHnefatafl.plateau.length-1,0),"Les coordonnées testés correspond au bastion bas gauche");
        assertTrue(MethodesHnefatafl.testBastion(0,MethodesHnefatafl.plateau.length-1),"Les coordonnées testés correspond au bastion haut droit");
        assertTrue(MethodesHnefatafl.testBastion((MethodesHnefatafl.plateau.length-1)/2,(MethodesHnefatafl.plateau.length-1)/2),"Les coordonnées testés correspond au bastion centrale (au trône)");
        assertFalse(MethodesHnefatafl.testBastion(2,9),"Les coordonnées testés ne correspond à aucun bastion");
    }
    @Test
    void roiGagnant(){
        MethodesHnefatafl.plateau = new int[][]{
                {0, 3, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {0,1}),"test du roi en haut à côté du bastion haut gauche");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 3, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {0,5}),"test du roi en haut à côté du bastion haut droit");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {1,6}),"test du roi à droite à côté du bastion haut droit");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 3},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {4,6}),"test du roi à droite à côté du bastion bas droit");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 3, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {6,4}),"test du roi en bas à côté du bastion bas droit");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {6,2}),"test du roi en bas à côté du bastion bas gauche");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {5,0}),"test du roi à gauche à côté du bastion bas gauche");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertTrue(MethodesHnefatafl.roiGagnant(new int[] {1,0}),"test du roi à gauche à côté du bastion haut gauche");
        MethodesHnefatafl.plateau = new int[][]{
                {0, 0, 0, 1, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {1, 0, 2, 0, 2, 0, 1},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}};
        assertFalse(MethodesHnefatafl.roiGagnant(new int[] {1,1}),"test du roi quand il ne peux pas rejoindre un bastion directement");

    }

}
