def xor(a, b):
    return a ^ b


def Check(a, b, used, arr, arr2, mas, mas2):
    if used[a]:
        return True
    used[a] = 1
    if xor(arr[a], arr2[b]):
        return False
    for i in range(26):
        A = mas[a][i]
        B = mas2[b][i]

        if xor(A == 0, B == 0):
            return False
        if not used[A]:
            if not Check(A, B, used, arr, arr2, mas, mas2):
                return False
    return True


def main():
    f = open("isomorphism.in", "r")
    n, m, k = map(int, f.readline().split())
    n += 1

    kkk = [int(_) for _ in f.readline().split()]
    arr = [0] * n
    for i in kkk:
        arr[i] = 1

    mas = [[0] * 26 for _ in range(n)]
    for i in range(m):
        a, b, c = map(str, f.readline().split())
        mas[int(a)][ord(c) - 97] = int(b)

    n2, m2, k2 = map(int, f.readline().split())
    n2 += 1
    if n == n2:
        kkk2 = [int(_) for _ in f.readline().split()]
        arr2 = [0] * n
        for i in kkk2:
            arr2[i] = 1

        mas2 = [[0] * 26 for _ in range(n)]
        for i in range(m2):
            a, b, c = map(str, f.readline().split())
            mas2[int(a)][ord(c) - 97] = int(b)
        f.close()

        f = open("isomorphism.out", "w")

        used = [0] * n
        if Check(1, 1, used, arr, arr2, mas, mas2):
            f.write("YES")
        else:
            f.write("NO")
        f.close()
    else:
        f = open("isomorphism.out", "w")
        f.write("NO")
        f.close()


if __name__ == '__main__':
    main()