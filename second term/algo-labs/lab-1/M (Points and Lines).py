import sys


def cpm(a, b):
    if a[0] > b[0]:
        return 1
    if a[0] == b[0]:
        if a[1] in [1, -1]:
            return 1
        if b[1] in [1, -1]:
            return -1
    return -1


def main():
    lines = []
    dotsCount = {}
    k = 0
    n, m = map(int, sys.stdin.readline().split())
    for i in range(n):
        a, b = map(int, sys.stdin.readline().split())
        lines.append([min(a, b), -1])
        lines.append([max(a, b), 1])
    dots = [int(i) for i in sys.stdin.readline().split()]
    for i in range(m):
        lines.append([dots[i], 0])
    lines = sorted(lines)

    for i in range(n * 2 + m):
        if lines[i][1] == 0:
            dotsCount[lines[i][0]] = k
        else:
            k += lines[i][1] * (-1)
    for i in dots:
        sys.stdout.write(str(dotsCount[i]) + ' ')
    sys.stdout.write('\n')


if __name__ == '__main__':
    main()