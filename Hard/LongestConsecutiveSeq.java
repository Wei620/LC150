import java.util.*;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
 * length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Tags: Array, HashTable
 */
class LongestConsecutiveSeq {
    public static void main(String[] args) {
        LongestConsecutiveSeq l = new LongestConsecutiveSeq();
        int[] a = {100, 4, 200, 1, 3, 2};
        System.out.println(l.longestConsecutive(a));
    }

	// geek http://www.geeksforgeeks.org/longest-consecutive-subsequence/
	/*
	Time Complexity: At first look, time complexity looks more than O(n). If we take a closer look, we can notice that it is O(n) under the assumption that hash insert and search take O(1) time. The function S.find() inside the while loop is called at most twice for every element. 
	*/
	public int longestConsecutive(int[] arr) {

        HashSet<Integer> S = new HashSet<Integer>();
        int ans = 0;
        int n = arr.length;
 
        // Hash all the array elements
        for (int i=0; i<n; ++i)
            S.add(arr[i]);
 
        // check each possible sequence from the start
        // then update optimal length
        for (int i=0; i<n; ++i)
        {
            // if current element is the starting
            // element of a sequence
            if (!S.contains(arr[i]-1))
            {
                // Then check for next elements in the
                // sequence
                int j = arr[i];
                while (S.contains(j))
                    j++;
 
                // update  optimal length if this length
                // is more
                if (ans<j-arr[i])
                    ans = j-arr[i];
            }
        }
        return ans;
     
    }
    /**
     * Use a map to store ranges
     * Get lower bound with smaller value
     * Get upper bound with larger value
     * Update max length with new bound
     * Put all possible ranges into map
     * including num[i] ~ num[i], low ~ upp, upp ~ low
     */
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0;
        for(int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i])) continue; // skip duplicates
            int low = num[i]; // initialize ranges
            int upp = num[i];
            if (map.containsKey(num[i] - 1)) low = map.get(num[i] - 1);
            if (map.containsKey(num[i] + 1)) upp = map.get(num[i] + 1);
            maxLen = Math.max(maxLen, upp - low + 1); // update length
            // put possible ranges into map for next iteration
            map.put(num[i], num[i]);
            map.put(low, upp);
            map.put(upp, low);
        }
        return maxLen;
    }
}
