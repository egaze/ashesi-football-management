import java.util.List;

public class TournamentMatch extends Match implements UpdateMatchInterface{
    private Team teamA;
    private Team teamB;// Friendly or tournament
    private String date;
    private String time;
    private String status; // played, not played, scheduled, postponed
    private int teamAGoals;
    private int teamBGoals;

    public TournamentMatch(Team teamA, Team teamB, String date, String time) {
        // remove matchType
        super(teamA, teamB, date, time);
        this.status = "uncompleted";
        this.teamAGoals = 0;
        this.teamBGoals = 0;
    }

    public TournamentMatch() {}// Default Constructor


    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public void updateMatchGoals(int goalsA, int goalsB) {
        super.updateMatchGoals(goalsA, goalsB);
        // update match details and make operations on leaderboard
    }

    public void postponeMatch(String date) { // change date of match
        super.postponeMatch(date);
    }

//    public static void createFixtures(List<Team> teams){
//        createFixtures(teams);
//        // method for algorithm to create fixtures for the entire season
//    }
}
