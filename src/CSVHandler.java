import java.io.*;
import java.util.*;

public class CSVHandler {
    public static final String TEAMS_FILE = "teams.csv";
    public static final String PLAYERS_FILE = "players.csv";
    public static final String MATCHES_FILE = "matches.csv";

    public static void saveTeams(List<Team> teams) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEAMS_FILE))) {
            for (Team team : teams) {
                writer.println(team.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Team> loadTeams() {
        List<Team> teams = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TEAMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                teams.add(Team.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public static void savePlayers(List<Player> players) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PLAYERS_FILE))) {
            for (Player player : players) {
                writer.println(player.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Player> loadPlayers(List<Team> teams) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PLAYERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                players.add(Player.fromCSV(line, teams));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    public static void saveMatches(List<Match> matches) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MATCHES_FILE))) {
            for (Match match : matches) {
                writer.println(match.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Match> loadMatches(List<Team> teams) {
        List<Match> matches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MATCHES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                matches.add(Match.fromCSV(line, teams));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
}