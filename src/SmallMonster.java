import java.util.Random;
import java.util.Scanner;

public class SmallMonster extends Monster {

    SmallMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("Решите задачу:");
        Random random = new Random();
        int a = random.nextInt(5);
        int b = random.nextInt(5);
        int trueAnswer = a - b;
        System.out.println("Реши пример: " + a + " - " + b + " = ?");
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        if (trueAnswer == answer) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        } else {
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }
}