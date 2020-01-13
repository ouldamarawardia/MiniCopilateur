package lex;

import java.util.ArrayList;

public class syntax {
int ligne=0,token=0;
public ArrayList<String> istruction=new ArrayList<String>();
public ArrayList<String> S=new ArrayList<String>();




	public ArrayList<String> P(ArrayList<String>P) {
		// TODO Auto-generated method stub
	
		if(ligne<P.size()&&P.get(0).equals("STARTP")) {
			System.out.println("		->Debut de program roconnue");
			S.add(" ->Debut de program roconnue");
			istruction.add(" ");
			ligne++;
			insts(P);
			if(ligne<P.size()&&P.get(ligne).equals("ENDP")) {
				System.out.println("		->Fin de program roconnue");
				S.add(" ->Fin de program roconnue");
				istruction.add(" ");
				ligne++;
				if(ligne<P.size()) {
					if(P.get(ligne).equals("COMNT ")) {
						System.out.println("		->Commentaire roconnue");
						S.add(" ->Commentaire roconnue");
						istruction.add(" ");
						ligne++;
						comnt(P);
					}

				}
			}
			 
			else {
				System.out.println("	->perdu dans la ligne  "+(ligne+1));
				S.add("Ens_Program	->perdu dans la ligne  "+(ligne+1));
				insts(P);
			}
		}
		else {
			System.out.println("Start_Program -> perdu");
			S.add("Start_Program -> perdu");
			insts(P);
		}
		return S;
		
	}

	private void comnt(ArrayList<String> P) {
		// TODO Auto-generated method stub
		if(ligne<P.size()&& P.get(ligne).equals("COMNT ")) {
			System.out.println("		->Commentaire roconnue");
			S.add(" ->Commentaire roconnue");
			ligne++;
			comnt(P);
		}
		
	}

	private void insts(ArrayList<String> P) {
		// TODO Auto-generated method stub
		if(ligne<P.size() && P.get(ligne)!="ENDP") {
		inst(P);
		}
		
		
	}

