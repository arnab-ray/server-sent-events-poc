package services;

import models.Score;
import org.eclipse.jetty.servlets.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author arnab.ray
 * @created on 23/08/22
 */
public class SSEEventSource implements EventSource {
    private static final Logger logger = LoggerFactory.getLogger(SSEEventSource.class);
    private Emitter emitter;
    private final String id;

    public SSEEventSource() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void onOpen(Emitter emitter) throws IOException {
        logger.info("onOpen {}", emitter);
        this.emitter = emitter;
    }

    @Override
    public void onClose() {
        logger.info("onClose");
        ScoreEventPublisher.createInstance().unsubscribe(this);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SSEEventSource) {
            SSEEventSource that = (SSEEventSource)obj;
            return Objects.equals(this.id, that.id);
        }
        return false;
    }

    public void emitEvent(Score score) throws IOException {
        logger.info("emitEvent {}", score);
        this.emitter.data(score.toString());
    }
}
