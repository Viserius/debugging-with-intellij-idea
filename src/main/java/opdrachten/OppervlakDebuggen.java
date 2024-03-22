package opdrachten;

import opdrachten.oppervlak.Figuur;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * <b>Voor je begint: Klik links op het icoontje om de JavaDoc leesbaarder te maken!</b>
 * <h1>Instructies</h1> Deze class heeft als doel om een reeks figuren te instantiëren.
 * Deze figuren bestaan uit zowel cirkels als vierkanten.
 * Vervolgens wordt er op ieder figuur een willekeurig oppervlakte ingesteld.
 * De waarde van de oppervlakte wordt willekeurig klein ({@code 0 < oppervlakte < 42}) of groot ({@code >= 42})
 * <p>
 * Helaas zitten er wat foutjes in deze code, en is het aan jou om deze te debuggen.
 * Je hoeft de code zelf niet te wijzigen voor deze opdrachten.
 * <p>
 * <h2>Opdracht 1 (Non-Suspending Breakpoint Logging)</h2>
 * Voer de code eenmaal uit, en observeer de validatiefouten in de logging.
 * Je wil de waarden loggen waarvoor de validatiefouten optreden, zonder iedere keer de applicatie te pauzeren.
 * <ol><li>Plaats een non-suspending breakpoint binnen {@link #valideerOppervlak(Figuur)} die enkel het oppervlak logt
 *  als deze invalide is. </li></ol>
 * <p>
 * <h2>Opdracht 2 (Data Flow To Here)</h2>
 * Verwijder de vorige breakpoint.
 * Je bent benieuwd welke paden een {@code figuur} allemaal kan bewandelen voordat het bij {@link #valideerOppervlak(Figuur)} komt.
 * <ol><li>Gebruik <i>Data Flow To Here</i> om erachter te komen via welke paden een {@code figuur}
 * `Figuur` vanaf instantiatie tot het argument van {@link #valideerOppervlak(Figuur)} komt.</li></ol>
 * <dl>
 *     <dt>Hint 1</dt>
 *     <dd>Plaats de cursor op de variabele `figuur` binnen de {@link #valideerOppervlak(Figuur)} voordat je Data Flow To Here 
 *     aanroept.</dd>
 *     <dt>Hint 2</dt>
 *     <dd>De <i>Data Flow To Here</i>-functionaliteit kan je makkelijk aanroepen door 2x op shift te drukken</dd>
 * </dl>
 * <p>
 * <h2>Opdracht 3 (Breakpoint als trigger voor een ander breakpoint)</h2>
 * Je hebt in Opdracht 1 gezien dat er validatiefouten optreden door een negatief oppervlak.
 * In opdracht 2 heb je gezien dat {@link #valideerOppervlak(Figuur)} 2x aangeroepen wordt bij instantiatie,
 *  en 1x nadat de negatieve oppervlaktes eruit gefilterd zijn (vanuit de {@link #main(String[])}-methode.
 * Je ziet aan de logging niet of de validatiefouten enkel optreden bij instantiatie,
 *  of ook nog nadat de figuren gefilterd zijn door {@link #main(String[])}.
 *  <ol>
 *      <li>Maak van de non-suspending breakpoint op de validatiefout een (regulier) suspending breakpoint.</li>
 *      <li>Plaats een non-suspending breakpoint op de filter-statement in de {@link #main(String[])}-methode.</li>
 *      <li>Voeg een voorwaarde toe aan de suspending breakpoing op de validatiefout,
 *          zodat deze enkel geactiveerd wordt nadat de lijst met figuren gefilterd zijn binnen {@link #main(String[])}}</li>
 *      <li>Pauzeert de applicatie? Dan is de fout nog aanwezig na het filteren.
 *      Loopt de applicatie door? Dan zit de fout in {@link #zetGrootOppervlak(Figuur)} of {@link #zetKleinOppervlak(Figuur)}</li>
 *  </ol>
 * <p>
 * <h2>Opdracht 4 (Conditional Breakpoint)</h2>
 * Je hebt gezien dat de validatiefout optreedt vanuit {@link #zetGrootOppervlak(Figuur)} of {@link #zetKleinOppervlak(Figuur)}.
 * Ook heb je gezien dat de validatiefout veroorzaakt wordt door een negatief oppervlak.
 * <ol>
 *     <li>Verwijder de voorgaande breakpoints.</li>
 *     <li>Plaats binnen beide methodes op de {@code return}-statement een conditional breakpoint,
 *      die alleen geactiveerd wordt als er vanuit die methode een figuur met negatief oppervlakt gereturned wordt.
 * </li>
 * <li>Weet je nu welke methode een oppervlak instelt die negatief kan zijn?</li>
 * </ol>
 * <h2>Opdracht 5 (Inline Watch)</h2>
 * Je weet dat de fout zit in {@link #zetGrootOppervlak(Figuur)}. Iedere keer dat je breakpoint op de return binnen
 * {@link #zetGrootOppervlak(Figuur)} geactiveerd wordt, zie je een representatie op de argumenten,
 * bijvoorbeeld Vierkant@1241 of Cirkel@1534.
 * Aangezien we enkel in de area geinteresseerd zijn, is deze informatie niet erg handig.
 * <ol>
 *     <li>Verwijder de conditional breakpoint die (toch) nooit geactiveerd wordt op {@link #zetKleinOppervlak(Figuur)}.</li>
 *     <li>Configureer een inline watch-expressie (naast de methode), waarmee je het oppervlak ziet van de figuur,
 *          iedere keer dat je breakpoint geactiveerd wordt.</li>
 *     <li>Klik een paar keer door, zie je dat de waardes veranderen, iedere keer dat je breakpoint af gaat?</li>
 * </ol>
 * <h2>Opdracht 6 (Tijdreizen met Dataframe rollback)</h2>
 * Iedere keer dat je breakpoint geactiveerd wordt, ben je eigenlijk al 'te laat' met debuggen,
 *  omdat je oppervlak al negatief is.
 *  Je breakpoint aan het begin van de methode plaatsen is ook geen oplossing,
 *      omdat je dan allemaal scenarios door moet klikken die allemaal goed gaan,
 *      omdat de fout maar eens in de zoveel tijd optreedt.
 * We gaan dit probleem oplossen door dataframes terug te rollen.
 * <ol>
 *     <li>Debug je applicatie nogmaals,
 *     zodat je conditional breakpoint op {@link #zetGrootOppervlak(Figuur)} geactiveerd wordt</li>
 *     <li>Binnen je IDE zie je in het debug-venster onder 'Threads & Variables' een lijst van dataframes.</li>
 *     <li>Klik op de bovenste dataframe op het 'terug'-pijltje (of DELETE) om deze dataframe terug te rollen.</li>
 *     <li>Klik vervolgens op 'Step Into' (of F7) om weer in de functie te stappen.
 *     Zie je dat je nu in het begin van de functie aan het debuggen bent, VOOR je breakpoint?
 *     Je hebt als het ware de tijd teruggedraaid! Dit kan je zo vaak doen als je wil.</li>
 * </ol>
 * <h2>Opdracht 7: Object Renderer</h2>
 * Je hebt nu een situatie dat je de functie zo vaak door kan klikken om te zien hoe de area-waardes veranderen.
 * Als je door de functie heen klikt met Step Over (F8), zie je naast de statement een weergave van je figuur.
 * Ook hier zie je een referentie. Je kan een inline watch toevoegen zoals je dat in Opdracht 5 deed, maar dit is
 * onhandig. Je moet dit dan voor iedere statement instellen. Een Renderer is hier beter geschikt voor.
 * <ol>
 *     <li>Ga weer naar het begin van {@link #zetGrootOppervlak(Figuur)} voor een negatief oppervlak
 *     zoals je dat eerder gedaan hebt.</li>
 *     <li>Klik op figuur, create renderer, en selecteer <i>When rendering a node, use following expression</i></li>
 *     <li>Vul hier een expressie in zodat een {@code Figuur} gerendert wordt als een oppervlak</li>
 *     <li>Probeer nu nogmaals door de methode heen te stappen,
 *     en merk op bij welke statement het oppervlak negatief wordt.</li>
 *     <li>Als het goed is heb je nu, puur door te debuggen, exact gevonden welke statement het probleem is!</li>
 * </ol>
 * <h2>BONUS: Caller Filter</h2>
 * Een Caller-Filter is een filter die je op je breakpoint kan plaatsen,
 * zodat je breakpoint alleen geactiveerd wordt als deze aangeroepen wordt vanuit een specifieke methode.
 * <ol>
 *     <li>Verwijder alle oude breakpoints.</li>
 *     <li>Plaats een breakpoint op {@link #valideerOppervlak(Figuur)}</li>
 *     <li>Voeg een voorwaarde aan dit breakpoint toe onder <i>Caller Filters</i>,
 *     zodat dit breakpoint alleen geactiveerd wordt vanuit {@link #zetGrootOppervlak(Figuur)}.</li>
 *     <li>Let op, gebruik niet het trucje van een Non-Suspending breakpoint!
 *     Gebruik <i>alleen</i> het breakpoint met een Caller Filter.</li>
 * </ol>
 * <dl>
 *     <dt>Hint 1</dt>
 *     <dd>Bij Caller Filter moet je een <i>Method Descriptor</i> toevoegen, zoals gedefinieerd in de Oracle
 *     Documentatie: https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3</dd>
 *     <dt>Hint 2</dt>
 *     <dd>Ter voorbeeld, de Method Descriptor van {@code valideerOppervlak} is als volgt:
 *     {@code opdrachten.OppervlakDebuggen.valideerOppervlak(Lopdrachten/oppervlak/Figuur;)}Z</dd>
 *     <dt>Hint 3</dt>
 *     <dd>In het voorbeeld van Hint 2, is de Z op het eind het BaseType karakter van een boolean binnen de JVM.</dd>
 *     <dt>Hint 4</dt>
 *     <dd>{@code zetGrootOppervlak} returnt geen primitive type, maar een instantie van een class.
 *     Hoe zou er naar de Figuur-class gerefereerd worden binnen de JVM?</dd>
 *     <dt>Hint 5</dt>
 *     <dd>Het antwoord is hier te vinden: {@link opdrachten.oppervlak.Hint5}</dd>
 * </dl>
 */
public class OppervlakDebuggen {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        List<Figuur> figuren = IntStream.range(0, 200)
                .mapToObj(i -> Figuur.getRandomFiguur())
                .map(figuur -> zetRandomGrootOfKleinOppervlak(figuur))
                .toList();

        // Herstel figuren die voldoen aan validatie
        figuren = figuren.stream().filter(figuur -> figuur.getArea() > 0).toList();
        figuren.forEach(fig -> valideerOppervlak(fig));
    }

    private static Figuur zetRandomGrootOfKleinOppervlak(Figuur figuur) {
        return rand.nextBoolean()
                ? zetGrootOppervlak(figuur)
                : zetKleinOppervlak(figuur);
    }

    private static Figuur zetGrootOppervlak(Figuur figuur) {
        figuur.setArea(0);
        figuur.setArea(rand.nextInt());
        figuur.setArea(figuur.getArea() / 1000);
        figuur.setArea(figuur.getArea() - figuur.getArea() % 1000);
        if(figuur.getArea() < 42) {
            figuur.setArea(figuur.getArea() * 13);
        }
        valideerOppervlak(figuur);
       return figuur;
    }

    private static Figuur zetKleinOppervlak(Figuur figuur) {
        figuur.setArea(rand.nextInt(42));
        figuur.setArea(figuur.getArea() / 2 + 6);
        valideerOppervlak(figuur);
        return figuur;
    }

    private static boolean valideerOppervlak(Figuur figuur) {
        if(figuur.getArea() >= 0 && figuur.getArea() < 42) {
            // Figuur heeft een klein oppervlak.
            return true;
        } else if (figuur.getArea() >= 42) {
             // Figuur heeft een groot oppervlak.
            return true;
        } else {
            System.out.println("ERROR: Validatie mislukt. Figuur heeft geen klein en geen groot oppervlak!");
            return false;
        }
    }
}
