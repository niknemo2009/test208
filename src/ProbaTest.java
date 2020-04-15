import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ProbaTest {

    @org.junit.jupiter.api.Test
    @DisplayName("!!!!!!!!!!!")
    void isSimpleTest() {
        Proba proba=new Proba();
        boolean factResult=proba.isSimple(8);

        assertEquals(factResult,false       );
    }

}