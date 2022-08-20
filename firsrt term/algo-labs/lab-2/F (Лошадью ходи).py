def main():
    f = open("knight.in", "r")
    n, m = map(int, f.readline().split())
    f.close()
    mas = []
    for i in range(n + 1):
        mas.append([0 for j in range(m + 1)])
    mas[1][1] = 1

    for i in range(2, n + 1):
        for j in range(2, m + 1):
            mas[i][j] = mas[i - 1][j - 2] + mas[i - 2][j - 1]

    f = open("knight.out", "a")
    f.write(str(mas[n][m]))
    f.close()

if __name__ == '__main__':
    main()