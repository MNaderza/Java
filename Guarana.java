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
public class Guarana extends Plant{
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new Guarana(xx, yy,world);
	world.Ad(tmp);
    }
    public Guarana(int xx, int yy, World w){
	super(xx, yy, w, 0, 0, 0, 'G', "Guarana");
    }
    @Override
    boolean Boosted() {
	return true;
    }
}
