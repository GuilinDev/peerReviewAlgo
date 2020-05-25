def generate_hale_seq(num):
    assert num > 0, "Input should be Positve number"

    lnum = [num]
    while num != 1:
        if (num % 2) == 0:
            num = num // 2
        else:
            num = (num * 3) + 1
        lnum.append(num)
    return lnum


n = input("Input a number: ")
print(generate_hale_seq(int(n)))
