直接模拟

```python
class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        # Time complexity is O(MN)，M is commands amount，N is obstacles amount
        # directions = ['N', 'E', 'S', 'W']
        # 0 - N, 1 - E, 2 - S, 3 - W
        dirs = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        obstacles = set(map(tuple, obstacles))
        x, y, direction, maxDistance = 0, 0, 0, 0
        for command in commands:
            if command == -2: 
                direction = (direction - 1) % 4
            elif command == -1: 
                direction = (direction + 1) % 4
            else:
                newX, newY = dirs[direction]
                while command:
                    if (x + newX, y + newY) not in obstacles:
                        x += newX
                        y += newY
                    command -= 1
                maxDistance = max(maxDistance, x**2 + y**2)
        return maxDistance
```
