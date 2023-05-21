import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {
    private static final long DURATION = 22;
    @Test
    @Timeout(DURATION)
    @Disabled
    void checkMainDuration() throws Exception {
            Main.main(new String[0]);
    }
}