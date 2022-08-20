summ = []


def printRes(v, length, T, w):
    global summ
    if summ[v][length] != -1:
        return summ[v][length]

    if length == 0:
        summ[v][length] = T[v]
        return summ[v][length]
    else:
        summ[v][length] = 0
        for to in w[v]:
            summ[v][length] += printRes(to, length - 1, T, w)
            summ[v][length] %= 1000000007
        return summ[v][length]


def main():
    global summ
    f = open("problem5.in", "r")
    n, m, k, l = map(int, f.readline().split())
    n += 1
    arr1 = [0] * n
    z = [[] for _ in range(n)]

    kkk = [int(_) for _ in f.readline().split()]
    for i in kkk:
        arr1[i] = 1

    for i in range(m):
        a, b, c = map(str, f.readline().split())
        z[int(a)].append([int(b), ord(c) - 97])

    f.close()

    summ = [[-1] * (l + 1) for _ in range(101)]
    w = [[] for _ in range(101)]
    arr = [0] * (101)

    queue = []
    queue.append({1})

    mas = set()
    mas.add(tuple({1}))

    count = 1
    num = dict()
    num[tuple({1})] = 1
    arr[1] = arr1[1]

    while len(queue) > 0:
        curr = queue[0]
        queue.pop(0)

        for i in range(26):
            new = set()
            for j in curr:
                for y in z[j]:
                    if y[1] == i:
                        new.add(y[0])

            if len(new) == 0:
                continue

            if tuple(new) not in mas:
                mas.add(tuple(new))
                num[tuple(new)] = count + 1
                count += 1
                queue.append(new)

                for j in new:
                    if arr1[j] == 1:
                        arr[num[tuple(new)]] = 1
                        break

            w[num[tuple(curr)]].append(num[tuple(new)])

    f = open("problem5.out", "w")
    f.write(str(printRes(1, l, arr, w)) + '\n')
    f.close()


if __name__ == '__main__':
    main()