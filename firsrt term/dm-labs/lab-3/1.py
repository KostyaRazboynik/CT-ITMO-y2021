def main():
    n = int(input())
    for i in range(2 ** n):
        x = i
        s = ''
        while x > 0:
            s = str(x % 2) + s
            x = int(x / 2)
        print((n - len(s)) * "0" + s)

if __name__ == '__main__':
    main()