import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDriver {
        private static List<Team> teamList = new ArrayList<>();
        private static Leaderboard leaderboard;
        private static Scanner scanner = new Scanner(System.in);
        private static List<String> fixtures = new ArrayList<>();

        public static void main(String[] args) {
                initializeTeams();
                leaderboard = new Leaderboard(teamList);

                while (true) {
                        displayMenu();
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (choice) {
                                case 1:
                                        displayLeaderboard();
                                        break;
                                case 2:
                                        displayTeamInfo();
                                        break;
                                case 3:
                                        displayPlayerInfo();
                                        break;
                                case 4:
                                        addPlayer();
                                        break;
                                case 5:
                                        transferPlayer();
                                        break;
                                case 6:
                                        updateMatchResult();
                                        break;
                                case 7:
                                        createFixtures();
                                        break;
                                case 8:
                                        displayFixtures();
                                        break;
                                case 9:
                                        System.out.println("Exiting the program. Goodbye!");
                                        return;
                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }
                }
        }

        private static void displayMenu() {
                System.out.println("\n--- Football League Manager ---");
                System.out.println("1. Display Leaderboard");
                System.out.println("2. Display Team Information");
                System.out.println("3. Display Player Information");
                System.out.println("4. Add Player");
                System.out.println("5. Transfer Player");
                System.out.println("6. Update Match Result");
                System.out.println("7. Create Fixtures");
                System.out.println("8. Display Fixtures");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");
        }

        private static void displayLeaderboard() {
                System.out.println("\n--- Leaderboard ---");
                System.out.println(leaderboard.displayLeaderboard());
        }

        private static void displayTeamInfo() {
                Team selectedTeam = selectTeam();
                if (selectedTeam != null) {
                        System.out.println("\n--- Team Information ---");
                        System.out.println(selectedTeam.displayInfo());
                        System.out.println("\n--- Team Players ---");
                        System.out.println(selectedTeam.displayTeamList());
                }
        }

        private static void displayPlayerInfo() {
                Team selectedTeam = selectTeam();
                if (selectedTeam != null) {
                        Player selectedPlayer = selectPlayer(selectedTeam);
                        if (selectedPlayer != null) {
                                System.out.println("\n--- Player Information ---");
                                System.out.println(selectedPlayer.displayInfo());
                        }
                }
        }

        private static Team selectTeam() {
                System.out.println("\n--- Select a Team ---");
                for (int i = 0; i < teamList.size(); i++) {
                        System.out.println((i + 1) + ". " + teamList.get(i).getName());
                }
                System.out.print("Enter the number of the team: ");
                int teamChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (teamChoice > 0 && teamChoice <= teamList.size()) {
                        return teamList.get(teamChoice - 1);
                } else {
                        System.out.println("Invalid team selection.");
                        return null;
                }
        }

        private static Player selectPlayer(Team team) {
                List<Player> players = team.getPlayers();
                System.out.println("\n--- Select a Player ---");
                for (int i = 0; i < players.size(); i++) {
                        System.out.println((i + 1) + ". " + players.get(i).getName());
                }
                System.out.print("Enter the number of the player: ");
                int playerChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (playerChoice > 0 && playerChoice <= players.size()) {
                        return players.get(playerChoice - 1);
                } else {
                        System.out.println("Invalid player selection.");
                        return null;
                }
        }

        private static void addPlayer() {
                Team team = selectTeam();
                if (team != null) {
                        System.out.print("Enter player name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter player age: ");
                        int age = scanner.nextInt();
                        System.out.print("Enter jersey number: ");
                        int jerseyNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter position: ");
                        String position = scanner.nextLine();

                        Player player = new Player(name, age, jerseyNumber, position, team);
                        team.addPlayer(player);
                        System.out.println("Player added successfully.");
                }
        }

        private static void transferPlayer() {
                System.out.println("\n--- Transfer Player ---");
                Team currentTeam = selectTeam();
                if (currentTeam != null) {
                        Player player = selectPlayer(currentTeam);
                        if (player != null) {
                                System.out.println("Select the new team for the player:");
                                Team newTeam = selectTeam();
                                if (newTeam != null) {
                                        currentTeam.removePlayer(player.getName());
                                        newTeam.addPlayer(player);
                                        player.setTeam(newTeam);
                                        System.out.println("Player " + player.getName() + " transferred from " + currentTeam.getName() + " to " + newTeam.getName());
                                }
                        }
                }
        }

        private static void updateMatchResult() {
                System.out.println("\n--- Update Match Result ---");
                Team homeTeam = selectTeam();
                if (homeTeam != null) {
                        System.out.println("Select the away team:");
                        Team awayTeam = selectTeam();
                        if (awayTeam != null) {
                                System.out.print("Enter home team goals: ");
                                int homeGoals = scanner.nextInt();
                                System.out.print("Enter away team goals: ");
                                int awayGoals = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                Match match = new FriendlyMatch(homeTeam, awayTeam, "N/A", "N/A");
                                match.updateMatchGoals(homeGoals, awayGoals);
                                leaderboard.updateLeaderboard();
                                System.out.println("Match result updated successfully.");
                        }
                }
        }

        private static void createFixtures() {
                fixtures = Match.createFixtures(teamList);
                System.out.println("Fixtures created successfully.");
        }

        private static void displayFixtures() {
                System.out.println("\n--- Fixtures ---");
                if (fixtures.isEmpty()) {
                        System.out.println("No fixtures have been created yet. Please create fixtures first.");
                } else {
                        for (int i = 0; i < fixtures.size(); i++) {
                                System.out.println((i + 1) + ". " + fixtures.get(i));
                        }
                }
        }

//        private static Team findTeam(String teamName) {
//                for (Team team : teamList) {
//                        if (team.getName().equalsIgnoreCase(teamName)) {
//                                return team;
//                        }
//                }
//                return null;
//        }

        private static void initializeTeams() {
                // Create list of teams
                List<Team> teamList = new ArrayList<>();
                Team elites = new Team("ELITES FC", "Nkunim");
                Team kasanoma = new Team("KASANOMA FC", "Quota");
                Team redArmy = new Team("RED ARMY FC", "Tim Asare");
                Team legends = new Team("LEGENDS FC", "PY");
                Team highlanders = new Team("HIGHLANDERS FC", "Adom Ofori");
                Team northside = new Team("NORTHSIDE FC", "IcyDelly");

                teamList.add(elites);
                teamList.add(kasanoma);
                teamList.add(redArmy);
                teamList.add(legends);
                teamList.add(highlanders);
                teamList.add(northside);

                // Create list of elittesplayers
                List<Player> elitesPlayerList = new ArrayList<>();
                elitesPlayerList.add(new Player("Gaze", 20, 3, "LB", elites));
                elitesPlayerList.add(new Player("Kofi", 19, 30, "ST", elites));
                elitesPlayerList.add(new Player("Moses", 18, 13, "DM", elites));
                elitesPlayerList.add(new Player("David", 20, 10, "LW", elites));
                elitesPlayerList.add(new Player("Joseph", 20, 1, "RW", elites));
                elitesPlayerList.add(new Player("Gabriel", 20, 2, "RB", elites));
                elitesPlayerList.add(new Player("Francis", 18, 4, "CB", elites));
                elitesPlayerList.add(new Player("Maxwell", 20, 6, "GK", elites));
                elitesPlayerList.add(new Player("Jonathan", 16, 16, "LCM", elites));
                elitesPlayerList.add(new Player("John", 20, 19, "RCM", elites));
                elitesPlayerList.add(new Player("Bless", 20, 7, "CB", elites));
                elitesPlayerList.add(new Player("Eyiram", 20, 18, "LB", elites));
                elitesPlayerList.add(new Player("Enoch", 15, 9, "LCM", elites));
                elitesPlayerList.add(new Player("Jesimiel", 21, 14, "RCM", elites));
                elitesPlayerList.add(new Player("Lucky", 23, 51, "ST", elites));
                elitesPlayerList.add(new Player("Robert", 20, 20, "DM", elites));
                elitesPlayerList.add(new Player("Eric", 20, 55, "LB", elites));
                elites.setTeamPlayers(elitesPlayerList);

                // Create list of kasanomaplayers
                List<Player>  kasanomaPlayerList = new ArrayList<>();
                kasanomaPlayerList.add(new Player("Aaron", 20, 3, "LB", kasanoma));
                kasanomaPlayerList.add(new Player("Adam", 19, 30, "LW", kasanoma));
                kasanomaPlayerList.add(new Player("Adrian", 18, 13, "ST", kasanoma));
                kasanomaPlayerList.add(new Player("Alan", 20, 10, "DM", kasanoma));
                kasanomaPlayerList.add(new Player("Albert", 20, 1, "RW", kasanoma));
                kasanomaPlayerList.add(new Player("Alexander", 20, 2, "RB", kasanoma));
                kasanomaPlayerList.add(new Player("Andrew", 18, 4, "CB", kasanoma));
                kasanomaPlayerList.add(new Player("Anthony", 20, 6, "GK", kasanoma));
                kasanomaPlayerList.add(new Player("Arthur", 16, 16, "LCM", kasanoma));
                kasanomaPlayerList.add(new Player("Ashton", 20, 19, "RCM", kasanoma));
                kasanomaPlayerList.add(new Player("Austin", 20, 7, "CB", kasanoma));
                kasanomaPlayerList.add(new Player("Barry", 20, 18, "LB", kasanoma));
                kasanomaPlayerList.add(new Player("Benjamin", 15, 9, "LW", kasanoma));
                kasanomaPlayerList.add(new Player("Blake", 21, 14, "ST", kasanoma));
                kasanomaPlayerList.add(new Player("Bradley", 23, 51, "DM", kasanoma));
                kasanomaPlayerList.add(new Player("Brandon", 20, 20, "RW", kasanoma));
                kasanomaPlayerList.add(new Player("Brain", 20, 55, "RB", kasanoma));
                kasanoma.setTeamPlayers(kasanomaPlayerList);

                // Create list of redArmyplayers
                List<Player>  redArmyPlayerList = new ArrayList<>();
                redArmyPlayerList.add(new Player("Caleb", 20, 3, "LB", redArmy));
                redArmyPlayerList.add(new Player("Charles", 19, 30, "LW", redArmy));
                redArmyPlayerList.add(new Player("Christian", 18, 13, "ST", redArmy));
                redArmyPlayerList.add(new Player("Christopher", 20, 10, "DM", redArmy));
                redArmyPlayerList.add(new Player("Cody", 20, 1, "RW", redArmy));
                redArmyPlayerList.add(new Player("Colin", 20, 2, "RB", redArmy));
                redArmyPlayerList.add(new Player("Connor", 18, 4, "CB", redArmy));
                redArmyPlayerList.add(new Player("Daniel", 20, 6, "GK", redArmy));
                redArmyPlayerList.add(new Player("Dean", 16, 16, "LCM", redArmy));
                redArmyPlayerList.add(new Player("Derek", 20, 19, "RCM", redArmy));
                redArmyPlayerList.add(new Player("Dominic", 20, 7, "CB", redArmy));
                redArmyPlayerList.add(new Player("Donald", 20, 18, "LB", redArmy));
                redArmyPlayerList.add(new Player("Dylan", 15, 9, "LW", redArmy));
                redArmyPlayerList.add(new Player("Edward", 21, 14, "ST", redArmy));
                redArmyPlayerList.add(new Player("Elijah", 23, 51, "DM", redArmy));
                redArmyPlayerList.add(new Player("Elliot", 20, 20, "RW", redArmy));
                redArmyPlayerList.add(new Player("Erik", 20, 55, "RB", redArmy));
                redArmy.setTeamPlayers(redArmyPlayerList);

                // Create list of legendsArmyplayers
                List<Player>  legendsPlayerList = new ArrayList<>();
                legendsPlayerList.add(new Player("Ethan", 20, 3, "LB", legends));
                legendsPlayerList.add(new Player("Evan", 19, 30, "LW", legends));
                legendsPlayerList.add(new Player("Felix", 18, 13, "ST", legends));
                legendsPlayerList.add(new Player("Finley", 20, 10, "DM", legends));
                legendsPlayerList.add(new Player("Francis", 20, 1, "RW", legends));
                legendsPlayerList.add(new Player("Gabriel", 20, 2, "RB", legends));
                legendsPlayerList.add(new Player("George", 18, 4, "CB", legends));
                legendsPlayerList.add(new Player("Gerald", 20, 6, "GK", legends));
                legendsPlayerList.add(new Player("Graham", 16, 16, "LCM", legends));
                legendsPlayerList.add(new Player("Grant", 20, 19, "RCM", legends));
                legendsPlayerList.add(new Player("Gregory", 20, 7, "CB", legends));
                legendsPlayerList.add(new Player("Harry", 20, 18, "LB", legends));
                legendsPlayerList.add(new Player("Henry", 15, 9, "LW", legends));
                legendsPlayerList.add(new Player("Hugh", 21, 14, "ST", legends));
                legendsPlayerList.add(new Player("Ian", 23, 51, "DM", legends));
                legendsPlayerList.add(new Player("Isaac", 20, 20, "RW", legends));
                legendsPlayerList.add(new Player("Jack", 20, 55, "RB", legends));
                legends.setTeamPlayers(legendsPlayerList);


                // Create list of highlandersplayers
                List<Player>  highlandersPlayerList = new ArrayList<>();
                highlandersPlayerList.add(new Player("Ethan", 20, 3, "LB", highlanders));
                highlandersPlayerList.add(new Player("Evan", 19, 30, "LW", highlanders));
                highlandersPlayerList.add(new Player("Felix", 18, 13, "ST", highlanders));
                highlandersPlayerList.add(new Player("Finley", 20, 10, "DM", highlanders));
                highlandersPlayerList.add(new Player("Francis", 20, 1, "RW", highlanders));
                highlandersPlayerList.add(new Player("Gabriel", 20, 2, "RB", highlanders));
                highlandersPlayerList.add(new Player("George", 18, 4, "CB", highlanders));
                highlandersPlayerList.add(new Player("Gerald", 20, 6, "GK", highlanders));
                highlandersPlayerList.add(new Player("Graham", 16, 16, "LCM", highlanders));
                highlandersPlayerList.add(new Player("Grant", 20, 19, "RCM", highlanders));
                highlandersPlayerList.add(new Player("Gregory", 20, 7, "CB", highlanders));
                highlandersPlayerList.add(new Player("Harry", 20, 18, "LB", highlanders));
                highlandersPlayerList.add(new Player("Henry", 15, 9, "LW", highlanders));
                highlandersPlayerList.add(new Player("Hugh", 21, 14, "ST", highlanders));
                highlandersPlayerList.add(new Player("Ian", 23, 51, "DM", highlanders));
                highlandersPlayerList.add(new Player("Isaac", 20, 20, "RW", highlanders));
                highlandersPlayerList.add(new Player("Jack", 20, 55, "RB", highlanders));
                highlanders.setTeamPlayers(highlandersPlayerList);

                Match.createFixtures(teamList);
        }
}
