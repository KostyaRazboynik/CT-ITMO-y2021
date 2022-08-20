def find(pos, l, count, arr, a):
    if count[pos][l] != -4:
        return count[pos][l]

    if l != 0:
        count[pos][l] = 0
        for i in a[pos]:
            count[pos][l] += find(i, l - 1, count, arr, a)
        return count[pos][l]
    else:
        count[pos][l] = arr[pos]
        return count[pos][l]


def main():
    f = open("problem4.in", "r")
    n, m, k, l = map(int, f.readline().split())
    n += 1

    kkk = [int(_) for _ in f.readline().split()]
    arr = [0] * n
    for i in kkk:
        arr[i] = 1

    mas = [[] for _ in range(n)]
    for i in range(m):
        a, b, c = map(str, f.readline().split())
        mas[int(a)].append(int(b))
    f.close()

    count = [[-4] * (l + 1) for _ in range(n)]
    x = find(1, l, count, arr, mas) % 1000000007

    f = open("problem4.out", "w")
    f.write(str(x))
    f.close()


if __name__ == '__main__':
    main()