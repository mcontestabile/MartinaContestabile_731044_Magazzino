package it.unibs.fp.esame.mylib.utilities;

/**
 * Classe di utilità, permette di salvare tutte le stringhe utilizzate nel
 * codice (maggiormente in {@code Handler}, poco, o mai, in altre classi),
 * così da avere classi più pulite e ordinate.
 */
public class UsefulStrings {
    public static final String WELCOME = "Benvenuto in\n\n" +
            "███╗   ███╗ █████╗  ██████╗  █████╗ ███████╗███████╗██╗███╗   ██╗ ██████╗ \n" +
            "████╗ ████║██╔══██╗██╔════╝ ██╔══██╗╚══███╔╝╚══███╔╝██║████╗  ██║██╔═══██╗\n" +
            "██╔████╔██║███████║██║  ███╗███████║  ███╔╝   ███╔╝ ██║██╔██╗ ██║██║   ██║\n" +
            "██║╚██╔╝██║██╔══██║██║   ██║██╔══██║ ███╔╝   ███╔╝  ██║██║╚██╗██║██║   ██║\n" +
            "██║ ╚═╝ ██║██║  ██║╚██████╔╝██║  ██║███████╗███████╗██║██║ ╚████║╚██████╔╝\n" +
            "╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \n" +
            "                                                                          ";
    public static final String GOODBYE = "Arrivederci. Grazie per aver utilizzato Magazzino.\nSperiamo che la sua esperienza con noi sia stata piacevole e di rivederLa presto.";

    public static final String NEGATIVE_MESSAGE = "\n\nCi dispiace informarLa che il prodotto da lei richiesto è al momento esaurito presso i nostri magazzini.\nLe chiediamo di pazientare, appena tornera' disponibile la avviseremo.\n";

    public static final String INSERT_PRODUCT_NAME = "\n\nInserire il nome del prodotto da ordinare.\n» ";
    public static final String POSITIVE_MESSAGE = "In base ai dati in nostro possesso, abbiamo a dispisizione quantità\nsufficienti di prodotto per permetterLe di effettuare un ordine.\n» ";
    public static final String CHOSEN_ARTICLE = "\n\nHa scelto questo articolo.\nLe ricordiamo le specifiche:\n";
    public static final String AVAILABLE_ARTICLES = "Articoli disponibili:\n";
    public static final String INSERT_HOW_MANY_PRODUCTS = "\n\nInserire la quantità desiderata dell'articolo.\nSi ricorda che non si può sforare la quantità disponibile presso il nostro magazzino.\n» ";
    public static final int MIN_PRODUCT = 1;

    public static final String PLACING_AN_ORDER = "Gentile cliente, siamo lieti di comunicarLe che l'acquisto è avvenuto con successo.\nQuesta era l'ultima quantità acquistabile.\nNon si preoccupi, abbiamo già provveduto ad ordinarne nuove unita'.\n";
    public static final String NOW_THE_ARTICLE_IS_AVAILABLE = "Precedentemente ci ha chiesto un articolo non disponibile.\nSiamo lieti di comunicarLe che risulta ordinabile.\nSi affretti, prima che si esaurisca nuovamente!\n";

    public static final int MIN_QUANTITY = 5;
    public static final int MAX_QUANTITY = 20;

    // 2 minuti di tempo = 2*60s = 120s * 1000 = 120000ms
    public static final int ANIMALS_FOOD_PRODUCTION = 120000;
    public static final String ANIMALS_FOOD = "Cibo per animali";

    // 50 secondi di tempo
    public static final int PETS_HEALTHCARE_PRODUCTION = 50000;
    public static final String PETS_HEALTHCARE = "Prodotti per la cura degli animali";

    public static final int BIRDS_FOOD_PRODUCTION = 70000;
    public static final String BIRDS_FOOD = "Cibo per uccellini";

    public static final String FIRST_MENU_OPTION = "Entrare nei sistemi del Magazzino.";
    public static final String SECOND_MENU_OPTION = "Scoprire chi ha scritto il programma.";
    public static final String END_MENU_OPTION = "Uscire dal programma.";
    public static final String MAIN_TASK_REQUEST = "Si scelga cosa fare";
    public static final String INSERT_AN_ORDER = "Inserire un ordine.";
    public static final String BACK_MENU_OPTION = "Handler principale.";

    public static final String AUTHOR = """        
             Author: Martina Contestabile.
             """;

    public static final String ENTER_TO_CONTINUE = "\nPremi invio per continuare...";

    public static final String OUTPUT_FILE = "titoli_azionari.txt";
    public static final String INPUT_FILE = "titoli_azionari.txt";

    public static final String YES = "Si";
    public static final String NO = "No";
}
