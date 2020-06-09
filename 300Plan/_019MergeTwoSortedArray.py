def merge1(left, right):
    if not left and not right:
        return None
    if not left:
        return right
    if not right:
        return left

    len1, len2 = len(left), len(right)
    temp = [None] * (len1 + len2)
    idx = idx1 = idx2 = 0

    while idx1 < len1 and idx2 < len2:
        if left[idx1] <= right[idx2]:
            temp[idx] = left[idx1]
            idx1 += 1
        else:
            temp[idx] = right[idx2]
            idx2 += 1
        idx += 1

    # 分别检查两个列表是否还有剩余
    while idx1 < len1:
        temp[idx] = left[idx1]
        idx1 += 1
        idx += 1

    while idx2 < len2:
        temp[idx] = right[idx2]
        idx2 += 1
        idx += 1

    return temp


def merge2(left, right):
    temp = []
    if not left and not right:  # left和right都加完了
        return temp
    if not left and right:  # left 已经加完
        return temp + right
    if left and not right:  # right 已经加完
        return temp + left

    if left and right:
        if left[0] <= right[0]:
            temp.append(left[0])
            temp = temp + merge2(left[1:], right)
        else:
            temp.append((right[0]))
            temp = temp + merge2(left, right[1:])

    return temp


left = []
right = [2, 5, 6, 9]
print(merge2(left, right))
