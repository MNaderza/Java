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
public class Hogweed extends Plant {
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new Hogweed(xx, yy,world);
	world.Ad(tmp);
    }
    public Hogweed(int xx, int yy, World w){
	super(xx, yy, w, 99, 0, 0, 'B', "Barszcz");
    }
    @Override
    boolean Killed(int s) {
	return true;
    }
    @Override
    public String Action(String com,KeyEvent e) {
        Random g=new Random();
	int n = g.nextInt(8);
	for (int i = -1; i < 2; i++) {
		for (int j = -1; j < 2; j++) {
			if (i == 0 && j == 0)
				continue;
			else if (x + i < world.GetWidth() && x + i > 0 && y + j < world.GetHeight() && y + j > 0) {
				Organism tmp = world.GetOrg(x + i, y + j);
				if (tmp!=null) {
					if (!tmp.HogweedImmune()) {
						com = Comunicate(com);
						com += " zabil ";
						com = tmp.Comunicate(com);
						com += "\n";
						world.Kill(tmp);
					}
				}
			}
		}
	}
	if (n == 0) {
		xy tmp = GetFree();
		if (tmp.x != -1) {
			Ad(tmp.x, tmp.y);
			com = Comunicate(com);
			com += " rozprzestrzenil(a) sie na " + Integer.toString(tmp.x) + " " + Integer.toString(tmp.y) + "\n";
		}
	}
	return com;
    }
}
