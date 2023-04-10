import java.util.*;

public class Monopoly {

    public static void main(String[] args) {
        // 创建游戏板、玩家和骰子
        Board board = new Board();
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));
        Dice dice = new Dice();

        // 游戏循环
        while (true) {
            for (Player player : players) {
                // 玩家开始回合
                System.out.println(player.getName() + "'s turn.");
                dice.roll();
                System.out.println("Rolled " + dice.getValue() + ".");
                player.move(dice.getValue(), board);

                // 玩家可能需要支付房租或购买土地
                if (board.isProperty(player.getPosition())) {
                    Property property = board.getProperty(player.getPosition());
                    if (property.getOwner() == null) {
                        // 土地没有主人，玩家可以购买
                        if (player.getMoney() >= property.getPrice()) {
                            player.buyProperty(property);
                            System.out.println(player.getName() + " bought " + property.getName() + ".");
                        } else {
                            System.out.println(player.getName() + " can't afford " + property.getName() + ".");
                        }
                    } else if (property.getOwner() != player) {
                        // 玩家需要支付房租
                        int rent = property.getRent();
                        player.payRent(rent, property.getOwner());
                        System.out.println(player.getName() + " paid " + rent + " to " + property.getOwner().getName() + ".");
                    }
                }

                // 玩家可能需要停留在特殊格子上
                if (board.isSpecial(player.getPosition())) {
                    SpecialTile tile = board.getSpecialTile(player.getPosition());
                    tile.apply(player);
                }

                // 玩家的回合结束
                System.out.println(player.getName() + " has " + player.getMoney() + " dollars.");
                System.out.println();

                // 如果只剩下一名玩家，游戏结束
                if (players.size() == 1) {
                    Player winner = players.get(0);
                    System.out.println(winner.getName() + " wins!");
                    return;
                }
            }
        }
    }
}

class Board {
    private List<Tile> tiles;
    private List<Property> properties;
    private List<SpecialTile> specialTiles;

    public Board() {
        // 创建游戏板
        // ...
    }

    public boolean isProperty(int position) {
        // 判断一个位置是否为土地
        // ...
    }

    public Property getProperty(int position) {
        // 获取一个土地对象
        // ...
    }

    public boolean isSpecial(int position) {
        // 判断一个位置是否为特殊格子
        // ...
    }

    public SpecialTile getSpecialTile(int position) {
        // 获取一个特殊格子对象
        // ...
    }
}

class Tile {
    // 游戏板的一个普通格子
    // ...
}

class Property extends Tile {
    private String name;
    private int price;
    private int rent;
    private Player owner;

    public Property
