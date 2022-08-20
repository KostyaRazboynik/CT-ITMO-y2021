def main():
    n = int(input())
    mas = []
    for i in range(2 ** n):
        x = i
        s = ''
        while x > 0:
            s = str(x % 2) + s
            x = int(x / 2)
        if s.find("11") == -1:
            mas.append((n - len(s)) * "0" + s)
    print(len(mas))
    for i in mas:
        print(i)
if __name__ == '__main__':
    main()