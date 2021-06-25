package it.unibs.fp.esame.mylib.menu_utils;

/**
 * {@code MenuItem} represent a required class for
 * {@code Handler} class.
 *
 * @author TheTrinity - Progetto Arnaldo 2021
 * @author Baresi Marco
 * @author Contestabile Martina
 * @author Iannella Simone
 * @see Menu
 */

public class MenuItem {
    private String text;
    private Runnable function;

    /**
     * {@code MenuItem} constructor.
     *
     * @param text of the option
     * @param function associated to the item
     */
    public MenuItem(String text, Runnable function) {
        this.text = text;
        this.function = function;
    }

    /**
     * @return {@link #text}
     */
    public String getText() {
        return text;
    }

    /**
     * @return {@link #function}
     */
    public Runnable getFunction() {
        return function;
    }
}
