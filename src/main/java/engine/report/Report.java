package engine.report;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.function.Supplier;

// Logs to the console (SLF4J) and records the action as a step in the Allure report.
// If the action fails, a screenshot is attached to THAT step before the error propagates.
public class Report {
    private static final Logger LOG = LoggerFactory.getLogger("engine");

    public static void step(String message, Runnable action, Supplier<byte[]> screenshot) {
        LOG.info(message);
        Allure.step(message, () -> {
            try {
                action.run();
            } catch (Throwable failure) {
                attach(screenshot);   // attaches to the current (failing) step
                throw failure;
            }
        });
    }

    private static void attach(Supplier<byte[]> screenshot) {
        try {
            byte[] png = screenshot.get();
            if (png != null && png.length > 0) {
                Allure.addAttachment("Screenshot", "image/png",
                        new ByteArrayInputStream(png), "png");
            }
        } catch (Exception ignored) {
            // never let screenshotting hide the real failure
        }
    }
}
