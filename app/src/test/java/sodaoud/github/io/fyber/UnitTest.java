package sodaoud.github.io.fyber;

import org.junit.Test;

import sodaoud.github.io.fyber.util.Util;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnitTest {

    @Test
    public void hashString() throws Exception {
        // Examples of sha1 from wikipedia
        assertEquals(Util.hashString("The quick brown fox jumps over the lazy dog"), "2fd4e1c67a2d28fced849ee1bb76e7391b93eb12");
        assertEquals(Util.hashString("The quick brown fox jumps over the lazy cog"), "de9f2c7fd25e1b3afad3e85a0bd17d9b100db4b3");
    }

}