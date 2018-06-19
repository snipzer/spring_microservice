package main.enumeration;

public enum Etat {

    ATTENTE(1L, "en attente"), COURS(2L, "en cours"), LIVRER(3L, "livrer");

    private Long id;

    private String libelle;

    Etat(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public static Etat getEtatById(Long id) {
        for (Etat etat : Etat.values()) {
            if (etat.getId().equals(id)) {
                return etat;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return this.libelle;
    }
}
