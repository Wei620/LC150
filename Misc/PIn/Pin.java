/*
Given a filename as input, print the number of words in the file.
Given a list of names, like [“Tom”, “Jim”, “Jon”], output a comma-separate string like “Tom, Jim and Jon”.
Write a function that takes a list of integer image heights and a number of columns and returns a grid layout for the pins for those columns.
Given a filename and a column number as input, print the n’th column of each line of the file (columns are separated by spaces).
Given two filenames as input, join the first line of the files with a tab and print it, then join the second line of each file and print it, and so on, print out any left over from the longer file if the files are of different lengths.
Given a matrix represented as an array of arrays of integers, transpose the matrix.
*/

/*
1.  Given a filename as input, print the number of words in the file.
1) Throw IOException if not exist or no read access.
2） Java supports BufferedReader to handle the overflow scenario. Specify the size as 4KB.
3) Implemented a prototype MyBufferedReader in case the interviewer wonders more details.
*/

//ASCII
void countWords(String fileName) throw IOException{
    if(fileName == null) return;
    
    boolean isLastCharLetter = false;
    int cnt = 0, c = 0; 
    final int BUF_SIZE = 2048;
    Reader in;
    
    try{
        in = new BufferedReader(new FileReader(fileName), BUF_SIZE);
        while((c = in.read())!= -1){// Not reach the end yet.
            boolean isCurrCharLetter = isLetter((char)c);
            if(isLastCharLetter && !isCurrCharLetter) cnt++;
            isLastCharLetter = isCurrCharLetter;
        }
        if(isLastCharLetter) cnt++;
		return cnt;
    }
    finally{
        if(in != null){
            in.close(); // close the input stream.
        }
    }
}

boolean isLetter(char c){
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

//Implement MyBufferedReader.
class MyBufferedReader{
	final int SIZE;
	int curr, len;
	byte[] buf;
	Reader in;
	
	public MyBufferedReader(Reader rd, int sz){
		SIZE = sz;
		buf = new byte[SIZE];
		in = rd;
	}
	
	public int read throw IOException(){
		if(in == null || len = -1) return -1;
		if(curr == len){
			try{
				len = in.read(buf);
				if(len == -1) return -1;
				curr = 0;
			}
		}
		return (int)buf[curr++];
	}
}

//Unicode
void countWords(String fileName) throw IOException{
    if(fileName == null) return;
    InputStream in;
    final int BUF_SIZE = 2048;
    try {
        in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName), BUF_SIZE));
        while (true) {
            in.readUTF();
            cnt++;
        }
    } catch(EOFException e) {
        //Do nothing. Reach the end of the file.
    } catch(UTFDataFormatException e){
        System.out.println("Reach the non-unicode string");
    }finally{
        if(in != null) in.close;
    }    
}

/*
2. Given a list of names, like [“Tom”, “Jim”, “Jon”], output a comma-separate string like “Tom, Jim and Jon”.
1) Put the word into appropriate format.
2) 2 splitter for different cases. (", ", " and ")
3) Any duplicates? If have, which one will be reserved, the first(hashset) or the last(hashmap) occurence?	
*/

// No dups. But there are null and space.
String splitNames(List<String> names){
    if(names == null || names.size() == 0) return "";
    final String SPLITTER1 = ", ";
    final String SPLITTER2 = " and ";
    StringBuilder res = new StringBuilder();
	
	int len = names.size(); 
    Iterator<String> iter = list.iterator();
	while(iter.hasNext()){
		if(format(iter.next()) == "") len--;
	}
	
	iter = list.iterator();
	while(iter.hasNext()){
		String str = format(iter.next());
		if(str != ""){
			res.append(str);
			len--;
			if(len > 1) res.append(SPLITTER1);
			else if(len == 1) res.append(SPLITTER2);
		}
	}
    return res.toString();
}

