import sys

def main():

    n, k = map(int, sys.stdin.readline().split())
    mas = [int(i) for i in sys.stdin.readline().split()]
    mas.insert(0, 0)
    C = []
    for i in range(n + 1):
        c = [1]
        for j in range(1, n + 1):
            c.append(0)
        C.append(c)

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            C[i][j] = C[i - 1][j] + C[i - 1][j - 1]

    res = 0
    for i in range(1, k + 1):
        for j in range(mas[i - 1] + 1, mas[i]):
            res += C[n - j][k - i]
    sys.stdout.write(str(res) + '\n')

if __name__ == '__main__':
    main()