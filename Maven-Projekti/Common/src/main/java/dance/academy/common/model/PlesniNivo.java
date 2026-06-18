package dance.academy.common.model;

/**
 * Enumeracija koja predstavlja nivoe znanja učesnika u određenom stilu plesa.
 * <p>
 * Nivo se dodeljuje učesniku nakon procene i određuje u koje programe
 * aktivnosti može da se upiše. Napredovanjem kroz programe učesnik
 * stiče veštine i prelazi na viši nivo.
 * </p>
 *
 * @author Rastko
 * @version 1.0
 */
public enum PlesniNivo {

    /** Početni nivo — učesnik tek počinje da uči ples, bez prethodnog iskustva. */
    POCETNI,

    /** Srednji nivo — učesnik poznaje osnove i spreman je za složenije tehnike. */
    SREDNJI,

    /** Napredni nivo — učesnik savladao tehniku i može da radi na finoćama. */
    NAPREDNI,

    /** Majstorski nivo — učesnik postiže profesionalni nivo izvođenja. */
    MAJSTORSKI
}