// Dups. Reserve the first occurence.
String splitNames(List<String> names){
    if(names == null || names.size() == 0) return "";
    final String SPLITTER1 = ", ";
    final String SPLITTER2 = " and ";
    StringBuilder res = new StringBuilder();
	
    HashSet<String> dict = new HashSet<>();
    Iterator<String> iter = list.iterator();
	while(iter.hasNext()){
		String str = format(iter.next());
		if(str != "" && !dict.contains(str)){//The empty string doesn't count.
            dict.add(str);
		}
	}
	
	int len = dict.size();
	iter = list.iterator();
	while(iter.hasNext()){
		String str = format(iter.next());
		boolean exist = dict.remove(str);
		if(exist){
			len--;
			res.append(str);
			if(len > 1) res.append(SPLITTER1)
			else if(len == 1) res.append(SPLITTER2);
		}
	}
    return res.toString();
}

// Dups. Reserve the last occurence.
String splitNames(List<String> names){
    if(names == null || names.size() == 0) return "";
    final String SPLITTER1 = ", ";
    final String SPLITTER2 = " and ";
    StringBuilder res = new StringBuilder();
	
    HashMap<String, Integer> freq = new HashMap<String, Integer>();
    Iterator<String> iter = list.iterator();
	while(iter.hasNext()){
		String str = format(iter.next());
		if(str != ""){
			if(!freq.contains(str)){//The empty string doesn't count.
				freq.put(str, 0);
			}
			freq.put(str, freq.get(str)+1);
		}
	}
	
	int len = freq.size();
	iter = list.iterator();
	while(iter.hasNext()){
		String str = format(iter.next());
		if(freq.contains(str)){
			int cnt = freq.get(str);
			if(--cnt == 0){
				freq.remove(str);
				len--;
				res.append(str);
				if(len > 1) res.append(SPLITTER1)
				else if(len == 1) res.append(SPLITTER2);
			}
		}
	}
    return res.toString();
}

// Trim the leading and trailing spaces. Capicalize the first letter. All others are low cases.
String format(String str){
    if(str == null || str.length == 0) return "";
    str = str.trim().toLowerCase();
    return (char)(str.charAt(0) - 32) + str.substring(1);
}


/*
3. Write a function that takes a list of integer image heights and a number of columns and returns a grid layout for the pins for those columns.

*/
public class PinterestGrid {

  public class Pin {
    private long id;
    private int height;

    public Pin(long id, int height) {
      this.id = id;
      this.height = height;
    }
  }

  public class Column implements Comparable<Column> {
    private List<Pin> pins;
    private Long id;
    private Integer depth; // in pixels

    public Column(long id) {
      pins = new ArrayList<Pin>();
      this.id = id;
      this.depth = 0;
    }

    public void addPin(Pin pin) {
      if (pin == null) {
        return;
      }
      pins.add(pin);
      depth += pin.height;
    }

    public int compareTo(Column that) {
      if (this.depth.compareTo(that.depth) != 0) {
        return this.depth.compareTo(that.depth);
      }
      return this.id.compareTo(that.id);
    }
  }

  public ArrayList<Column> layout(List<Pin> pins, int nCols) {
    if (pins == null || pins.size() == 0 || nCols <= 0) {
      return null;
    }
    // Initialize columns
    PriorityQueue<Column> columns = new PriorityQueue<Column>();
    for (int i = 1; i <= nCols; i++) {
      columns.add(new Column(i));
    }
    // Read pins into list
    for (Pin pin : pins) {
      Column shortestColumn = columns.poll();
      shortestColumn.addPin(pin);
      columns.add(shortestColumn);
    }

    // Return list of columns, in order from shortest to longest
    return new ArrayList<Column>(columns);
  }
}

/*
4. Given a filename and a column number as input, print the n’th column of each line of the file (columns are separated by spaces).
1） Assume the “nth” column is 1-based index.
2) Assume a line is ended with '\n'(line feed).
3） The first column is treated as empty string "" if a line is started with space. 
4) There're 3 cases for the current char, which are space, line feed and others. 

*/
void printColumnN(String fileName, int n) throw IOException{
    if(fileName == null || n <= 0) return;
    int cnt = 0, curr, last = 128;//leading space counts,  otherwise last = ' ';
    StringBuilder sb = new StringBuilder();
    Reader in; 

    try{
        in = new BufferedReader(new FileReader(fileName), 2048);
        while((curr = in.read())!= -1){//Not reach the end yet.
            if(curr == ' '){// case 1
                if(last != ' ' && ++cnt == n){
                    System.out.println(sb.toString());
                }
                sb.setLength(0); //clean up the string bulder.
            }
            else if(curr = '\n'){//case 2
                if(last != ' ' && ++cnt == n){
					System.out.println(sb.toString());
                }
				if(cnt < n){
                    System.out.println("");
                }
                sb.setLength(0);
                cnt = 0; // reset for the next line.
                last = 128;
            }
            else{//case 3
                sb.append(curr);
            }
			last = curr;
        }
    }
    finally{
        if(in != null) in.close();
    }
}

