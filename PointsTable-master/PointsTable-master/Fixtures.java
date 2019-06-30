package dharmin;

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class working extends Applet implements ActionListener
{
	
	private static final long serialVersionUID = 1L;

	Image pic;
	
	int roundCounter=0;
	int teams=20;
	int totalRounds = teams - 1;
    int matchesPerRound = teams / 2;
    String[][] rounds = new String[totalRounds][matchesPerRound];
	
	
	Random rd;
	TextField tf1[],tf2[],tf3[],tf4[];
	Button b;
	Button rand;
	Button search;
	
	String[] TeamName;
	String[] TeamNameCopy;
	boolean [][] Matches;	
	int Points[], GD[];
	int win[],loose[],draw[];
	
	TextField team[],point[],godi[],pos[];
	TextField wins[],looses[],draws[];
	TextField tname,tpos,tpoints,tgd;


	public void init()
	{
		Font f=new Font("Serif",Font.BOLD,15);
		
		
		pic =getImage(getDocumentBase(),"download.jpg");
		rd=new Random();
		rand=new Button();
		rand.setBounds(400, 90, 200, 30);
		rand.setLabel("Click for Random Values");
		rand.addActionListener(this);
		//add(rand);
		setLayout(new BorderLayout());
		
		search=new Button();
		search.setBounds(30, 450, 200, 30);
		search.setLabel("Click to search the team's details");
		search.addActionListener(this);
		setLayout(new BorderLayout());
		
		Matches = new boolean[20][20]; 
		TeamName =new String[] {"MCI", "MUN", "TOT", "CHE", "ARS", "WAT", "NEW", "BUR", "LIV", "SOU", "HUD", "BHA", "WBA", "LEI", "SWA", "WHU", "STK", "EVE", "BOU", "CRY"};
		TeamNameCopy =new String[] {"MCI", "MUN", "TOT", "CHE", "ARS", "WAT", "NEW", "BUR", "LIV", "SOU", "HUD", "BHA", "WBA", "LEI", "SWA", "WHU", "STK", "EVE", "BOU", "CRY"};
		//Selected = new boolean [20];
		Points = new int[20];
		GD = new int[20];
		win = new int[20];
		loose = new int[20];
		draw= new int[20];
		
		team=new TextField[20];
		point=new TextField[20];
		godi=new TextField[20];
		pos=new TextField[20];
		wins=new TextField[20];
		looses=new TextField[20];
		draws=new TextField[20];
		
		tname=new TextField();
		tname.setBounds(260, 450, 200, 30);
		tname.setText("Enter Team Name here");
		tname.addActionListener(this);
		//add(tname);
		setLayout(new BorderLayout());
		
		tpos=new TextField();
		tpos.setBounds(30, 510, 50, 30);
		tpos.setText("Position");
		tpos.addActionListener(this);
		//add(tpos);
		setLayout(new BorderLayout());
		
		tpoints=new TextField();
		tpoints.setBounds(110, 510, 50, 30);
		tpoints.setText("Points");
		tpoints.addActionListener(this);
		//add(tpoints);
		setLayout(new BorderLayout());
		
		tgd=new TextField();
		tgd.setBounds(190, 510, 50 , 30);
		tgd.setText("GD");
		tgd.addActionListener(this);
		//add(tgd);
		setLayout(new BorderLayout());
		
		for (int i = 0; i< 20; i++)
		{
			GD[i] = 0;
			Points[i] = 0;
			win[i]=0;
			loose[i]=0;
			draw[i]=0;
			for (int j = 0; j< 20; j++)
				Matches[i][j] = false;
		}
		
		
		tf1=new TextField[10];
		tf2=new TextField[10];
		tf3=new TextField[10];
		tf4=new TextField[10];
		
                for(int i=0;i<10;i++)
                {
                    tf1[i]=new TextField();
                    tf1[i].addActionListener(this);
                    add(tf1[i]);
                    tf1[i].setBounds(30,i*40+30,100,30);
                    setLayout(new BorderLayout());
                    
                    tf2[i]=new TextField();
                    tf2[i].addActionListener(this);
                    add(tf2[i]);
                    tf2[i].setBounds(160,i*40+30,30,30);
                    setLayout(new BorderLayout());

                    
                    tf3[i]=new TextField();
                    tf3[i].addActionListener(this);
                    add(tf3[i]);
                    tf3[i].setBounds(200,i*40+30,30,30);
                    setLayout(new BorderLayout());

                    
                    tf4[i]=new TextField();
                    tf4[i].addActionListener(this);
                    add(tf4[i]);
                    tf4[i].setBounds(260,i*40+30,100,30);
                    setLayout(new BorderLayout());

                }
		
		b=new Button();
		b.setBounds(400,30,200,30);
		b.setLabel("Press to Start");
		b.addActionListener(this);
		b.setFont(f);
		add(b);
		setLayout(new BorderLayout());
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==search)
		{
			String tm=tname.getText();
			int ind=searchTeam(tm);
			tpos.setText(String.valueOf(ind+1));
			tpoints.setText(String.valueOf(Points[ind]));
			tgd.setText(String.valueOf(GD[ind]));
		}
		
		
		if(e.getSource()==rand)
		{
			for(int i=0;i<10;i++)
				{
					tf2[i].setText(String.valueOf(rd.nextInt(8)));
					tf3[i].setText(String.valueOf(rd.nextInt(8)));
				}
		}	
		
		if(e.getSource()==b)
		{
			
			if(b.getLabel()=="Press to Start")
			{
				add(tname);
				add(rand);
				add(search);
				add(tpoints);
				add(tpos);
				add(tgd);
				matchFixtures();
				display();
				b.setLabel("Submit result of round 1");
				pos[0]=new TextField();
		        pos[0].addActionListener(this);
		        add(pos[0]);
		        pos[0].setBounds(900,30,30,20);
		        setLayout(new BorderLayout());
		        
		        team[0]=new TextField();
		        team[0].addActionListener(this);
		        add(team[0]);
		        team[0].setBounds(960,30,100,20);
		        setLayout(new BorderLayout());
		        
		        point[0]=new TextField();
		        point[0].addActionListener(this);
		        add(point[0]);
		        point[0].setBounds(1090,30,30,20);
		        setLayout(new BorderLayout());

		        
		        godi[0]=new TextField();
		        godi[0].addActionListener(this);
		        add(godi[0]);
		        godi[0].setBounds(1150,30,30,20);
		        setLayout(new BorderLayout());
		        
		        wins[0]=new TextField();
		        wins[0].addActionListener(this);
		        add(wins[0]);
		        wins[0].setBounds(1205,30,30,20);
		        setLayout(new BorderLayout());
		        
		        looses[0]=new TextField();
		        looses[0].addActionListener(this);
		        add(looses[0]);
		        looses[0].setBounds(1260,30,30,20);
		        setLayout(new BorderLayout());
		        
		        draws[0]=new TextField();
		        draws[0].addActionListener(this);
		        add(draws[0]);
		        draws[0].setBounds(1320,30,30,20);
		        setLayout(new BorderLayout());

				for(int i=2;i<21;i++)
		        {
					pos[i-1]=new TextField();
		            pos[i-1].addActionListener(this);
		            add(pos[i-1]);
		            pos[i-1].setBounds(900,i*20+10,30,20);
		            setLayout(new BorderLayout());
		            
					team[i-1]=new TextField();
		            team[i-1].addActionListener(this);
		            add(team[i-1]);
		            team[i-1].setBounds(960,i*20+10,100,20);
		            setLayout(new BorderLayout());
		            
		            point[i-1]=new TextField();
		            point[i-1].addActionListener(this);
		            add(point[i-1]);
		            point[i-1].setBounds(1090,i*20+10,30,20);
		            setLayout(new BorderLayout());

		            
		            godi[i-1]=new TextField();
		            godi[i-1].addActionListener(this);
		            add(godi[i-1]);
		            godi[i-1].setBounds(1150,i*20+10,30,20);
		            setLayout(new BorderLayout());

		            wins[i-1]=new TextField();
		            wins[i-1].addActionListener(this);
		            add(wins[i-1]);
		            wins[i-1].setBounds(1205,i*20+10,30,20);
		            setLayout(new BorderLayout());
		            
		            looses[i-1]=new TextField();
		            looses[i-1].addActionListener(this);
		            add(looses[i-1]);
		            looses[i-1].setBounds(1260,i*20+10,30,20);
		            setLayout(new BorderLayout());
		            
		            draws[i-1]=new TextField();
		            draws[i-1].addActionListener(this);
		            add(draws[i-1]);
		            draws[i-1].setBounds(1320,i*20+10,30,20);
		            setLayout(new BorderLayout());
		            

		        }
				
			}
		
			else if(b.getLabel()=="Submit result of round 1")
			{
				tableDisplay();
				b.setLabel("Submit result of round 2");
				reset();
				display();
			}
			else if(b.getLabel()=="Submit result of round 2")
			{
				tableDisplay();
				b.setLabel("Submit result of round 3");reset();
				display();
			}
			else if(b.getLabel()=="Submit result of round 3")
			{
				tableDisplay();
				b.setLabel("Submit result of round 4");reset();
				display();
			}
			else if(b.getLabel()=="Submit result of round 4")
			{
				tableDisplay();
				b.setLabel("Submit result of round 5");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 5")
			{
				tableDisplay();
				b.setLabel("Submit result of round 6");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 6")
			{
				tableDisplay();
				b.setLabel("Submit result of round 7");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 7")
			{
				tableDisplay();
				b.setLabel("Submit result of round 8");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 8")
			{
				tableDisplay();
				b.setLabel("Submit result of round 9");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 9")
			{
				tableDisplay();
				b.setLabel("Submit result of round 10");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 10")
			{
				tableDisplay();
				b.setLabel("Submit result of round 11");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 11")
			{
				tableDisplay();
				b.setLabel("Submit result of round 12");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 12")
			{
				tableDisplay();
				b.setLabel("Submit result of round 13");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 13")
			{
				tableDisplay();
				b.setLabel("Submit result of round 14");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 14")
			{
				tableDisplay();
				b.setLabel("Submit result of round 15");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 15")
			{
				tableDisplay();
				b.setLabel("Submit result of round 16");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 16")
			{
				tableDisplay();
				b.setLabel("Submit result of round 17");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 17")
			{
				tableDisplay();
				b.setLabel("Submit result of round 18");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 18")
			{
				tableDisplay();
				b.setLabel("Submit result of round 19");display();reset();
			}
			else if(b.getLabel()=="Submit result of round 19")
			{
				remove(rand);
				tableDisplay();
				for(int i=0;i<20;i++)
				{
					tf2[i].setText("NA");
					tf3[i].setText("NA");
					tf2[i].setEditable(false);
					tf3[i].setEditable(false);
				}
				
				
			}
			
		
		}
	}
	public void paint(Graphics g)
	{
	
		g.drawString("Position", 900, 20);
		g.drawString("Teams",  960, 20);
		g.drawString("Points", 1090, 20);
		g.drawString("GD", 1150, 20);
		g.drawString("Wins", 1210-10, 20);
		g.drawString("Looses", 1270-10, 20);
		g.drawString("Draws", 1330-10, 20);
		g.drawString("Enter the result of match", 30, 20);
		g.drawString("Match teams", 200, 20);
		g.drawImage(pic, 100,30,this);
	}
	
	void matchFixtures()
	{
		for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = TeamName[home] + TeamName[away];
               
            }
           
        }
		
	}
	void display()
	{
		for(int i=0;i<10;i++)
		{
			String team1=rounds[roundCounter][i].substring(0,3);
			String team2=rounds[roundCounter][i].substring(3);
			tf1[i].setText(team1);
			tf4[i].setText(team2);
			
		}
		roundCounter++;
	}
	int searchTeam(String s)
	{
		int i;
		for(i=0;i<20;i++)
		{
			if(TeamNameCopy[i].equals(s))
				break;
		}
		return i;
	}
	void scoreupdate()
	{
		for(int i=0;i<10;i++)
		{
			String s1=tf2[i].getText();
			int s01=Integer.parseInt(s1);
			String s2=tf3[i].getText();
			int s02=Integer.parseInt(s2);
			if(s01>s02)
			{
				String winningTeam = tf1[i].getText();
				int index=searchTeam(winningTeam);
				Points[index]=Points[index]+3;
				GD[index]=GD[index]+(s01-s02);
				win[index]+=1;
				
				String losingTeam = tf4[i].getText();
				int index1=searchTeam(losingTeam);
				Points[index1]=Points[index1]+0;
				GD[index1]=GD[index1]+(s02-s01);
				loose[index1]+=1;
				
			}
			else if(s01==s02)
			{
				String winningTeam = tf1[i].getText();
				int index=searchTeam(winningTeam);
				Points[index]=Points[index]+1;
				GD[index]=GD[index]+(s01-s02);
				draw[index]+=1;
				
				String losingTeam = tf4[i].getText();
				int index1=searchTeam(losingTeam);
				Points[index1]=Points[index1]+1;
				GD[index1]=GD[index1]+(s02-s01);
				draw[index1]+=1;
			}
			else if(s01<s02)
			{
				String winningTeam = tf4[i].getText();
				int index=searchTeam(winningTeam);
				Points[index]=Points[index]+3;
				GD[index]=GD[index]+(s02-s01);
				win[index]+=1;
				
				
				String losingTeam = tf1[i].getText();
				int index1=searchTeam(losingTeam);
				Points[index1]=Points[index1]+0;
				GD[index1]=GD[index1]+(s01-s02);
				loose[index1]+=1;
				
			}
			
		}
		
	}
	void sort()
	{
		for(int i=0;i<20;i++)
			for(int j=0;j<19;j++)
			{
				if(Points[j]<Points[j+1])
				{
					int temp=Points[j];
					Points[j]=Points[j+1];
					Points[j+1]=temp;
					
					String temp1=TeamNameCopy[j];
					TeamNameCopy[j]=TeamNameCopy[j+1];
					TeamNameCopy[j+1]=temp1;
					
					int temp2=GD[j];
					GD[j]=GD[j+1];
					GD[j+1]=temp2;
					
					int temp3=win[j];
					win[j]=win[j+1];
					win[j+1]=temp3;
					
					int temp4=loose[j];
					loose[j]=loose[j+1];
					loose[j+1]=temp4;
					
					int temp5=draw[j];
					draw[j]=draw[j+1];
					draw[j+1]=temp5;
				}
				else if(Points[j]==Points[j+1])
				{
					if(GD[j]<GD[j+1])
					{
						int temp2=GD[j];
						GD[j]=GD[j+1];
						GD[j+1]=temp2;
						
						String temp1=TeamNameCopy[j];
						TeamNameCopy[j]=TeamNameCopy[j+1];
						TeamNameCopy[j+1]=temp1;
						
						
						int temp=Points[j];
						Points[j]=Points[j+1];
						Points[j+1]=temp;
						
						int temp3=win[j];
						win[j]=win[j+1];
						win[j+1]=temp3;
						
						int temp4=loose[j];
						loose[j]=loose[j+1];
						loose[j+1]=temp4;
						
						int temp5=draw[j];
						draw[j]=draw[j+1];
						draw[j+1]=temp5;
					}

				}
				
			}
	}
	void tableDisplay()
	{
		scoreupdate();
		sort();
		for(int i=0;i<20;i++)
		{
			point[i].setText(String.valueOf(Points[i]));
			team[i].setText(TeamNameCopy[i]);
			godi[i].setText(String.valueOf(GD[i]));
			pos[i].setText(String.valueOf(i+1));
			wins[i].setText(String.valueOf(win[i]));
			looses[i].setText(String.valueOf(loose[i]));
			draws[i].setText(String.valueOf(draw[i]));
		}
	}
	void reset()
	{
		for(int i=0;i<10;i++)
		{
			tf2[i].setText("");
			tf3[i].setText("");
		}
	}
}


