/*
 * Author: Tiffany Lin
 * Date: 05/20/2015
 * Project Game Logic: Game Class
 * Description: Create a BlackJack Game between user and computer dealer
 */
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;
import java.util.Scanner;
public class Game{
	HashMap<String, Vector<Card>> round = new HashMap<String, Vector<Card>>();
	HashMap<String, Integer> scores = new HashMap<String, Integer>();
	Deck gameDeck;
	static int playerScore=0;
	static int dealerScore=0;
	static Scanner scan=new Scanner(System.in);
	public Game(){
		gameDeck=new Deck();
	}
	public int deal(String name){//if player p chooses to add another card to hand
			Vector<Card> newCards=round.get(name);//grab the current hand of player name
			Card c=gameDeck.draw();
			System.out.println("Picked up: "+c.toString());
			newCards.add(c);//add the new randomly picked card to the current hand
			round.put(name, newCards);//put new card into existing hand
			if(name.equals("PLAYER")){
				playerScore = calculateScore(name, newCards);
				if(playerScore>21){//if total card value is over 21
					System.out.println("Sorry! You're now over 21. You're out.");
					scores.put(name, playerScore);
					return 0;	
				}else if(playerScore==21){//if exactly 21
					System.out.println("Congrats! You got Blackjack!");
					round.put(name, newCards);
					scores.put(name, playerScore);
					return 1;
				}else{//if under 21
					round.put(name, newCards);
					scores.put(name, playerScore);
					return -1;
				}
			}else if(name.equals("COMPUTER")){
				dealerScore=dealerDeal(name, newCards);
				if(dealerScore>21){//if total card value is over 21
					System.out.println("Dealer is now over 21. OUT.");
					scores.put(name, dealerScore);
					return 0;				
				}else if(dealerScore==21){//if exactly 21
					System.out.println("Dealer has Blackjack!");
					round.put(name, newCards);
					scores.put(name, dealerScore);
					return 1;
				}else{//if under 21
					round.put(name, newCards);
					scores.put(name, dealerScore);
					return -1;
				}
			}
			return -1;
	}
	public int calculateScore(String name, Vector<Card>list){
		int totalValue=0;
		int answer=1;
	//size is dynamically changed
		for(int i=0;i<list.size();i++){
			Card tempCard=list.get(i);
			if(tempCard.getNumber().equals("Ace")){
				System.out.println("One of your cards is Ace. Would you like the Ace to be a value of 1 or 11?");
				answer=scan.nextInt();
				if(answer==11){
					totalValue+=11;
					//need to set the ACE in list equal to 11
					tempCard.setNumber(11);
					list.set(i, tempCard);
				}else if(answer==1){
					totalValue+=1;
					tempCard.setNumber(1);
					list.set(i, tempCard);
				}else{
					System.out.println("INVALID ANSWER. Can only be 1 or 11. Automatic assignment of 1");
					totalValue+=tempCard.getCardNumber();
					list.set(i,tempCard);
				}
			}else{
				totalValue+=tempCard.getCardNumber();
			}
		}//put totalValue into scores
		scores.put(name,totalValue);	
		return totalValue;
	}
	public int dealerDeal(String name, Vector<Card>list){
		int totalValue=0;
		int acePosition=0;
		boolean present=false;
		for(int i=0;i<list.size();i++){
			if(!list.get(i).getNumber().equals("Ace")){
				totalValue+=list.get(i).getCardNumber();
			}else{
				acePosition=i;
				present=true;
			}
		}
		Card tempCard=new Card();
		if (totalValue<=10&&present==true){
			tempCard.setNumber(11);
			list.set(acePosition, tempCard);
			totalValue+=11;
		}else if(totalValue>10&& present==true){
			tempCard.setNumber(1);
			list.set(acePosition,tempCard);
			totalValue+=1;
		}
		return totalValue;
	}
	public void determineWinner(int pScore, int dScore){
		if(pScore==21 && dScore!=21){//player has hit 21 and dealer did not
			System.out.println("CONGRATS! YOU HAVE WON OVER THE DEALER!");
		}else if(dScore==21 && pScore!=21){//dealer has hit 21 and player did not
			System.out.println("SORRY! YOU LOST! DEALER WON");
		}else if(dScore>21&&pScore<=21){//dealer went over 21 and player is still under or at 21
			System.out.println("CONGRATS! YOU HAVE WON OVER THE DEALER!");
		}else if(pScore>21 &&dScore<=21){//if player went over 21 and dealer is still under or at 21
			System.out.println("SORRY! YOU LOST! DEALER WON");
		}else if(pScore<dScore && dScore<21){//both under 21 & if dealer's score is higher than player score
			System.out.println("SORRY! YOU LOST! DEALER WON");
		}else if(dScore<pScore && pScore<21){// both under 21 & if player's score is higher than dealer score
			System.out.println("CONGRATS! YOU HAVE WON OVER THE DEALER!");
		}else if(dScore==pScore){
			if(round.get("PLAYER").size()>round.get("COMPUTER").size()){
				System.out.println("CONGRATS! YOU HAVE WON OVER THE DEALER!");
			}else if(round.get("PLAYER").size()<round.get("COMPUTER").size()){
				System.out.println("SORRY! YOU LOST! DEALER WON");
			}else{
				System.out.println("DEALER AND PLAYER TIED.");
			}
		}else{//both out
			System.out.println("BOTH LOST. CONSIDERED TIE.");
		}
	}
	public void start(){
		//shuffles deck
		gameDeck.shuffle();
		//add players to the game
		System.out.println("[YOU]");
		Vector<Card> youCard=new Vector<>();
		Vector<Card> compCard=new Vector<>();
		for(int i=0;i<2;i++){
			youCard.add(gameDeck.draw());
			System.out.println(youCard.get(i).toString());
			compCard.add(gameDeck.draw());
		}
		round.put("PLAYER", youCard);
		round.put("COMPUTER", compCard);
		//print out current list of players with cards
		System.out.println("[CURRENT GAME]");
		for (Map.Entry<String, Vector<Card>> entry : round.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		//check to see if player wants to keep going***
		String answer="YES";
		int count=0;
		while(answer.equals("YES")){
			System.out.println("Would you like to be dealt another card?(YES/NO)");
			answer=scan.next();
			answer=answer.toUpperCase();
			if(answer.equals("YES")){
				int result=deal("PLAYER");
				if(result==0||result==1){
					answer="NO";
				}
				count++;	
			}else if(answer.equals("NO")&&count==0){
				System.out.println("You do not want more cards.");
				scores.put("PLAYER", calculateScore("PLAYER",round.get("PLAYER")));
			}else if(answer.equals("NO")&&count>0){
				System.out.println("You chose not to pick up anymore cards.");
				scores.put("PLAYER", playerScore);
			}else{
				System.out.println("INVALID RESPONSE.");
				answer="YES";
			}
		}
		System.out.println("-------");
		System.out.println("Please wait for dealer...");
		System.out.println("-------");
		//COMPUTER'S TURN (DEALER)
		boolean notDone=true;
		while(notDone){
			int dealerSum=dealerDeal("COMPUTER",round.get("COMPUTER"));
			scores.put("COMPUTER",dealerSum);
			if(dealerSum<17){
				deal("COMPUTER");
			}else{
				System.out.println("DEALER STAYS AT "+dealerSum);
				notDone=false;
			}
		}
		//COMPARE PLAYER AND COMPUTER TO SEE WHO HAS THE HIGHER SCORE
		System.out.println("-------");
		System.out.println("ROUND SCORES: ");
		int[] tally=new int[2];int n=0;
		for(Map.Entry<String, Integer>entry1: scores.entrySet()){
			System.out.println(entry1.getKey() + " "+entry1.getValue());
			tally[n]=(entry1.getValue());
			n++;
		}
		System.out.println("=====================");
		determineWinner(tally[0],tally[1]);		
	}	
	public static void main(String[] args){
		System.out.println("WELCOME TO BLACKJACK GAME");
		System.out.println("========================");
		
		Game newGame=new Game();
		newGame.start();
	}
}
