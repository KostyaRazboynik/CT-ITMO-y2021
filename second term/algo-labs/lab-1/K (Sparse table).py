import math
import sys


def main():
    n, m, a1 = map(int, sys.stdin.readline().split())
    a = [0] * n
    st = [[0] * math.floor(math.log2(n) + 5) for _ in range(n)]
    u, v = map(int, sys.stdin.readline().split())
    st[0][0] = a1
    a[0] = a1
    for i in range(1, n):
        a[i] = (23 * a[i - 1] + 21563) % 16714589
        st[i][0] = a[i]
    l = [0] * (n + 5)
    l[0] = 0
    l[1] = 0
    for i in range(2, n + 1):
        l[i] = l[i // 2] + 1
    i = 1
    while (1 << i) <= n:
        j = 0
        while j <= n - (1 << i):
            st[j][i] = min(st[j][i - 1], st[j + (1 << (i - 1))][i - 1])
            j += 1
        i += 1
    lvl = l[abs(u - v) + 1]
    ri = min(st[min(u, v) - 1][lvl], st[max(u, v) - (1 << lvl)][lvl])
    for i in range(1, m):
        u = (17 * u + 751 + ri + 2 * i) % n + 1
        v = (13 * v + 593 + ri + 5 * i) % n + 1
        lvl = l[abs(u - v) + 1]
        ri = min(st[min(u, v) - 1][lvl], st[max(u, v) - (1 << lvl)][lvl])
    sys.stdout.write(str(u) + ' ' + str(v) + ' ' + str(ri) + '\n')


if __name__ == '__main__':
    main()