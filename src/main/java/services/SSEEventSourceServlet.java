package services;

import org.eclipse.jetty.servlets.EventSource;
import org.eclipse.jetty.servlets.EventSourceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author arnab.ray
 * @created on 23/08/22
 */
public class SSEEventSourceServlet extends EventSourceServlet {
    private static final Logger logger = LoggerFactory.getLogger(SSEEventSourceServlet.class);
    private final ScoreEventPublisher eventPublisher;

    public SSEEventSourceServlet(ScoreEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    protected EventSource newEventSource(HttpServletRequest request) {
        logger.info("SSEEventSourceServlet {}", request);
        SSEEventSource sseEventSource = new SSEEventSource();
        eventPublisher.subscribe(sseEventSource);
        return sseEventSource;
    }
}
