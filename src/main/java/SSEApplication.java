import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.EventSourceServlet;
import resources.ScoreResource;
import services.SSEEventSourceServlet;
import services.ScoreEventPublisher;

/**
 * @author arnab.ray
 * @created on 23/08/22
 */
public class SSEApplication extends Application<SSEConfiguration> {

    public static void main(String[] args) throws Exception {
        new SSEApplication().run(args);
    }

    @Override
    public String getName() {
        return "sse-poc";
    }

    @Override
    public void initialize(Bootstrap<SSEConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(SSEConfiguration configuration, Environment environment) {
        environment.jersey().register(new ScoreResource(ScoreEventPublisher.createInstance()));
        environment.servlets().addServlet("/sse", createServlet()).addMapping("/sse");
    }

    private EventSourceServlet createServlet() {
        return new SSEEventSourceServlet(ScoreEventPublisher.createInstance());
    }
}
