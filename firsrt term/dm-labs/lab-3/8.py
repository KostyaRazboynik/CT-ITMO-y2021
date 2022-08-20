import sys
import copy

def main():
    global n
    global k

    def Combinations(cur, mas, j):
        if cur != k:
            for i in range(j + 1, n):
                newMas = copy.copy(mas)
                newMas.append(i + 1)
                Combinations(cur + 1, newMas, i)
        else:
            s = ''
            for i in range(k):
                s += str(mas[i]) + ' '
            sys.stdout.write(s + '\n')

    n, k = map(int, sys.stdin.readline().split())
    mas = []
    Combinations(0, mas, -1)

if __name__ == '__main__':
    main()
