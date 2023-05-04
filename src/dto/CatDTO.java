package dto;

import java.util.List;

public class CatDTO {
    private boolean alive;
    private PawsDTO paws;
    private List<KittenDTO> kittensDTO;

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

    public List<KittenDTO> getKittensDTO() {
        return kittensDTO;
    }

    public void setKittensDTO(List<KittenDTO> kittensDTO) {
        this.kittensDTO = kittensDTO;
    }

    @Override
    public String toString() {
        return "CatDTO{" +
                "alive=" + alive +
                ", paws=" + paws +
                ", kittensDTO=" + kittensDTO +
                '}';
    }
}
