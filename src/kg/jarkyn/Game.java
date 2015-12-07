package kg.jarkyn;

public class Game {
    private Mark winnerMark;
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;

    public Game(Board board, Player playerX, Player playerO) {
        this.winnerMark    = Mark.NONE;
        this.board         = board;
        this.playerX       = playerX;
        this.playerO       = playerO;
        this.currentPlayer = setCurrentPlayer();
    }

    public boolean isOver() {
        return board.isFinalState();
    }

    public boolean isWon() {
        return winnerMark() != Mark.NONE;
    }

    public void play() {
        while (!isOver() && currentPlayer.hasNextMove()) {
            playTurn();
        }
    }

    public void playTurn() {
        addMove(currentPlayer.pickPosition(board));
        swapPlayers();
    }

    public Mark winnerMark() {
        if (winnerMark == Mark.NONE) {
            winnerMark = board.winnerMark();
        }
        return winnerMark;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void addMove(int position) {
        board = getBoard().addMove(position, currentPlayer.getMark());
    }

    private void swapPlayers() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    private Player setCurrentPlayer(){
        return playersHaveEqualAmountOfMoves() ? playerX : playerO;
    }

    private boolean playersHaveEqualAmountOfMoves() {
        return (board.getAvailable().size() % 2) != 0;
    }
}
