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

  public void addFollowers(User user){
    this.followers[totalFollowers++] = user;
  }

  public void addFollowing(User user){
    this.following[totalFollowing++] = user;
  }

}