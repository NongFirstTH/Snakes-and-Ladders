import java.util.Random;

public class Player{
    public int position;
    public int currow;
    public int curcol;
    public String color;

    public Player(String color){
        this.color = color;
    }

    public void setPosition(int pos,int n){
        this.position = pos;
        currow = Math.abs(position-n+currow);
        curcol = position/n;
    }

    public int RollDice(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }
}
