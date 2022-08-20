from math import log2, ceil
import sys


def is_uncestor(u, v):
    return


def main():
    n = int(sys.stdin.readline())
    x = ceil(log2(n))
    up = [[0 for _ in range(n + 1)] for i in range(x)]
    p = [int(_) for _ in sys.stdin.readline().split()]
    for i in range(n):
        up[0][i + 1] = p[i]
    for d in range(1, x):
        for i in range(n + 1):
            up[d][i] = up[d - 1][up[d - 1][i]]

    for i in range(1, n + 1):
        sys.stdout.write(str(i) + ":")
        for j in range(x):
            if up[j][i] != 0:
                sys.stdout.write(" " + str(up[j][i]))
        sys.stdout.write('\n')


if __name__ == '__main__':
    main()