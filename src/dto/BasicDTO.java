package dto;

public class BasicDTO {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BasicDTO{" +
                "id=" + id +
                '}';
    }
}
