

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws IOException {
    Twitty twittyApp = new Twitty();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // int user, connection, query, counter = 0;
    String u = br.readLine();
    String c = br.readLine();
    int user = Integer.parseInt(u);
    int connection = Integer.parseInt(c);
    LinkedList<String> res = new LinkedList<>();
    while (user-- > 0) {
      String[] query = br.readLine().toLowerCase().split(" ");
      twittyApp.insertUser(new User(query[0], query[1], query[2], query[3]));

    }

    while (connection-- > 0) {
      String[] cd = br.readLine().toLowerCase().split(" ");
      twittyApp.connectUser(cd[0], cd[1]);
    }
    while (true) {
      String command = br.readLine();
      if (command == null)
        break;
      else if (command.length() == 0)
        break;
      String[] com = command.toLowerCase().split(" ");
      switch (com[0]) {
        case "insert": {
          res.addLast(twittyApp.insertUser(new User(com[1], com[2], com[3], com[4])));
          break;
        }
        case "connect": {
          res.addLast(twittyApp.connectUser(com[1], com[2]));
          break;
        }
        case "mostfollowed": {
          res.addLast(twittyApp.mostFollowed());
          break;
        }
        case "minrt": {
          res.addLast(Integer.toString(twittyApp.minimumRetweet(com[1], com[2])));
          break;
        }
        case "numgroup": {
          res.addLast(twittyApp.grouping() + "");
          break;
        }
        case "grouptopic": {
          res.addLast(twittyApp.topicDetection());
          break;
        }
        default: {
          throw new Error("WRONG FORMAT");
        }
      }
    }
    // res.addLast(twittyApp.amGraph.seeGraphUnd());
    res.print();
    // User n1 = new User("dava", "bola", "mancing", "gunung");
    // User n2 = new User("diva", "mancing", "renang", "makan");
    // User n3 = new User("deva", "mancing", "renang", "makan");
    // User n4 = new User("devx", "mancing", "renang", "makan");
    // User n5 = new User("devz", "mancing", "renang", "makan");
    // twittyApp.insertUser(n1);
    // twittyApp.insertUser(n2);
    // twittyApp.insertUser(n3);
    // twittyApp.insertUser(n4);
    // twittyApp.insertUser(n5);
    // twittyApp.connectUser("dava", "diva");
    // twittyApp.connectUser("diva", "devx");
    // twittyApp.connectUser("dava", "deva");
    // twittyApp.connectUser("deva", "diva");
    // twittyApp.mostFollowed();
    // System.out.println(twittyApp.grouping());
    // // System.out.println(Arrays.deepToString(twittyApp.amGraph.getListGroup()));
    // System.out.println(twittyApp.topicDetection());
    // System.out.println(twittyApp.minimumRetweet("deva", "dava"));

  }
}

class Twitty {
  AdjacencyMatriksGraph amGraph;

  public Twitty() {
    amGraph = new AdjacencyMatriksGraph();
  }

  public String insertUser(User usr) {
    amGraph.addVertex(usr);
    return usr.getName() + "_inserted";
  }

