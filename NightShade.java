/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;

import projektpo.World;

/**
 *
 * @author mina
 */
public class NightShade extends Plant {
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new NightShade(xx, yy,world);
	world.Ad(tmp);
    }
    public NightShade(int xx, int yy, World w){
	super(xx, yy, w, 10, 0, 0, 'J', "Jagoda");

    }
    @Override
    boolean Killed(int s) {
	return true;
    }
}
