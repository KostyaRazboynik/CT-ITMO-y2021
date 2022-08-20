def main():
    f = open("input.txt", "r")
    n, m = map(int, f.readline().split())
    mas = []
    for i in range(n):
        s = f.readline()
        b = [int(i) for i in s.split()]
        mas.append(b)
    f.close()
    for i in range(n):
        for j in range(m):
            if i == 0:
                if j == 0:
                    maxx = 0
                else:
                    maxx = mas[i][j - 1]
            else:
                if j == 0:
                    maxx = mas[i - 1][j]
                else:
                    if mas[i - 1][j] > mas[i][j - 1]:
                        maxx = mas[i - 1][j]
                    else:
                        maxx = mas[i][j - 1]
            mas[i][j] += maxx
    s = ''
    i = n - 1
    j = m - 1
    while i > 0 or j > 0:
        if i == 0:
            j -= 1
            s += 'R'
        elif j == 0:
            i -= 1
            s += 'D'
        else:
            if mas[i - 1][j] > mas[i][j - 1]:
                i -= 1
                s += 'D'
            else:
                j -= 1
                s += 'R'

    f = open("output.txt", "a")
    f.write(str(mas[n - 1][m - 1]) + '\n')
    s = s[::-1]
    f.write(s)
    f.close()


if __name__ == '__main__':
    main()