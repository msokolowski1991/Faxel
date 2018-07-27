package faxel.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Try {
    private static Logger LOG = LoggerFactory.getLogger(SheetDefinition.class);

    static void silently(Tryable r) {
        try {
            r.tryRun();
        } catch (Throwable cause) {
            LOG.error("Failed to execute Try", cause);
        }
    }

    interface Tryable {
        void tryRun() throws Throwable;
    }
}
