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
public class Wolf extends Animal {
    @Override    
    void Ad(int xx, int yy){
	Organism tmp = new Wolf(xx, yy,world);
	world.Ad(tmp);
    }
    public Wolf(int xx, int yy, World w){
	super(xx, yy, w, 9, 5, 0, 'W', "Wilk");
    }
}
