package opdrachten;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeanderendeStroomTest {

    @Test
    void stroomTest() {
        MeanderendeStroom subject = new MeanderendeStroom();
        assertEquals(9722, subject.stroom());
    }
}