public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        Kitten kitten = new Kitten();
        kitten.setAlive(true);
        kitten.setPaws(4);

        Cat c = new Cat();
        c.setAlive(true);
        c.setPaws(4);
        c.setKitten(kitten);

        CatDTO dto = new CatDTO();

        service.convertToDTO(c, dto);

        System.out.println(dto);
    }
}