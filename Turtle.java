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
public class Turtle extends Animal{
    @Override    
    void Ad(int xx, int yy){
	Organism tmp = new Turtle(xx, yy,world);
	world.Ad(tmp);
    }
    public Turtle(int xx, int yy, World w){
	super(xx, yy, w, 2, 1, 0, 'Z', "Zolw");

    }
    @Override
    boolean Resisted(int s) {
	return s<5;
    }
    @Override
    public String Action(String com, KeyEvent e) {
        Random g=new Random();
	int move = g.nextInt(4);
	if (move == 0) {
		com=super.Action(com, e);
	}
	return com;
    }
}
