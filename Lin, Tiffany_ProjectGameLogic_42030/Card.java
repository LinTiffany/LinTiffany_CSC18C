/*
 * Author: Tiffany Lin
 * Date: 05/20/2015
 * Project Game Logic: Card Class
 * Description: Create a BlackJack Game between user and computer dealer
 */
import java.util.Random;
public class Card{
	private String suit;
	private String number;
	private String hashCode;
	private int cardNum;
	private int suitNum;
	public Card(){//pick a random card
		Random rand=new Random();
		suitNum=rand.nextInt(4)+1;
		cardNum=rand.nextInt(13)+1;
		setSuit();
		setNum();
		hashCode=hash(suit, number);
	}
	public void setSuit(){
			if(suitNum==1){
				suit="Diamonds";
			}else if(suitNum==2){
				suit="Clubs";
				}else if(suitNum==3){
				suit="Hearts";
				}else if(suitNum==4){
					suit="Spades";
					}
	}
	public void setNumber(int n){
		cardNum=n;
	}
	public void setNum(){
		if(cardNum==13){
			number="King";
			cardNum=10;
			}else if(cardNum==12){
				number="Queen";
				cardNum=10;
				}else if(cardNum==11){
					number="Jack";
					cardNum=10;
					}else if(cardNum==1){
						number="Ace";
						}else{//2-10
							number=String.valueOf(cardNum);
							}	
	}
	public String getSuit(){return suit;}
	public String getNumber(){return number;}
	public String getHash(){return hashCode;}
	public int getSuitNumber(){return suitNum;}
	public int getCardNumber(){return cardNum;}
	public String hash(String s, String n){
		String code=s.substring(0,1)+n;//ex: H1 (Ace of Hearts) or S12 (Queen of Spades)
		return code;
	}
	public String toString(){
		return number+" of "+suit;
	}
}
