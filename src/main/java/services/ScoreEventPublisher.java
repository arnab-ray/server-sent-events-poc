package services;

import models.Score;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author arnab.ray
 * @created on 23/08/22
 */
public class ScoreEventPublisher {
    private static ScoreEventPublisher instance = null;
    private static final Logger logger = LoggerFactory.getLogger(ScoreEventPublisher.class);
    private final List<SSEEventSource> listeners = new CopyOnWriteArrayList<>();

    private ScoreEventPublisher() {

    }

    public static ScoreEventPublisher createInstance() {
        if (instance == null) {
            instance = new ScoreEventPublisher();
        }

        return instance;
    }

    public void subscribe(SSEEventSource listener) {
        listeners.add(listener);
        logger.info("New one added, total consumer: {}", listeners.size());
    }

    public void unsubscribe(SSEEventSource listener) {
        listeners.remove(listener);
        logger.info("Removed listener, total consumer: {}", listeners.size());
    }

    public void publish(Score score) {
        logger.info("Processing score {}", score);
        listeners.forEach(listener -> {
            try {
                listener.emitEvent(score);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
