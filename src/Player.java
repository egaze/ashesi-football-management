import java.util.List;

public class Player implements PlayerTeamInterface {
    private String name;
    private int age;
    private int jerseyNumber;
    private String position;
    private Team team;

    public Player(String name, int age, int jerseyNumber, String position, Team team) {
        this.name = name;
        this.age = age;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return team.getName();
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String displayInfo() {
        return String.format("Name: %s, Age: %d, Jersey: %d, Position: %s, Team: %s",
                name, age, jerseyNumber, position, team.getName());
    }

    public String toCSV() {
        return String.format("%s,%d,%d,%s,%s", name, age, jerseyNumber, position, team.getName());
    }

    public static Player fromCSV(String csvLine, List<Team> teams) {
        String[] parts = csvLine.split(",");
        Team team = teams.stream().filter(t -> t.getName().equals(parts[4])).findFirst().orElse(null);
        return new Player(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3], team);
    }
}
