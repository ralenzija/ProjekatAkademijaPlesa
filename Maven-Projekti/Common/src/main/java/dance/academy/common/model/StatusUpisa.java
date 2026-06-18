package dance.academy.common.model;

/**
 * Enumeracija koja predstavlja moguće statuse upisa na program aktivnosti.
 * <p>
 * Status upisa prati životni ciklus jednog {@link UpisNaProgram} od trenutka
 * kreiranja pa sve do završetka ili otkazivanja. Na osnovu statusa sistem
 * odlučuje koje operacije su dozvoljene nad upisom.
 * </p>
 *
 * <ul>
 *   <li>{@link #KREIRAN} — upis je tek napravljen, čeka se potvrda</li>
 *   <li>{@link #POTVRDJEN} — instruktor je potvrdio upis</li>
 *   <li>{@link #ODBIJEN} — upis je odbijen od strane akademije</li>
 *   <li>{@link #OTKAZAN} — učesnik je sam otkazao upis</li>
 *   <li>{@link #ZAVRSEN} — program je uspešno završen</li>
 * </ul>
 *
 * @author Rastko
 * @version 1.0
 */
public enum StatusUpisa {

    /** Upis je kreiran i čeka dalju obradu. */
    KREIRAN,

    /** Upis je potvrđen od strane akademije ili instruktora. */
    POTVRDJEN,

    /** Upis je odbijen, učesnik ne može pohađati program. */
    ODBIJEN,

    /** Učesnik je otkazao upis pre početka programa. */
    OTKAZAN,

    /** Program je uspešno završen. */
    ZAVRSEN
}