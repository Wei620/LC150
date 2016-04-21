/*
277 Find the Celebrity

Suppose you are at a party with n people (labeled from 0 ton - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the othern - 1 people know him/her but he/she does not know any of them.
Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a functionint findCelebrity(n), your function should minimize the number of calls toknows.
Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return-1.
*/

/* ****
1. 选拔-验证 两轮扫描.
2. 如果有,一定会在第一轮选出来, 因为他不认识任何人.
3. 验证,两个条件均可淘汰,有人不认识他, 他认识任何人(第一轮只查了上台以后的部分)

293
1. 两个条件, 也可用两一个"所有人都认识他"选拔, 不认识下台.
2. 验证还是两个条件.

411
1. 验证查两个条件, 任何不满足跳出.-1
	排除 i == candidatae
*****/

/*
The first pass is to pick out the candidate. If candidate knows i, then switch
candidate. The second pass is to check whether the candidate is real.*/
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i)) // candidate knows i
                candidate = i;
        }
        
        for(int i = 0; i < n; i++){
            if(i != candidate && 
                (knows(candidate, i) || 
                !knows(i, candidate))) 
                return -1;
        }
        return candidate;
    }
}