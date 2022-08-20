def main():
    f = open("problem1.in", "r")
    s = f.readline()
    n, m, k = map(int, f.readline().split())
    n += 1

    arr = [0] * n
    kkk = [int(_) for _ in f.readline().split()]

    for i in kkk:
        arr[i] = 1

    w = [[0] * 26 for _ in range(n)]
    for i in range(m):
        a, b, c = map(str, f.readline().split())
        w[int(a)][ord(c) - 97] = int(b)
    f.close()

    x = 1
    for i in range(len(s) - 1):
        x = w[x][ord(s[i]) - 97]

    f = open("problem1.out", "a")
    if arr[x] == 1:
        f.write("Accepts")
    else:
        f.write("Rejects")
    f.close()


if __name__ == '__main__':
    main()