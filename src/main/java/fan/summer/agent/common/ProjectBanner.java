package fan.summer.agent.common;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * 类的详细说明
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/19
 */
public class ProjectBanner implements Banner {
    private static final String v =
            " _    _  __  __                           _        \n" +
                    "| |  | ||  \\/  |                         | |       \n" +
                    "| |__| || \\  / | ___  _ __ ___   ___  ___| |_ __ _ \n" +
                    "|  __  || |\\/| |/ _ \\| '_ ` _ \\ / _ \\/ __| __/ _` |\n" +
                    "| |  | || |  | | (_) | | | | | |  __/ (__| || (_| |\n" +
                    "|_|  |_||_|  |_|\\___/|_| |_| |_|\\___|\\___|\\__\\__,_|\n" +
                    "                                                  \n" +
                    "                    HMoneta-Agent (ver)               \n";
    private static final String BANNER =
            " _   _   __  __                          _                        _                             _   \n" +
                    "| | | | |  \\/  |   ___    _ __     ___  | |_    __ _             / \\      __ _    ___   _ __   | |_ \n" +
                    "| |_| | | |\\/| |  / _ \\  | '_ \\   / _ \\ | __|  / _` |  _____    / _ \\    / _` |  / _ \\ | '_ \\  | __|\n" +
                    "|  _  | | |  | | | (_) | | | | | |  __/ | |_  | (_| | |_____|  / ___ \\  | (_| | |  __/ | | | | | |_ \n" +
                    "|_| |_| |_|  |_|  \\___/  |_| |_|  \\___|  \\__|  \\__,_|         /_/   \\_\\  \\__, |  \\___| |_| |_|  \\__|\n" +
                    "                                                                          |___/                      \n" +
                    "                    HMoneta-Agent (ver)               \n";

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        String version = environment.getProperty("agent.version");
        assert version != null;
        String banner = BANNER.replace("ver", version);
        out.print(banner);
    }
}
