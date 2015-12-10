package kg.jarkyn;

import java.util.List;

public interface HumanInput {
    int getMove(List<Integer> available);
    boolean hasHumanMove();
}
