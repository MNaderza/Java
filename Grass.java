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
public class Grass extends Plant {
    @Override
    void Ad(int xx, int yy){
	Organism tmp = new Grass(xx, yy,world);
	world.Ad(tmp);
    }
    public Grass(int xx, int yy, World w){
	super(xx, yy, w, 0, 0, 0, 'T', "Trawa");
    }
}
