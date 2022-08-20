import sys


def main():
    n, k = map(int, sys.stdin.readline().split())
    mas = [int(x) for x in sys.stdin.readline().split()]

    mas.append(n + 1)
    curr = k - 1
    while curr >= 0 and mas[curr + 1] - mas[curr] < 2:
        curr -= 1
    if curr != -1:
        mas[curr] += 1
        for i in range(curr + 1, k):
            mas[i] = mas[i - 1] + 1
        for i in range(k):
            sys.stdout.write(str(mas[i]) + ' ')
        sys.stdout.write('\n')
    else:
        sys.stdout.write(str(-1) + '\n')


if __name__ == '__main__':
    main()