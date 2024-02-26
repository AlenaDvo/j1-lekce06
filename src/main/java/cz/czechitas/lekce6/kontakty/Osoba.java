package cz.czechitas.lekce6.kontakty;

import java.util.*;

/**
 * Informace o osobě.
 */
public class Osoba {
    private final Set<String> emaily = new HashSet<>();
    private final Set<String> telefony = new HashSet<>();
    private final List<String> deti = new ArrayList<>();
    private String jmeno;
    private String prijmeni;

    public Osoba(String jmeno, String prijmeni) {
        this.jmeno = Objects.requireNonNull(jmeno);
        this.prijmeni = Objects.requireNonNull(prijmeni);
    }

    public void run() {
        pridejEmail("honza@lhota.cz");
        pridejEmail("jan@kralovstvi.cz");
        pridejEmail("kral@kralovstvi.cz");
        System.out.printf("E-maily: %s", String.join(", ", emaily)).println();

        boolean obsahujeEmail = obsahujeEmail("jan@kralovstvi.cz");
        System.out.printf("Obsahuje e-mail 'jan@kralovstvi.cz'? %b", obsahujeEmail).println();

        pridejTelefon("739198456");
        pridejTelefon("800000000");
        pridejTelefon("212345678");
        pridejTelefon("739198456");
        System.out.printf("Telefony poprve: %s", String.join(", ", telefony)).println();

        odstranTelefon("739198456");
        System.out.printf("Telefony podruhe: %s", String.join(", ", telefony)).println();

        smazVsechnyTelefony();
        System.out.printf("Pocet telefonu v seznamu: %d", telefony.size()).println();

        boolean jeSeznamTelefonuPrazdny = jeSeznamTelefonuPrazdny();
        System.out.printf("Je seznam telefonu prazdny? %b", jeSeznamTelefonuPrazdny).println();

        pridejDite("Eliska");
        pridejDite("Eva");
        pridejDite("Edita");
        pridejDite("Ester");
        System.out.printf("Deti: %s", String.join(", ", deti)).println();

        boolean eliskaJe = jeNaSeznamuDite("Eliska");
        System.out.printf("Eliska je na seznamu deti? %b", eliskaJe).println();

        Integer editaPoradi = kolikateJe("Edita");
        if (editaPoradi == null) {
            System.out.println("Edita neni na seznamu dětí.");
        } else {
            System.out.printf("Edita je na seznamu deti %d. v poradi.", editaPoradi).println();
        }

        Integer emaPoradi = kolikateJe("Ema");
        if (emaPoradi == null || emaPoradi == 0) {
            System.out.println("Ema neni na seznamu deti.");
        } else {
            System.out.printf("Ema je na seznamu deti %d. v poradi.", emaPoradi).println();
        }

        vypisDeti();
    }

    //region Metody k implementaci.

    /**
     * Přidá uvedený e-mail do seznamu e-mailů.
     *
     * @param email
     */
    private void pridejEmail(String email) {
        emaily.add(email);
    }

    /**
     * Zjistí, zda seznam e-mailů obsahuje uvedenou hodnotu.
     *
     * @param email
     * @return
     */
    private boolean obsahujeEmail(String email) {
        return emaily.contains(email);
    }

    /**
     * Přidá telefon na seznam telefonů.
     *
     * @param telefon
     */
    private void pridejTelefon(String telefon) {
        telefony.add(telefon);
    }

    /**
     * Odstraní telefon ze seznamu telefonů.
     *
     * @param telefon
     */
    private void odstranTelefon(String telefon) {
        telefony.remove(telefon);
    }

    /**
     * Zjistí, zda je seznam telefonů prázdný.
     */
    private boolean jeSeznamTelefonuPrazdny() {
        return telefony.isEmpty();
    }

    /**
     * Smaže všechny telefony ze seznamu.
     */
    private void smazVsechnyTelefony() {
        telefony.clear();
    }

    /**
     * Přidá do seznamu další dítě.
     *
     * @param jmeno Křestní jméno dítěte.
     */
    private void pridejDite(String jmeno) {
        deti.add(jmeno);
    }

    /**
     * Vrací údaj, zda dané dítě je uvedené v seznamu dětí.
     *
     * @param jmeno Křestní jméno hledaného dítěte.
     * @return
     */
    private boolean jeNaSeznamuDite(String jmeno) {
        return deti.contains(jmeno);
    }

    /**
     * Vrací pořadové číslo, kolikáté je uvedené dítě na seznamu.
     * <p>
     * Vrací pořadí v běžném číslování, tj. první dítě je 1. Pokud dítě na seznamu není, vrací {@code null}.
     *
     * @param jmeno Křestní jméno hledaného dítěte.
     * @return Pořadí dítěte.
     */
    private Integer kolikateJe(String jmeno) {
        return deti.indexOf(jmeno) + 1;
    }

    /**
     * Vypíše jména všech dětí na standardní výstup.
     * <p>
     * Jména dětí jsou vypsána v pořadí, v jakém jsou uvedena v seznamu.
     */
    private void vypisDeti() {

        for (String dite: deti) {
            System.out.println(dite);
        }
        //Pro výpis jednoho dítěte se použije System.out.println()
        //Pro průchod celým seznamem se použije for each cyklus (s dvojtečkou) – po zadání "deti.for" IntelliJ Ieda napoví.
        //Pozor, nejde o metodu forEach()!
    }
    //endregion


    //region Implementace osoby.
    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = Objects.requireNonNull(jmeno);
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = Objects.requireNonNull(prijmeni);
    }

    public Set<String> getEmaily() {
        return emaily;
    }

    public Set<String> getTelefony() {
        return telefony;
    }

    public List<String> getDeti() {
        return deti;
    }

    @Override
    public String toString() {
        return jmeno + ' ' + prijmeni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Osoba)) return false;
        Osoba osoba = (Osoba) o;
        return jmeno.equals(osoba.jmeno) && prijmeni.equals(osoba.prijmeni) && emaily.equals(osoba.emaily) && telefony.equals(osoba.telefony) && deti.equals(osoba.deti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmeno, prijmeni, emaily, telefony, deti);
    }

    //endregion
}