	private void inst(ArrayList<String> P) {
		// TODO Auto-generated method stub
		String inst=P.get(ligne);
		String[] mots = inst.split(" ");
		
		
		switch(mots[token]){
		
		case "GIVE" : token++;
		
		if(mots[token].equals("ID")) {token++;
			if(mots[token].equals("DP")) {token++;
				if(mots[token].equals("IVAR")||mots[token].equals("FVAR")) {token++;
					if(mots[token].equals("FINLIGNE")) {token++;
						if(token==mots.length) {
							System.out.println("		->Affectation une valeur a une variable roconnue");
							S.add(" ->Affectation une valeur a une variable roconnue");
							istruction.add("GIVE");
							ligne++; token=0; insts(P);
						}
						else{System.out.println("erreur syntaxique dans la ligne  "+(ligne+1));
						S.add("erreur syntaxique dans la ligne  "+(ligne+1));
						ligne++;
						token=0;
						 insts(P);
						}
					}
					else {
						System.out.println(";;	-> perdu dans la ligne  "+(ligne+1));
						S.add(";;	-> perdu dans la ligne  "+(ligne+1));
						ligne++;
						token=0;
						 insts(P);
					}
				
				}
				else {
					System.out.println("variable	-> perdu dans la ligne  "+(ligne+1)); 
					S.add("variable	-> perdu dans la ligne  "+(ligne+1));
					ligne++;
					token=0;
					 insts(P);
				}
			}
			else {
				System.out.println(":	-> perdu dans la ligne  "+(ligne+1));
				S.add(":	-> perdu dans la ligne  "+(ligne+1));
				ligne++;
				token=0;
				 insts(P);
			}
		}
		else {System.out.println("identifient ->perdu"); 
		S.add("identifient ->perdu");
		ligne++;
		token=0;
		 insts(P);
		}
		
		break;
		
		
		case "AFFECT" : token++;
		if(mots[token].equals("ID")) {token++;
		 	if(mots[token].equals("TO")) {token++;
		 		if(mots[token].equals("ID")) {token++;
		 			if(mots[token].equals("FINLIGNE")) {token++;
		 				if(token==mots.length) {	
		 					System.out.println("		->Affectation une variable a une variable roconnue");
		 					S.add(" ->Affectation une variable a une variable roconnue");
		 					istruction.add("AFFECT");
		 					ligne++; token=0; insts(P);
						}
			 			else{System.out.println("erreur syntaxique dans la ligne  "+(ligne+1));
			 			S.add("erreur syntaxique dans la ligne  "+(ligne+1));
			 			ligne++;
			 			token=0;
			 			 insts(P);
			 			}
		 			}
		 			else {
						System.out.println(";;	-> perdu dans la ligne "+(ligne+1));
						S.add(";;	-> perdu dans la ligne "+(ligne+1));
						ligne++;
						token=0;
						 insts(P);
					}
		 		}
		 		else {
					System.out.println("identificateur	-> perdu dans la ligne  "+(ligne+1));
					S.add("identificateur	-> perdu dans la ligne  "+(ligne+1));
					ligne++;
					token=0;
					 insts(P);
		 		}
		 	}
		 	else {
				System.out.println("to	-> perdu dans la ligne  "+(ligne+1));
				S.add("to	-> perdu dans la ligne  "+(ligne+1));
				ligne++;
				token=0;
				 insts(P);
			}
		}
		else {
			System.out.println("identificateur	-> perdu dans la ligne  "+(ligne+1));
			S.add("identificateur	-> perdu dans la ligne  "+(ligne+1));
			ligne++;
			token=0;
			 insts(P);
		}
			
		break;
		
		
		case "FLOAT":token++;
		if(mots[token].equals("DP")) {token++;
	 		if(mots[token].equals("ID")) {token++; int nbId=vars(mots);
	 			if(mots[token].equals("FINLIGNE")) {token++;
	 				if(token==mots.length) {
	 					System.out.println("		->Declaration de "+nbId+" reel roconnue");
	 					S.add(" ->Declaration de "+nbId+" reel roconnue");
	 					istruction.add("FLOAT");
	 					ligne++;
	 					token=0;
	 					insts(P);
	 				}
	 			}
		 		else {
					System.out.println(";;	-> perdu dans la ligne  "+(ligne+1));
					S.add(";;	-> perdu dans la ligne  "+(ligne+1));
					ligne++;
					token=0;
					 insts(P);
				}
		 	}
		 	else {
				System.out.println("identifiant	-> perdu dans la ligne  "+(ligne+1));
				S.add("identifiant	-> perdu dans la ligne  "+(ligne+1));
				ligne++;
				token=0;
				 insts(P);
			}
		}
		else {
			System.out.println(":	-> perdu dans la ligne  "+(ligne+1));
			S.add(":	-> perdu dans la ligne  "+(ligne+1));
			ligne++;
			token=0;
			 insts(P);
		}	
	 			
	 			
		break;
		
		
		
		case "INT" : token++;
		if(mots[token].equals("DP")) {token++;
	 		if(mots[token].equals("ID")) {token++; int nbId=vars(mots);
	 			if(mots[token].equals("FINLIGNE")) {token++;
	 				if(token==mots.length) {
	 					System.out.println("		->Declaration de "+nbId+" entier roconnue");
	 					S.add(" ->Declaration de "+nbId+" entier roconnue");
	 					istruction.add("INT");
	 					ligne++;
	 					token=0; insts(P);
	 				}
	 			}
		 		else {
					System.out.println(";;	-> perdu dans la ligne  "+(ligne+1));
					S.add(";;	-> perdu dans la ligne  "+(ligne+1));
					ligne++;
					token=0;
					 insts(P);
				}
		 	}
		 	else {
				System.out.println("identifiant	-> perdu dans la ligne  "+(ligne+1));
				S.add("identifiant	-> perdu dans la ligne  "+(ligne+1));
				ligne++;
				token=0;
				 insts(P);
			}
		}
		else {
			System.out.println(":	-> perdu dans la ligne  "+(ligne+1));
			S.add(":	-> perdu dans la ligne  "+(ligne+1));
			ligne++;
			token=0;
			 insts(P);
		}	
	 			
	 			
		break;
		
		case "SHOWV":token++;
		if(mots[token].equals("DP")) {token++;
		 	if(mots[token].equals("ID")) {token++;
		 		if(mots[token].equals("FINLIGNE")) {token++;
		 			if(token==mots.length) {
		 			System.out.println("	->Affichage de valeur d une variable  roconnue");
		 			S.add(" ->Affichage de valeur d une variable  roconnue");
		 			istruction.add(" ");
						ligne++;
						token=0; insts(P);
		 				}
		 			}
			 		else {
						System.out.println(";;	-> perdu dans la ligne  "+(ligne+1));
						S.add(";;	-> perdu dans la ligne  "+(ligne+1));
						ligne++;
						token=0;
						 insts(P);
					}
			 	}
			 	else {
					System.out.println("identifiant	-> perdu dans la ligne  "+(ligne+1));
					S.add("identifiant	-> perdu dans la ligne  "+(ligne+1));
					ligne++;
					token=0;
					 insts(P);
				}
			}
			else {
				System.out.println(":	-> perdu dans la ligne  "+(ligne+1));
				S.add(":	-> perdu dans la ligne  "+(ligne+1));
				ligne++;
				token=0;
				 insts(P);
			}	
		 	
		
		
			break;
			
		case "SHOWM":token++;
		if(mots[token].equals("DP")) {token++;
		 	if(mots[token].equals("MSG")) {token++;
		 		if(mots[token].equals("FINLIGNE")) {token++;
		 			if(token==mots.length) {
		 			System.out.println("		->Affichage d'un message  roconnue");
		 			S.add(" ->Affichage d'un message  roconnue");
		 			istruction.add(" ");
						ligne++;
						token=0; insts(P);
		 				}
		 			}
			 		else {
						System.out.println(";;	-> perdu dans la ligne  "+(ligne+1));
						S.add(";;	-> perdu dans la ligne  "+(ligne+1));
						ligne++;
						token=0;
						 insts(P);
					}
			 	}
			 	else {
					System.out.println("Message	-> perdu dans la ligne  "+(ligne+1));
					S.add("Message	-> perdu dans la ligne  "+(ligne+1));
					ligne++;
					token=0;
					 insts(P);
				}
			}
			else {
				System.out.println(":	-> perdu dans la ligne  "+(ligne+1));
				S.add(":	-> perdu dans la ligne  "+(ligne+1));
				ligne++;
				token=0;
				 insts(P);
			}	
		 	
		
		
			break;
			
			
		case "COMNT": token=0; ligne++; 
 			System.out.println("		->Commentaire  roconnue");
 			S.add(" ->Commentaire  roconnue");
 			istruction.add(" ");
 			insts(P);
 		break;
 		
 		
 		
		case "VIDE": token=0; ligne++; 
			System.out.println("->ligne vide roconnue");
			S.add(" ->ligne vide roconnue");
			istruction.add(" ");
			insts(P);
		break;

		
		
		case "IF":token++;
		int m=ligne;
		if(mots[token].equals("TIR")) {token++;
		 	if(mots[token].equals("ID")) {token++;
		 		if(mots[token].equals("OP")) {token++;
		 			if(mots[token].equals("ID")) {token++;
		 				if(mots[token].equals("TIR")) {token++;	
		 				System.out.println("->Condition Alors ");
		 				S.add(" ->Condition Alors ");
		 				 m=ligne;
		 				
		 				istruction.add("IF");
		 				if(token>=mots.length) {
		 					
		 					ligne++;
		 					token=0;
		 					BlocInst(P);
		 				}
		 				else {
		 					
		 					inst(P);
		 					S.set(m,"->Condition Alors "+S.get(m+1));
		 					S.remove(m+1);
		 				}
		 					Elif(P,mots);
		 					insts(P);
		 				}
		 				else {
							System.out.println("-- -> perdu dans la ligne  "+(ligne+1));
							S.add("-- -> perdu dans la ligne  "+(ligne+1));
							
							if(token>=mots.length) {
								ligne++;
								token=0;
								BlocInst(P);
			 				}
			 				else {
			 					token=7;
			 					inst(P);
			 					S.set(m,"->Condition Alors --	perdu dans la ligne"+S.get(m));
			 					S.remove(m);
						}
							
				 	}}
				 	else {
						System.out.println("Identificateur	-> perdu dans la ligne  "+(ligne+1));
						S.add("Identificateur -> perdu dans la ligne  "+(ligne+1));
						if(token>=mots.length) {
							ligne++;
							token=0;
							BlocInst(P);
		 				}
		 				else {
		 					token=7;
		 					inst(P);
		 					S.set(m,"->Condition Alors Identificateur	perdu dans la ligne"+S.get(m+1));
		 					S.remove(m+1);
					}
					}
				}
				else {
					System.out.println("opérateur -> perdu dans la ligne  "+(ligne+1));
					S.add("opérateur	-> perdu dans la ligne  "+(ligne+1));
					if(token>=mots.length) {
						ligne++;
						token=0;
						BlocInst(P);
	 				}
	 				else {
	 					token=7;
	 					inst(P);
	 					S.set(m,"->Condition Alors opérateur	perdu dans la ligne"+S.get(m+1));
	 					S.remove(m+1);
				}
				}
		 	}
		 	else {
				System.out.println("Identificateur	-> perdu dans la ligne  "+(ligne+1));
				S.add("Identificateur	-> perdu dans la ligne  "+(ligne+1));
				if(token>=mots.length) {
					ligne++;
					token=0;
					BlocInst(P);
 				}
 				else {
 					token=7;
 					inst(P);
 					S.set(m,"->Condition Alors Identificateur	perdu dans la ligne"+S.get(m+1));
 					S.remove(m+1);
			}
			}
		}
		else {
			System.out.println("--	-> perdu dans la ligne  "+(ligne+1));
			S.add("--	-> perdu dans la ligne  "+(ligne+1));
			if(token>=mots.length) {
				ligne++;
				token=0;
				BlocInst(P);
				}
				else {
					token=7;
					inst(P);
					S.set(m,"->Condition Alors --	perdu dans la ligne"+S.get(m+1));
					S.remove(m+1);
		}
		}
			
		break;
		
		
		
		
		
	}
		
		
		
		
	}

