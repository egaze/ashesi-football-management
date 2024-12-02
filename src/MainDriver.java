import java.io.File;
import java.util.*;

public class MainDriver {
        private static List<Team> teamList = new ArrayList<>();
        private static List<Match> matches = new ArrayList<>();
        private static Leaderboard leaderboard;
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
                if (!dataFilesExist()) {
                        saveInitialData();
                }

                loadData();
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
                                        scheduleMatch();
                                        break;
                                case 7:
                                        updateMatchResult();
                                        break;
                                case 8:
                                        displayMatches();
                                        break;
                                case 9:
                                        saveData();
                                        System.out.println("Data saved. Exiting the program. Goodbye!");
                                        return;
                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }
                }
        }

        private static void displayMenu() {
                System.out.println("\n==== Football League Manager ====");
                System.out.println("1. Display Leaderboard");
                System.out.println("2. Display Team Information");
                System.out.println("3. Display Player Information");
                System.out.println("4. Add Player");
                System.out.println("5. Transfer Player");
                System.out.println("6. Schedule Match");
                System.out.println("7. Update Match Result");
                System.out.println("8. Display Matches");
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
                System.out.println("======Teams available======");
                int count = 1;
                Map<Integer, String> teamNumbers = new HashMap<>();
                for (Team team : teamList) {
                        teamNumbers.put(count, team.getName());
                        System.out.println(count+"--"+team.getName());
                        count++;
                }
                System.out.print("Select the team number: ");
                int teamNumber = scanner.nextInt();
                for (Team team : teamList) {
                        if (team.getName().equalsIgnoreCase(teamNumbers.get(teamNumber))) {
                                return team;
                        }
                }
                System.out.println("Team not found.");
                return null;
        }

        private static Player selectPlayer(Team team) {
                System.out.print("Enter player name: ");
                scanner.nextLine(); // consume new line
                String playerName = scanner.nextLine();
                for (Player player : team.getPlayers()) {
                        if (player.getName().equalsIgnoreCase(playerName)) {
                                return player;
                        }
                }
                System.out.println("Player not found.");
                return null;
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

        private static void scheduleMatch() {
                System.out.println("\n--- Schedule Match ---");
                Team homeTeam = selectTeam();
                if (homeTeam != null) {
                        System.out.println("Select the away team:");
                        Team awayTeam = selectTeam();
                        if (awayTeam != null && !homeTeam.equals(awayTeam)) {
                                System.out.print("Enter match date (YYYY-MM-DD): ");
                                String date = scanner.nextLine();
                                System.out.print("Enter match time (HH:MM): ");
                                String time = scanner.nextLine();
                                System.out.print("Is this a tournament match? (y/n): ");
                                boolean isTournament = scanner.nextLine().equalsIgnoreCase("y");

                                Match match;
                                if (isTournament) {
                                        match = new TournamentMatch(homeTeam, awayTeam, date, time);
                                } else {
                                        match = new FriendlyMatch(homeTeam, awayTeam, date, time);
                                }
                                matches.add(match);
                                System.out.println("Match scheduled successfully.");
                        } else {
                                System.out.println("Invalid away team selection.");
                        }
                }
        }

        private static void updateMatchResult() {
                System.out.println("\n--- Update Match Result ---");
                displayMatches();
                System.out.print("Enter the match number to update: ");
                int matchIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                if (matchIndex >= 0 && matchIndex < matches.size()) {
                        Match match = matches.get(matchIndex);
                        System.out.print("Enter home team goals: ");
                        int homeGoals = scanner.nextInt();
                        System.out.print("Enter away team goals: ");
                        int awayGoals = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        match.updateMatchGoals(homeGoals, awayGoals);
                        leaderboard.updateLeaderboard();
                        System.out.println("Match result updated successfully.");
                } else {
                        System.out.println("Invalid match selection.");
                }
        }

        private static void displayMatches() {
                System.out.println("\n--- Matches ---");
                if (matches.isEmpty()) {
                        System.out.println("No matches have been scheduled yet.");
                } else {
                        for (int i = 0; i < matches.size(); i++) {
                                System.out.println((i + 1) + ". " + matches.get(i).displayInfo());
                        }
                }
        }


        private static void loadData() {
                teamList = CSVHandler.loadTeams();
                List<Player> players = CSVHandler.loadPlayers(teamList);
                for (Player player : players) {
                        player.getTeam().addPlayer(player);
                }
                matches = CSVHandler.loadMatches(teamList);
        }

        private static void saveData() {
                CSVHandler.saveTeams(teamList);
                List<Player> allPlayers = new ArrayList<>();
                for (Team team : teamList) {
                        allPlayers.addAll(team.getPlayers());
                }
                CSVHandler.savePlayers(allPlayers);
                CSVHandler.saveMatches(matches);
        }



        private static boolean dataFilesExist() {
                File teamsFile = new File(CSVHandler.TEAMS_FILE);
                File playersFile = new File(CSVHandler.PLAYERS_FILE);
                return teamsFile.exists() && playersFile.exists();
        }

        private static void saveInitialData() {
                List<Team> initialTeams = new ArrayList<>();
                List<Player> allPlayers = new ArrayList<>();

                // Create teams
                Team elites = new Team("ELITES FC", "Nkunim");
                Team kasanoma = new Team("KASANOMA FC", "Quota");
                Team redArmy = new Team("RED ARMY FC", "Tim Asare");
                Team legends = new Team("LEGENDS FC", "PY");
                Team highlanders = new Team("HIGHLANDERS FC", "Adom Ofori");
                Team northside = new Team("NORTHSIDE FC", "IcyDelly");

                initialTeams.add(elites);
                initialTeams.add(kasanoma);
                initialTeams.add(redArmy);
                initialTeams.add(legends);
                initialTeams.add(highlanders);
                initialTeams.add(northside);

                // Create players for each team
                createElitesPlayers(elites, allPlayers);
                createKasanomaPlayers(kasanoma, allPlayers);
                createRedArmyPlayers(redArmy, allPlayers);
                createLegendsPlayers(legends, allPlayers);
                createHighlandersPlayers(highlanders, allPlayers);

                // Save teams and players to CSV files
                CSVHandler.saveTeams(initialTeams);
                CSVHandler.savePlayers(allPlayers);

                System.out.println("Initial data saved to CSV files.");
        }

        private static void createElitesPlayers(Team elites, List<Player> allPlayers) {
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
                allPlayers.addAll(elitesPlayerList);
        }

        private static void createKasanomaPlayers(Team kasanoma, List<Player> allPlayers) {
                List<Player> kasanomaPlayerList = new ArrayList<>();
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
                allPlayers.addAll(kasanomaPlayerList);
        }

        private static void createRedArmyPlayers(Team redArmy, List<Player> allPlayers) {
                List<Player> redArmyPlayerList = new ArrayList<>();
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
                allPlayers.addAll(redArmyPlayerList);
        }

        private static void createLegendsPlayers(Team legends, List<Player> allPlayers) {
                List<Player> legendsPlayerList = new ArrayList<>();
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
                allPlayers.addAll(legendsPlayerList);
        }

        private static void createHighlandersPlayers(Team highlanders, List<Player> allPlayers) {
                List<Player> highlandersPlayerList = new ArrayList<>();
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
                allPlayers.addAll(highlandersPlayerList);
        }
}
