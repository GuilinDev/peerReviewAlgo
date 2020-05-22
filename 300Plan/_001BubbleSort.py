def bubbleSort(arr):
    n = len(arr)

    for i in range(n):
        # Last i elements are already in place
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]


arr = [2, 100, 3, 5, 0]
print(*arr)
bubbleSort(arr)
print(*arr)
