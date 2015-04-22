
public class BinaryNode {
	String data;
	BinaryNode left=null;
	BinaryNode right=null;
	public BinaryNode(){
		data="";
	}
	public BinaryNode(String value){
		data=value;
	}

	public boolean add(String value) {

		int index = value.compareTo(data);
		
		if (index == 0 ) { // we found the same value,
			return false;
		}
		else if (index < 0) {	// less value, build left node.
			if (left == null) {
				left = new BinaryNode(value); // create new node.
				return true;
			} else {
				return left.add(value);	// continue left side to ADD.
			}
		} else {	// grater value, build right node.
			if (right == null) {
				right = new BinaryNode(value);	// Create new right node.
				return true;
			} else {
				return right.add(value);// continue right side to ADD.
			}
		}

	}
	
	public boolean search(String value) {


		int index = value.compareTo(data);
		
		if (index == 0 ) { // FOUND IT.
			return true;
		}
		
		else if (index < 0) {	// less value, search left side.
			if (left == null) {
				return false;	// NOT FOUND.
			} else {
				return left.search(value);	// continue left side SEARCH.
			}
			
		} else {	// grater value, search right node.
			if (right == null) {
				return false;	// NOT FOUND.
			} else {
				return right.search(value);// continue right side SEARCH.
			}
		}
	}
}
