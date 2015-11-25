package kg.jarkyn;

public class GameMaker {
    private static Player playerX;
    private static Player playerO;

    public static Game makeGame(GameOption gameOption, HumanInput input) {
        if (gameOption == GameOption.AI_ONLY) {
            playerX = makeAiPlayer(Mark.X);
            playerO = makeAiPlayer(Mark.O);
        } else if (gameOption == GameOption.AI_SECOND) {
            playerX = makeHumanPlayer(Mark.X, input);
            playerO = makeAiPlayer(Mark.O);
        } else if (gameOption == GameOption.HUMAN_ONLY){
            playerX = makeHumanPlayer(Mark.X, input);
            playerO = makeHumanPlayer(Mark.O, input);
        } else {
            playerX = makeAiPlayer(Mark.X);
            playerO = makeHumanPlayer(Mark.O, input);
        }
        return new Game(new Board(), playerX, playerO);
    }

    public static Player getPlayerX() {
        return playerX;
    }

    public static Player getPlayerO() {
        return playerO;
    }

    private static HumanPlayer makeHumanPlayer(Mark mark, HumanInput ui) {
        return new HumanPlayer(mark, ui);
    }

    private static AiPlayer makeAiPlayer(Mark mark) {
        return new AiPlayer(mark);
    }
}
