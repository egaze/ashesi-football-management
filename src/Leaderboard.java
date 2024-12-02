import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {
    private List<Team> teams;

    public Leaderboard(List<Team> teams) {
        this.teams = new ArrayList<>(teams);
    }

    public String displayLeaderboard() {
        sortTeams();
        StringBuilder leaderboard = new StringBuilder("Pos\t\t\tTeam\t\t\tP\tW\tD\tL\tGF\tGA\tGD\tPts\n");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            leaderboard.append(String.format("%d\t%14s\t\t\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n",
                    i + 1, team.getName(), team.getPlayed(), team.getWins(), team.getDraws(),
                    team.getLosses(), team.getGoalsFor(), team.getGoalAgainst(),
                    team.getGoalDifference(), team.getPoints()));
        }
        return leaderboard.toString();
    }

    public Team getTopTeam() {
        sortTeams();
        return teams.isEmpty() ? null : teams.get(0);
    }

    public void updateLeaderboard() {
        sortTeams();
    }

    private void sortTeams() {
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team a, Team b) {
                if (b.getPoints() != a.getPoints()) {
                    return b.getPoints() - a.getPoints();
                }
                if (b.getGoalDifference() != a.getGoalDifference()) {
                    return b.getGoalDifference() - a.getGoalDifference();
                }
                return b.getGoalsFor() - a.getGoalsFor();
            }
        });
    }
}