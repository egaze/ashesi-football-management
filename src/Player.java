public class Player implements DisplayInfoInterface {
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

    public void setTeam(Team team) {
        this.team = team;
    }


    @Override
    public String displayInfo() {
        return String.format("Name: %s, Age: %d, Jersey: %d, Position: %s, Team: %s",
                name, age, jerseyNumber, position, getTeamName());
    }
}
