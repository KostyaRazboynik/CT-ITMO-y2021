def Check(arr, w, s):
    a = [{1}, set()]

    for i in range(len(s) - 1):
        a[(1 + i % 2) % 2].clear()
        for j in a[i % 2]:
            for k in range(len(w[j][ord(s[i]) - 97])):
                a[(1 + i % 2) % 2].add(w[j][ord(s[i]) - 97][k])

    for i in a[(len(s) - 1) % 2]:
        if arr[i]:
            return True
    return False


def main():
    f = open("problem2.in", "r")
    s = f.readline()
    n, m, k = map(int, f.readline().split())
    n += 1

    arr = [0] * n
    kkk = [int(_) for _ in f.readline().split()]

    for i in kkk:
        arr[i] = 1

    w = [[[] for _ in range(26)] for __ in range(n)]
    for i in range(m):
        a, b, c = map(str, f.readline().split())
        w[int(a)][ord(c) - 97].append(int(b))
    f.close()

    f = open("problem2.out", "w")
    if Check(arr, w, s):
        f.write("Accepts")
    else:
        f.write("Rejects")
    f.close()


if __name__ == '__main__':
    main()