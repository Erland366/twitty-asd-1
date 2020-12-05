import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Main {
  public static void main(String[] args) {
    Twitty twittyApp = new Twitty();
    User n1 = new User("dava", "bola", "mancing", "gunung");
    User n2 = new User("diva", "mancing", "renang", "makan");
    User n3 = new User("deva", "mancing", "renang", "makan");
    User n4 = new User("devx", "mancing", "renang", "makan");
    User n5 = new User("devz", "mancing", "renang", "makan");
    twittyApp.insertUser(n1);
    twittyApp.insertUser(n2);
    twittyApp.insertUser(n3);
    twittyApp.insertUser(n4);
    twittyApp.insertUser(n5);
    twittyApp.connectUser("dava", "diva");
    twittyApp.connectUser("diva", "devx");
    twittyApp.connectUser("dava", "deva");
    twittyApp.connectUser("deva", "diva");
    twittyApp.mostFollowed();
    System.out.println(twittyApp.grouping());
    // System.out.println(Arrays.deepToString(twittyApp.amGraph.getListGroup()));
    System.out.println(twittyApp.topicDetection());
    System.out.println(twittyApp.minimumRetweet("deva","dava"));
    
    

  }
}
