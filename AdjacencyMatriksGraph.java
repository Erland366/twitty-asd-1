import java.util.Arrays;

class Vertex {
  User user;
  boolean wasVisited;

  public Vertex(User usr) {
    this.user = usr;
    this.wasVisited = false;
  }
}

public class AdjacencyMatriksGraph {
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

  public User getUserVertex(String username) {
    User res = null;
    for (int i = 0; i < nVerts; i++) {
      if (username == vertexList[i].user.getName()) {
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
      if (userInList == user) {
        break;
      }
      res++;
    }
    return res;
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
    if (v==target) {
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

  public int printAllPaths(int s, int d) 
    { 
        boolean[] isVisited = new boolean[nVerts]; 
        Stack pathList = new Stack(); 
  
        // add source to path[] 
        pathList.push(s); 
  
        // Call recursive utility 
        DFSPath(s, d, isVisited, pathList); 
        return count-1;
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
