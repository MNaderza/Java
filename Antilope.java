/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;

import projektpo.World;
import java.util.Random;
import projektpo.xy;
import java.awt.event.KeyEvent;

/**
 *
 * @author mina
 */
public class Antilope extends Animal{
    void Ad(int xx, int yy){
	Organism tmp = new Antilope(xx, yy,world);
	world.Ad(tmp);
    }
    public Antilope(int xx, int yy, World w){
	super(xx, yy, w, 4, 5, 0, 'A', "Antylopa");

    }
    @Override
    boolean Ran() {
        Random g=new Random();
	int n = g.nextInt(2);
	if (n == 0) {
		xy tmp=GetFree();
		if (tmp.x != -1) {
			x = tmp.x;
			y = tmp.y;
			return true;
		}
	}
	return false;
    }
    @Override
    public String Action(String com,KeyEvent e) {
	int tmpx = x;
	int tmpy = y;
	age++;
        Random g=new Random();
	for (int i = 0; i < 2; i++) {
		int tmp = g.nextInt(3);
		switch (tmp) {
		case 0: break;
		case 1:
			if (x < world.GetWidth() - 1)
				tmpx++;
			break;
		case 2:
			if (x > 0)
				tmpx--;
			break;
		}
		tmp = g.nextInt(3);
		switch (tmp) {
		case 0: break;
		case 1:
			if (y < world.GetHeight() - 1)
				tmpy++;
			break;
		case 2:
			if (y > 0)
				tmpy--;
			break;
		}
	}
	com=Collision(tmpx, tmpy,com);
	return com;
}
}
