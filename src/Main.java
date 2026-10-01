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
        if (playerans.equals("ДА")) {
            System.out.println("Игра началась!");
            System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
            System.out.println("Координаты персонажа - (x: " + ox + ", y: " + oy + ")");

            int x = sc.nextInt();
            int y = sc.nextInt();

            if (x != ox && y != oy) {
                System.out.println("Некорректный ход");
            } else if (Math.abs(x - ox) == 1) {
                ox = x;
                playerplace += 1;
            } else if (Math.abs(y - oy) == 1) {
                oy = y;
                playerplace += 1;
            } else {
                System.out.println("Координаты не изменены");
            }

        } else {
            System.out.println("Печально, ну бб :(");
        }



    }
}
