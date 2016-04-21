/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 * Tags: String
 */
class CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
        System.out.println(countAndSay(6));
    }
	
	/* ****
	1. 连读统计
	2. 翻译， 类似罗马。 注意复数。
	3. 两种分割符。
	****/	
	
	private String countAndSay(String str){
		int len = str.length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < len; i++){
			int cnt = 0;
			while(i < len - 1 && str.charAt(i) == str.charAt(i+1)){
				cnt++;
				i++;
			}
			sb.append(cnt).append(str.charAt(i));			
		}
		return sb.toString();
	}
	
	public String countAndSay(int n){
		String str = "1";
		for(int i = 0; i < n; i++){
			str = countAndSay(str);
		}
		
		String[] nums = {"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine "};
		
		char[] cArr = str.toCharArray();
		sb.setLength(0);
		for(int i = 0; i*2 < cArr.length; i++){
			int cnt = cArr[i * 2] - '0';
			sb.append(nums[cnt]).append(cArr[i * 2 + 1]);
			if(cnt > 1) sb.append('s');
			if(i < cArr.length/2 - 1) sb.append(", ");
			else if(i == cArr.length/2 - 1) sb.append(" then ");
		}
		returbn sb.toString();
	}
    
    /**
     * Build from n - 1 to n
     * Traverse and get count of each char and append to result
     */
    public static String countAndSay(int n) {
        String res = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] prev = res.toCharArray();
            for (int i = 0; i < prev.length; i++) {
                int count = 1; // initialize current count as 1
                while (i + 1 < prev.length && prev[i] == prev[i + 1]) {
                    count++; // search for same char
                    i++;
                }
                sb.append(count).append(prev[i]);
            }
            res = sb.toString();
        }
        return res;
    }
    
    /**
     * Bottom-up approach
     */
    public static String countAndSayB(int n) {
        if (n <= 0) return "";
        if (n == 1) return "1";
        int i = 2;
        String res = "1";
        while (i <= n) {
            String curRes = "";
            int count = 1;
            char prevNum = res.charAt(0);
            for (int j = 1; j < res.length(); j++) {
                char curNum = res.charAt(j);
                if (prevNum == curNum) {
                    count++;
                } else {
                    curRes += count; // update current result
                    curRes += prevNum;
                    count = 1; // reset count
                }
                prevNum = curNum;
            }
            curRes += count; // update last loop
            curRes += prevNum;
            res = curRes; // build next count
            i++;
        }
        return res;
    }
}