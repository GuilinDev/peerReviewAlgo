class Solution(object):
    def numJewelsInStones(self, J: str, S: str) -> int:
        jewels = {}
        for i in J:
            jewels[i] = 1
        number = 0
        for i in S:
            if i in jewels:
                number += 1
        return number


ob1 = Solution()
print(ob1.numJewelsInStones("aZc", "catTableZebraPicnic"))
