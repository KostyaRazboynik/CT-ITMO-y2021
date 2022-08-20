flag = False
mod = 1000000007


def check(x, used, visited, alf, w):
    global flag
    if used[x] == 0 or flag is True:
        return
    if visited[x] == 1:
        flag = True
        return
    visited[x] = 1
    for word in alf:
        if w[x].get(word) is not None:
            check(w[x].get(word), used, visited, alf, w)
            if flag is True:
                return
    visited[x] = 0


def res(x, used, arr, alf, w):
    if used[x] == 0:
        return 0
    count = 0
    if arr[x] == 1:
        count += 1
    for word in alf:
        if w[x].get(word) is not None:
            count = (count + res(w[x].get(word), used, arr, alf, w) + mod) % mod
    return count % mod


def find(x, visited, used, z, alf):
    if visited[x] == 1:
        return
    visited[x] = 1
    used[x] = 1
    for word in alf:
        if z[x].get(word) is not None:
            for a in z[x].get(word):
                find(a, visited, used, z, alf)


def main():
    global flag
    f = open("problem3.in", "r")
    n, m, k = map(int, f.readline().split())
    w = [{} for _ in range(n)]
    z = [{} for _ in range(n)]
    alf = set()
    arr = [0] * n

    kkk = [int(_) for _ in f.readline().split()]
    for i in kkk:
        arr[i - 1] = 1

    for i in range(m):
        a, b, c = map(str, f.readline().split())
        w[int(a) - 1][c] = int(b) - 1
        if z[int(b) - 1].get(c) is None:
            z[int(b) - 1][c] = []
        z[int(b) - 1][c].append(int(a) - 1)
        alf.add(c)
    f.close()

    was = [0] * n
    used = [0] * n

    for i in range(n):
        if arr[i] == 1:
            find(i, was, used, z, alf)
    was = [0] * n

    check(0, used, was, alf, w)

    f = open("problem3.out", "w")
    if flag is True:
        f.write("-1\n")
    else:
        f.write(str(res(0, used, arr, alf, w)))
    f.close()


if __name__ == '__main__':
    main()