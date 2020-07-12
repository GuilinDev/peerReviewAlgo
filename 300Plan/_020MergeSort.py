def merge_sort(lst):
    lst_sorted = lst
    if len(lst_sorted) > 1:
        mid = len(lst_sorted) // 2
        leftPart = lst_sorted[:mid]
        rightPart = lst_sorted[mid:]

        # 先递归直至只有两个数
        merge_sort(leftPart)
        merge_sort(rightPart)

        lIndex = rIndex = index = 0
        while lIndex < len(leftPart) and rIndex < len(rightPart):
            if leftPart[lIndex] < rightPart[rIndex]:
                lst_sorted[index] = leftPart[lIndex]
                lIndex += 1
            else:
                lst_sorted[index] = rightPart[rIndex]
                rIndex += 1
            index += 1

        while lIndex < len(leftPart):
            lst_sorted[index] = leftPart[lIndex]
            lIndex += 1
            index += 1
        while rIndex < len(rightPart):
            lst_sorted[index] = rightPart[rIndex]
            rIndex += 1
            index += 1
    return lst_sorted


lst = [12, -1, 100, 5, 2, 17]
print(merge_sort(lst))
