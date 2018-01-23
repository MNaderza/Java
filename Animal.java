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
public abstract class Animal extends Organism {
    Animal(int xx, int yy, World w, int str, int spe,int a,char m, String n) {
        super(xx,yy,w,str,spe,a,m,n);
    }
    @Override
    boolean Eaten() {
	return true;
    }
    @Override
    public String Action(String com, KeyEvent e) {
	int tmpx = x;
	int tmpy = y;
	age++;
        Random generator=new Random();
	int tmp = generator.nextInt(3);
	switch (tmp) {
	case 0: break;
	case 1:
		if (x < world.GetWidth() - 1)
			tmpx++;
		break;
	case 2:
		if (x > 0)
			tmpx--;
		break;
	}
	tmp = generator.nextInt(3);
	switch (tmp) {
	case 0: break;
	case 1:
		if (y < world.GetHeight() - 1)
			tmpy++;
		break;
	case 2:
		if (y > 0)
			tmpy--;
		break;
	}
	com=Collision(tmpx, tmpy, com);
	return com;
    }
    @Override
    String Collision(int tmpx, int tmpy, String com){
	Organism enemy = world.GetOrg(tmpx, tmpy);
	if (enemy!=null && enemy!=this)
	{
		if (enemy.Eaten()) {
			if (enemy.Killed(strenght)) {
				com = Comunicate(com);
				com += " zjadl ";
				com = enemy.Comunicate(com);
				com += " i umarl \n";
				world.Kill(enemy);
				world.Kill(this);
			}
			else if (enemy.Boosted()) {
				com = Comunicate(com);
				com += " zjadl ";
				com = enemy.Comunicate(com);
				com += " i otrzymal +3 do sily \n";
				x = tmpx;
				y = tmpy;
				strenght += 3;
				world.Kill(enemy);
			}
			else {
				com = Comunicate(com);
				com += " zjadl ";
				com = enemy.Comunicate(com);
				com += "\n";
				x = tmpx;
				y = tmpy;
				world.Kill(enemy);
			}
		}
		else if (enemy.Resisted(strenght) ) {
			com=enemy.Comunicate(com);
			com+=" odparl atak ";
			com=Comunicate(com);
			com+="\n";
		}
		else if (enemy.Ran()) {
				com=enemy.Comunicate(com);
				com+=" uciekl przed ";
				com=Comunicate(com);
				com+="\n";
			}
		else if (enemy.Multiplied(mark))
		{
			if (can_multiply)
			{
				com = this.Multiply(enemy, com);
				enemy.CantMupliply();
			}
			else can_multiply = true;
		}
		else if (enemy.Died(strenght))
		{
			com=Comunicate(com);
			com+=" zaatakowal i zabil ";
			com= enemy.Comunicate(com);
			com+="\n";
			x = tmpx;
			y = tmpy;
			world.Kill(enemy);
		}
		else if (enemy.Killed(strenght)) {
			{
				com=Comunicate(com);
				com+=" zaatakowal ";
				com=enemy.Comunicate(com);
				com+= " i polegl \n";
				world.Kill(this);
			}
		}
	}
	else {
		x=tmpx;
		y=tmpy;
	}
	return com;
}
}
