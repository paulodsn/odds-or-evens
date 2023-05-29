package client.router;

public class RouterFactory {
  public static Router getView(RouterType viewType) {
    if (viewType == RouterType.MENU) {
      return new Menu();
    } else if (viewType == RouterType.ABOUT_GAME) {
      return new AboutGameView();
    }

    throw new Error("View not found");
  }
}
