import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

class CliControl {

    private ArrayList<Command> commands;
    private Scanner scan;

    private static final String HELP_CMDNAME = "help";
    private static final String CMDHELP_USAGE_PREFIX = "help ";

    public Dungeon dung;
    public Mode mode = Mode.GENERAL;

    CliControl(Dungeon d) {
        this.scan = new Scanner(System.in);
        this.commands = generateCommands();

        this.dung = d;

        giveUserCLIcontrol();
    }

    private void giveUserCLIcontrol() {
        Coords playerPos = dung.placeExits();
        
        // ~~~~~~~~~~~~PLAYER INIT~~~~~~~~~~~~~~
        //System.out.println("What is your name?");
        //System.out.print("\n> ");
        String playName = "McPixel"; //sc.nextLine();
        Player player = new Player(playName);
        dung.placePlayer(player, playerPos);
        // ~~
        System.out.println("\n\n Welcome to the Dungeon, " + player.name + "\n\n");

        while (true) {
            switch (this.mode) {
                case GENERAL:
                    System.out.println("\n\n [INVENTORY] [STATS] [WORLD]		[EXIT] \n\n"); 
                    Dungeon.prettyPrint(dung.currentLevel);              
                    break;
                case INVENTORY:
                    System.out.println("\n\n [USE] [THROW AWAY]        [BACK]");
                    break;
                case WORLD:
                    System.out.println("\n\n [MOVE] [EXIT LEVEL]        [BACK]");
                    Dungeon.prettyPrint(dung.currentLevel);
                    break;
                case STATS:
                    System.out.println("\n\n [MANAGE SKILLS] [RESPEC]       [BACK]");
                    break;
                default:
                    System.out.println("\n\n [INVENTORY] [STATS] [WORLD]		[EXIT] \n\n");
                    Dungeon.prettyPrint(dung.currentLevel);
                    break;
            }

            System.out.println("\n\n What do you want to do?");

            // Wait for user pressing Enter
            System.out.print("\n> ");
            String line = scan.nextLine().trim().toLowerCase();
            // Infinite loop ends when user enters "exit" or "quit"

            // Issue commands
            if (line.isEmpty()) {
                continue;
            } else {
                execute(line);
            }


        }
    }

    private ArrayList<Command> generateCommands() {

        ArrayList<Command> cmds = new ArrayList<>();

        cmds.add(new Command("inventory", 
                new String[]
                {"inv", "i"},
                "Manage your inventory. ") {

            @Override
            void action() {
                System.out.println("\n\n INVENTORY");
                mode = Mode.INVENTORY;
                dung.player.viewInventory();
            }
        });

        cmds.add(new Command("stats", 
                new String[]
                {"stat", "s"},
                "View your statistics. ") {

            @Override
            void action() {
                System.out.println("\n\n STATS");

                mode = Mode.STATS;
                //dung.player.viewStats();
            }
        });

        cmds.add(new Command("world", 
                new String[]
                {"wor", "w"},
                "Interact with the world. ") {

            @Override
            void action() {
                mode = Mode.WORLD;
            }
            
        });

        cmds.add(new Command("back", 
                new String[]
                {"b"},
                "Return to previous menu. ") {

            @Override
            void action() {
                mode = Mode.GENERAL;
            }
        });  
        ///  EXIT AND HELP COMMANDS AT THE END  ///

        cmds.add(new Command(
                "exit", new String[]
                {"ex", "q"},
                "Exits the game. ") {

            @Override
            void action() {
                System.out.format("\n" +
                        "Exiting dungeon..\n");
                System.exit(0);
            }
        });

        cmds.add(new Command(
                "help", new String[]
                {"h", "?"},
                "Prints help for controlling this simulation.") {

            @Override
            void action() {
                String HELP = "\n" +
                        "When paused, you can make changes to the model \n" +
                        "and view attributes of the simulation objects. \n" +
                        "\n" +
                        "List of all commands: \n" +
                        "" + generateCommandHelp() +
                        "\n" +
                        "There will be other commands coming. \n" +
                        "\n" +
                        "Input command or press enter (input empty line) to resume simulation. ";
                System.out.println(HELP);
            }
        });

        return cmds;
    }

    private Command findCommand(String command_name) {

        for (Command cmd : commands)
            for (String alias : cmd.abbrev)
                if (command_name.startsWith(alias))
                    return cmd;
        return null;
    }

    // TODO delete
    private void descriptionOf(String command_name) {

        if (command_name.trim().length() == 0) return;

        Command cmd = findCommand(command_name);
        if (cmd == null) {
            System.out.format("'%s' is an unknown command.\n", command_name);
            return;
        }

        System.out.format("'%s': %s\n", cmd.name, cmd.description);
    }

    void execute(String command_name) {

        if (command_name.trim().length() == 0) return;

        if (command_name.startsWith(CMDHELP_USAGE_PREFIX)) {

            command_name = command_name.substring(CMDHELP_USAGE_PREFIX.length());
            descriptionOf(command_name);

        } else {

            Command cmd = findCommand(command_name);
            if (cmd == null) {
                System.out.format("'%s' is an unknown command - cannot execute it.\n", command_name);
                return;
            }
            cmd.action();
        }

    }

    // TODO delete
    private String generateCommandHelp() {
        StringBuilder sb = new StringBuilder("");
        for (Command cmd : commands)
            sb.append(String.format(
                    " %-16s  %s\n",
                    "'"+ cmd.name +"':", cmd.description));
        return sb.toString();
    }



    abstract class Command {

        final String name;
        final String[] abbrev;
        final String description;

        Command(String name, String[] abbrev, String description) {
            this.name = name;
            this.abbrev = abbrev;
            this.description = description;
        }

        abstract void action();
    }

}

enum Mode {
    GENERAL,
    INVENTORY,
    WORLD,
    STATS
}