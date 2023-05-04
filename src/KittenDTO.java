public class KittenDTO {
    private boolean alive;
    private int paws;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getPaws() {
        return paws;
    }

    public void setPaws(int paws) {
        this.paws = paws;
    }

    @Override
    public String toString() {
        return "KittenDTO{" +
                "alive=" + alive +
                ", paws=" + paws +
                '}';
    }
}
