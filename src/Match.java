import java.util.ArrayList;
import java.util.List;

public abstract class Match implements UpdateMatchInterface{
    private Team teamA;
    private Team teamB;// Friendly or tournament
    private String date;
    private String time;
    private String status; // played, not played, scheduled, postponed
    private int teamAMatchGoals;
    private int teamBMatchGoals;

    public Match(Team teamA, Team teamB, String date, String time) {
        // remove matchType
        this.teamA = teamA;
        this.teamB = teamB;
        this.date = date;
        this.time = time;
        this.status = "uncompleted";
        this.teamAMatchGoals = 0;
        this.teamBMatchGoals = 0;
    }

    public Match() {}// Default Constructor }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void updateMatchGoals(int goalsA, int goalsB) {
        this.teamAMatchGoals = goalsA;
        this.teamBMatchGoals = goalsB;
        this.teamA.addGoalsFor(goalsA);
        this.teamA.addGoalAgainst(goalsB);
        this.teamB.addGoalsFor(goalsB);
        this.teamB.addGoalAgainst(goalsA);

        if (teamAMatchGoals > teamBMatchGoals) {
            this.teamA.addWin();
            this.teamB.addLoss();}
        else if (teamBMatchGoals > teamAMatchGoals) {
            this.teamB.addWin();
            this.teamA.addLoss();
            }
        else if (teamBMatchGoals == teamAMatchGoals) {
            this.teamB.addDraw();
            this.teamA.addDraw();
        }
        // update match details and make operations on leaderboard
    }

    public void postponeMatch(String date) { // change date of match
        this.date = date;
        this.status = "postponed";
    }


    public static List<String> createFixtures(List<Team> teams) {
        boolean isOdd = teams.size() % 2 != 0;
        if (isOdd) { teams.add(new Team("DUMMY", "N/A")); }

        List<String> fixtures = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team team1 = teams.get(i);
                Team team2 = teams.get(j);

                if (!(team1.getName().equals("DUMMY")) && !(team2.getName().equals("DUMMY"))) {
                    fixtures.add(team1.getName() + " vs " + team2.getName());
                    fixtures.add(team2.getName() + " vs " + team1.getName());
                }
            }
        }

        // Display all fixtures
        System.out.println("Fixtures:");
        fixtures.forEach(x -> System.out.println(x));

        return fixtures;
    }
}
