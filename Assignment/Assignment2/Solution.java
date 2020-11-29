import java.util.*;

public class Solution<Key extends Comparable<Key>, Value>  {
    private Node root;
    int size = 0;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        if(size == 0){
        	return true;
        }
        return false;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
      return size;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
    	Node curNode = root;
       if(key == null){
       		throw new IllegalArgumentException("There is not key conatin in the root");
       }
       else{
       		while(curNode.key != key){
       			int cmp = key.compareTo(curNode.key);
       			if(cmp < 0){
       				curNode = curNode.left;
       			}
       			else{
       				curNode = curNode.right;
       			}
       		}
       		return true;
       }
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
    	if(key == null){
	        throw new IllegalArgumentException("argument to get value() is null");
	    }
        Node curNode = root;
        while(curNode.key != key){
        	int cmp = key.compareTo(curNode.key);
        	if(cmp < 0){
        		curNode = curNode.left;
        	}
	        else if(cmp > 0){
	        	curNode = curNode.right;
	        }
        }
        if(curNode.key == key){
        	System.out.println(curNode.val);
        }
        return curNode.val;
    }

    // /**
    //  * Inserts the specified key-value pair into the symbol table, overwriting the old 
    //  * value with the new value if the symbol table already contains the specified key.
    //  * Deletes the specified key (and its associated value) from this symbol table
    //  * if the specified value is {@code null}.
    //  *
    //  * @param  key the key
    //  * @param  val the value
    //  * @throws IllegalArgumentException if {@code key} is {@code null}
    //  */
    public void put(Key key, Value val) {
        Node newNode = new Node(key,val);
        if(root == null){
        	root = newNode;
        }
        else{
        	Node curNode = root;
        	Node parent;
        	while(true){
        		parent = curNode;
        		int cmp = key.compareTo(curNode.key);
        		if(cmp < 0){
        			curNode = curNode.left;
        			if(curNode == null){
        				parent.left = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        		else if(cmp > 0){
        			curNode = curNode.right;
        			if(curNode == null){
        				parent.right = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        	}
        }
        size = size + 1;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
       if(isEmpty()){
       	throw new NoSuchElementException("There is no element in the tree");
       }
       else{
       		Node curNode = root;
       		while(curNode.left != null){
       			curNode = curNode.left;
       		}
       		return curNode.key;
       }
    } 

    public Key max() { 
        if(isEmpty()){
       		throw new NoSuchElementException("There is no element in the tree");
       }
       else{
       		Node curNode = root;
       		while(curNode.right != null){
       			curNode = curNode.right;
       		}
       		return curNode.key;
       }
    } 

   

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
	public Key floor(Key key){
		if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node curNode = root;
		Node parent = null;
		while(curNode != null){
			parent = curNode;
			int cmp = key.compareTo(parent.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp == 0){
				return parent.key;
			}
			if(cmp < 0){
				curNode = parent.left;
			}
			//This condition is for checking floor of given key in right side
			else if(cmp > 0){
				curNode = parent.right;
				//This condition returns previous parent as floor of given key as it is less the right parent
				int cm = key.compareTo(curNode.key);
				if(cm < 0 ){
					return parent.key;
				}
				//This is for if the key is still greater then right parent
				else{
					curNode = parent.right;
				} 
			}
		}
		return parent.key;
	}

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */

    //Return key of rank k.
    public Key select(Key key){
		if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node curNode = root;
		Node parent = null;
		while(curNode != null){
			parent = curNode;
			int cmp = key.compareTo(parent.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp > 0){
				curNode = parent.right;
			}
			//This condition is for checking floor of given key in left side
			else if(cmp < 0){
				curNode = parent.left;
				//This condition checks if key is greater than left parent then it returns the curNode 
				int cm = key.compareTo(parent.key);
				if(cm > 0 ){
					return curNode.key;
				}
				//This is for if the key is still less then left parent
				else{
					curNode = parent.left;
				} 
			}
		}
		return parent.key;
	}
    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public void keys(Key lo, Key hi){
    	if(lo == null || hi == null){
        	throw new IllegalArgumentException("calls keys() with a null key");
        }
    	Node curNode = root;
    	Node parent;
        while(curNode != null) {
        	parent = curNode;
        	if(parent == null) { 
            	return; 
        	} 
        	int cmp = lo.compareTo(parent.key);
    		int cm = hi.compareTo(parent.key); 
            if (cmp < 0) { 
            	curNode = parent.left;
        	}
        	if (cmp <= 0 && cm >= 0) { 
            	System.out.print(parent.key + " "); 
        	} 
        	if (cm > 0) { 
            	curNode = parent.right;
        	}
        }
    }
    // public void inOrderTraversalNode(Node curNode){
    // 	if(curNode != null){
    // 		inOrderTraversalNode(curNode.left);
    // 		System.out.println(curNode);
    // 		inOrderTraversalNode(curNode.right);
    // 	}
    // }
   
    /* Run the program by giving the approriate command obtained from
    input files through input.txt files. The output should be displayed
    exactly like the file output.txt shows it to be.*/
  
    public static void main(String[] args) { 
        Solution <String, Integer> theTree = new Solution <String, Integer>();
        theTree.put("ABDUL",1);
        theTree.get("ABDUL");
        theTree.put("HRITHIK",2);
        theTree.put("SAI",3);
        theTree.put("SAMAL",6);
        theTree.get("SAI");
        theTree.put("TASHI",4);
        System.out.println(theTree.size());
        System.out.println(theTree.min());
        System.out.println(theTree.floor("HRITHIK"));
        System.out.println(theTree.floor("HAHA"));
        System.out.println(theTree.select("HRITHIKH"));
        theTree.keys("ABDUL","TASHI");
        System.out.println();
        theTree.put("CHIMI",5);
        theTree.put("SAMAL",4);
        theTree.get("SAMAL");
        theTree.put("NIMA",7);
        System.out.println(theTree.size());
        theTree.get("CHIMI");
        System.out.println(theTree.floor("CHIMI"));
        theTree.put("SONAM",8);
        theTree.keys("ABDUL","TASHI");
    }
}
