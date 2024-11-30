import java.util.List;
import java.util.Map;

public class Team implements DisplayInfoInterface{
    private String name;
    private String coachName;
    private List<Player> players;
    private int goalsFor;
    private int goalAgainst;
    private int goalDifference;
    private List<String> teamFixtures;
    private int points;
    private int numberOfTransfers;
    private Map<Team, List<Player>> teamMapping;

    public void displayTeamFixtures(){}

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
