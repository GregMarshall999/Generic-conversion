import dto.CatDTO;
import entities.Cat;
import entities.Kitten;
import entities.Paws;
import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Converter service = new Converter();

        Paws paws = new Paws();
        paws.setAmount(4);

        Kitten kitten1 = new Kitten();
        kitten1.setAlive(true);
        kitten1.setPaws(paws);

        Kitten kitten2 = new Kitten();
        kitten2.setAlive(true);
        kitten2.setPaws(paws);

        Cat c = new Cat();
        c.setAlive(true);
        c.setPaws(paws);
        c.setKittens(List.of(kitten1, kitten2));

        CatDTO dto = new CatDTO();

        service.convertTo(c, dto);

        System.out.println(dto);
    }

    static class Test {
        private List<Cat> test;

        public Test() {
            test = new ArrayList<>();
        }

        public List<Cat> getTest() {
            return test;
        }

        public void setTest(List<Cat> test) {
            this.test = test;
        }
    }
}