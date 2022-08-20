def main():

    global res
    res = []
    def makeSums(cur, start, mas):
        if cur == 0:
            s = str(mas[0])
            for i in range(1, len(mas)):
                s += "+" + str(mas[i])
            res.append(s)
        else:
            for i in range(start, cur + 1):
                newMas = [j for j in mas]
                newMas.append(i)
                makeSums(cur - i, i, newMas)


    n, r = map(int, input().split())
    makeSums(n, 1, [])

    print(res[r])

if __name__ == '__main__':
    main()
