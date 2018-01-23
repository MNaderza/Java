/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;

import projektpo.World;
import java.util.Random;
import java.awt.event.KeyEvent;

/**
 *
 * @author mina
 */
public class Foxx extends Animal{
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new Foxx(xx, yy,world);
	world.Ad(tmp);
    }
    public Foxx(int xx, int yy, World w){
	super(xx, yy, w, 3, 7, 0, 'L', "Lis");

    }
    @Override
    public String Action(String com,KeyEvent e) {
	int tmpx = x;
	int tmpy = y;
	int[][] tab=new int[9][2];
	int counter = 0;
	age++;
	for (int i = -1; i < 2; i++) {
		for (int j = -1; j < 2; j++) {
			if (i == 0 && j == 0)
				continue;
			else if (x + i < world.GetWidth() && x + i > 0 && y + j < world.GetHeight() && y + j > 0) {
				Organism tmp = world.GetOrg(x + i, y + j);
				if(tmp!=null){
					if (tmp.GetStrenght() <= strenght) {
						tab[counter][0] = i;
						tab[counter][1] = j;
						counter++;
					}
				}
				else {
					tab[counter][0] = i;
					tab[counter][1] = j;
					counter++;
				}
			}
		}
	}
	if (counter > 0) {
            Random g=new Random();
		int n = g.nextInt(counter);
		tmpx += tab[n][0];
		tmpy += tab[n][1];
	}
	com = Collision(tmpx, tmpy, com);
	return com;
}
}
