import java.util.ArrayList;
import java.util.List;

public abstract class Match implements UpdateMatchInterface {
    protected Team teamA;
    protected Team teamB;
    protected String date;
    protected String time;
    protected String status;
    protected int teamAGoals;
    protected int teamBGoals;

    public Match(Team teamA, Team teamB, String date, String time) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.date = date;
        this.time = time;
        this.status = "Scheduled";
        this.teamAGoals = 0;
        this.teamBGoals = 0;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void updateMatchGoals(int goalsA, int goalsB) {
        this.teamAGoals = goalsA;
        this.teamBGoals = goalsB;
        this.teamA.addGoalsFor(goalsA);
        this.teamA.addGoalAgainst(goalsB);
        this.teamB.addGoalsFor(goalsB);
        this.teamB.addGoalAgainst(goalsA);

        if (teamAGoals > teamBGoals) {
            this.teamA.addWin();
            this.teamB.addLoss();
        } else if (teamBGoals > teamAGoals) {
            this.teamB.addWin();
            this.teamA.addLoss();
        } else {
            this.teamA.addDraw();
            this.teamB.addDraw();
        }

        this.status = "Completed";
        updateTeamStats();
    }

    protected abstract void updateTeamStats();

    public void postponeMatch(String newDate) {
        this.date = newDate;
        this.status = "Postponed";
    }

    public String displayInfo() {
        return String.format("%s vs %s on %s at %s (%s)", teamA.getName(), teamB.getName(), date, time, status);
    }

    public static List<String> createFixtures(List<Team> teams) {
        List<String> fixtures = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team team1 = teams.get(i);
                Team team2 = teams.get(j);
                fixtures.add(team1.getName() + " vs " + team2.getName());
                fixtures.add(team2.getName() + " vs " + team1.getName());
            }
        }
        return fixtures;
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s,%d,%d,%s",
                teamA.getName(), teamB.getName(), date, time, status, teamAGoals, teamBGoals, getClass().getSimpleName());
    }

    public static Match fromCSV(String csvLine, List<Team> teams) {
        String[] parts = csvLine.split(",");
        Team teamA = teams.stream().filter(t -> t.getName().equals(parts[0])).findFirst().orElse(null);
        Team teamB = teams.stream().filter(t -> t.getName().equals(parts[1])).findFirst().orElse(null);
        Match match;
        if (parts[7].equals("FriendlyMatch")) {
            match = new FriendlyMatch(teamA, teamB, parts[2], parts[3]);
        } else {
            match = new TournamentMatch(teamA, teamB, parts[2], parts[3]);
        }
        match.setStatus(parts[4]);
        match.updateMatchGoals(Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
        return match;
    }
}