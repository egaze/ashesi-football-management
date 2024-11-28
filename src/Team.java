import java.util.List;

public class Team {
    String name;
    String coachName;
    List<Player> players;
    int goalsFor;
    int goalAgainst;
    int goalDifference;
    List<String> fixtures;
    int points;

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
}
