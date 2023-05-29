package client.router;

public enum RouterType {
  MENU(1),
  ABOUT_GAME(2);

  private final int type;

  RouterType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }
}
