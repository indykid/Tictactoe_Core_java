package kg.jarkyn;

public class GameFactory {

    public static Game makeGame(Board board, GameOption gameOption, HumanInput input) {
        Player playerX;
        Player playerO;
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
        return new Game(board, playerX, playerO);
    }

    private static HumanPlayer makeHumanPlayer(Mark mark, HumanInput ui) {
        return new HumanPlayer(mark, ui);
    }

    private static AiPlayer makeAiPlayer(Mark mark) {
        return new AiPlayer(mark);
    }
}
