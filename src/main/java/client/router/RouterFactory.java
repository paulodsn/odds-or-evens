package client.router;

import java.util.HashMap;
import java.util.Map;

public class RouterFactory {
  private Map<RouterType, Router> routes = new HashMap<>();

  public RouterFactory() {
    routes.put(RouterType.MENU, new Menu());
    routes.put(RouterType.ABOUT_GAME, new AboutGameView());
    routes.put(RouterType.GAME, new Game());
  }

  public void handle(RouterType routerType) {
    Router router = this.routes.get(routerType);

    if (router != null) {
      router.display();
      router.handleChoice();
    } else {
      throw new Error("Router not found");
    }
  }
}
