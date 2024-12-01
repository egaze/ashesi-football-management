public class FriendlyMatch extends Match {
    public FriendlyMatch(Team teamA, Team teamB, String date, String time) {
        super(teamA, teamB, date, time);
    }

    @Override
    protected void updateTeamStats() {
        // Friendly matches don't affect team stats in the league
    }
}