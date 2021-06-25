package it.unibs.fp.esame.magazzino;

import it.unibs.fp.esame.mylib.utilities.RandomNumbers;
import it.unibs.fp.esame.mylib.utilities.UsefulStrings;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe istanziabile. Permette di creare oggetti di
 * tipo articolo.
 */
public class Article {
    /*
     * Nome dell'articolo, uantità minime e
     * massime per ciascun articolo, quantità
     * attuale del prodotto e tempo di produzione.
     * Non si può sforare da maximum e minimum.
     * Sono private perché l'utente non può
     * assolutamente modificare questi valori a suo
     * piacimento, la modifica è autorizzata solo su
     * volontà del programmatore.
     */
    private String name;
    private int minimum;
    private int maximum;
    private int quantity;
    private int requestedTime;

    // Questo oggetto consente il calcolo del tempo trascorso.
    Timer timer;

    /**
     * Costruttore dell'oggetto articolo
     * @param name nome dell'articolo.
     * @param minimum quantità minima che si può avere dell'articolo {@code X}.
     * @param maximum quantità massima che si può avere dell'articolo {@code X}.
     * @param requestedTime tempo richiesto per produrre l'articolo {@code X}.
     */
    public Article(String name, int minimum, int maximum, int quantity, int requestedTime) {
        this.name = name;
        this.minimum = minimum;
        this.maximum = maximum;
        this.quantity = quantity;
        this.requestedTime = requestedTime;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean checkIfIsNeededANewProduction(int quantity) {
        if (quantity < minimum) return true;
        return false;
    }


    public void generateNewUnitiesForWarehouse() {
        quantity += settingInitialQuantity(minimum, maximum);
    }

    /**
     * Questo metodo permette di settare la quantità
     * iniziale di ciascun oggetto di tipo {@code Articolo}.
     * @param maximum massima quantità possibile.
     * @param minimum minima quantità possibile.
     * @return quantità iniziale.
     */
    public int settingInitialQuantity(int maximum, int minimum) {
        return RandomNumbers.obtainInt(maximum, minimum);
    }

    /**
     * All'utente non interessa il funzionamento del thread
     * e, quindi, come funziona il ritardo dell'esecuzione della
     * possibilità di ordinare. Di conseguenza, il metodo ha
     * come modificatore di accesso « private ».
     */
    private void productionTime() {
        try {
            Thread.sleep(requestedTime);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    /**
     * Controllo se il nome dell'oggetto {@code Articolo} ricercato
     * esiste davvero o meno. Il metodo è pensato per un riuso, nel
     * caso venga implementata la possibilità all'utente di inserire
     * nuovi articoli nel magazzino (nel caso il programma venga
     * usato da un'azienda di logistica).
     * @param n nome inserito dall'utente nella ricerca.
     * @return se esiste true, se non esiste false.
     */
    public boolean checkNameAvailability(String n) {
        if (name.equalsIgnoreCase(n)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Articolo\n" +
                "nome » " + name +
                "\nquantità disponibile » " + quantity +
                "\ntempo richiesto per produrlo » " + requestedTime +"\n";
    }
}
