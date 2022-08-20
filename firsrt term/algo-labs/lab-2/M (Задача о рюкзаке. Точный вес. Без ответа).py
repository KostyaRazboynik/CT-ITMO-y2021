def main():
    n, s = map(int, input().split())
    str = input()
    w = [int(i) for i in str.split()]
    summ = sum(w)
    if s > summ:
        print('NO')
    else:
        mas = [0] * (summ + 1)
        mas[0] = 1
        for i in range(n):
            curr = []
            element = w[i]
            for j in range(len(mas)):
                if mas[j] == 1:
                    curr.append(j)
            for j in curr:
                mas[j + element] = 1
        if mas[s] == 1:
            print('YES')
        else:
            print('NO')


if __name__ == '__main__':
    main()