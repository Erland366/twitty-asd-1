class Node<T>{
  T vert;
  public Node<T> prev,next;
  Node(T vert){
      this.vert=vert;
  }
}

public class LinkedList<T>{
  public Node<T> head,tail;
  int size;
  void addLast(T data){
      if(head==null){
          head=tail=new Node<>(data);
      }else{
          tail.next= new Node<>(data);
          tail=tail.next;
      }
      size++;
  }
}