  public String connectUser(String username, String username2) {
    int indexUser = amGraph.getVertexIndex(username);
    int indexUser2 = amGraph.getVertexIndex(username2);

    User user1 = amGraph.getUserVertex(username);
    User user2 = amGraph.getUserVertex(username2);
    user1.addFollowing(user2);
    user2.addFollowers(user1);

    amGraph.addEdge(indexUser, indexUser2);
    return ("connect_" + username + "_" + username2 + "_success");
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

  public String mostFollowed() {
    String res = "";
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
        if (followersTerbanyak[i + 1] == null) {
          res += followersTerbanyak[i] + "";
        } else {
          res += followersTerbanyak[i] + ",";
        }
      }
    }
    return res;
  }

  public int minimumRetweet(String korban, String target) {
    int count = -1;
    int Korban = amGraph.getVertexIndex(korban);
    boolean[] cek = new boolean[amGraph.getNVerts()];
    int a = amGraph.getCounter(Korban, cek, count, target);
    return a;
  }

  public int grouping() {
    int c = amGraph.connectedComponents();
    return c;
  }

  public String topicDetection() {
    String res = "";
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
          res += topicPalingBanyak[d] + " ";
        }
      }
      if (i == totalGroup - 1) {
        res += "";
      } else {
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

class Node<T> {
  T vert;
  public Node<T> prev, next;

  Node(T vert) {
    this.vert = vert;
  }
}

class LinkedList<T> {
  public Node<T> head, tail;
  int size;

  void addLast(T data) {
    if (head == null) {
      head = tail = new Node<>(data);
    } else {
      tail.next = new Node<>(data);
      tail = tail.next;
    }
    size++;
  }

  void print() {
    for (Node<T> p = head; p != null; p = p.next) {
      System.out.println(p.vert);
    }
  }
}

class Vertex {
  User user;
  boolean wasVisited;

  public Vertex(User usr) {
    this.user = usr;
    this.wasVisited = false;
  }
}

class AdjacencyMatriksGraph {
  private final int MAX_VERTS = 20;
  private Vertex vertexList[];
  private int adjMat[][];
  private int adjMatUndir[][];
  private int nVerts;
  private Stack theStack;
  private Queue theQueue;
  public int listGroup[][];
  public LinkedList<Integer>[] ll = new LinkedList[20];;
  private int temp;
  public int count;
  int counter = -1;

  public AdjacencyMatriksGraph() // constructor
  {
    vertexList = new Vertex[MAX_VERTS];
    // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    adjMatUndir = new int[MAX_VERTS][MAX_VERTS];
    listGroup = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    for (int j = 0; j < MAX_VERTS; j++) // set adjacency
    {
      for (int k = 0; k < MAX_VERTS; k++) // matrix to 0
      {
        adjMat[j][k] = 0;
        adjMatUndir[j][k] = 0;
      }
    }
    theStack = new Stack();
    theQueue = new Queue();
    temp = 0;
  }

  public String seeGraphUnd(){
    String a = "";
    for (int j = 0; j < MAX_VERTS; j++) // set adjacency
    {
      for (int k = 0; k < MAX_VERTS; k++) // matrix to 0
      {
        a += this.adjMatUndir[j][k] + " ";
      }
      a += "\n";
    }
    return a;
  }

  public String seeGraphDir(){
    String a = "";
    for (int j = 0; j < MAX_VERTS; j++) // set adjacency
    {
      for (int k = 0; k < MAX_VERTS; k++) // matrix to 0
      {
        a += this.adjMat[j][k] + " ";
      }
      a += "\n";
    }
    return a;
  }

  public User getUserVertex(String username) {
    User res = null;
    for (int i = 0; i < nVerts; i++) {
      if (username.equals(vertexList[i].user.getName())) {
        res = vertexList[i].user;
        break;
      }
    }
    return res;
  }

  public int getVertexIndex(String username) {
    User user = null;
    for (int i = 0; i < nVerts; i++) {
      if (username == vertexList[i].user.getName()) {
        user = vertexList[i].user;
        break;
      }
    }

    int res = 0;
    for (int i = 0; i < nVerts; i++) {
      User userInList = vertexList[i].user;
      if (userInList.getName().equals(username)) {
        break;
      }
      res++;
    }
    return res;
  }
  
  public int getCounter(int v, boolean[] visited, int count3,String target){
    DFS(v, visited, count3, target);
    return this.counter;
  }

  public void addVertex(User usr) {
    vertexList[nVerts++] = new Vertex(usr);
  }

  public void addEdge(int start, int end) {
    adjMat[start][end] = 1;
    adjMatUndir[start][end] = end;
    adjMatUndir[end][start] = start;
  }

  public int getAdjUnvisitedVertex(int v) {
    for (int j = 0; j < nVerts; j++) {
      if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
        return j;
      }
    }
    return -1;
  }

  public void DFSPath(int v, int target, boolean[] isVisited, Stack thes) {
    if (v == target) {
      // System.out.println(thes.peek());
      return;
    }
    isVisited[v] = true;
    for (int i = 0; i < nVerts; i++) {
      if (adjMat[v][i] == 1 && !isVisited[i]) {
        thes.push(adjMat[v][i]);
        DFSPath(i, target, isVisited, thes);
        count++;
        thes.pop();
      }
    }
    isVisited[v] = false;
  }

  public String dfs(int x) // depth-firstsearch
    { // beginatvertex0
      String a = "";
        vertexList[x].wasVisited = true; //karna dimulai dari node x maka wasVisited di set true (sudah dikunjungi)
        displayVertex(x); // cetak vertex awal
        theStack.push(x); // push vertex awal ke stack
        while (!theStack.isEmpty()) // pada awal while, stack berisi vertex awal. Dan looping tidak berhenti hingga stack kosong
        {
            int v = getAdjUnvisitedVertex(theStack.peek()); // memanggil methodnya, dimana pasti mengembalikan nilai integer atau -1
            if (v == -1) // cek jika tidak ada vertex lagi maka stack di pop
            {
                theStack.pop();
            } else// jika ternyata masih ada vertex
            {
                vertexList[v].wasVisited = true;
                a += this.getVertexList()[v].user.getName() + " ";
                theStack.push(v); // pushit
            }
        } // endwhile
        for (int j = 0; j < nVerts; j++) // reset flags
        {
            vertexList[j].wasVisited = false;
        }
        return a;
    }

  public int printAllPaths(int s, int d) {
    boolean[] isVisited = new boolean[nVerts];
    Stack pathList = new Stack();

    // add source to path[]
    pathList.push(s);

    // Call recursive utility
    DFSPath(s, d, isVisited, pathList);
    return count - 1;
  }

  public Vertex[] getVertexList() {
    return vertexList;
  }

  public int getNVerts() {
    return nVerts;
  }

  public int[][] getAdjMatUndir() {
    return adjMatUndir;
  }

  public void displayVertex(int v) {
    System.out.print(vertexList[v].user);
  }

  void DFSUtil(int v, boolean[] visited) {
    visited[v] = true;
    ll[temp].addLast(v);
    // Recur for all the vertices
    // adjacent to this vertex
    for (int i = 0; i < adjMatUndir[v].length; i++) {
      if (!visited[adjMatUndir[v][i]]) {
        DFSUtil(i, visited);
      }
    }
  }
  
  public void DFS(int v, boolean[] visited, int count3,String target){
    if(vertexList[v].user.getName().equals(target)){
      if(counter == -1 || counter > count3){
        this.counter = count3;
      }
    }else{
      visited[v]= true;
      count3++;
      for(int i = 0; i < nVerts; i++){
        if(adjMat[v][i] == 1 && !visited[i]){
          DFS(i, visited, count3, target);
        }
      }
    }
  }

  int connectedComponents() {
    // Mark all the vertices as not visited
    boolean[] visited = new boolean[nVerts];
    int count = 0;
    int s = 0;
    for (int i = 0; i < nVerts; ++i) {
      if (!visited[i]) {
        ll[temp] = new LinkedList<>();
        DFSUtil(i, visited);
        count++;
        temp++;
      }
    }
    return count;
  }

  int[][] getListGroup() {
    return listGroup;
  }

}

