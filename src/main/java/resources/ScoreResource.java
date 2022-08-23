package resources;

import com.codahale.metrics.annotation.Timed;
import models.Score;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ScoreEventPublisher;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author arnab.ray
 * @created on 23/08/22
 */
@Path("/v1/score")
@Produces(MediaType.APPLICATION_JSON)
public class ScoreResource {
    private static final Logger logger = LoggerFactory.getLogger(ScoreResource.class);
    private final ScoreEventPublisher scoreEventPublisher;

    public ScoreResource(ScoreEventPublisher scoreEventPublisher) {
        this.scoreEventPublisher = scoreEventPublisher;
    }

    @POST
    @Path("/live-scores")
    @Timed
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Response trigger(Score score) {
        logger.info("score: {}", score);
        scoreEventPublisher.publish(score);
        return Response.ok().build();
    }
}
