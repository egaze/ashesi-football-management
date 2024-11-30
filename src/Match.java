import java.util.List;

public abstract class Match implements UpdateMatchInterface{
    private Team teamA;
    private Team teamB;// Friendly or tournament
    private String date;
    private String time;
    private String status; // played, not played, scheduled, postponed
    private int teamAGoals;
    private int teamBGoals;

    public Match(Team teamA, Team teamB, String date, String time) {
        // remove matchType
        this.teamA = teamA;
        this.teamB = teamB;
        this.date = date;
        this.time = time;
        this.status = "uncompleted";
        this.teamAGoals = 0;
        this.teamBGoals = 0;
    }

    public Match() {}// Default Constructor }

    public void setTeamAGoals(int goals) {
        this.teamAGoals = goals;
    }

    public void setTeamBGoals(int goals) {
        this.teamBGoals = goals;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void updateMatch(Match match) {
        // update match details and make operations on leaderboard
    }

    public void postponeMatch(String date) { // change date of match
        this.date = date;
        this.status = "postponed";
    }

    public void createFixtures(List<Team> teams){
        // method for algorithm to create fixtures for the entire season
    }
}
