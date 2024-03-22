package opdrachten.stroom;

import opdrachten.MeanderendeStroom;

public class LagerGelegenConstructies implements MeanderendeStroom.TheoretischeKnelpunten {
    @Override
    public boolean otterburcht(int c) {
        return true;
    }

    @Override
    public boolean dam(int c) {
        return true;
    }

    @Override
    public boolean brug(int c) {
        return (c % 6) == 0;
    }
}
