import java.util.List;

public class Matches {
    Team teamA;
    Team teamB;
    String matchType; // Friendly or tournament
    String date;
    String time;
    String status; // played, not played, scheduled, postponed
    int teamAGoals;
    int teamBGoals;

    public Matches(Team teamA, Team teamB, String matchType, String date, String time) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.matchType = matchType;
        this.date = date;
        this.time = time;
        this.status = "uncompleted";
        this.teamAGoals = 0;
        this.teamBGoals = 0;
    }

    public Matches() {}// Default Constructor }

    public void setTeamAGoals(int teamAGoals) {
        this.teamAGoals = teamAGoals;
    }

    public void setTeamBGoals(int teamBGoals) {
        this.teamBGoals = teamBGoals;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void postponeMatch(String date) { // change date of match
        this.date = date;
    }

    public void updateMatch(Matches match) {
        // should also update leaderboard
    }

    public void createFixtures(List<Team> teams){
        // method for algorithm to create fixtures for the entire season
    }
}
