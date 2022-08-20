import sys

def main():
    n, k = map(int, sys.stdin.readline().split())
    if k == 0:
        s = '(' * n + ')' * n
        sys.stdout.write(s + '\n')
    else:
        k += 1
        C = [[0 for i in range(n + 1)] for j in range(2 * n + 1)]
        C[0][0] = 1


        for i in range(2 * n):
            for j in range(n + 1):
                if j + 1 <= n:
                    C[i + 1][j + 1] += C[i][j]
                if j > 0:
                    C[i + 1][j - 1] += C[i][j]

        depth = 0
        s = ''
        for i in range(n * 2):
            if C[2 * n - i - 1][depth + 1] >= k:
                s += '('
                depth += 1
            else:
                k -= C[2 * n - i - 1][depth + 1]
                s += ')'
                depth -= 1
        sys.stdout.write(s + '\n')


if __name__ == '__main__':
    main()