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
public abstract class Plant extends Organism{
    Plant(int xx, int yy, World w, int str, int spe,int a,char m, String n) {
        super(xx,yy,w,str,spe,a,m,n);
    }
    @Override
    public String Action(String com, KeyEvent e) {
        Random g=new Random();
	int n = g.nextInt(15);
	if (n == 0) {
		xy tmp = GetFree();
		if (tmp.x != -1) {
			Ad(tmp.x, tmp.y);
			com=Comunicate(com);
			com += " rozprzestrzenil(a) sie na " + Integer.toString(tmp.x) + " " + Integer.toString(tmp.y) + "\n";
		}
	}
	return com;
    }
    @Override
    boolean Eaten() {
	return true;
    }
    @Override
    boolean HogweedImmune() {
	return true;
    }
    @Override
    String Collision(int xx, int yy, String com) { return com; }
}
