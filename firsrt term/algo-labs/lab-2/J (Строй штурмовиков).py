def main():
    n, m = map(int, input().split())
    mas = []
    for i in range(n):
        s = input()
        mas.append([int(i) for i in s.split()])
    maxDim = 0
    for i in range(n):
        for j in range(m):
            if i * j != 0:
                if mas[i][j] == 1:
                    mas[i][j] = min(mas[i -1][j - 1], mas[i][j - 1], mas[i - 1][j]) + 1
                if mas[i][j] > maxDim:
                    maxDim = mas[i][j]
    print(maxDim)

if __name__ == '__main__':
    main()