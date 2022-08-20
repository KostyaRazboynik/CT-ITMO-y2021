def main():
    f = open("knight.in", "r")
    n = int(f.readline())
    f.close()
    mas = [0] * n
    mas[0] = 2
    if n > 1:
        mas[1] = 3
        for i in range(2, n):
            mas[i] = mas[i - 1] + mas[i - 2]
    else:
        mas[0] = 2
    f = open("knight.out", "a")
    f.write(str(mas[-1]))
    f.close()

if __name__ == '__main__':
    main()