package listeners;

import engine.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

// Manages the Allure report lifecycle around a run:
//  - before all tests: wipe old results so the report shows only THIS run
//  - after all tests:  build + serve the report and open it in the browser
public class ReportServer implements IExecutionListener {
    private static final Logger LOG = LoggerFactory.getLogger(ReportServer.class);
    private static final Path RESULTS = Path.of("target", "allure-results");

    @Override
    public void onExecutionStart() {
        deleteRecursively(RESULTS);
    }

    @Override
    public void onExecutionFinish() {
        if (!Config.autoServeReport()) {
            return;
        }
        if (!System.getProperty("os.name", "").toLowerCase().contains("win")) {
            LOG.info("Auto-serve skipped (non-Windows). Run it with:  mvn allure:serve");
            return;
        }
        try {
            LOG.info("Opening the Allure report in your browser...");
            // 'start' launches mvn in a new, detached window so it keeps serving
            // after the test process exits.
            new ProcessBuilder("cmd", "/c", "start \"\" mvn io.qameta.allure:allure-maven:serve")
                    .directory(new File(System.getProperty("user.dir")))
                    .start();
        } catch (Exception e) {
            LOG.warn("Could not open the report automatically: {}", e.getMessage());
            LOG.warn("Run it yourself with:  mvn allure:serve");
        }
    }

    // Delete a folder and everything inside it (no-op if it doesn't exist).
    private void deleteRecursively(Path dir) {
        if (!Files.exists(dir)) {
            return;
        }
        try (var paths = Files.walk(dir)) {
            paths.sorted(Comparator.reverseOrder()).forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException ignored) {
                    // best-effort cleanup; ignore locked/temporary files
                }
            });
        } catch (IOException e) {
            LOG.warn("Could not clear old results in {}: {}", dir, e.getMessage());
        }
    }
}
