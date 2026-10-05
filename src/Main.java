import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);

        String castle = "\uD83C\uDFF0";
        String person = "Г";
        String monster = "Мм";

        int castleY = 1;
        int sizeofplace = 5;
        int castleX = 1 + rnd.nextInt(sizeofplace);
        int health = 3;
        int ox = 1;
        int oy = sizeofplace;
        int playerplace = 0;
        int monsters = 5;

        String[][] board = new String[sizeofplace][sizeofplace];

        for (int i = 0; i < sizeofplace; i++) {
            for (int j = 0; j < sizeofplace; j++) {
                board[i][j] = "  ";
            }
        }

        for (int i = 0; i < monsters; i++) {
            int mx = rnd.nextInt(sizeofplace);
            int my = rnd.nextInt(sizeofplace);
            board[my][mx] = monster;
        }

        board[castleY - 1][castleX - 1] = castle;
        board[oy - 1][ox - 1] = person;

        System.out.println("Ты готов начать игру? (Напиши: ДА или НЕТ)");
        String playerans = sc.nextLine();

        switch (playerans) {
            case "ДА":
                System.out.println("Игра началась!");
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                break;
            case "НЕТ":
                System.out.println("Печально, ну бб :(");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }

        if (playerans.equals("ДА")) {

            while (health > 0 && !(castleX == ox && castleY == oy)) {

                playerplace += 1;

                String wall = "+ —— + —— + —— + —— + —— +";

                for (int y = 1; y <= sizeofplace; y++) {
                    System.out.println(wall);
                    System.out.print("|");
                    for (int x = 1; x <= sizeofplace; x++) {
                        System.out.print(" " + board[y - 1][x - 1] + " |");
                    }
                    System.out.println();
                }
                System.out.println(wall);

                System.out.println("Ход номер: " + playerplace);
                System.out.println("Жизни: " + health);
                System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
                System.out.println("Координаты персонажа - (x: " + ox + ", y: " + oy + ")");

                int x = sc.nextInt();
                int y = sc.nextInt();

                if (x != ox && y != oy) {
                    System.out.println("Некорректный ход");
                    health -= 1;
                } else if (Math.abs(x - ox) == 1 || Math.abs(y - oy) == 1) {

                    if (board[y - 1][x - 1].equals(castle)) {
                        System.out.println("Ты добрался до замка!");
                        break;
                    } else if (board[y - 1][x - 1].equals("  ")) {
                        board[oy - 1][ox - 1] = "  ";
                        ox = x;
                        oy = y;
                        board[oy - 1][ox - 1] = person;
                        System.out.println("Ход корректный");
                    } else {
                        System.out.println("Там монстр");
                        health -= 1;
                    }

                } else {
                    System.out.println("Координаты не изменены");
                    health -= 1;
                }
            }

            if (health <= 0) {
                System.out.println("Игра окончена");
            }
        }
    }
}