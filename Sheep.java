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
public class Sheep extends Animal{
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new Sheep(xx, yy,world);
	world.Ad(tmp);
    }
public Sheep(int xx, int yy, World w){
	super(xx, yy, w, 3, 4, 0, 'O', "Owca");

    }
}
