package InterviewSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class LocationProcessor {

		private ArrayList<Location> locations = new ArrayList<Location>();
		private int maxX;
		private int maxY;
		private int traineeNum;

		public LocationProcessor(String inputFile) {
			loadInputFile(inputFile);
		}

		public void loadInputFile(String file) {
			
			try {
				File fw = new File(file);
				Scanner reader = new Scanner(fw);
				int lineNumber = 1;
				traineeNum = 0;
				ArrayList<String> locAndIns = new ArrayList<String>();
				
				while (reader.hasNextLine()) {
					String line = reader.nextLine();
					
					if (!line.isEmpty() && lineNumber == 1) {
						// read first line and save value into a string coordinate 
						String pitch = line;
						maxX = Integer.parseInt(pitch.split(" ")[0]);
						maxY = Integer.parseInt(pitch.split(" ")[1]);
					
					}else if(!line.isEmpty() && lineNumber % 2 == 0){
						locAndIns.add(line);
					}else if(!line.isEmpty() && lineNumber % 2 == 1) {
						locAndIns.add(line);
						traineeNum++;
						
						// process each instruction
						processInstructions(locAndIns);
						
						// clear the ArrayList<String>
						locAndIns.clear();
					}
					lineNumber++;
				}	
			}catch (Exception e) {
				System.out.println("loadInputFile");
				e.printStackTrace();
			}
		}

		
		public void processInstructions(ArrayList<String> information) {
			// get the coordinates and the instruction
			String coordinates = information.get(0);
			String instruction = information.get(1);
			
			// determine the final location according to the instruction
			determineLocation(coordinates, instruction);
			
		}
		
		public void determineLocation(String c, String i) {
			int x = Integer.parseInt(c.split(" ")[0]);
			int y = Integer.parseInt(c.split(" ")[1]);
			String heading = c.split(" ")[2];
			
			for(int q = 0; q<i.length(); q++) {
				char ins = i.charAt(q);
				
				switch(heading) {
		         case "N" :
		           if(ins == 'M'){
		        	   y++;
		        	   inVenue(x, y, heading);
		           } else if(ins == 'L'){
		        	   heading = "W";
		           } else {
		        	   heading = "E"; 
		           }
		            break;
		            
		         case "S" :
		        	 if(ins == 'M'){
			        	y--;
				        inVenue(x, y, heading);
			           } else if(ins == 'L'){
			        	   heading = "E";
			           } else {
			        	   heading = "W"; 
			           }
		            break;
		            
		         case "E" :
		        	 if(ins == 'M'){
			           x++;
				       inVenue(x, y, heading);
			           } else if(ins == 'L'){
			        	   heading = "N";
			           } else {
			        	   heading = "S"; 
			           }
		            break;
		            
		         case "W" :
		        	 if(ins == 'M'){
			        	 x--;
				        inVenue(x, y, heading);
			           } else if(ins == 'L'){
			        	   heading = "S";
			           } else {
			        	   heading = "N"; 
			           }
		            break;
		      }
			}
			
			// save each final location into the ArrayList
			Location loc = new Location(x, y, heading);
			locations.add(loc);
		}
		
		public void inVenue(int a, int b, String h) {
			if(a >= 0 && a<= maxX && b >= 0 && b <= maxY) {
				System.out.println("The trainee_" + traineeNum + " is in the prescribed venue. The position is: " + a + " " + b + " " + h);
			} else {
				System.out.println("The trainee_" + traineeNum + " is out of the prescribed venue. The position is: " + a + " " + b + " " + h);
			}
		}
		
		public void writeResultFile(String file) {
			//print into the output file
			try {
				PrintWriter out = new PrintWriter(new File(file));
				for (Location l : locations) {
					out.println(l.getX() + " " + l.getY() + " " + l.getHeading());
				}
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("The file not found.");
			}
		}

	}

