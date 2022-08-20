import sys


def find(t, k, x):
    if t[x] == k and x * 2 >= len(t):
        return x
    if t[x] >= k and x * 2 < len(t):
        if (t[x * 2] >= k):
            return find(t, k, x * 2)
        else:
            return find(t, k - t[x * 2], x * 2 + 1)


def main():
    n, m = map(int, sys.stdin.readline().split())
    mas = [int(x) for x in sys.stdin.readline().split()]
    stepen = 1
    while stepen < n:
        stepen *= 2

    t = [0] * (2 * stepen)
    for i in range(n):  # build
        t[stepen + i] = mas[i]
    for i in range(stepen - 1, 0, -1):
        t[i] = t[2 * i] + t[i * 2 + 1]

    for i in range(m):
        type, x = map(int, sys.stdin.readline().split())
        if type == 1:  # update
            x += stepen
            if t[x] == 1:
                t[x] = 0
            else:
                t[x] = 1
            while x > 1:
                t[x // 2] = t[x] + t[x ^ 1]
                x //= 2
        else:  # sum
            res = find(t, x + 1, 1)

            sys.stdout.write(str(res - stepen) + '\n')


if __name__ == '__main__':
    main()