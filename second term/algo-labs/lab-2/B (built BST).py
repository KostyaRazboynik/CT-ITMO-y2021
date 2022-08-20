import sys


def main():
    n = int(sys.stdin.readline())
    mas = [int(_) for _ in sys.stdin.readline().split()]
    mas.sort()

    sys.stdout.write(str(n) + "\n")

    for i in range(n - 1):
        sys.stdout.write(str(mas[i]) + " -1 " + str(i + 2) + '\n')
    sys.stdout.write(str(mas[n - 1]) + " -1 -1\n")

    sys.stdout.write(str(1) + '\n')


if __name__ == '__main__':
    main()