package kg.jarkyn;

import java.util.List;

public interface HumanInput {
//    int selectGame();
//
//    void announceDraw();

//    void playGame();

//    void setGame(kg.jarkyn.Game game);

    int getMove(List<Integer> available);

//    void announceGameOver();

    boolean hasHumanMove();
}
