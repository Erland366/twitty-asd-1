class Main {
  public static void main(String[] args) {
    Twitty twittyApp = new Twitty();
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
