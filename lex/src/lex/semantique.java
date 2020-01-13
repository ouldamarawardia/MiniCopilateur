package lex;


import java.util.ArrayList;

public class semantique {
int ligne=0,token=0;
public ArrayList<String> istruction=new ArrayList<String>();
public ArrayList<String> S=new ArrayList<String>();



boolean exist(String mot,ArrayList<String> tab) {
	for(int i=0;i<tab.size();i++) {
		if(mot==tab.get(i)) {
			return true;
		}
		i++;
	}
	return false;
}


ArrayList<String>  entiers = new ArrayList<String>();
ArrayList<String>  reels = new ArrayList<String>();

	public ArrayList<String> P(ArrayList<String>P,ArrayList<String>phrase) {
		// TODO Auto-generated method stub
	
		if(ligne<P.size()&&P.get(0).equals("STARTP")) {
			System.out.println(" ->Debut de program");
			S.add(" ->Debut de program");
			
			ligne++;
			insts(P,phrase);
			if(ligne<P.size()&&P.get(ligne).equals("ENDP")) {
				System.out.println(" ->Fin de program");
				S.add(" ->Fin de program");
				
				ligne++;
				if(ligne<P.size()) {
					if(P.get(ligne).equals("COMNT ")) {
						System.out.println("->Commentaire roconnue");
						S.add(" ->Commentaire roconnue");
					
						ligne++;
						comnt(P);
					}
				}
			}
		}
		else {
			insts(P,phrase);
		}
		return S;
		
	}

	private void comnt(ArrayList<String> P) {
		// TODO Auto-generated method stub
		if(ligne<P.size()&& P.get(ligne).equals("COMNT ")) {
			System.out.println("->Commentaire roconnue");
			S.add(" ->Commentaire roconnue");
			ligne++;
			comnt(P);
		}
		
	}

	private void insts(ArrayList<String> P,ArrayList<String> phrase) {
		// TODO Auto-generated method stub
		if(ligne<P.size() && P.get(ligne)!="ENDP") {
		inst(P,phrase);
		}
		
	}

