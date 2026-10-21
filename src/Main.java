import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        String castle = "\uD83C\uDFF0";
        int castleY = 1;
        int sizeofplace = 5;
        int castleX = 1 + rnd.nextInt(sizeofplace);
        int step = 0;
        int countMonster = 5;
        String[][] board = new String[sizeofplace][sizeofplace];
        for (int i = 0; i < sizeofplace; i++) {
            for (int j = 0; j < sizeofplace; j++) {
                board[i][j] = "  ";
            }
        }

        Person person = new Person(sizeofplace);
        board[person.getY() - 1][person.getX() - 1] = person.getImage();

        Monster[] monsters = new Monster[countMonster];
        int count = 0;
        while (count < countMonster) {
            Monster monster = null;
            int type = rnd.nextInt(4);
            if (type == 0) {
                monster = new Monster(sizeofplace);
            } else if (type == 1) {
                monster = new BigMonster(sizeofplace);
            } else if (type == 2) {
                monster = new SmallMonster(sizeofplace);
            } else {
                monster = new PuzzleMonster(sizeofplace);
            }
            int row = monster.getY() - 1;
            int column = monster.getX() - 1;
            if (board[row][column].equals("  ")) {
                board[row][column] = monster.getImage();
                monsters[count] = monster;
                count = count + 1;
            }
        }
        board[castleY - 1][castleX - 1] = castle;

        System.out.println("Ты готов начать игру? (Напиши: ДА или НЕТ)");
        String playerans = sc.nextLine();
        int difficultGame = 1;
        switch (playerans) {
            case "ДА":
                System.out.println("Игра началась!");
                System.out.println("Выбери сложность игры (от 1 до 5):");
                difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                break;
            case "НЕТ":
                System.out.println("Печально, ну бб :(");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }

        if (playerans.equals("ДА")) {
            while (person.getLive() > 0 && !(castleX == person.getX() && castleY == person.getY())) {
                step = step + 1;
                outputBoard(board, person.getLive());
                System.out.println("Ход номер: " + step);
                System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
                System.out.println("Координаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");
                int x = sc.nextInt();
                int y = sc.nextInt();
                if (x < 1 || x > sizeofplace || y < 1 || y > sizeofplace) {
                    System.out.println("Некорректный ход");
                    person.downLive();
                } else if (person.isMoveCorrect(x, y)) {
                    String next = board[y - 1][x - 1];
                    if (next.equals("  ")) {
                        board[person.getY() - 1][person.getX() - 1] = "  ";
                        person.move(x, y);
                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                        System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() + "\nХод номер: " + step);
                    } else if (next.equals(castle)) {
                        System.out.println("Вы прошли игру!");
                        break;
                    } else {
                        for (int i = 0; i < monsters.length; i++) {
                            Monster monster = monsters[i];
                            if (monster.conflictPerson(x, y)) {
                                if (monster.taskMonster(difficultGame)) {
                                    board[person.getY() - 1][person.getX() - 1] = "  ";
                                    person.move(x, y);
                                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                                    step = step + 1;
                                } else {
                                    person.downLive();
                                }
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Некорректный ход");
                    person.downLive();
                }
            }
            if (person.getLive() <= 0) {
                System.out.println("Игра окончена");
            }
        }
        sc.close();
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";
        for (int i = 0; i < board.length; i++) {
            System.out.println(wall);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(leftBlock + board[i][j] + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество жизней:\t" + live + "\n");
    }
}