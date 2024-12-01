import java.util.ArrayList;
import java.util.List;

public class Team implements DisplayInfoInterface {
    private String name;
    private String coachName;
    private List<Player> players;
    private int goalsFor;
    private int goalAgainst;
    private int goalDifference;
    private int wins;
    private int losses;
    private int draws;
    private List<String> teamFixtures;
    private int points;

    public Team(String name, String coachName) {
        this.name = name;
        this.coachName = coachName;
        this.players = new ArrayList<>();
        this.goalsFor = 0;
        this.goalAgainst = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.goalDifference = 0;
        this.teamFixtures = new ArrayList<>();
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public void addWin() {
        this.wins++;
        this.points += 3;
    }

    public void addLoss() {
        this.losses++;
    }

    public void addDraw() {
        this.draws++;
        this.points += 1;
    }

    public String displayTeamFixtures() {
        return String.join("\n", teamFixtures);
    }

    public void addGoalsFor(int goals) {
        this.goalsFor += goals;
        updateGoalDifference();
    }

    public void addGoalAgainst(int goals) {
        this.goalAgainst += goals;
        updateGoalDifference();
    }

    private void updateGoalDifference() {
        this.goalDifference = this.goalsFor - this.goalAgainst;
    }

    public void setTeamPlayers(List<Player> players) {
        this.players = players;
    }

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

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getPlayed() {
        return wins + losses + draws;
    }

    public static void transfer(Team t1, Team t2, String transferPlayer) {
        Player player = t1.removePlayer(transferPlayer);
        if (player != null) {
            t2.addPlayer(player);
            player.setTeam(t2);
        }
    }

    public Player removePlayer(String playerName) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(playerName)) {
                return players.remove(i);
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void updatePoints() {
        this.points = (wins * 3) + draws;
    }

    @Override
    public String displayInfo() {
        return String.format("Team: %s, Coach: %s, Players: %d, Points: %d, GF: %d, GA: %d, GD: %d",
                name, coachName, players.size(), points, goalsFor, goalAgainst, goalDifference);
    }

    public String displayTeamList() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Team: %s\n", name));
        for (Player player : players) {
            sb.append(player.displayInfo()).append("\n");
        }
        return sb.toString();
    }

    public String toCSV() {
        return String.format("%s,%s,%d,%d,%d,%d,%d,%d,%d,%d",
                name, coachName, goalsFor, goalAgainst, goalDifference, wins, losses, draws, points, players.size());
    }

    public static Team fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Team team = new Team(parts[0], parts[1]);
        team.goalsFor = Integer.parseInt(parts[2]);
        team.goalAgainst = Integer.parseInt(parts[3]);
        team.goalDifference = Integer.parseInt(parts[4]);
        team.wins = Integer.parseInt(parts[5]);
        team.losses = Integer.parseInt(parts[6]);
        team.draws = Integer.parseInt(parts[7]);
        team.points = Integer.parseInt(parts[8]);
        return team;
    }

    public void addFixture(String fixture) {
        teamFixtures.add(fixture);
    }
}