	private void inst(ArrayList<String> P,ArrayList<String>  phrase) {
		// TODO Auto-generated method stub
		String inst=P.get(ligne);
		
		String inst1=phrase.get(ligne);
		String[] mots = inst.split(" ");
		String[] var = inst1.split(" ");
		
		
		switch(mots[token]){
		
		case "GIVE" : token++;
		
		if(mots[token].equals("ID")) {
			if(!entiers.contains(var[token])&&!reels.contains(var[token])) {
				S.add(" ->erreur variable  "+var[token]+" non declarer");
				ligne++;token=0;insts(P,phrase);
				
			}
			else {token++;
			if(mots[token].equals("DP")) {token++;
				if(mots[token].equals("IVAR")||mots[token].equals("FVAR")) {
					if(mots[token].equals("FVAR")&&entiers.contains(var[token-2])) {
	
						S.add(" ->erreur Affectation un reel "+var[token-2]+" a un entier "+var[token-1]);
						ligne++;
						token=0;
						insts(P,phrase);
					}
					else{token++;
						if(mots[token].equals("FINLIGNE")) {token++;
						if(token==mots.length) {
							
							System.out.println(inst1+" ->Affectation une valeur a une variable");
							S.add(" ->Affectation "+var[token]+" a une variable ");
							istruction.add("GIVE"); 
							ligne++; token=0; insts(P,phrase);
						}
						else{
						
						ligne++;
						token=0;
						insts(P,phrase);
						}
					}
					else {
						
						ligne++;
						token=0;
						insts(P,phrase);
					}
				
				}}
				else {
					
					ligne++;
					token=0;
					insts(P,phrase);
				}
			}
			else {
				
				ligne++;
				token=0;
				insts(P,phrase);
			}
			}}
		
		else {
		
		ligne++;
		token=0;
		insts(P,phrase);
		}
		
		break;
		
		
		case "AFFECT" : token++;
		if(mots[token].equals("ID")) {
			if(!entiers.contains(var[token])&&!reels.contains(var[token])) {
				S.add("->erreur variable  "+var[token]+" non declarer");
				ligne++;token=0;insts(P,phrase);
			
			}
			
			else{token++;
		 	if(mots[token].equals("TO")) {token++;
		 		if(mots[token].equals("ID")) {
		 			if(!entiers.contains(var[token])&&!reels.contains(var[token])) {
						S.add("->erreur variable  "+var[token]+" non declarer");
						ligne++;token=0;insts(P,phrase);
					
					}
		 			else {
		 			if(reels.contains(var[token-2])&&entiers.contains(var[token])) {
						S.add("->erreur Affectation de reel "+var[token]+" a  un entier");
						ligne++;token=0;insts(P,phrase);
					
					}
		 			else {
		 			token++;
		 		
		 			if(mots[token].equals("FINLIGNE")) {token++;
		 				if(token==mots.length) {	
		 					if(reels.contains(var[token-4])&&reels.contains(var[token-2])) {
		 					System.out.println("->Affectation une variable a une variable ");
		 					S.add(" ->Affectation d un reel a un reel ");
		 					}
		 					else if(entiers.contains(var[token-4])&&reels.contains(var[token-2])) {
			 					System.out.println("->Affectation une variable a une variable ");
			 					S.add(" ->Affectation d un entier a un reel ");
			 					}
		 					else if(entiers.contains(var[token-4])&&entiers.contains(var[token-2])) {
			 					System.out.println("->Affectation une variable a une variable ");
			 					S.add(" ->Affectation d un entier a un entier ");
			 					}
		 					
		 					istruction.add("AFFECT");
		 					ligne++; token=0; insts(P,phrase);
						}
			 			else {
			 				
			 			ligne++;
			 			token=0;
			 			insts(P,phrase);
			 			}
		 			}
		 			else {
						
						ligne++;
						token=0;
						insts(P,phrase);
					}
		 		}}}
		 		else {
					
					ligne++;
					token=0;
					insts(P,phrase);
		 		}
		 	}
		 	else {
				
				ligne++;
				token=0;
				insts(P,phrase);
			}
		}}
		else {
			
			ligne++;
			token=0;
			insts(P,phrase);
		}
			
		break;
		
		
		case "FLOAT":token++;
		if(mots[token].equals("DP")) {token++;
	 		if(mots[token].equals("ID")) {
	 			if(entiers.contains(var[token])) {
	 				S.add("->erreur "+var[token]+" est Deja declarer comme un entier");
	 				ligne++;
 					token=0;
 					insts(P,phrase);
				}
	 			else if(reels.contains(var[token])) {
	 				S.add("->erreur "+var[token]+" est Deja declarer comme un reel");
	 				ligne++;
 					token=0;
 					insts(P,phrase);
				}
	 			else {
	 				reels.add(var[token]);
	 			
	 			token++; int nbId=vars(mots);
	 			for(int j=nbId;j>0;j-=2)
	 				reels.add(var[token-j]);
	 			if(mots[token].equals("FINLIGNE")) {token++;
	 				if(token==mots.length) {
	 					System.out.println("->Declaration de "+nbId+" reel ");
	 					S.add("->Declaration de "+nbId+" reel ");
	 					istruction.add("FLOAT");
	 					ligne++;
	 					token=0;
	 					insts(P,phrase);
	 				}
	 			}
		 		else {
					
					ligne++;
					token=0;
					insts(P,phrase);
				}
	 			}}
		 	else {
				
				ligne++;
				token=0;
				insts(P,phrase);
			}
		}
		else {
			
			ligne++;
			token=0;
			insts(P,phrase);
		}	
	 			
	 			
		break;
		
		
		
		case "INT" : token++;
		if(mots[token].equals("DP")) {token++;
	 		if(mots[token].equals("ID")) {
	 			if(entiers.contains(var[token])) {
	 				S.add("->erreur "+var[token]+" est Deja declarer comme un entier");
	 				ligne++;
 					token=0;
 					insts(P,phrase);
				}
	 			else if(reels.contains(var[token])) {
	 				S.add("->erreur "+var[token]+" est Deja declarer comme un reel");
	 				ligne++;
 					token=0;
 					insts(P,phrase);
				}
	 			else {
	 				
	 				entiers.add(var[token]);
	 			
	 			
	 			token++; int nbId=vars(mots);
	 			for(int j=nbId;j>0;j-=2)
	 				entiers.add(var[token-j]);
	 			if(mots[token].equals("FINLIGNE")) {token++;
	 				if(token==mots.length) {
	 					System.out.println("->Declaration de "+nbId+" entier ");
	 					S.add(" ->Declaration de "+nbId+" entier ");
	 					istruction.add("INT");
	 					ligne++;
	 					token=0; insts(P,phrase);
	 				}
	 			}
		 		else {
					
					ligne++;
					token=0;
					insts(P,phrase);
				}
		 	}}
		 	else {
				
				ligne++;
				token=0;
				insts(P,phrase);
			}
		}
		else {
			
			ligne++;
			token=0;
			insts(P,phrase);
		}	
	 			
	 			
		break;
		
		case "SHOWV":token++;
		if(mots[token].equals("DP")) {token++;
		 	if(mots[token].equals("ID")) {
		 		if(!entiers.contains(var[token])&&!reels.contains(var[token])) {
					S.add("->erreur variable  "+var[token]+" non declarer");
					ligne++;token=0;insts(P,phrase);
				
				}
		 		else {
		 		token++;
		 		if(mots[token].equals("FINLIGNE")) {token++;
		 			if(token==mots.length) {
		 			System.out.println("->Affichage la valeur de "+var[token+2]);
		 			S.add(" ->Affichage la  valeur de "+var[token+2]);
		 			istruction.add(" ");
						ligne++;
						token=0; insts(P,phrase);
		 				}
		 			}
			 		else {
						
						ligne++;
						token=0;
						insts(P,phrase);
					}
			 	}}
			 	else {
					ligne++;
					token=0;
					insts(P,phrase);
				}
			}
			else {
				
				ligne++;
				token=0;
				insts(P,phrase);
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
						token=0; insts(P,phrase);
		 				}
		 			}
			 		else {
					
						ligne++;
						token=0;
						insts(P,phrase);
					}
			 	}
			 	else {
				
					ligne++;
					token=0;
					insts(P,phrase);
				}
			}
			else {
				
				ligne++;
				token=0;
				insts(P,phrase);
			}	
		 	
		
		
			break;
			
			
		case "COMNT": token=0; ligne++; 
 			System.out.println(" ->Commentaire  roconnue");
 			S.add(" ->Commentaire  roconnue");
 			istruction.add(" ");
 			insts(P,phrase);
 		break;
 		
 		
 		
		case "VIDE": token=0; ligne++; 
			System.out.println("		->ligne vide roconnue");
			S.add(" ->ligne vide roconnue");
			istruction.add(" ");
			insts(P,phrase);
		break;

		
		
		case "IF":token++;
		int m=ligne;
		if(mots[token].equals("TIR")) {token++;
		 	if(mots[token].equals("ID")) {
		 		if(!entiers.contains(var[token])&&!reels.contains(var[token])) {
					S.add("->erreur variable  "+var[token]+" non declarer");
					ligne++;token=0;insts(P,phrase);
				
				}
		 		else {
		 		
		 		token++;
		 		if(mots[token].equals("OP")) {token++;
		 			if(mots[token].equals("ID")) {
		 				if(!entiers.contains(var[token])&&!reels.contains(var[token])) {
							S.add("->erreur variable  "+var[token]+" non declarer");
							ligne++;token=0;insts(P,phrase);
						
						}
				 		else {
		 				token++;
		 				if(mots[token].equals("TIR")) {token++;	
		 				System.out.println("->Condition Alors ");
		 				S.add("->Condition Alors ");
		 				istruction.add("IF");
		 				if(token>=mots.length) {
		 					
		 					ligne++;
		 					token=0;
		 					BlocInst(P,phrase);
		 				}
		 				else {
		 					
		 					inst(P,phrase);
		 					S.set(m,"->Condition Alors "+S.get(m+1));
		 					S.remove(m+1);
		 					
		 				}
		 					Elif(P,mots,phrase);
		 					insts(P,phrase);
		 				}
		 				else {
							
							ligne++;
							token=0;
						}
				 		} 	}
		 			
				 	else {
						
				 		if(token>=mots.length) {
		 					
		 					ligne++;
		 					token=0;
		 					BlocInst(P,phrase);
		 				}
		 				else {
		 					token=7;
		 					inst(P,phrase);
		 					S.set(m,"->Condition Alors "+S.get(m+1));
		 					S.remove(m+1);
		 					
		 				}
					}
				}
				else {
					
					if(token>=mots.length) {
	 					
	 					ligne++;
	 					token=0;
	 					BlocInst(P,phrase);
	 				}
	 				else {
	 					token=7;
	 					inst(P,phrase);
	 					S.set(m,"->Condition Alors "+S.get(m+1));
	 					S.remove(m+1);
	 					
	 				}
				}
		 	}}
		 	else {
				
		 		if(token>=mots.length) {
 					
 					ligne++;
 					token=0;
 					BlocInst(P,phrase);
 				}
 				else {
 					token=7;
 					inst(P,phrase);
 					S.set(m,"->Condition Alors "+S.get(m+1));
 					S.remove(m+1);
 					
 				}
			}
		}
		else {
			
			if(token>=mots.length) {
					
					ligne++;
					token=0;
					BlocInst(P,phrase);
				}
				else {
					token=7;
					inst(P,phrase);
					S.set(m,"condition '--' ->perdu  "+S.get(m+1));
					S.remove(m+1);
					
				}
		}
			
		break;
		
		
		
		
		
	}
		
		
		
		
	}

	private void Elif(ArrayList<String> P, String[] mots,ArrayList<String> phrase) {
		// TODO Auto-generated method stub
		
		if(ligne<P.size()&&P.get(ligne).equals("ELSE")) {
			System.out.println("		->Sinon roconnue ");
			S.add("		->Sinon roconnue ");
				ligne++; token=0;
				
				if(ligne<P.size()&&P.get(ligne).equals("STARTIF")) {
					BlocInst(P,phrase);
				}
				else {
					inst(P,phrase);
				}
		}
		else if(ligne<P.size()&&P.get(ligne).equals("VIDE")) {
			System.out.println(" ->vide roconnue ");
			S.add(" ->vide roconnue ");
			istruction.add(" ");
			ligne++;
			Elif(P,mots,phrase);
		}
		else if(ligne<P.size()&&P.get(ligne).equals("COMNT")) {
			istruction.add(" ");
			System.out.println(" ->commentaire roconnue ");
			S.add(" ->commentaire roconnue ");
			ligne++;
			Elif(P,mots,phrase);
		}
	}

	private void BlocInst(ArrayList<String> P,ArrayList<String> phrase) {
		// TODO Auto-generated method stub

		if(P.get(ligne).equals("STARTIF")) {
			System.out.println(" ->Debut d'un bloc roconnue");
			S.add("->Debut d'un bloc roconnue");
			istruction.add(" ");
			ligne++;
			insts(P,phrase);
			if(P.get(ligne).equals("ENDIF")) {
				
				System.out.println(" ->Fin d'un bloc roconnue");
				S.add(" ->Fin d'un bloc roconnue");
				istruction.add(" ");
				ligne++; 
			}
			else {
				System.out.println("Finish	->perdu dans la ligne  "+(ligne+1));
				S.add("Finish	->perdu dans la ligne  "+(ligne+1));
				ligne++; 
			}
		}
		else {
			System.out.println("Start	->perdu dans la ligne  "+(ligne+1));
			S.add("Start	->perdu dans la ligne  "+(ligne+1));
			ligne++; 
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
