package com.scope.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.scope.InputHandler.InputHandler;
import com.scope.NewGame.GamePanel;

public class GameOver extends GameState {

	private Color titleColor;
	private Color color;
	private Font font;
	private Color bgColor;
	private Font titleFont;
	
	public GameOver(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		InputHandler.setFalse();
		
		try {
			
			bgColor = Color.BLACK;
			titleColor = Color.WHITE;
			color = Color.YELLOW;
			font = new Font("Arial", Font.PLAIN, 14);
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(bgColor);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 40, 70);
		
		g.setFont(font);
		String s = "Your score: " + gsm.score;
		g.drawString(s, 80, 100);
		
		g.setColor(color);
		g.drawString("Back to menu", 85, 175);
	}
	
	public void handleInput() {
		if(InputHandler.isPressed(InputHandler.ENTER)) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	
}