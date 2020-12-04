class Twitty {
  AdjacencyMatriksGraph amGraph;

  public Twitty() {
    amGraph = new AdjacencyMatriksGraph();
  }

  /*
   * TODO: INSERT USER [V] TODO: CONNECTIONG USER [] TODO: MOST FOLLOWED [] TODO:
   * MINIMUM RETWEET [] TODO: GROUPING [] TODO: TOPIC DETECTION []
   * 
   */

  /*
   * Penggunaan method insertUser melibatkan method addVertex pada graph
   * 
   */
  public void insertUser(User usr) {
    amGraph.addVertex(usr);
    System.out.println(usr.getName() + "_inserted");
  }

  public void connectUser(String username, String username2) {
    int indexUser = amGraph.getVertexIndex(username);
    int indexUser2 = amGraph.getVertexIndex(username2);

    amGraph.addEdge(indexUser, indexUser2);
    System.out.println("connect_" + username + "_" + username2 + "_success");
  }

  public Vertex[] getAllUser() {
    return amGraph.getVertexList();
  }

  public int getTotalUser() {
    return amGraph.getNVerts();
  }

  public int getFollowerOfUser(User usr) {
    return usr.getTotalFollowers();
  }

  public void mostFollowed() {
    Vertex[] userList = getAllUser();
    int totalUser = getTotalUser();
    for (int i = 0; i < totalUser; i++) {

    }
  }

  public void minimumRetweet() {

  }

  public void grouping() {

  }

  public void topicDetection() {

  }

}

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
