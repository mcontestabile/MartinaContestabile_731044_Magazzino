package it.unibs.fp.esame.mylib.utilities;

import java.io.*;
import java.util.ArrayList;

public class FileInputAndOutput {
    public  void readingAFile() throws IOException {
        // Incapsulamento nel BufferedReader di un file aperto in lettura.
        BufferedReader bufferedReader = new BufferedReader(new FileReader(UsefulStrings.INPUT_FILE));

        String nextStr;

        /* La lettura del file avviene una riga per volta.
         * Il ciclo while aiuta a controllare che le righe
         * a disposizone siano terminate.
         */
        nextStr = bufferedReader.readLine();
        while (nextStr != null){
            // Visualizzazione della riga
            System.out.println(nextStr);
            // Lettura  della prossima riga
            nextStr = bufferedReader.readLine();
        }

        /* Chiusura del file, altrimenti si rischia di
         * perdere informazione e/o manomettere i dati letti.
         */
        bufferedReader.close();
    }

    private static void writeOutputFile(ArrayList<String> stats, String humanWins, String computerWins) throws IOException {
        String LINE_SEPARATOR = "line.separator";

        /* La classe File serve a rappresentare solo i nomi dei file. Fornisce un modo
         * di riferire un file per nome e di effettuare operazioni sull’intero file
         * (ad esempio, cancellarlo o rinominarlo), ma non sul suo contenuto (non offre
         * metodi di lettura e scrittura dei dati). Controllo che non esistanp file con
         * lo stesso nome. In caso esista già, ne creo uno nuovo.
         */
        File file = new File (UsefulStrings.OUTPUT_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }


        /* FileWriter permette la scrittura in formato testo di singoli caratteri.*/
        FileWriter fileWriter = new FileWriter(file);
        /* BufferedReader e BufferedWriter usano un buffer (memoria tampone) per
        memorizzare temporaneamente i caratteri da leggere/scrivere, in modo da
        ridurre il numero di accessi al file.
        Gli oggetti di questa classe sono dei wrappers: incapsulano gli oggetti
        della classe e FileWriter estendendone le funzionalità.*/
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        /*
        Per scrivere dati su di un file, occorre:
        1. creare un oggetto della classe FileWriter, passando il nome del file al costruttore;
        2. utilizzare il metodo public void write(int c) per scrivere i caratteri;
        3. chiudere il file con il metodo close().
         */
        for(String s : stats) {
            bufferedWriter.write(s + System.getProperty(LINE_SEPARATOR));
        }

        bufferedWriter.write(humanWins + System.getProperty(LINE_SEPARATOR));
        bufferedWriter.write(computerWins + System.getProperty(LINE_SEPARATOR));

        /* Chiusura del file, altrimenti si rischia di
         * perdere informazione e/o manomettere i dati scritti.
         */
        bufferedWriter.close();
    }
}
