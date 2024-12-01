import java.util.List;

public class FriendlyMatch extends Match implements UpdateMatchInterface{
    private Team teamA;
    private Team teamB;// Friendly or tournament
    private String date;
    private String time;
    private String status; // played, not played, scheduled, postponed
    private int teamAGoals;
    private int teamBGoals;

    public FriendlyMatch(Team teamA, Team teamB, String date, String time) {
        super(teamA, teamB, date, time);
        this.status = "uncompleted";
        this.teamAGoals = 0;
        this.teamBGoals = 0;
    }

    public FriendlyMatch() {}// Default Constructor }

    @Override
    public void updateMatchGoals(int goalsA, int goalsB) {
        super.updateMatchGoals(goalsA, goalsB);
    }

    public void setStatus(String status) {
        super.setStatus(status);
    }

    public void postponeMatch(String date) { // change date of match
        super.postponeMatch(date);
    }

    public void updateMatch(Match match) {
        // should also update leaderboard
    }

//    public static void createFixtures(List<Team> teams){
//        createFixtures(teams);
//        // method for algorithm to create fixtures for the entire season
//    }
}
