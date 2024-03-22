package opdrachten.stroom;

import opdrachten.MeanderendeStroom;

public class HogerGelegenConstructies implements MeanderendeStroom.TheoretischeKnelpunten {
    @Override
    public boolean otterburcht(int c) {
        return (c % 4) == 0;
    }

    @Override
    public boolean dam(int c) {
        return (c % 2) == 0;
    }

    @Override
    public boolean brug(int c) {
        return true;
    }
}
