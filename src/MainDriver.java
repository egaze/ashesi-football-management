import java.util.ArrayList;
import java.util.List;

public class MainDriver { public static void main(String[] args) {
        // Create list of teams
        List<Team> teamList = new ArrayList<>();
        Team elites = new Team("ELITES FC", "Nkunim");
        Team kasanoma = new Team("KASANOMA FC", "Quota");
        Team redArmy = new Team("RED ARMY FC", "Tim Asare");
        Team legends = new Team("LEGENDS FC", "PY");
        Team highlanders = new Team("HIGHLANDERS FC", "Adom Ofori");
        Team northside = new Team("NORTHSIDE FC", "IcyDelly");

        teamList.add(elites);
        teamList.add(kasanoma);
        teamList.add(redArmy);
        teamList.add(legends);
        teamList.add(highlanders);
        teamList.add(northside);

        // Create list of elittesplayers
        List<Player> elitesPlayerList = new ArrayList<>();
        elitesPlayerList.add(new Player("Gaze", 20, 3, "LB", elites));
        elitesPlayerList.add(new Player("Kofi", 19, 30, "ST", elites));
        elitesPlayerList.add(new Player("Moses", 18, 13, "DM", elites));
        elitesPlayerList.add(new Player("David", 20, 10, "LW", elites));
        elitesPlayerList.add(new Player("Joseph", 20, 1, "RW", elites));
        elitesPlayerList.add(new Player("Gabriel", 20, 2, "RB", elites));
        elitesPlayerList.add(new Player("Francis", 18, 4, "CB", elites));
        elitesPlayerList.add(new Player("Maxwell", 20, 6, "GK", elites));
        elitesPlayerList.add(new Player("Jonathan", 16, 16, "LCM", elites));
        elitesPlayerList.add(new Player("John", 20, 19, "RCM", elites));
        elitesPlayerList.add(new Player("Bless", 20, 7, "CB", elites));
        elitesPlayerList.add(new Player("Eyiram", 20, 18, "LB", elites));
        elitesPlayerList.add(new Player("Enoch", 15, 9, "LCM", elites));
        elitesPlayerList.add(new Player("Jesimiel", 21, 14, "RCM", elites));
        elitesPlayerList.add(new Player("Lucky", 23, 51, "ST", elites));
        elitesPlayerList.add(new Player("Robert", 20, 20, "DM", elites));
        elitesPlayerList.add(new Player("Eric", 20, 55, "LB", elites));
        elites.setTeamPlayers(elitesPlayerList);

        // Create list of kasanomaplayers
        List<Player>  kasanomaPlayerList = new ArrayList<>();
        kasanomaPlayerList.add(new Player("Aaron", 20, 3, "LB", kasanoma));
        kasanomaPlayerList.add(new Player("Adam", 19, 30, "LW", kasanoma));
        kasanomaPlayerList.add(new Player("Adrian", 18, 13, "ST", kasanoma));
        kasanomaPlayerList.add(new Player("Alan", 20, 10, "DM", kasanoma));
        kasanomaPlayerList.add(new Player("Albert", 20, 1, "RW", kasanoma));
        kasanomaPlayerList.add(new Player("Alexander", 20, 2, "RB", kasanoma));
        kasanomaPlayerList.add(new Player("Andrew", 18, 4, "CB", kasanoma));
        kasanomaPlayerList.add(new Player("Anthony", 20, 6, "GK", kasanoma));
        kasanomaPlayerList.add(new Player("Arthur", 16, 16, "LCM", kasanoma));
        kasanomaPlayerList.add(new Player("Ashton", 20, 19, "RCM", kasanoma));
        kasanomaPlayerList.add(new Player("Austin", 20, 7, "CB", kasanoma));
        kasanomaPlayerList.add(new Player("Barry", 20, 18, "LB", kasanoma));
        kasanomaPlayerList.add(new Player("Benjamin", 15, 9, "LW", kasanoma));
        kasanomaPlayerList.add(new Player("Blake", 21, 14, "ST", kasanoma));
        kasanomaPlayerList.add(new Player("Bradley", 23, 51, "DM", kasanoma));
        kasanomaPlayerList.add(new Player("Brandon", 20, 20, "RW", kasanoma));
        kasanomaPlayerList.add(new Player("Brain", 20, 55, "RB", kasanoma));
        kasanoma.setTeamPlayers(kasanomaPlayerList);

