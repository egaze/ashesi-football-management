import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Team implements DisplayInfoInterface{
    private String name;
    private String coachName;
    private List<Player> players;
    private int goalsFor;
    private int goalAgainst;
    private int goalDifference;
    private int wins;
    private int losses;
    private int draws;
    private List<String> teamFixtures;
    private int points;
    private int numberOfTransfers;
    private Map<Team, List<Player>> teamMapping;

    public Team(String name, String coachName) {
        this.name = name;
        this.coachName = coachName;
        this.players = new ArrayList<>();
        this.goalsFor = 0;
        this.goalAgainst = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.goalDifference = this.goalsFor - this.goalAgainst;
        this.teamFixtures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addWin(){
        this.wins++;
    }

    public void addLoss(){
        this.losses++;
    }

    public void addDraw(){
        this.draws++;
    }

    public void displayTeamFixtures(){}

    public void addGoalsFor(int goals) {
        this.goalsFor += goals;
    }

    public void addGoalAgainst(int goals) {
        this.goalAgainst += goals;
    }

    public void setTeamPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalAgainst() {
        return goalAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getPoints(){
        return points;
    }

    public static void transfer(Team t1, Team t2, String transferPlayer) {
        t1.removePlayer(transferPlayer);
        // method to transfer a person from one team to another
    }

    public Player removePlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                players.remove(player);
                return player; // Return the removed player
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }


    public String displayInfo() {
        return null;
    }

    public void displayTeamList(Team team) {}

    public static void removeTeam(Team team) {
        // Remove a team from the league
    }

    public static void addTeam(Team team) {
        // Add a team to the league
    }
}
