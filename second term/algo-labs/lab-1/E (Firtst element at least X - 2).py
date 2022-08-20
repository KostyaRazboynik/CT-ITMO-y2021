import sys

tree = []
treeSize = 1


def build(arr, x, lx, rx):
    global tree
    if (rx == lx + 1):
        if (lx < len(arr)):
            tree[x] = arr[lx]
    else:
        m = (lx + rx) // 2
        build(arr, 2 * x + 1, lx, m)
        build(arr, 2 * x + 2, m, rx)
        tree[x] = max(tree[2 * x + 1], tree[2 * x + 2])


def update(idx, v, x, lx, rx):
    global tree
    if rx == lx + 1:
        tree[x] = v
    else:
        m = (lx + rx) // 2
        if idx < m:
            update(idx, v, 2 * x + 1, lx, m)
        else:
            update(idx, v, 2 * x + 2, m, rx)
        tree[x] = max(tree[2 * x + 1], tree[2 * x + 2])


def query(v, l, x, lx, rx):
    if tree[x] < v:
        return -1
    if rx <= l:
        return -1
    if rx == lx + 1:
        return lx
    m = (lx + rx) // 2
    res = query(v, l, 2 * x + 1, lx, m)
    if (res == -1):
        res = query(v, l, 2 * x + 2, m, rx)
    return res


def main():
    global tree, treeSize
    n, m = map(int, sys.stdin.readline().split())
    arr = [int(_) for _ in sys.stdin.readline().split()]
    while treeSize < n:
        treeSize *= 2
    tree = [0] * (2 * treeSize)
    build(arr, 0, 0, treeSize)
    for i in range(m):
        type, x, y = map(int, sys.stdin.readline().split())
        if type == 2:
            sys.stdout.write(str(query(x, y, 0, 0, treeSize)) + '\n')
        else:
            update(x, y, 0, 0, treeSize)


if __name__ == '__main__':
    main()