package opdrachten;

import opdrachten.stroom.HogerGelegenConstructies;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

import static opdrachten.stroom.Zijstroom.weerspiegeling;

/**
 * <b>Voor je begint: Klik links op het icoontje om de JavaDoc leesbaarder te maken!</b>
 * <h1>Instructies</h1> Deze class heeft als doel om een tekst te verwerken tot losse karakters/chars, deze te filteren te loggen en
 * uiteindelijk op te tellen. De code is alleen slecht leesbaar, doet dit omslachtig en de bijbehorende test
 * {@link MeanderendeStroomTest#stroomTest()} faalt.
 * <p>
 * We lopen in 3 opdrachten door deze code heen om kennis te maken met een aantal debugging tools die we zojuist in
 * de 'Devoxx' talk van Anton Arhipov hebben gezien.
 * <p>
 * De code hoeft niet aangepast te worden.
 * <p>
 * <h2>Opdracht 0 (Breakpoints)</h2>
 * Het doel van deze opdracht is om bekend te worden met breakpoints, debugging en een aantal bijbehorende shortcuts.
 * De daadwerkelijke code is niet relevant!
 * <ol>
 *     <li>Plaats een breakpoint op de regel waar de variabele {@code oorsprong} wordt geïnitialiseerd.<br>
 *     <i>breakpoints kunnen gezet worden door naast de regelnummers te klikken</i></li>
 *     <li>Plaats een breakpoint op de regel waar de variabele {@code resultaat} wordt geïnitialiseerd.
 *     <i>Voer de code in debugging uit met behulp van de unittest ({@link MeanderendeStroomTest#stroomTest()} of de
 *     {@link #main(String[])}<br>
 *     <i>er is in de linkerbalk een 'play button' te zien, als je hier op klikt krijg je oopk de 'debug' optie</i>
 *     <li>Stap met F9 (next breakpoint, F8 (next line) en F7 (step into) door de code heen</li>
 *     <li>Op de regel waar resultaat wordt geïnitialiseerd heb je met 'step into' de optie om te kiezen waar je in
 *     wilt stappen, bijvoorbeeld de {@link MeanderendeStroom#stroomversnelling()}</li>
 * </ol>
 * <p>
 * <h2>Opdracht 1 (Lambda Stream chain)</h2>
 * Voer de code uit met behulp van de unittest ({@link MeanderendeStroomTest#stroomTest()} of de
 * {@link #main(String[])}. Gebruik de 'trace current stream chain' functionaliteit om te kijken of er onnodige
 * stappen worden gedaan.
 * <ol>
 *     <li>Plaats een suspending breakpoint op de stream die resulteert in de variabele {@code kronkel}.
 *     <i>suspending breakpoints zijn te herkennen aan hun rode kleur</i></li>
 *     <li>Open de 'Trace Current Stream Chain' functionaliteit van IntelliJ<br>
 *     <i>dit is een knop in het debug scherm of je kan de functie zoeken via 'ctrl+shift+a' of 'dubbel shift'</i>
 *     </li>
 *     <li>Welke code zou verwijderd of in commentaar gezet kunnen worden zonder dat dit invloed heeft op het gelogde
 *     eindresultaat?</li>
 * </ol>
 * <p>
 * <h2>Opdracht 2 (Method Breakpoints)</h2>
 * Methodes van een interface kunnen in de praktijk wat verstopt zitten, ook de IDE geeft in dit geval niet aan dat
 * een methode gebruikt wordt en als dit het geval is waar. Dat is hier ook het geval. We zien in
 * {@link TheoretischeKnelpunten} 3 methodes, namelijk:
 * <ul>
 *     <li>{@link TheoretischeKnelpunten#otterburcht(int)}</li>
 *     <li>{@link TheoretischeKnelpunten#dam(int)}</li>
 *     <li>{@link TheoretischeKnelpunten#brug(int)}</li>
 * </ul>
 * IntelliJ geeft aan dat twee van deze methodes gebruikt worden.
 * <ol>
 *     <li>Plaats een method breakpoint om de derde methode te vinden.<br>
 *     <i>method breakpoints zijn te herkennen aan de 'ruit' vorm</i></li>
 * </ol>
 * <h2>Opdracht 3 (Evaluate and log)</h2>
 * De unittest {@link MeanderendeStroomTest#stroomTest()} werkt nog steeds niet en we willen de code met rust laten
 * tijdens deze opdrachten, daarom nu de opdracht om met behulp van de geavanceerde opties van een breakpoint de
 * test alsnog te laten slagen.
 * <p>
 * Er zijn meerdere oplossingen mogelijk.
 * <ol>
 *     <li>Één van de oplossingen is om een (non-suspending) breakpoint te plaatsen<br>
 *     <i>non-suspending breakpoints zijn te herkennen aan hun gele kleur</i>
 *     <li>Overschrijf bijvoorbeeld de {@code oorsprong} met de string:<br>
 *     "{@code Men dacht dat de klimaatverandering wel weg zou ebben, maar tegenwoordig werken we in 'Groningen aan Zee'}"</li>
 * </ol>
 */
public class MeanderendeStroom {

    private static final TheoretischeKnelpunten hogerGelegenConstructies = new HogerGelegenConstructies();

    public static void main(String[] args) throws Exception {
        new MeanderendeStroom().stroom();
    }

    public int stroom() {
        String oorsprong = "Een meanderend, kabbelend stroompje dat van de Kempkensberg af vloeit";
        String kronkel = oorsprong.chars()
                .mapToObj(bron())
                .filter(zuivering())
                .mapToInt(stroomversnelling())
                .filter(knelpuntA())
                .peek(uitkijkpost())
                .filter(knelpuntB())
                .mapToObj(uiterwaard())
                .collect(Collectors.joining());

        List<String> delta = kronkel.chars()
                .filter(weerspiegeling())
                .mapToObj(uiterwaard())
                .toList();

        int resultaat = delta.stream().mapToInt(stroomversnelling()).reduce(8834, Integer::sum);
        System.out.println("Eindresultaat: " + resultaat);

        return resultaat;
    }

    private static IntFunction<String> bron() {
        return i -> String.valueOf(((char) i));
    }

    private static Predicate<String> zuivering() {
        return s -> !s.equals(" ");
    }

    private static IntConsumer uitkijkpost() {
        return c -> System.out.println(c);
    }

    private static IntFunction<String> uiterwaard() {
        return i -> Integer.valueOf(i).toString();
    }

    private static ToIntFunction<String> stroomversnelling() {
        return s -> s.charAt(0);
    }

    private static IntPredicate knelpuntA() {
        return c -> hogerGelegenConstructies.otterburcht(c);
    }

    private static IntPredicate knelpuntB() {
        return c -> hogerGelegenConstructies.dam(c);
    }

    public interface TheoretischeKnelpunten {
        boolean otterburcht(int c);

        boolean dam(int c);

        boolean brug(int c);
    }
}
