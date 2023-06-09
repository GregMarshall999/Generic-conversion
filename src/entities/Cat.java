package entities;

import java.util.List;

public class Cat extends BasicEntity {
    private boolean alive;
    private String name;
    private Paws paws;
    private List<Kitten> kittens;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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