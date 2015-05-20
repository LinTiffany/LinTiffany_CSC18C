/*
 * Author: Tiffany Lin
 * Date: 05/20/2015
 * Project Game Logic: Deck Class
 * Description: Create a BlackJack Game between user and computer dealer
 */
import java.util.Stack;
public class Deck{
	Stack<Card> newStack=new Stack<>();
	Stack<String> hashStack=new Stack<>();
	int cardsInDeck;
	public Deck(){
		cardsInDeck=0;
		shuffle();
	}
	public void shuffle(){//resets deck and fills deck with cards
		Card newCard;
		while(cardsInDeck<52){//keep looping until we get deck all filled with cards
			newCard=new Card();//pick a random card
			if(newStack.size()==0){//if no cards in stack
				newStack.push(newCard);
				hashStack.push(newCard.getHash());
				cardsInDeck++;
			}else{//if more than 0 cards in stack
				boolean present=false;
				for(int i=0;i<hashStack.size();i++){
					if(hashStack.elementAt(i).equals(newCard.getHash())){
						present=true;
					}
				}//if not found in hashstack
				if(present==false){
					newStack.push(newCard);
					hashStack.push(newCard.getHash());
					cardsInDeck++;
				}
			}
		}
	}
	public Card draw(){//pull the top card off of the stack of cards
		hashStack.remove(hashStack.size()-1);
		cardsInDeck--;
		return newStack.pop();
	}
	public void printCards(){
		for(int i=0;i<newStack.size();i++){
			System.out.println(newStack.get(i).toString());
		}
	}
	public void printHash(){
		for(int i=0;i<hashStack.size();i++){
			System.out.println(hashStack.get(i).toString());
		}
	}
}
