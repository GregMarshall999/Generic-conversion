public class CatDTO {
    private int paws;
    private boolean alive;
    private KittenDTO kittenDTO;

    public int getPaws() {
        return paws;
    }

    public void setPaws(int paws) {
        this.paws = paws;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public KittenDTO getKittenDTO() {
        return kittenDTO;
    }

    public void setKittenDTO(KittenDTO kittenDTO) {
        this.kittenDTO = kittenDTO;
    }

    @Override
    public String toString() {
        return "CatDTO{" +
                "paws=" + paws +
                ", alive=" + alive +
                ", kittenDTO=" + kittenDTO +
                '}';
    }
}
