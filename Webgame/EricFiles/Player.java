public class Player{

  private int health = 100;
  private int x;
  private int y;
  private int gold = 0;

  public Player(){

  }
  public Player(int h, int x1, int y1, int g){
    health = h;
    x = x1;
    y = y1;
    gold = g;
  }

  private int getX(){
    return x;
  }
  private void setX(int num){
    x = num;
  }
  private int getY(){
    return y;
  }
  private void setY(int num){
    y = num;
  }
  private int getGold(){
    return gold;
  }
  private void setGold(int num){
    gold = num;
  }
  private int getHealth(){
    return health;
  }
  private void setHealth(int num){
    health = num;
  }
}
