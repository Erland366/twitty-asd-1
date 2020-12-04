import java.util.Arrays;

class Twitty {
  AdjacencyMatriksGraph amGraph;

  public Twitty() {
    amGraph = new AdjacencyMatriksGraph();
  }

  public void insertUser(User usr) {
    amGraph.addVertex(usr);
    System.out.println(usr.getName() + "_inserted");
  }

  public void connectUser(String username, String username2) {
    int indexUser = amGraph.getVertexIndex(username);
    int indexUser2 = amGraph.getVertexIndex(username2);

    User user1 = amGraph.getUserVertex(username);
    User user2 = amGraph.getUserVertex(username2);

    user1.addFollowing(user2);
    user2.addFollowers(user1);
    
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
    String followersTerbanyak[] = new String[totalUser];
    int max=0;
    for (int i = 0; i < totalUser; i++) {
      if(userList[i].user.getTotalFollowers()>max){
        max = userList[i].user.getTotalFollowers();
      }
    }
    int counter = 0;
    for (int i = 0; i < totalUser; i++) {
      if(userList[i].user.getTotalFollowers()==max){
        followersTerbanyak[counter] = userList[i].user.getName();
        counter++;
      }
    }
    System.out.println(Arrays.toString(followersTerbanyak));
  }

  public void minimumRetweet() {

  }

  public void grouping() {

  }

  public void topicDetection() {

  }

}