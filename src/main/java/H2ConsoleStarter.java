import org.h2.tools.Server;

public class H2ConsoleStarter {
    public static void main(String[] args) throws Exception {
        // Start H2 web console on port 8082
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("H2 console started at http://localhost:8082");
    }
}
