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

        if(position >= n*n) currow = 0;
        else currow = n-1 - ((position-1)/n);

        if(position >= n*n) {
            curcol = 0;
        } else if((position / n)%2 == 1){
                if(position%n==0) curcol = 0;
                else curcol = n - (pos%n);
        }else{
            if(currow == n-1){
                curcol = Math.abs(position-n+currow);
            }else{
                if(position%n==0) curcol = n-1;
                else curcol = position%n-1;
            }
        }

    }

    public int RollDice(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }
}