class Queue {

  private final int SIZE = 100000000;
  private int[] queArray;
  private int front;
  private int rear;

  public Queue() {
    queArray = new int[SIZE];
    front = 0;
    rear = -1;
  }

  public void insert(int j) {
    if (rear == SIZE - 1) {
      rear = -1;
    }
    queArray[++rear] = j;
  }

  public int remove() {
    int temp = queArray[front++];
    if (front == SIZE) {
      front = 0;
    }
    return temp;
  }

  public boolean isEmpty() {
    return (rear + 1 == front || (front + SIZE - 1 == rear));
  }
}

class Stack {

  private final int SIZE = 100000000;
  private int[] st;
  private int top;

  public Stack() // constructor
  {
    st = new int[SIZE]; // make array
    top = -1;
  }

  public void push(int j) // put item on stack
  {
    st[++top] = j;
  }

  public int pop() // take item off stack
  {
    return st[top--];

  }

  public int peek() // peek at top of stack
  {
    return st[top];

  }

  public boolean isEmpty() // true if nothing on stack-
  {
    return (top == -1);
  }
}

class User {
  private String name;
  private User followers[];
  private int totalFollowers;
  private User following[];
  private int totalFollowing;
  public String minat[];

  public User(String name, String minat1, String minat2, String minat3) {
    this.name = name;
    minat = new String[3];
    this.minat[0] = minat1;
    this.minat[1] = minat2;
    this.minat[2] = minat3;
    followers = new User[100];
    following = new User[100];
    totalFollowers = 0;
    totalFollowing = 0;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String[] minat() {
    return minat;
  }

  public int getTotalFollowers() {
    return totalFollowers;
  }

  public int getTotalFollowing() {
    return totalFollowing;
  }

  public void addFollowers(User user) {
    this.followers[totalFollowers++] = user;
  }

  public void addFollowing(User user) {
    this.following[totalFollowing++] = user;
  }

}
