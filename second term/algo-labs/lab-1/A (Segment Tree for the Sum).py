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
        tree[x] = tree[2 * x + 1] + tree[2 * x + 2]


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
        tree[x] = tree[2 * x + 1] + tree[2 * x + 2]


def query(l, r, x, lx, rx):
    if r <= lx:
        return 0
    if rx <= l:
        return 0
    if lx >= l and rx <= r:
        return tree[x]
    m = (lx + rx) // 2

    return query(l, r, 2 * x + 1, lx, m) + query(l, r, 2 * x + 2, m, rx)


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