        // Create list of redArmyplayers
        List<Player>  redArmyPlayerList = new ArrayList<>();
        redArmyPlayerList.add(new Player("Caleb", 20, 3, "LB", redArmy));
        redArmyPlayerList.add(new Player("Charles", 19, 30, "LW", redArmy));
        redArmyPlayerList.add(new Player("Christian", 18, 13, "ST", redArmy));
        redArmyPlayerList.add(new Player("Christopher", 20, 10, "DM", redArmy));
        redArmyPlayerList.add(new Player("Cody", 20, 1, "RW", redArmy));
        redArmyPlayerList.add(new Player("Colin", 20, 2, "RB", redArmy));
        redArmyPlayerList.add(new Player("Connor", 18, 4, "CB", redArmy));
        redArmyPlayerList.add(new Player("Daniel", 20, 6, "GK", redArmy));
        redArmyPlayerList.add(new Player("Dean", 16, 16, "LCM", redArmy));
        redArmyPlayerList.add(new Player("Derek", 20, 19, "RCM", redArmy));
        redArmyPlayerList.add(new Player("Dominic", 20, 7, "CB", redArmy));
        redArmyPlayerList.add(new Player("Donald", 20, 18, "LB", redArmy));
        redArmyPlayerList.add(new Player("Dylan", 15, 9, "LW", redArmy));
        redArmyPlayerList.add(new Player("Edward", 21, 14, "ST", redArmy));
        redArmyPlayerList.add(new Player("Elijah", 23, 51, "DM", redArmy));
        redArmyPlayerList.add(new Player("Elliot", 20, 20, "RW", redArmy));
        redArmyPlayerList.add(new Player("Erik", 20, 55, "RB", redArmy));
        redArmy.setTeamPlayers(redArmyPlayerList);

        // Create list of legendsArmyplayers
        List<Player>  legendsPlayerList = new ArrayList<>();
        legendsPlayerList.add(new Player("Ethan", 20, 3, "LB", legends));
        legendsPlayerList.add(new Player("Evan", 19, 30, "LW", legends));
        legendsPlayerList.add(new Player("Felix", 18, 13, "ST", legends));
        legendsPlayerList.add(new Player("Finley", 20, 10, "DM", legends));
        legendsPlayerList.add(new Player("Francis", 20, 1, "RW", legends));
        legendsPlayerList.add(new Player("Gabriel", 20, 2, "RB", legends));
        legendsPlayerList.add(new Player("George", 18, 4, "CB", legends));
        legendsPlayerList.add(new Player("Gerald", 20, 6, "GK", legends));
        legendsPlayerList.add(new Player("Graham", 16, 16, "LCM", legends));
        legendsPlayerList.add(new Player("Grant", 20, 19, "RCM", legends));
        legendsPlayerList.add(new Player("Gregory", 20, 7, "CB", legends));
        legendsPlayerList.add(new Player("Harry", 20, 18, "LB", legends));
        legendsPlayerList.add(new Player("Henry", 15, 9, "LW", legends));
        legendsPlayerList.add(new Player("Hugh", 21, 14, "ST", legends));
        legendsPlayerList.add(new Player("Ian", 23, 51, "DM", legends));
        legendsPlayerList.add(new Player("Isaac", 20, 20, "RW", legends));
        legendsPlayerList.add(new Player("Jack", 20, 55, "RB", legends));
        legends.setTeamPlayers(legendsPlayerList);


        // Create list of highlandersplayers
        List<Player>  highlandersPlayerList = new ArrayList<>();
        highlandersPlayerList.add(new Player("Ethan", 20, 3, "LB", highlanders));
        highlandersPlayerList.add(new Player("Evan", 19, 30, "LW", highlanders));
        highlandersPlayerList.add(new Player("Felix", 18, 13, "ST", highlanders));
        highlandersPlayerList.add(new Player("Finley", 20, 10, "DM", highlanders));
        highlandersPlayerList.add(new Player("Francis", 20, 1, "RW", highlanders));
        highlandersPlayerList.add(new Player("Gabriel", 20, 2, "RB", highlanders));
        highlandersPlayerList.add(new Player("George", 18, 4, "CB", highlanders));
        highlandersPlayerList.add(new Player("Gerald", 20, 6, "GK", highlanders));
        highlandersPlayerList.add(new Player("Graham", 16, 16, "LCM", highlanders));
        highlandersPlayerList.add(new Player("Grant", 20, 19, "RCM", highlanders));
        highlandersPlayerList.add(new Player("Gregory", 20, 7, "CB", highlanders));
        highlandersPlayerList.add(new Player("Harry", 20, 18, "LB", highlanders));
        highlandersPlayerList.add(new Player("Henry", 15, 9, "LW", highlanders));
        highlandersPlayerList.add(new Player("Hugh", 21, 14, "ST", highlanders));
        highlandersPlayerList.add(new Player("Ian", 23, 51, "DM", highlanders));
        highlandersPlayerList.add(new Player("Isaac", 20, 20, "RW", highlanders));
        highlandersPlayerList.add(new Player("Jack", 20, 55, "RB", highlanders));
        highlanders.setTeamPlayers(highlandersPlayerList);

        Match.createFixtures(teamList);
    }
}
