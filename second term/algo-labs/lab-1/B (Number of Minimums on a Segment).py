import sys


def main():
    n, m = map(int, sys.stdin.readline().split())
    mas = [int(x) for x in sys.stdin.readline().split()]
    t = []
    for i in range(n):
        t.append([0, 0])
    for i in range(n):
        t.append([0, 1])

    for i in range(n):  # build
        t[n + i][0] = mas[i]

    for i in range(n - 1, 0, -1):
        t[i][0] = min(t[2 * i][0], t[i * 2 + 1][0])
        if t[2 * i][0] == t[i * 2 + 1][0]:
            t[i][1] += (t[2 * i][1] + t[i * 2 + 1][1])
        else:
            if t[2 * i][0] < t[2 * i + 1][0]:
                t[i][1] += t[2 * i][1]
            else:
                t[i][1] += t[2 * i + 1][1]
    for i in range(m):
        type, a, b = map(int, sys.stdin.readline().split())
        if type == 1:  # update
            t[a + n][0] = b
            a += n
            i = a
            while i > 1:
                t[i // 2][0] = min(t[i][0], t[i ^ 1][0])
                if t[i][0] == t[i ^ 1][0]:
                    t[i // 2][1] = (t[i][1] + t[i ^ 1][1])
                else:
                    if t[i][0] < t[i ^ 1][0]:
                        t[i // 2][1] = t[i][1]
                    else:
                        t[i // 2][1] = t[i ^ 1][1]
                i //= 2
        else:  # sum
            res = 10 ** 9 + 1
            count = 0
            a += n
            a -= n
            a += n
            b += n
            while a < b:
                if b % 2 == 1:
                    b -= 1
                    if res > t[b][0]:
                        res = t[b][0]
                        count = t[b][1]
                    elif res == t[b][0]:
                        count += t[b][1]
                b //= 2
                if a % 2 == 1:
                    if res > t[a][0]:
                        res = t[a][0]
                        count = t[a][1]
                    elif res == t[a][0]:
                        count += t[a][1]
                    a += 1
                a //= 2

            sys.stdout.write(str(res) + ' ' + str(count) + '\n')


if __name__ == '__main__':
    main()