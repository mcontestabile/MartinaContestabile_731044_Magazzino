package it.unibs.fp.esame.magazzino;

import it.unibs.fp.esame.mylib.menu_utils.*;
import it.unibs.fp.esame.mylib.utilities.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Questa classe è quella che permette un dialogo con l'utente,
 * il quale vorrà ordinare merce.
 *
 *  La scelta di implementare una classe interna
 *  ({@code Reminder}) è dovuta al fatto che consiste in talmente
 *  poche righe di codice che non è strettamente necessario crearne
 *  una a sé stante, soprattutto in base al fatto che viene utilizzata
 *  esclusivamente in {@code Article}.
 *
 *  Fonti utilizzate per implementare la funzionalità di attesa:
 *  » https://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/timer.html
 *  » https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html
 *  » https://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html
 *  » https://www.javatpoint.com/post/java-timer-schedule-method
 *  » http://www.di.uniba.it/~ig/animazione/thread.htm
 *
 *  * Il primo website rimandava al link https://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/ex5/Reminder.java,
 *  * il quale conteneva la richiesta esplicitata dalla consegna del programma dell'esame, con delle modifiche
 *  * da effettuare per integrarsi in modo omogeneo al codice scritto per {@code Magazzino}.
 */
public class Handler {
    private WareHouse wareHouse;

    /**
     * Questo è il main menu, che permette di avviare
     * l'invio di ordini al magazzino o di terminare il
     * programma {@code Magazzino}.
     */
    public void startMenu() {
        welcomeMessage();
        /*
         * "Addormento" il thread per permettere all'utente di
         * visualizzare il messaggio di benvenuto e avere uno stacco,
         * visivamente più elegante, fra la selezione delle opzioni
         * e il benvenuto.
         */
        Time.pause(Time.MEDIUM_MILLIS_PAUSE);

        wareHouse = createWareHouse();
        Time.pause(Time.MEDIUM_MILLIS_PAUSE);
        /*
         * Menu principale, permette l'accesso ai sotto-menu.
         */
        MenuItem[] items  = {
                new MenuItem(UsefulStrings.FIRST_MENU_OPTION, this::mainTask),
                new MenuItem(UsefulStrings.SECOND_MENU_OPTION, this::authorTask)
        };


        Menu menu = new Menu(UsefulStrings.MAIN_TASK_REQUEST, items);
        menu.changeExitText(UsefulStrings.END_MENU_OPTION);
        menu.run();

        System.out.println("\n" + UsefulStrings.GOODBYE);
    }

    /**
     * Questo metodo lancia il messaggio di benvenuto una volta
     * avviato il programma {@code Magazzino}.
     */
    public void welcomeMessage() {
        System.out.println(UsefulStrings.WELCOME);
    }

    /**
     * Nel caso l'utente abbia selezionato l'opzione
     * «Entrare nei sistemi del Magazzino.», il programma
     * avvia questo metodo. Questo è un sotto-menù, qui
     * inizia la gestione vera e propria del magazzino.
     * L'utente può scegliere se inseriere un ordine oppure
     * tornare al menù principale.
     */
    public void mainTask() {
        MenuItem[] items = new MenuItem[]{
                new MenuItem(UsefulStrings.INSERT_AN_ORDER, this::insertAnOrder)
        };

        Menu menu = new Menu(UsefulStrings.MAIN_TASK_REQUEST, items);
        menu.changeExitText(UsefulStrings.BACK_MENU_OPTION);
        menu.run();
    }

    /**
     * Questo metodo mostra chi ha scritto il codice di {@code Azienda Sanitaria}.
     */
    public final void authorTask() {
        System.out.println(UsefulStrings.AUTHOR);
        Time.pause(Time.LOW_MILLIS_PAUSE);
        DataInput.readString(UsefulStrings.ENTER_TO_CONTINUE);
    }

