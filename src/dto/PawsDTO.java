package dto;

public class PawsDTO extends BasicDTO {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PawsDTO{" +
                "amount=" + amount +
                '}';
    }
}
