/*
276 Paint Fence
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color. 

Return the total number of ways you can paint the fence. 

Note:
n and k are non-negative integers.

*/

/* ****
303

1. 把前两个看成整体. 前两个房子同色k, 不同色k(k-1), 
2. 当前不同色,前两所有 *　(k-1)
   当前同色 = 前两个不同色
   
411
1. 边漆变数, 数到最后一个.
*****/

public int numWays(int n, int k) {
    if(n == 0) return 0;
    else if(n == 1) return k;
    int diffColorCounts = k*(k-1);
    int sameColorCounts = k;
    for(int i=2; i<n; i++) {
        int temp = diffColorCounts;
        diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
        sameColorCounts = temp;
    }
    return diffColorCounts + sameColorCounts;
}

/*
The reason why we have these two cases is that we can easily compute both of them,
and that is all I do. When adding a new post, we can use the same color as the last
one (if allowed) or different color. If we use different color, there're k-1 options, and
the outcomes shoule belong to the diffColorCounts category. If we use same color,
there's only one option, and we can only do this when the last two have different
colors (which is the diffColorCounts).
*/
