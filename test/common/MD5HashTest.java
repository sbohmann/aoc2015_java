package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5HashTest {
    @Test
    void knownExamples() {
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", MD5Hash.forAsciiString(""));
        assertEquals("b026324c6904b2a9cb4b88d6d61c81d1", MD5Hash.forAsciiString("1\n"));
    }
}
