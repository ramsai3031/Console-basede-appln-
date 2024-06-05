
import java.util.*;

class Character {
    private String name;
    private int level;
    private int health;
    private int attack;
    private int experience;

    public Character(String name) {
        this.name = name;
        this.level = 1;
        this.health = 100;
        this.attack = 10;
        this.experience = 0;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getExperience() {
        return experience;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void gainExperience(int exp) {
        this.experience += exp;
        if (this.experience >= 100) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.experience = 0;
        this.health += 20;
        this.attack += 5;
        System.out.println("Congratulations! You leveled up to level " + this.level);
    }
}

class Monster {
    private String name;
    private int health;
    private int attack;

    public Monster(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}

public class TextBasedRPG {
    private static Character player;
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter your character's name: ");
        String name = scanner.nextLine();
        player = new Character(name);
        System.out.println("Welcome, " + player.getName() + "! Your adventure begins now.");

        while (true) {
            System.out.println("\n1. Battle a monster");
            System.out.println("2. View character stats");
            System.out.println("3. Exit");
            System.out.print("Choose an action: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    battle();
                    break;
                case 2:
                    viewStats();
                    break;
                case 3:
                    System.out.println("Thank you for playing! Goodbye.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void battle() {
        Monster monster = new Monster("Goblin", 50, 8);
        System.out.println("A wild " + monster.getName() + " appears!");

        while (monster.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\n1. Attack");
            System.out.println("2. Run away");
            System.out.print("Choose an action: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                int playerDamage = random.nextInt(player.getAttack());
                int monsterDamage = random.nextInt(monster.getAttack());

                monster.takeDamage(playerDamage);
                player.takeDamage(monsterDamage);

                System.out.println("You dealt " + playerDamage + " damage to the " + monster.getName() + ".");
                System.out.println("The " + monster.getName() + " dealt " + monsterDamage + " damage to you.");

                if (monster.getHealth() <= 0) {
                    System.out.println("You defeated the " + monster.getName() + "!");
                    player.gainExperience(50);
                } else if (player.getHealth() <= 0) {
                    System.out.println("You were defeated by the " + monster.getName() + ".");
                    System.out.println("Game Over.");
                    return;
                }
            } else if (choice == 2) {
                System.out.println("You ran away from the battle.");
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewStats() {
        System.out.println("\nCharacter Stats:");
        System.out.println("Name: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Experience: " + player.getExperience());
    }
}
