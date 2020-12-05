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
    int max = 0;
    for (int i = 0; i < totalUser; i++) {
      if (userList[i].user.getTotalFollowers() > max) {
        max = userList[i].user.getTotalFollowers();
      }
    }
    int counter = 0;
    for (int i = 0; i < totalUser; i++) {
      if (userList[i].user.getTotalFollowers() == max) {
        followersTerbanyak[counter++] = userList[i].user.getName();
      }
    }
    sortingWord(followersTerbanyak);
    for (int i = 0; i < followersTerbanyak.length; i++) {
      if (followersTerbanyak[i] == null) {

      } else {
        System.out.print(followersTerbanyak[i]+",");
      }
    }
    System.out.println();
  }

  public int minimumRetweet(String username, String username2) {
    int indexUser = amGraph.getVertexIndex(username);
    int indexUser2 = amGraph.getVertexIndex(username2);

    User user1 = amGraph.getUserVertex(username);
    User user2 = amGraph.getUserVertex(username2);

    int c = amGraph.printAllPaths(indexUser2,indexUser);
    return c;
  }

  public int grouping() {
    int c = amGraph.connectedComponents();
    return c;
  }

  public String topicDetection() {
    String res="";
    int totalGroup = grouping();
    Vertex[] vertexList = amGraph.getVertexList();
    int[][] indexUserInGroup = amGraph.getListGroup();
    LinkedList[] llus = amGraph.ll;
    int[][] newIndexUserInGroup = new int[totalGroup][20];
    // for(int i=0;i<totalGroup;i++){
    // for(int j=0;j<20;j++)
    // }
    // int indexOf
    // int
    int x = 0;
    int y = 0;
    for (int i = 0; i < totalGroup; i++) {
      int length = llus.length;
      for (int j = 0; j < length; j++) {
        newIndexUserInGroup[i][j] = -1;
        if (llus[i].head != null) {
          newIndexUserInGroup[i][y] = (int) llus[i].head.vert;
          llus[i].head = llus[i].head.next;
          y++;
        } else {
        }
      }

      y = 0;
      x++;
    }

    GroupTopic[] gtList = new GroupTopic[totalGroup];
    for (int i = 0; i < totalGroup; i++) {
      gtList[i] = new GroupTopic();
      int jumlahMinatTerbanyak = 0;
      int cc = 0;
      String[] minatAll = new String[60];
      int[] totPerWord = new int[60];
      String minatAllUn[] = new String[60];
      int[] totPerWordUn = new int[60];
      int ler = 0;
      for (int j = 0; j < 20; j++) {
        int indexUser = newIndexUserInGroup[i][j];
        if (indexUser == -1) {
          break;
        }
        for (int k = 0; k < 3; k++) {
          minatAll[cc++] = vertexList[indexUser].user.minat[k];
        }

        sortingWord(minatAll);
        // System.out.println(Arrays.toString(minatAll));
        int le = 0;
        for (int k = 0; k < 60; k++) {
          if (minatAll[k] != null) {
            le++;
          }
        }
        int tog = 0;
        int ct = 1;
        for (int k = 0; k < le; k++) {
          boolean found = false;
          for (int l = k + 1; l < le; l++) {
            if (minatAll[l].equals(minatAll[k])) {
              found = true;
              ct++;
              break;
            }
          }

          if (!found) {

            minatAllUn[tog] = minatAll[k];
            totPerWordUn[tog] = ct;
            tog++;
            ct = 1;
          }
        }

      }
      for (int d = 0; d < minatAllUn.length; d++) {
        if (minatAllUn[d] != null) {
          ler++;
        }
      }

      String topicPalingBanyak[] = new String[ler];
      int max = 0;
      for (int d = 0; d < ler; d++) {
        if (totPerWordUn[d] > max) {
          max = totPerWordUn[d];
        }
      }
      int counter = 0;
      for (int d = 0; d < ler; d++) {
        if (totPerWordUn[d] == max) {
          topicPalingBanyak[counter++] = minatAllUn[d];
        }
      }
      for (int d = 0; d < topicPalingBanyak.length; d++) {
        if (topicPalingBanyak[d] == null) {

        } else {
          res += topicPalingBanyak[d]+" ";
        }
      }
      if(i==totalGroup-1){
        res+="";
      }else{
        res += "\n";
      }
    }

    return res;
  }

  void sortingWord(String arr[]) {
    int size = arr.length;

    for (int i = 0; i < size - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] == null) {
          break;
        }
        if (arr[i].compareTo(arr[j]) > 0) {
          String temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }
  }

}

class GroupTopic {
  public String topTopic[];
  int counter;

  public GroupTopic() {
    topTopic = new String[20];
  }

  public void addTopTopic(String newTopic) {
    topTopic[counter++] = newTopic;
  }
}