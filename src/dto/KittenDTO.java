package dto;

public class KittenDTO extends BasicDTO {
    private boolean alive;
    private PawsDTO paws;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public PawsDTO getPaws() {
        return paws;
    }

    public void setPaws(PawsDTO paws) {
        this.paws = paws;
    }

    @Override
    public String toString() {
        return "dto.KittenDTO{" +
                "alive=" + alive +
                ", paws=" + paws +
                '}';
    }
}
