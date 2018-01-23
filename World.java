/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;
import Organisms.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import Organisms.Human;

/**
 *
 * @author mina
 */
public class World {
    private int width=40,height=40;
    private String com="";
    Organism[] OrgList=new Organism[width*height];
    public int GetHeight(){
        return height; 
    }
    public int GetWidth(){
        return width;
    }
    String GetCom(){
        return com;
    }
    void AdCom(String com){
        this.com+=com;
    }
    public void Ad(Organism tmp){
        for(int i=width*height-1;i>=0;i--)
        {
            if(OrgList[i]==null)
            {
                OrgList[i]=tmp;
                break;
            }
        }
    }
    public void Save() throws FileNotFoundException{
        PrintWriter zapis = new PrintWriter("zapis.txt");
        for (int i=0;i<width*height;i++) {
            if(OrgList[i]==null)
                zapis.print("null"+" ");
            else if(OrgList[i].GetMark()=='C')
                zapis.print(OrgList[i].GetMark()+" "+OrgList[i].GetX()+" "+OrgList[i].GetY()+" "+OrgList[i].GetSkillCounter()+" "+OrgList[i].GetSkillUsable()+" ");
            else zapis.print(OrgList[i].GetMark()+" "+OrgList[i].GetX()+" "+OrgList[i].GetY()+" ");
        }
        zapis.close();
    }
    public void load()
    {
        File file = new File("zapis.txt");
        Scanner scanner;
        try
        {
            scanner = new Scanner(file);
        }
        catch(FileNotFoundException ex)
        {
            return;
        }
        for(int i=0;i<width*height-1;i++){
            OrgList[i]=null;
        }
        for(int j=0;j<width*height-1;j++)
        {
            String name=scanner.next();
            if(name.equals("null"))
            {
                continue;
            }
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(name.equals("C")){
                int sc=scanner.nextInt();
                int su=scanner.nextInt();
                Create(x,y,name,sc,su);
            }
            else Create(x,y,name,-10,-10);
            
            
        }
    }
    public void Create(int x, int y, String name,int sc,int su){
        Organism tmp=new Sheep(1,1,this);
         switch(name){
            case "W":tmp=new Wolf(x,y,this); break;
            case "O":tmp=new Sheep(x,y,this); break;
            case "L":tmp=new Foxx(x,y,this); break;
            case "Z":tmp=new Turtle(x,y,this); break;
            case "A":tmp=new Antilope(x,y,this); break;
            case "T":tmp=new Grass(x,y,this); break;
            case "G":tmp=new Guarana(x,y,this); break;
            case "J":tmp=new NightShade(x,y,this); break;
            case "M":tmp=new Dandelion(x,y,this); break;
            case "B":tmp=new Hogweed(x,y,this); break;
            case "C":tmp=new Human(x,y,this); break;
         }
        for(int i=width*height-1;i>=0;i--)
        {
            
            if(OrgList[i]==null)
            {
                OrgList[i]=tmp;
                if(sc!=-10) {
                    OrgList[i].SetSC(sc);
                    OrgList[i].SetSU(su);
                }
                break;
            }
        }
    }
    private int Find(Organism tmp) {
	for (int i = 0; i < width*height; i++) {
		if (tmp == OrgList[i]) {
			return i;
		}
	}
	return -1;
    }
    public void Kill(Organism tmp){
        int i = Find(tmp);
        if(i!=-1)
            OrgList[i]=null;
    }
    public Organism  GetOrg(int xx,int yy) {
	for (int i = 0; i < width*height; i++) {
            if(OrgList[i]!=null){
		if (OrgList[i].GetX() == xx && OrgList[i].GetY() == yy) {
			return OrgList[i];
		}
            }
	}
	return null;

    }
    private void Sort(){
       Arrays.sort(OrgList, new Comparator<Organism>(){
               @Override
                public int compare(Organism o1, Organism o2){ 
                   if(o1==null && o2==null)
                        return 0;
                    else if(o1==null)
                        return 1;
                    else if(o2==null)
                        return -1;
                    else if((o1.GetSpeed()>o2.GetSpeed())||(o1.GetSpeed()==o2.GetSpeed() && o1.GetAge()>o2.GetAge()))
                        return -1;
                    else if (o1.GetSpeed()==o2.GetSpeed() && o1.GetAge()==o2.GetAge())
                        return 0;
                    return 1;
                }
        });
    }
    boolean ExecuteTurn(KeyEvent e) {
                com="";
                Sort();
		for(int i=0;i<width*height;i++)
		{
                    if(OrgList[i]!=null)
			com=OrgList[i].Action(com, e);
			//ListToMap();
		}
		com += "Koniec tury, poruszaj sie strzalkami, aktywuj umiejetnosc spacja. Czlowiek-czarny kwadrat\n";
                System.out.println(com);
		return true;
    }
    World() {
        for(int i=0;i<width*height;i++){
            OrgList[i]=null;
        }
    }
    void Fill(){
        Organism tmp=new Human(1,1,this);
        OrgList[0]=tmp;
	Random generator=new Random();
	for (int i = 1; i < width*height/15; i++)
	{   
		int x = generator.nextInt(width);
		int y = generator.nextInt(height);
		while (GetOrg(x, y)!=null) {
			x = generator.nextInt(width);
			y = generator.nextInt(height);
		}
		int s = generator.nextInt(10);
		switch (s) {
		case 3: tmp = new Sheep(x, y, this); break;
		case 0: tmp = new Wolf(x, y, this); break;
		case 2: tmp = new Turtle(x, y, this); break;
		case 1: tmp = new Antilope(x, y, this); break;
		case 5: tmp = new Grass(x, y, this); break;
		case 4: tmp = new Dandelion(x, y, this); break;
		case 6: tmp = new Guarana(x, y, this); break;
		case 7: tmp = new NightShade(x, y, this); break;
		case 8: tmp = new Hogweed(x, y, this); break;
		case 9: tmp = new Foxx(x, y, this); break;
		}
                OrgList[i]=tmp;
	}
	Sort();
    }
}
