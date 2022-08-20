def dfs(arr, alf, w1, w2, n1, n2):
    used = [[0] * (n2 + 1) for _ in range(n1 + 1)]
    a = [1, 1]
    stack = []
    stack.append(a)
    while len(stack) > 0:
        curr = stack.pop()
        x = curr[0]
        y = curr[1]
        if arr[0][x] != arr[1][y]:
            return False
        used[x][y] = True
        for s in alf:
            if used[w1[x][s]][w2[y][s]] == False:
                stack.append([w1[x][s], w2[y][s]])

    return True


def main():
    alf = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
           "w", "x", "y", "z"]
    f = open("equivalence.in", "r")
    n1, m1, k1 = map(int, f.readline().split())

    arr = [[], []]
    arr[0] = [0 for _ in range(n1 + 1)]
    w1 = []
    for i in range(n1 + 1):
        w1.append(dict())
        for s in alf:
            w1[i][s] = 0

    kkk = [int(_) for _ in f.readline().split()]
    for i in kkk:
        arr[0][i] = 1

    for i in range(m1):
        a, b, c = map(str, f.readline().split())
        w1[int(a)][c] = int(b)

    n2, m2, k2 = map(int, f.readline().split())

    arr[1] = [0 for _ in range(n2 + 1)]
    w2 = []
    for i in range(n2 + 1):
        w2.append(dict())
        for s in alf:
            w2[i][s] = 0

    kkk = [int(_) for _ in f.readline().split()]
    for i in kkk:
        arr[1][i] = 1

    for i in range(m2):
        a, b, c = map(str, f.readline().split())
        w2[int(a)][c] = int(b)
    f.close()

    f = open("equivalence.out", "w")
    if dfs(arr, alf, w1, w2, n1, n2):
        f.write("YES\n")
    else:
        f.write("NO\n")
    f.close()


if __name__ == '__main__':
    main()