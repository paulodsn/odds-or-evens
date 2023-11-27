package client.util;

public class Response {
    private String id;
    private String message;

    public Response(String response) {
        String[] parts = response.split(";");

        this.id = parts[0];
        this.message = parts[1];
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMessage(String response) {
        this.message = response;
    }

    public String getMessage() {
        return message;
    }
}
