def main():
    n, s = map(int, input().split())
    st = input()
    w = [int(i) for i in st.split()]
    summ = sum(w) + 1
    mas = ['0'] * (summ + 1)
    mas[0] = '1'
    for i in range(n):
        el = w[i]
        curr = []
        for j in range(len(mas)):
            if mas[j][0] == '1':
                curr.append(j)
        for j in curr:
            if mas[j + el] == '0':
                mas[j + el] = ('1 ' + mas[j][2:] + ' ' + str(el))


    if s >= summ:
        for i in range(len(mas) - 1, -1, -1):
            if mas[i][0] == '1':
                res = [int(i) for i in mas[i][1:].split()]
                print(sum(res))
                print(len(res))
                print(*res)
                break
    else:
        for i in range(s, -1, -1):
            if mas[i][0] == '1':
                res = [int(i) for i in mas[i][1:].split()]
                print(sum(res))
                print(len(res))
                print(*res)
                break
if __name__ == '__main__':
    main()