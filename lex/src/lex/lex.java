package lex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



/**
 * @author ouldAmara wardia ourabah houcine
 *
 */

public class lex {
	static ArrayList <String>insts= new ArrayList<String>();
	static ArrayList <String>valeur= new ArrayList<String>();
	static ArrayList <String>phrase= new ArrayList<String>();
	
	public  boolean isInt(String str) {
		return str.matches("\\d+");
	}

	public boolean isFloat(String str) {
		return str.matches("\\d+\\.\\d+");
	}

	public  boolean isId(String str) {
		return str.matches("[a-z|A-Z](_?([a-z|A-Z]|\\d)+)*");
	}

	
	
	
	int ligne=0,token=0;
	public static ArrayList<String> lexique(String compila,ArrayList <String>insts,ArrayList <String> phrase) {
		int i;
		int line;
		String semantique= "";
		String inst = "";
		lex t= new lex();
		ArrayList <String> tokens =new ArrayList<String>();
		/* Le chemin vers le fichier à lire */
		String pathFichier=compila;

		BufferedReader fluxEntree=null;
		try {
			/* Création du flux vers le fichier texte */
			fluxEntree = new BufferedReader(new FileReader(pathFichier));

			/* Lecture d'une ligne */
			String ligneLue = fluxEntree.readLine();
			
			
			line =0; 
			while(ligneLue!=null){
				line++;
				phrase.add(ligneLue);
				inst="";semantique="";
				String[] mots = ligneLue.split(" ");
				i=0;
				while(i<mots.length) {

					String MSG ="";	String Comment=""; char cot='"';


					switch(mots[i]) {


					case "Start_Program": System.out.println("Start_Program : Mot reserve debut d un programme");
					tokens.add("Start_Program : Mot reserve debut d un programme");
					inst+="STARTP";
					//program[ligne][i]="STARTP";
					break;

					case "Int_Number": System.out.println("Int_Number :Mot reserve Déclaration d un entier");
					tokens.add("Int_Number :Mot reserve Déclaration d un entier");
					inst+="INT ";
					//program[ligne][i]="INT";
					break;

					case "Real_Number": System.out.println("Real_Number :Mot reserve Déclaration d un reel");
					tokens.add("Real_Number :Mot reserve Déclaration d un reel");
					inst+="FLOAT ";
					//program[ligne][i]="FLOAT";

					break;

					case "Give": System.out.println("Give : Mot reserve affectation val a var");
					tokens.add("Give : Mot reserve affectation val a var");
					inst+="GIVE ";
					//program[ligne][i]="GIVE";

					break;

					case "If": System.out.println("If :Mot reserve pour Condition");
					tokens.add("If :Mot reserve pour Condition");
					inst+="IF ";
					//program[ligne][i]="IF";

					break;

					case "Else": System.out.println("Else :Mot reserve else de if");
					tokens.add("Else :Mot reserve else de if");
					inst+="ELSE";
					//program[ligne][i]="ELSE";

					break;

					case "Start": System.out.println("Start :Mot reserve Debut bloc if/else");
					tokens.add("Start :Mot reserve Debut bloc if/else");
					inst+="STARTIF";
					//program[ligne][i]="STARTIF";

					break;

					case "Finish": System.out.println("Finish :fin du bloc if/else");
					tokens.add("Finish :fin du bloc if/else");
					inst+="ENDIF";
					//program[ligne][i]="ENDIF";

					break;

					case "Affect": System.out.println("Affect :Mot reserve Affectation var a var");
					tokens.add("Affect :Mot reserve Affectation var a var");
					inst+="AFFECT ";
					//program[ligne][i]="AFFECT";

					break;

					case "to": System.out.println("to :Mot reserve");
					tokens.add("to :Mot reserve");
					inst+="TO ";
					//program[ligne][i]="TO";

					break;

					case "ShowMes": System.out.println("ShowMes :Mot reserve pour message");
					tokens.add("ShowMes :Mot reserve pour message");
					inst+="SHOWM ";
					//program[ligne][i]="SHOWM";

					break;

					case "ShowVal": System.out.println("ShowVal :Mot reserve show valeur d une variable");
					tokens.add("ShowVal :Mot reserve show valeur d une variable");
					inst+="SHOWV ";
					//program[ligne][i]="SHOWV";

					break;

					case "End_Program": System.out.println("End_Program :Mot reserve fi du programme");
					tokens.add("End_Program :Mot reserve fi du programme");
					inst+="ENDP";
					//program[ligne][i]="ENDP";

					break;

					case ",": System.out.println(", : Mot reserve virgule");
					tokens.add(", : Mot reserve virgule");
					inst+="VIR ";
					//program[ligne][i]="VIR";

					break;

					case ";;": System.out.println(";; : Mot reserve fin d une instruction");
					tokens.add(";; : Mot reserve fin d une instruction");
					inst+="FINLIGNE ";
					//program[ligne][i]="FINLIGNE";

					break;

					case "<": System.out.println("< : inferieur");
					tokens.add("< : inferieur");
					inst+="OP ";
					//program[ligne][i]="OP";

					break;

					case ">": System.out.println("> : superieur");
					tokens.add("> : superieur");
					inst+="OP ";
					//program[ligne][i]="OP";

					break;

					case "--": System.out.println("-- : Mot reserver");
					tokens.add("-- : Mot reserver");
					inst+="TIR ";
					//program[ligne][i]="TIR";


					break;

					case ">=": System.out.println(">= : inferieur ou egale");
					tokens.add(">= : inferieur ou egale");
					inst+="OP ";
					//program[ligne][i]="OP";

					break;

					case "<=": System.out.println("<= : superieur ou egale");
					tokens.add("<= : superieur ou egale");
					inst+="OP ";
					//program[ligne][i]="OP";

					break;

					case "==": System.out.println("== : egale");
					tokens.add("== : egale");
					inst+="OP ";
					//program[ligne][i]="OP";

					break;

					case "!=": System.out.println("!= : non egale");
					tokens.add("!= : non egale");
					inst+="OP ";
					//program[ligne][i]="OP";
					
					break; 
					case "\"": 
						
						
						MSG=""+cot;

							i++;

							while(mots[i].charAt(0)!=cot) {

								MSG=MSG+" "+mots[i];

								i++;

							}


							MSG=MSG+'"';
							System.out.println(MSG+" :c'est un message");

							tokens.add(MSG+" :c'est un message");
							inst+="MSG ";
							//program[ligne][i]="MSG";


						break;
						
						
						
					case "":
					tokens.add("VIDE");
					inst+="VIDE ";
					//program[ligne][i]="VIDE";

					break; 

					case "//.": System.out.println("//. :Mot reserve pour commentaire");
					tokens.add("//. :Mot reserve pour commentaire");
					inst+="COMNT ";
					//program[ligne][i]="COMNT";

					i++;

					while(i<mots.length){
						Comment=Comment+" "+mots[i];
						i++;
						

					}
					if(i==mots.length) {
						System.out.println(Comment+" : c'est un Commentaire");
						tokens.add(Comment+" : c'est un Commentaire");
					}

					break;

					case ":": System.out.println(": : Mot reserve");
					tokens.add(": : Mot reserve");
					inst+="DP ";
					//program[ligne][i]="DP";


					break;


					default: 

						if(t.isFloat(mots[i])) {System.out.println(mots[i]+" : nbr reel"); 
						tokens.add(mots[i]+" : nbr reel");
						inst+="FVAR ";
						//program[ligne][i]="FVAR";
						semantique += "FVAR ";

						}

						else if(t.isInt(mots[i])) {System.out.println(mots[i]+" : nbr enter");
						tokens.add(mots[i]+" : nbr enter");
						inst+="IVAR ";
						//program[ligne][i]="IVAR";
						semantique += "IVAR ";


						}
						else if(t.isId(mots[i])) {System.out.println(mots[i]+" : identificateur");
						tokens.add(mots[i]+" : identificateur");
						inst+="ID ";
					    semantique +=mots[i]+" ";
						//program[ligne][i]="ID";


						}

						else {System.out.println("erreur lexicale dans la ligne "+line+" le "+i+" mot");


						}

						break; 




					}





					i++;


				}


				//insts.add(tokens.toString());
				
			
				 insts.add(inst);
				
				 valeur.add(semantique);


				ligneLue = fluxEntree.readLine();
			}
		}
		catch(IOException exc){
			exc.printStackTrace();
		}
		finally{
			try{
				if(fluxEntree!=null){
					/* Fermeture du flux vers le fichier */
					fluxEntree.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		

		  
			   
		return tokens;


		

		
		
		
	

	}
		
	
	
	
	
	
	
	
	

	
	
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
   
   // semantique smntq= new semantique();
   // smntq.semantiques();
    
  
    
    
		//::for(int i=0;i<insts.size();i++)
    // System.out.println(insts.get(0));
	//	syntax stx= new syntax();

   // stx.P(insts);

		
}

	
}