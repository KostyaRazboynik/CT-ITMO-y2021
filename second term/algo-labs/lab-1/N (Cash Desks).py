import sys


def main():
    count = 0
    arr = []
    n = int(sys.stdin.readline())
    for i in range(n):
        h1, m1, s1, h2, m2, s2 = (map(int, sys.stdin.readline().split()))
        arr.append((h1 * 3600 + m1 * 60 + s1, 1))
        arr.append((h2 * 3600 + m2 * 60 + s2, -1))
        if arr[-2][0] >= arr[-1][0]:
            count += 1
    arr.sort()
    result = 0
    g = 0
    for i, type in arr:
        count += type
        if count == n:
            g = i
        if type == -1 and count == n - 1:
            result += i - g
    if count == n:
        result += 86400 - g
    sys.stdout.write(str(result) + '\n')


if __name__ == '__main__':
    main()