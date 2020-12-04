class Main {
  public static void main(String[] args) {
    Twitty twittyApp = new Twitty();
    User n1 = new User("dava", "bola", "mancing", "gunung");
    User n2 = new User("diva", "mancing", "renang", "makan");
    twittyApp.insertUser(n1);
    twittyApp.insertUser(n2);
    twittyApp.connectUser("dava", "diva");

  }
}
