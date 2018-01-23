/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;

import projektpo.World;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author mina
 */
public class Human extends Animal{
    private final static int SKILL = KeyEvent.VK_SPACE;
    private final static int UP = KeyEvent.VK_UP;
    private final static int DOWN = KeyEvent.VK_DOWN;
    private final static int LEFT = KeyEvent.VK_LEFT;
    private final static int RIGHT = KeyEvent.VK_RIGHT;
    @Override    
    void Ad(int xx, int yy){}
    public Human(int xx, int yy, World w){
	super(xx, yy, w, 3, 4, 0, 'C', "Czlowiek");
        SC=0;
        SU=true;

    }
    @Override
    public String Action(String com, KeyEvent e){
	int tmpx = x;
	int tmpy = y;
	age++;
        int event=e.getKeyCode();
	if (event==SKILL) {
		if (SU) {
			SC= 5;
			SU = false;
			com+="Aktywowano umiejenosc specjalna\n";
		}
		else if (SC > 0)
		{
			com+="Umiejetnosc specjalna jest juz aktywna\n";
		}
		else if (SC <= 0) {
			com+="Umiejetnosc specjalna nie moze zostac uruchomiona przez "+ Integer.toString(5 - SC)+ " tury\n";
		}
	}
        else {
            Random g=new Random();
            int r=g.nextInt(1);
		if (SC > 2 || (SC > 0 && r == 0)) {
			switch (event) {
			case DOWN: tmpy += 2; break;
			case UP: tmpy -= 2; break;
			case LEFT: tmpx -= 2; break;
			case RIGHT: tmpx += 2; break;
			}
		}
		else {
			switch (event) {
			case DOWN: tmpy++; break;
			case UP: tmpy--; break;
			case LEFT: tmpx--; break;
			case RIGHT: tmpx++; break;
			}
		}
		if (!SU)
		{
			SC--;
			if (SC == -5) {
				SU = true;
			}
		}
	}
	if (tmpx >= 0 && tmpy >= 0 && tmpx < world.GetWidth() && tmpy < world.GetHeight()) {
		com = Collision(tmpx, tmpy, com);
	}
	return com;
    }
    @Override
    public int GetSkillUsable() {
	if (SU)
		return 1;
	return 0;
    }
    @Override
    public int GetSkillCounter() {
	return SC;
    }
}
