/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;

import projektpo.World;
import java.awt.event.KeyEvent;
/**
 *
 * @author mina
 */
public class Dandelion extends Plant {
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new Dandelion(xx, yy,world);
	world.Ad(tmp);
    }
public Dandelion(int xx, int yy, World w){
	super(xx, yy, w, 0, 0, 0, 'M',"Mlecz");

}
    @Override
    public String Action(String com, KeyEvent e) {
	for (int i = 0; i < 3; i++) {
		com = super.Action(com,e);
		}
	return com;
    }
}
