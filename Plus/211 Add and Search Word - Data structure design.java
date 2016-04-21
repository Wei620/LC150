/*
211 Add and Search Word - Data structure design
Design a data structure that supports the following two operations: 
void addWord(word)
bool search(word)


search(word) can search a literal word or a regular expression string containing only letters a-z or ’.’
A ‘.’ means it can represent any one letter.

For example:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true


Note:
 You may assume that all words are consist of lowercase letters a-z. 

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first. 
*/

/*****
1. Trie 的应用。 遇到 '.' 相当于当前字母(i)匹配当前任何非空子节点.
	枚举后续字母substring(i+1）的匹配。 递归.
2. "."对应ct的根节点.
*****/

public class WordDictionary extends Trie{

    // Adds a word into the data structure.
    public void addWord(String word) {
		insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return searchHelper(root, word);
    }
	
	public boolean searchHelper(TrieNode node, String word) {
		TrieNode t = node;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(c != '.'){
				t = t.children[c - 'a'];
				if(t == null) return false;
			}
			else{
				for(TrieNode ct : children){
					if(ct != null && searchHelper(ct, word.substring(i+1))){
						return true;
					}
				}
				return false;
			}
		}		
		return t.isWord;
	}
	
	
}

class TrieNode {
	public char val;
	public boolean isWord;
	public TrieNode[] children = new TrieNode[26];
	public TrieNode() {}
	TrieNode(char c){
		TrieNode node = new TrieNode();
		node.val = c;
	}
}

public class Trie {
	private TrieNode root;
	public Trie() {
		root = new TrieNode();
		root.val = ' ';
	}
	
	public void insert(String word) {
		TrieNode curr = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(curr.children[c - 'a'] == null){
				curr.children[c - 'a'] = new TrieNode(c);
			}
			curr = curr.children[c - 'a'];
		}
		curr.isWord = true;
	}
	
	public boolean search(String word) {
		TrieNode curr = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(curr.children[c - 'a'] == null) return false;
			curr = curr.children[c - 'a'];
		}
		return curr.isWord;
	}
	
	// Never used. Just practice.
	public boolean startsWith(String prefix) {
		TrieNode curr = root;
		for(int i = 0; i < prefix.length(); i++){
			char c = prefix.charAt(i);
			if(curr.children[c - 'a'] == null) return false;
			curr = curr.children[c - 'a'];
		}
		return true;
	}
	
	/*
	public TrieNode searchNode(String word) {
		TrieNode curr = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(curr.children[c - 'a'] == null) return false;
			curr = curr.children[c - 'a'];
		}
	}*/
}