package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexStringTest {
    @Test
    void simpleCases() {
        assertEquals("00", HexString.forByte((byte) 0));
        assertEquals("ff", HexString.forByte((byte) -1));
        assertEquals("7f", HexString.forByte((byte) 127));
        assertEquals("80", HexString.forByte((byte) -128));
    }
}
