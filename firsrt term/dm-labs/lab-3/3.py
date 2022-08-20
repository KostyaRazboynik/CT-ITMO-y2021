def main():

    n = int(input())
    k = 3 ** (n - 1)

    for i in range(k):
        x = i
        first = ''
        while x > 0:
            first = str(x % 3) + first
            x = int(x / 3)
        first = (n - len(first)) * "0" + first
        print(first[::-1])
        second = ''
        for i in range(n):
            second += str((int(first[i]) + 1) % 3)
        print(second[::-1])
        third = ''
        for i in range(n):
            third += str((int(second[i]) + 1) % 3)
        print(third[::-1])
if __name__ == '__main__':
    main()