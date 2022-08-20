import sys
import copy

def main():

    def Sums(cur, start, mas):
        if cur != 0:
            for i in range(start, cur + 1):
                newMas = copy.copy(mas)
                newMas.append(i)
                Sums(cur - i, i, newMas)
        else:
            s = str(mas[0])
            for i in range(1, len(mas)):
                s += "+" + str(mas[i])
            sys.stdout.write(s + '\n')

    n = int(sys.stdin.readline())
    mas = []
    Sums(n, 1, mas)

if __name__ == '__main__':
    main()