    public void insertAnOrder() {
        /*
         * L'utente-acquirente visualizza i prodotti all'interno
         * del magazzino, in modo da poter decidere quanti prenderne.
         */
        System.out.println(UsefulStrings.AVAILABLE_ARTICLES);
        Time.pause(Time.MEDIUM_MILLIS_PAUSE);
        System.out.println(wareHouse.getArticles().toString());

        String articleName;
        do {
            articleName = DataInput.readNotEmptyString(UsefulStrings.INSERT_PRODUCT_NAME);
            Time.pause(Time.LOW_MILLIS_PAUSE);
        } while (!wareHouse.checkNameAvailability(articleName, wareHouse.getArticles()));

        Article article = wareHouse.getArticle(articleName);

        System.out.println(UsefulStrings.CHOSEN_ARTICLE);
        System.out.println(article);

        boolean isNeededANewOrder = article.checkIfIsNeededANewProduction(article.getQuantity());


        /**
         * La classe timer possiede il metodo {@code public void schedule(TimerTask task, Date time)}
         * {@code Task} è l'operazione da calendarizzare nel programma (in base al tempo di produzione fornito al singolo
         * oggetto di tipo {@code Articolo}.
         * {@code Time}, invece, è il tempo che passerò prima che l'operazione sia possibile (in questo caso,
         * ordinare al magazzino il prodotto X).
         */
        class Reminder {
            private Timer timer;

            public Reminder(int seconds) {
                timer = new Timer();
                timer.schedule(new Reminder.RemindTask(), seconds*1000);
            }

            class RemindTask extends TimerTask {

                @Override
                public void run() {
                    article.generateNewUnitiesForWarehouse();
                    System.out.println(UsefulStrings.NOW_THE_ARTICLE_IS_AVAILABLE);
                    timer.cancel(); //Terminate the timer thread
                }
            }
        }

        /*
         * In questo programma, per testare si è deciso di implementare un tempo
         * di produzione molto breve (massimo 2 minuti), in modo tale da visualizzare
         * effettivamente l'efficacia del programma. In ogni caso, basterà editare
         * in mylib » utilities » UsefulStrings.java il tempo di produzione del
         * singolo prodotto.
         */
        if (isNeededANewOrder) {
            System.out.println(UsefulStrings.NEGATIVE_MESSAGE);
            new Reminder(article.getRequestedTime());
        } else {
            System.out.println(UsefulStrings.POSITIVE_MESSAGE);
            int order = DataInput.readIntWithMinimum(UsefulStrings.INSERT_HOW_MANY_PRODUCTS, UsefulStrings.MIN_PRODUCT);

            article.setQuantity(article.getQuantity() - order);
            int newQuantity = article.getQuantity();

            if (article.checkIfIsNeededANewProduction(newQuantity)) {
                System.out.println(UsefulStrings.PLACING_AN_ORDER);
                new Reminder(article.getRequestedTime());
            }
        }
    }

    /**
     * L'utente, in base al teso dell'esercizio fornito, non inserisce i prodotti nel
     * magazzino, ma semplicemente seleziona quali e quanti acquistarne fra quelli
     * messi a disposizione nel programma.
     * Di conseguenza, tocca al programmatore creare l'oggetto {@code Magazzino} e, quindi,
     * gli oggetti {@code Articolo} ad esso collegato.
     */
    public WareHouse createWareHouse() {
        ArrayList<Article> articlesInTheWarehouse = new ArrayList<>();

        int iqAF = settingInitialQuantity(UsefulStrings.MIN_QUANTITY, UsefulStrings.MAX_QUANTITY);
        Article animalsFood = new Article(UsefulStrings.ANIMALS_FOOD, UsefulStrings.MIN_QUANTITY, UsefulStrings.MAX_QUANTITY, iqAF, UsefulStrings.ANIMALS_FOOD_PRODUCTION);

        int iqPH = settingInitialQuantity(UsefulStrings.MIN_QUANTITY, UsefulStrings.MAX_QUANTITY);
        Article petHealthcare = new Article(UsefulStrings.PETS_HEALTHCARE, UsefulStrings.MIN_QUANTITY, UsefulStrings.MAX_QUANTITY, iqPH, UsefulStrings.PETS_HEALTHCARE_PRODUCTION);

        int iqBF = settingInitialQuantity(UsefulStrings.MIN_QUANTITY, UsefulStrings.MAX_QUANTITY);
        Article birdsFood = new Article(UsefulStrings.BIRDS_FOOD, UsefulStrings.MIN_QUANTITY, UsefulStrings.MAX_QUANTITY, iqBF, UsefulStrings.BIRDS_FOOD_PRODUCTION);

        articlesInTheWarehouse.add(animalsFood);
        articlesInTheWarehouse.add(petHealthcare);
        articlesInTheWarehouse.add(birdsFood);

        return new WareHouse(articlesInTheWarehouse);
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
}
