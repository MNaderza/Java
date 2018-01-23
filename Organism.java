/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organisms;
import projektpo.World;
import projektpo.xy;
import java.util.Random;
import java.awt.event.KeyEvent;
/**
 *
 * @author mina
 */
public abstract class Organism {
    int x, y, speed, strenght,age,SC;
    boolean SU;
	String name;
	World world;
	char mark;
	boolean can_multiply = true;
        public abstract String Action(String com, KeyEvent e);
        abstract String Collision(int x, int y,String com);
        abstract void Ad(int x, int y);
        public int GetSpeed()
{
	return speed;
}
int GetStrenght()
{
	return strenght;
}
String Comunicate(String com) {
	com +=name + " " + Integer.toString(x) + " " + Integer.toString(y);
	return com;
}
boolean Eaten() {
	return false;
}
boolean HogweedImmune() {
	return false;
}
boolean Boosted() {
	return false;
}
xy GetFree() {
	int tmpx = x;
	int tmpy = y;
	int counter = 0;
	int[][] places=new int[9][2];
	for (int i = -1; i < 2; i++)
	{
		for (int j = -1; j < 2; j++) {
			if (i == 0 && j == 0) {
				continue;
			}
			if (!(tmpx + i < 0) && !(tmpx + i >= world.GetWidth()) && !(tmpy + j < 0) && !(tmpy + j >= world.GetHeight()))
			{
				if (world.GetOrg(tmpx + i, tmpy + j)==null)
				{
					places[counter][0] = i;
					places[counter][1] = j;
					counter++;
				}
			}
		}
	}
	xy tmp_struct=new xy(-1,-1);
	if (counter > 0) {
                Random generator=new Random();
		int tmp = generator.nextInt(counter);
		tmpx += places[tmp][0];
		tmpy += places[tmp][1];
		tmp_struct.x = tmpx;
		tmp_struct.y = tmpy;
	}
	return tmp_struct;
	
}
void CantMupliply() {
		can_multiply = false;
}
String Multiply(Organism tmpo,String com) {
		xy tmp = GetFree();
		if (tmp.x != -1) {
			Ad(tmp.x, tmp.y);
			com=Comunicate(com);
			com+=" rozmnozyl sie z ";
			com=tmpo.Comunicate(com);
			com+="\n";
		}
		return com;
}
public int GetSkillCounter() {
	return 0;
}
public int GetSkillUsable() {
	return 0;
}
public int GetX()
{
	return x;
}
public int GetY()
{
	return y;
}
public int GetAge() {
	return age;
}
public char GetMark() {
	return mark;
}
public void SetSC(int a){
    SC=a;
}
public void SetSU(int a){
    if (a==0) SU=false;
    else SU=true;
}
boolean Multiplied(char m) {
	if (mark == m) {
		can_multiply = false;
		return true;
	}
	else return false;
}
boolean Killed(int s) {
	if (s < strenght)
		return true;
	else
		return false;
}
boolean Ran() {
	return false;
}
boolean Died(int s) {
	if (s >= strenght)
		return true;
	else return false;
}
boolean Resisted(int s) {
	return false;
}

Organism(int xx, int yy, World w, int str, int spe,int a,char m, String n) {
	x = xx;
	y = yy;
	speed = spe;
	strenght = str;
	age = a;
	world = w;
	mark = m;
	name = n;
        SC=0;
        SU=false;
}
}
