import java.util.Random;

public class Player{
    private int currow;
    private int curcol;
    private String color;

    public Player(String color){
        this.color = color;
    }

    public int RollDice(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }
}
