import java.util.Random;
import java.util.Scanner;

public class Monster {
    private int x;
    private int y;
    private String image = "Мм";

    Monster(int sizeBoard) {
        Random random = new Random();
        x = 1 + random.nextInt(sizeBoard);
        y = 1 + random.nextInt(sizeBoard);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImage() {
        return image;
    }

    public boolean conflictPerson(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean taskMonster(int difficultGame) {
        System.out.println("Решите задачу:");

        Random random = new Random();

        int a = random.nextInt(10 * difficultGame);
        int b = random.nextInt(10 * difficultGame);
        int trueAnswer = a + b;

        System.out.println("Реши пример: " + a + " + " + b + " = ?");
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