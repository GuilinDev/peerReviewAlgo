arr = [2, 100, 3, 5, 0]

print(*arr)

for i in range(len(arr)):
    min_index = i
    for j in range(i + 1, len(arr)):
        if arr[j] < arr[min_index]:
            min_index = j

    if i is not min_index:
        arr[i], arr[min_index] = arr[min_index], arr[i]

print(*arr)