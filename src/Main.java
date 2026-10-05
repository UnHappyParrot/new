import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sizeofplace = 5;
        int health = 3;
        int ox = 1 + sizeofplace / 2;
        int oy = 1 + sizeofplace / 2;
        int playerplace = 0;
        int monster = 0;
        int person = 0;
        String gamingField = "+ —— + —— + —— + —— + —— +\n"
                + "|    |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "|    |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "|    |    | " + monster + " |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "|    |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "| " + person + " |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +";


        System.out.println("Ты готов начать игру? (Напиши: ДА или НЕТ)");
        String playerans = sc.nextLine();
        switch (playerans) {
            case "ДА":
            System.out.println("Игра началась!");
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
            System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
            System.out.println("Координаты персонажа - (x: " + ox + ", y: " + oy + ")");

            int x = sc.nextInt();
            int y = sc.nextInt();

            if (x != ox && y != oy) {
                System.out.println("Некорректный ход");
            } else if (Math.abs(x - ox) == 1 || Math.abs(y - oy) == 1) {
                ox = x;
                oy = y;
                playerplace += 1;
                System.out.println("Ход корректный; Новые координаты: " +
                        ox + ", " + oy + "\nХод номер: " + playerplace);
            } else {
                System.out.println("Координаты не изменены");
            }
            break;
            case("НЕТ"):
            System.out.println("Печально, ну бб :(");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }
        }


        //lll
    }
