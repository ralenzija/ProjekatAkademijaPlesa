package dance.academy.common.model;

/**
 * Enumeracija koja predstavlja stilove plesa koje plesna akademija nudi.
 * <p>
 * Svaki stil plesa ima svoje karakteristike, poreklo i tehniku. Koristi se
 * pri definisanju {@link ProgramAktivnosti}, {@link Sertifikat} i
 * {@link NivoVestine} kako bi se jasno odredilo o kom stilu plesa se radi.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public enum PlesniStil {

    /** Salsa — latinoamerički ples porekla iz Kube i Portorika. */
    SALSA,

    /** Bachata — romantični ples porekla iz Dominikanske Republike. */
    BACHATA,

    /** Kizomba — senzualni ples porekla iz Angole. */
    KIZOMBA,

    /** Tango — argentinski ples poznat po dramatičnim pokretima. */
    TANGO,

    /** Valcer — klasični evropski ples u 3/4 taktu. */
    VALCER,

    /** Samba — brazilski ples koji se tradicionalno igra na karnevalu. */
    SAMBA,

    /** Cha-cha — kubanski ples živahnog ritma sa karakterističnim korakom. */
    CHA_CHA,

    /** Rumba — kubanski ples sporog tempa i naglašenih pokreta kukova. */
    RUMBA,

    /** Jive — energičan ples koji potiče iz američkog swing pokreta. */
    JIVE,

    /** Swing — američki ples nastao uz džez muziku 1920-ih godina. */
    SWING,

    /** Zouk — senzualni ples porekla sa Karipskih ostrva. */
    ZOUK
}