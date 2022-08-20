import sys

def main():
    n = int(sys.stdin.readline())
    s = sys.stdin.readline()
    mas = [int(i) for i in s.split()]
    i = n - 2
    while (i >= 0) and (mas[i] >= mas[i + 1]):
        i -= 1
    if i >= 0:
        j = i + 1
        while (j < n - 1) and (mas[j + 1] > mas[i]):
            j += 1
        x = mas[j]
        mas[j] = mas[i]
        mas[i] = x
        for i in range(i + 1):
            print(mas[i], end = " ")
        for i in range(n - 1, i, -1):
            print(mas[i], end = " ")
    else:
        res = [0] * n
        print(*res)

if __name__ == '__main__':
    main()