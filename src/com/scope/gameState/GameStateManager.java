package com.scope.gameState;

import java.awt.Graphics2D;

public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;
    private int score;

    public static final int NUMGAMESTATES = 3;
    public static final int MENUSTATE = 0;
    public static final int LEVELSTATE = 1;
    public static final int GAMEOVER = 2;

    public GameStateManager() {
        gameStates = new GameState[NUMGAMESTATES];

        currentState = MENUSTATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        if (state == MENUSTATE)
            gameStates[state] = new MenuState(this);
        else if (state == LEVELSTATE)
            gameStates[state] = new LevelState(this);
        else if (state == GAMEOVER)
            gameStates[state] = new GameOver(this);
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void update() {
        if (gameStates[currentState] != null)
            gameStates[currentState].update();
    }

    public void draw(Graphics2D g) {
        if (gameStates[currentState] != null)
            gameStates[currentState].draw(g);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}