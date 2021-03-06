package faxel.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.FaxelException;

class Try {
    private static Logger LOG = LoggerFactory.getLogger(SheetDefinition.class);

    static void onFailureThrowRuntimeException(Tryable r, String message, Object ... args) {
        try {
            r.tryRun();
        } catch (Throwable cause) {
            final String formattedMessage = String.format(message, args);
            LOG.error("Failed to execute Try {}. {}", cause, formattedMessage);
            throw new FaxelException(cause, formattedMessage);
        }
    }

    interface Tryable {
        void tryRun() throws Throwable;
    }

}
