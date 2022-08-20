import sys

def main():
    s = sys.stdin.readline()
    n = len(s) // 2
    C = [[0 for i in range(n + 1)] for j in range(2 * n + 1)]
    C[0][0] = 1


    for i in range(2 * n):
        for j in range(n + 1):
            if j + 1 <= n:
                C[i + 1][j + 1] += C[i][j]
            if j > 0:
                C[i + 1][j - 1] += C[i][j]

    res = 0
    depth = 0
    for i in range(n):
        if s[i] == '(':
            depth += 1
        else:
            res += C[n - i - 1][depth + 1]
            depth -= 1
    sys.stdout.write(str(res) + '\n')


if __name__ == '__main__':
    main()