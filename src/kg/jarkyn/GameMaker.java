package kg.jarkyn;

public class GameMaker {
    private static Player playerX;
    private static Player playerO;

    public static Game makeGame(GameOption gameOption, HumanInput input) {
        if (gameOption == GameOption.AI_FIRST) {
            playerX = makeAiPlayer(Mark.X);
            playerO = makeHumanPlayer(Mark.O, input);
        } else if (gameOption == GameOption.AI_SECOND) {
            playerX = new HumanPlayer(Mark.X, input);
            playerO = new AiPlayer(Mark.O);
        } else {
            playerX = new HumanPlayer(Mark.X, input);
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
