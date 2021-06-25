package it.unibs.fp.esame.magazzino;

/**
 * {@code Main class} del programma {@code Magazzino}.
 * Per lasciare il main « pulito », si è scelto di lasciare
 * tutti i metodi di interazione nella classe {@code Handler}.
 */
public class MagazzinoMain {
    public static void main(String[] args) {
        Handler handler = new Handler();
        handler.startMenu();
    }
}
