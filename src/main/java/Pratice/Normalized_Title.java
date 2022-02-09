package Pratice;

public class Normalized_Title {
    public String getHighestTitle(String rawTitle, String[] cleanTitles) {
        String res = "";
        int highScore = 0;
        for (String ct : cleanTitles) {
            int curScore = getScore(rawTitle, ct);
            if (curScore > highScore) {
                highScore = curScore;
                res = ct;
            }
        }
        return res;
    }

    //思路非常简单,两个title分别去查一下就行了。
    //这个下面有问题，比如a b c和d c的例子，那只能开二维矩阵去搜最高分。
    //不考虑顺序的话，就用map来记录词和位置吧。（而且它说没有重复的词，也是暗示用map）
    public int getScore(String raw, String ct) {
        int s = 0, temp = 0;
        int rIdx = 0, cIdx = 0;
        String[] rA = raw.split(" ");
        String[] cA = ct.split(" ");
        while (rIdx < rA.length) {
            String rCur = rA[rIdx];
            String cCur = cA[cIdx];
            if (!rCur.equals(cCur)) {
                cIdx = 0;
                temp = 0;
            } else {
                temp++;
                cIdx++;
            }
            rIdx++;
            s = Math.max(s, temp);
            if (cIdx == cA.length) break;
        }

        return s;
    }

    public static void main(String[] args) {
        Normalized_Title test = new Normalized_Title();
        String rawTitle = "senior software engineer";
        String[] cleanTitles = {
                "software engineer",
                "mechanical engineer",
                "senior software engineer"};

        String result = test.getHighestTitle(rawTitle, cleanTitles);
        System.out.println(result);
    }


    /* =============================================================================
    Follow Up
    =============================================================================
    raw title和clean title中有duplicate word怎么办
    比如raw = "a a a b", clean = "a a b"
        这样的话，靠指针就抓不出第二个a开始的aab，因为查第二个a的时候，是当做第二个a来算的。这个case
        应该返回3而不是2。
        那还想什么，开二维矩阵走DP吧。
=============================================================================*/
    public String getHightestTitleWithDup(String rawTitle, String[] cleanTitles) {
        String res = "";
        int highScore = 0;
        String[] rA = rawTitle.split(" ");
        for (String ct : cleanTitles) {
            String[] cA = ct.split(" ");
            int temp = getScoreWithDup(rA, cA);
            System.out.println("temp is " + temp);
            if (temp > highScore) {
                highScore = temp;
                res = ct;
            }
        }
        return res;
    }

    //二维矩阵里面每个位置都要查,因为不一定是从哪个位置开始匹配的,反正复杂度都是一样的。
    public int getScoreWithDup(String[] rA, String[] cA) {
        int col = rA.length;
        int row = cA.length;
        int res = 0;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            String cCur = cA[i];
            for (int j = 0; j < col; j++) {
                String rCur = rA[j];
                if (rCur.equals(cCur)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.max(1, dp[i - 1][j - 1] + 1);
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}