import java.util.List;

public class Team implements DisplayInfoInterface{
    String name;
    String coachName;
    List<Player> players;
    int goalsFor;
    int goalAgainst;
    int goalDifference;
    List<String> fixtures;
    int points;
    int numberOfTransfers;
    // Map<Team, Players>

    public String displayTeamFixtures(){return null;}

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

    public static void transfer(Team t1, Team t2) {
        // method to transfer a person from one team to another
    }

    public void removePlayer(Player player) {

    }

    public void addPlayer(Player player) {}

    public String displayInfo() {
        return null;
    }

    public void displayTeamList(Team team) {}
}
