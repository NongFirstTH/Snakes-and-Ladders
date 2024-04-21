import java.util.Random;

public class Player{
    public int currow;
    public int curcol;
    public String color;

    public Player(String color){
        this.color = color;
    }

    public int RollDice(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }
}
