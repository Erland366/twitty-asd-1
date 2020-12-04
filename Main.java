import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int jumlahPengguna = in.nextInt();
    int jumlahConnection = in.nextInt();
    Twitty twittyApp = new Twitty();

    while(jumlahPengguna --> 0){
      String nama = in.next();
      String hobi1 = in.next();
      String hobi2 = in.next();
      String hobi3 = in.next();
      User user = new User(nama, hobi1, hobi2, hobi3);
      twittyApp.insertUser(user);
    }
    System.out.println(twittyApp.getAllUser().);

    while(jumlahConnection --> 0){
      String konek1 = in.next();
      String konek2 = in.next();
      twittyApp.connectUser(konek1, konek2);
    }
    
    
    // User n1 = new User("dava", "bola", "mancing", "gunung");
    // User n2 = new User("diva", "mancing", "renang", "makan");
    // twittyApp.insertUser(n1);
    // twittyApp.insertUser(n2);
    // twittyApp.connectUser("dava", "diva");

=======
    User n1 = new User("dava", "bola", "mancing", "gunung");
    User n2 = new User("diva", "mancing", "renang", "makan");
    User n3 = new User("deva", "mancing", "renang", "makan");
    twittyApp.insertUser(n1);
    twittyApp.insertUser(n2);
    twittyApp.insertUser(n3);
    twittyApp.connectUser("dava", "diva");
    twittyApp.connectUser("diva", "dava");
    twittyApp.connectUser("deva", "dava");
    twittyApp.mostFollowed();

  }
}
