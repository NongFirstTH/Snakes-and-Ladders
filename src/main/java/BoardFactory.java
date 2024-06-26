import java.util.LinkedList;

public record BoardFactory() {
    public Board Board3Player() {
        int boardSize = 10;
        Player p1 = new Player("red", boardSize);
        Player p2 = new Player("blue", boardSize);
        Player p3 = new Player("green", boardSize);

        p1.setPosition(1);
        p2.setPosition(1);
        p3.setPosition(1);

        LinkedList<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        return new Board(boardSize, players);
    }
}