	private void Elif(ArrayList<String> P, String[] mots) {
		// TODO Auto-generated method stub
		
		if(ligne<P.size()&&P.get(ligne).equals("ELSE")) {
			System.out.println(" ->Sinon roconnue ");
			S.add("->Sinon roconnue ");
				ligne++; token=0;
				
				if(ligne<P.size()&&P.get(ligne).equals("STARTIF")) {
					BlocInst(P);
				}
				else {
					inst(P);
				}
		}
		else if(ligne<P.size()&&P.get(ligne).equals("VIDE")) {
			System.out.println("->vide roconnue ");
			S.add(" ->vide roconnue ");
			istruction.add(" ");
			ligne++;
			Elif(P,mots);
		}
		else if(ligne<P.size()&&P.get(ligne).equals("COMNT")) {
			istruction.add(" ");
			System.out.println("->commentaire roconnue ");
			S.add(" ->commentaire roconnue ");
			ligne++;
			Elif(P,mots);
		}
	}

	private void BlocInst(ArrayList<String> P) {
		// TODO Auto-generated method stub

		if(P.get(ligne).equals("STARTIF")) {
			System.out.println(" ->Debut d'un bloc roconnue");
			S.add(" ->Debut d'un bloc roconnue");
			istruction.add(" ");
			ligne++;
			insts(P);
			if(P.get(ligne).equals("ENDIF")) {
				
				System.out.println("->Fin d'un bloc roconnue");
				S.add(" ->Fin d'un bloc roconnue");
				istruction.add(" ");
				ligne++; 
			}
			else {
				System.out.println("Finish->perdu dans la ligne  "+(ligne+1));
				S.add("Finish->perdu dans la ligne  "+(ligne+1));
				ligne++; 
				insts(P);
			}
		}
		else {
			System.out.println("Start	->perdu dans la ligne  "+(ligne+1));
			S.add("Start->perdu dans la ligne  "+(ligne+1));
			ligne++; 
			insts(P);
		}
	}

	private int vars(String[] mots) {
		// TODO Auto-generated method stub
		
		if (token<mots.length && mots[token].equals("VIR")) {token++; 
			if(mots[token].equals("ID")) {token++;
			
			return (1+vars(mots));
			
				
			}
			else {
				System.out.println("identifiant	->perdu dans la ligne  "+(ligne+1));
				S.add("identifiant	->perdu dans la ligne  "+(ligne+1));
			}
		}
		return 1;
		
	}
}
