import copy
import sys

def main():
    n = int(sys.stdin.readline())
    s = sys.stdin.readline()
    mas = [int(x) for x in s.split()]

    prev = copy.copy(mas)
    flag = 0
    for i in range(n - 2, -1, -1):
        if prev[i] > prev[i + 1]:
            m = i + 1
            for j in range(i + 1, n):
                if prev[i] > prev[j] > prev[m]:
                    m = j
            x = prev[i]
            prev[i] = prev[m]
            prev[m] = x
            for el in prev[:i + 1]:
                sys.stdout.write(str(el) + ' ')

            for el in prev[: i : -1]:
                sys.stdout.write(str(el) + ' ')
            flag += 1
            sys.stdout.write('\n')
            break
    if flag == 0:
        sys.stdout.write('0 ' * n + '\n')


    next = copy.copy(mas)
    flag = 0
    for i in range(n - 2, -1, -1):
        if next[i] < next[i + 1]:
            m = i + 1
            for j in range(i + 1, n):
                if next[m] > next[j] > next[i]:
                    m = j
            x = next[i]
            next[i] = next[m]
            next[m] = x
            for el in next[:i + 1]:
                sys.stdout.write(str(el) + ' ')

            for el in next[: i : -1]:
                sys.stdout.write(str(el) + ' ')
            flag += 1
            sys.stdout.write('\n')
            break
    if flag == 0:
        sys.stdout.write('0 ' * n + '\n')


if __name__ == '__main__':
    main()