def main():

    n = int(input())
    k = 2 ** n
    mas = [1] * k
    mas[0] = 0
    for i in range(2, k):
        mas[i] = i ^ i//2

    for i in range(k):
        res = ""
        x = mas[i]
        for j in range(n):
            res += str(x % 2)
            x //= 2
        print(res[::-1])

if __name__ == '__main__':
    main()