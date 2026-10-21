import java.util.Random;
import java.util.Scanner;

public class PuzzleMonster extends Monster {

    private int secret;
    private int maxNumber;

    PuzzleMonster(int sizeBoard) {
        super(sizeBoard);
        Random random = new Random();
        maxNumber = 0;
        secret = 0;
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        Random random = new Random();

        if (difficultGame == 1 || difficultGame == 2) {
            maxNumber = 3;
        } else if (difficultGame == 3 || difficultGame == 4) {
            maxNumber = 5;
        } else {
            maxNumber = 7;
        }

        secret = 1 + random.nextInt(maxNumber);

        System.out.println("Угадай число от 1 до " + maxNumber + ". У тебя 3 попытки!");
        Scanner scanner = new Scanner(System.in);
        int attempts = 3;
        while (attempts > 0) {
            System.out.println("Осталось попыток: " + attempts);
            System.out.print("Твой вариант: ");
            int guess = scanner.nextInt();
            if (guess == secret) {
                System.out.println("Верно! Ты угадал число " + secret + " и победил монстра");
                return true;
            } else if (guess < secret) {
                System.out.println("Загаданное число больше");
            } else {
                System.out.println("Загаданное число меньше");
            }
            attempts = attempts - 1;
        }
        System.out.println("Попытки закончились. Было загадано число " + secret);
        System.out.println("Ты проиграл эту битву!");
        return false;
    }
}