import sys

def main():
    n = int(sys.stdin.readline())
    s = sys.stdin.readline()
    mas = [int(i) for i in s.split()]
    used = [0] * n
    P = [1]
    for i in range(1, n):
        P.append(P[i - 1] * i)

    def perm2num(mas):
        count = 0
        for i in range(n):
            for j in range(mas[i] - 1):
                if used[j] == 0:
                    count += P[n - i - 1]
            used[mas[i] - 1] = 1
        sys.stdout.write(str(count) + '\n')


    perm2num(mas)



if __name__ == '__main__':
    main()
