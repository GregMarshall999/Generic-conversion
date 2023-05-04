package entities;

import java.util.List;

public class Cat {
    private boolean alive;
    private Paws paws;
    private List<Kitten> kittens;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Paws getPaws() {
        return paws;
    }

    public void setPaws(Paws paws) {
        this.paws = paws;
    }

    public List<Kitten> getKittens() {
        return kittens;
    }

    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }
}