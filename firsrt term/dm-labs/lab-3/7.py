import copy
import sys

def main():
    global n

    def Permutations(cur, mas, bol):
        if cur != n:
            for i in range(n):
                if bol[i] == 1:
                    newMas = copy.copy(mas)
                    newMas.append(i + 1)
                    bol[i] = 0
                    Permutations(cur + 1, newMas, bol)
                    bol[i] = 1
        else:
            s = ''
            for i in range(n):
                s += str(mas[i]) + " "
            sys.stdout.write(s + '\n')

    n = int(sys.stdin.readline())
    bol = [1] * n
    mas = []
    Permutations(0, mas, bol)

if __name__ == '__main__':
    main()