/*
5. Given two filenames as input, join the first line of the files with a tab and print it, then join the second line of each file and print it, and so on, print out any left over from the longer file if the files are of different lengths.
1) Assume every line except for the last line should be end with a '\n'. 
   The '\n' is optional for the last line in the file.
*/

void joinLines(String inFN1, String inFN2, String outFN) throw IOException{
	if(inFN1 == null || inFN2 == null || outFN == null ||
		inFN1.equals(outFN) || inFN2.equals(outFN)) return;
	Reader in;
	Writer out;
	
	try{
		in1 = new BufferedReader(new FileReader(inFN1), 2048);
		in2 = new BufferedReader(new FileReader(inFN2), 2048);
		out = new BufferedWriter(new FileWriter(outFN), 2048);
		
        int cnt1;
		while((cnt1 = println(in1, out)) != -1){ // Not reach the end of input1.
            if(cnt1 > 0 && !isEmptyLine(in2)) out.write('\t'); 
            println(in2, out);
			out.write('\n');
		}
		while(!println(in2, out)) // Read rest of input2 if exists
			out.write('\n')
		}
		out.flush();
	}
	finally{
		if(in1 != null) in1.close();
		if(in2 != null) in2.close();
		if(in3 != null) in3.close();
	}
}

// Print the content of input stream to the output stream until reach a line feed '\n' or the end of the file. 
// Return the number of characters we just printed to the output stream.
// Return -1 if no characters are left in the input stream.
int println(Reader in, Writer out) throw IOException{
	if(in == null || out == null) return -1;
	try{
		int c, cnt = 0;
		while((c = in.read())!=-1){
			if(c == '\n') return cnt;
			out.write(c);
            cnt++;
		}
		return cnt == 0? -1 : cnt;
	}
}

// Peek the next character. Return true if it's a '\n' or we reach the end of the file.
boolean isEmptyLine(Reader in){
	if(in == null) return true;
	in.mark(2);//mark the current position. As long as the read times is less than 2, the read position can be resumed by calling reset().
	int c = in.read();
	in.reset();
	return c == -1 || c == '\n';
}

/*
6. Given a matrix represented as an array of arrays of integers, transpose the matrix.
1） in-place? Java can only handle the square matrix.
2） cache efficiency. Assume the cache line size in L1 is 64Byte. The cache set in L1 is at least 16 in size. So we can load the whole block of integers in to the L1 cache.
*/
int[][] transpose(int[][] src){	
	if(src == null || src.length == 0 || src[0].length == 0) return null;
	int blocksize = 64/4 = 16; // x86
	int m = src.length, n = src[0].length;
	int[][] dst = int[n][m];
	
	for (int i = 0; i < m; i += blocksize) {
		for (int j = 0; j < n; j += blocksize) {
			// transpose the block beginning at [i,j]
			for (int k = i; k < i + blocksize; k++) {
				for (int l = j; l < j + blocksize; l++) {
					dst[k][l] = src[l][k];
				}
			}
		}
	}
}

// m == n, in-place upper triangle.
int[][] transpose(int[][] src){	
	if(src == null || src.length == 0 || src[0].length == 0) return null;
	int blocksize = 64/4 = 16; // x86
	int m = src.length, n = src[0].length;
	
	for (int i = 0; i < n; i += blocksize) {
		for (int j = 0; j < n; j += blocksize) {
			// transpose the block beginning at [i,j]
			for (int k = i; k < i + blocksize; k++) {
				int start = i == j? k + 1 : j;
				for (int l = start; l < j + blocksize; l++) {
					swap(src[k][l], src[l][k]);
				}
			}
		}
	}
}

http://stackoverflow.com/questions/5200338/a-cache-efficient-matrix-transpose-program
https://en.wikipedia.org/wiki/Cache-oblivious_algorithm

