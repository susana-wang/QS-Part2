
package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class GetPrefixLengthTest {

    // Statement Coverage — S1 to S16 visit every node in the CFG
    @Test
    void s1_nullInput() {
        assertEquals(-1, FilenameUtils.getPrefixLength(null));
    }

    @Test
    void s2_emptyString() {
        assertEquals(0, FilenameUtils.getPrefixLength(""));
    }

    @Test
    void s3_colonOnly() {
        assertEquals(-1, FilenameUtils.getPrefixLength(":"));
    }

    @Test
    void s4_tildeAlone() {
        assertEquals(2, FilenameUtils.getPrefixLength("~"));
    }

    @Test
    void s5_singleSlash() {
        assertEquals(1, FilenameUtils.getPrefixLength("/"));
    }

    @Test
    void s6_tildeWithPath() {
        assertEquals(2, FilenameUtils.getPrefixLength("~/docs"));
    }

    @Test
    void s7_tildeNoSeparator() {
        assertEquals(7, FilenameUtils.getPrefixLength("~alone"));
    }

    @Test
    void s8_windowsDriveWithSep() {
        // MC/DC N17: ch2=sep → !isSep(ch2)=F, len=3 → len==2=F → N17=F → return 3
        assertEquals(3, FilenameUtils.getPrefixLength("C:\\"));
    }

    @Test
    void s9_windowsDriveNoSep() {
        // On non-Windows systems, supportsDriveLetter() returns false,
        // so the function returns 0 instead of 2.
        // This branch is OS-dependent — documented in report.
        int result = FilenameUtils.getPrefixLength("C:");
        assertTrue(result == 0 || result == 2,
                "Expected 0 (non-Windows) or 2 (Windows), got: " + result);
    }

    @Test
    void s10_invalidDrivePrefix() {
        assertEquals(-1, FilenameUtils.getPrefixLength("1:"));
    }

    @Test
    void s11_uncPathValid() {
        // MC/DC N21: ch0=sep, ch1=sep → !isSep(ch0)||!isSep(ch1)=F → enters UNC block
        int result = FilenameUtils.getPrefixLength("\\\\server\\x");
        assertTrue(result > 0);
    }

    @Test
    void s12_uncPathNoSep() {
        assertEquals(-1, FilenameUtils.getPrefixLength("\\\\a"));
    }

    @Test
    void s13_plainFilename() {
        assertEquals(0, FilenameUtils.getPrefixLength("foo"));
    }

    @Test
    void s14_singleLetterNotSep() {
        // Decision coverage: N10 isSeparator=false branch
        assertEquals(0, FilenameUtils.getPrefixLength("a"));
    }

    @Test
    void s15_slashThenLetter() {
        // MC/DC N21: ch0=sep=T, ch1=not-sep=F → !isSep(ch0)||!isSep(ch1)=T → skips UNC block
        // Condition coverage: isSep(ch1)=false
        assertEquals(1, FilenameUtils.getPrefixLength("/a"));
    }

    @Test
    void s16_driveLetterNonSepChar() {
        int result = FilenameUtils.getPrefixLength("C:a");
        assertTrue(result == 0 || result == 2,
                "Expected 0 (non-Windows) or 2 (Windows), got: " + result);
    }

    @Test
    void s17_unixSepAsDrivePrefix() {
        // Hits ch0 == UNIX_NAME_SEPARATOR branch inside ch1==':' block
        assertEquals(1, FilenameUtils.getPrefixLength("/:"));
    }

    @Test
    void s18_uncValidHostname() {
        // Hits isValidHostName=true branch
        int result = FilenameUtils.getPrefixLength("\\\\server\\share");
        assertTrue(result > 0);
    }

    @Test
    void s19_uncInvalidHostname() {
        // Hits isValidHostName=false branch
        assertEquals(-1, FilenameUtils.getPrefixLength("\\\\inv@lid\\share"));
    }
}
