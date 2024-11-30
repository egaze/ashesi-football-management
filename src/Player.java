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

    public String getTeamName(){
        //  get the team the player is in from Team class

        return null;
    }

    public String displayInfo() {
        return null;
    }
}
