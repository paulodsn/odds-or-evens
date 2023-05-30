package server.router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RouterFactory {
  private Map<String, Router> routes = new HashMap<>();

  public RouterFactory() {
    routes.put("/welcome", new Welcome());
  }

  public void handle(String request) throws IOException {
    String route = extractPath(request);
    String payload = extractPayload(request);

    Router router = this.routes.get(route);

    if (router != null) {
      router.handle(payload);
    } else {
      throw new Error("Router not found");
    }
  }

  public static String extractPath(String request) {
    String[] parts = request.split(" ");
    if (parts.length >= 2) {
      return parts[0];
    }

    return "/";
  }

  public static String extractPayload(String request) {
    String[] parts = request.split(" ");
    if (parts.length >= 2) {
      return parts[1];
    }

    return "";
  }
}
