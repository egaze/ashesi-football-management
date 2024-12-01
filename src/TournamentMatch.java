public class TournamentMatch extends Match {
    public TournamentMatch(Team teamA, Team teamB, String date, String time) {
        super(teamA, teamB, date, time);
    }

    @Override
    protected void updateTeamStats() {
        teamA.updatePoints();
        teamB.updatePoints();
    }
}