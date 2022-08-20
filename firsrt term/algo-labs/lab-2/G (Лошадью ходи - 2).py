def main():

    def FFF(x, y, z, w, mas):
        if x < 1 or y < 1 or x > z or y > w:
            return 0
        if x == 1 and y == 1:
            return 1
        if mas[x][y] != -1:
            return mas[x][y]
        mas[x][y] = (FFF(x - 2, y - 1, z, w, mas) + FFF(x - 2, y + 1, z, w, mas) + FFF(x - 1, y - 2, z, w, mas) + FFF(x + 1, y - 2, z, w, mas)) % int(1e6 + 7)
        return mas[x][y]

    f = open("knight.in", "r")
    n, m = map(int, f.readline().split())
    f.close()
    mas = []
    for i in range(n + 1):
        mas.append([-1 for j in range(m + 1)])
    mas[1][1] = 1

    f = open("knight.out", "a")
    f.write(str(FFF(n, m, n, m, mas)))
    f.close()

if __name__ == '__main__':
    main()