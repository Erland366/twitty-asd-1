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

  public AdjacencyMatriksGraph() // constructor
  {
    vertexList = new Vertex[MAX_VERTS];
    // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    adjMatUndir = new int[MAX_VERTS][MAX_VERTS];
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
    adjMatUndir[start][end] = 1;
    adjMatUndir[end][start] = 1;
  }

  public int getAdjUnvisitedVertex(int v) {
    for (int j = 0; j < nVerts; j++) {
      if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
        return j;
      }
    }
    return -1;
  }

  public void dfs(int x) {
    vertexList[x].wasVisited = true; // karna dimulai dari node x maka wasVisited di set true (sudah dikunjungi)
    theStack.push(x); // push vertex awal ke stack
    while (!theStack.isEmpty()) // pada awal while, stack berisi vertex awal. Dan looping tidak berhenti hingga
                                // stack kosong
    {
      int v = getAdjUnvisitedVertex(theStack.peek()); // memanggil methodnya, dimana pasti mengembalikan nilai integer
                                                      // atau -1
      if (v == -1) // cek jika tidak ada vertex lagi maka stack di pop
      {
        theStack.pop();
      } else// jika ternyata masih ada vertex
      {
        vertexList[v].wasVisited = true;
        displayVertex(v); // displayit
        theStack.push(v); // pushit
      }
    } // endwhile
    for (int j = 0; j < nVerts; j++) // reset flags
    {
      vertexList[j].wasVisited = false;
    }
  }

  public void bfs(int x) {

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

}
