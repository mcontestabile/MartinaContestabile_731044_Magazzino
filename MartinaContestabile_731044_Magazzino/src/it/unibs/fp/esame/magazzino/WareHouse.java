package it.unibs.fp.esame.magazzino;

import java.util.ArrayList;

/**
 * Questo metodo inizializza un oggetto di tipo {@code Magazzino}.
 * In questo programma, ne abbiamo solo uno. In futuro, nel caso si
 * voglia gestire un numero superiore a uno di mazìgazzini, basterà
 * creare un ArrayList (o un qualsiasi tipo di collezione di oggetti,
 * in base alle esigenze).
 */
public class WareHouse {
    private ArrayList<Article> articles;

    /**
     * Costruttore dell'oggetto {@code Magazzino},
     * è una collezione di oggetti {@code Articolo},
     * questi ultimi dotati di determinate proprietà,
     * definite a priori nella loro classe.
     * @param articles
     */
    public WareHouse(ArrayList<Article> articles) {
        this.articles = articles;
    }

    /**
     * Getter
     * @return articoli nel magazzino
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }

    /**
     * Setter, permette, in un futuro in cui all'utente
     * è permesso di aggiungere nuovi oggetti {@code Articolo},
     * di aggiungerli al {@code Magazzino}.
     * Inoltre, permette l'inserimento del rifornimento
     * di articoli, nel caso si scenda rispetto alla soglia
     * minima e sia necessario averne una nuova scorta.
     * @param articles articoloi da inserire nel magazzino.
     */
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public Article getArticle(String n) {
        for (Article a : articles) {
            if (a.checkNameAvailability(n)) return a;
        }
        return null;
    }

    /**
     * Controllo se il nome dell'oggetto {@code Articolo} ricercato
     * esiste davvero o meno. Il metodo è pensato per un riuso, nel
     * caso venga implementata la possibilità all'utente di inserire
     * nuovi articoli nel magazzino (nel caso il programma venga
     * usato da un'azienda di logistica).
     * @param n nome inserito dall'utente nella ricerca.
     * @param articles articoli all'interno del magazzino.
     * @return se esiste true, se non esiste false.
     */
    public boolean checkNameAvailability(String n, ArrayList<Article> articles) {
        for (Article a : articles) {
            if(a.checkNameAvailability(n)) return true;
        }
        return false;
    }
}
