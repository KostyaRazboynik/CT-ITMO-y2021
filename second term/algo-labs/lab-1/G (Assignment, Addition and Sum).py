import sys


def check(a, b):
    if a == -10000000000:
        return b
    return a + b


def propihon(x, lx, rx, tree):
    if (tree[x][0] == -10000000000 and tree[x][1] == -10000000000) or rx == lx + 1:
        return
    m = (lx + rx) >> 1
    if tree[x][0] != -10000000000:
        tree[(x << 1) + 1][0] = tree[x][0]
        tree[(x << 1) + 1][1] = -10000000000
        tree[(x << 1) + 1][2] = tree[x][0] * (m - lx)

        tree[(x << 1) + 2][0] = tree[x][0]
        tree[(x << 1) + 2][1] = -10000000000
        tree[(x << 1) + 2][2] = tree[x][0] * (rx - m)

    if tree[x][1] != -10000000000:
        tree[(x << 1) + 1][1] = check(tree[(x << 1) + 1][1], tree[x][1])
        tree[(x << 1) + 1][2] += (m - lx) * tree[x][1]

        tree[(x << 1) + 2][1] = check(tree[(x << 1) + 2][1], tree[x][1])
        tree[(x << 1) + 2][2] += (m - lx) * tree[x][1]

    tree[x][0] = -10000000000
    tree[x][1] = -10000000000


def update(l, r, v, x, lx, rx, tree):
    propihon(x, lx, rx, tree)
    if lx >= r:
        return
    if rx <= l:
        return
    if lx >= l and rx <= r:
        tree[x][0] = v
        tree[x][1] = -10000000000
        tree[x][2] = (rx - lx) * v
    else:
        m = (lx + rx) >> 1
        update(l, r, v, (x << 1) + 1, lx, m, tree)
        update(l, r, v, (x << 1) + 2, m, rx, tree)
        tree[x][2] = tree[(x << 1) + 1][2] + tree[(x << 1) + 2][2]


def add(l, r, v, x, lx, rx, tree):
    propihon(x, lx, rx, tree)
    if lx >= r:
        return
    if rx <= l:
        return
    if lx >= l and rx <= r:
        tree[x][1] = check(tree[x][1], v)
        tree[x][2] += (rx - lx) * v
    else:
        m = (lx + rx) >> 1
        add(l, r, v, (x << 1) + 1, lx, m, tree)
        add(l, r, v, (x << 1) + 2, m, rx, tree)
        tree[x][2] = tree[(x << 1) + 1][2] + tree[(x << 1) + 2][2]


def query(l, r, x, lx, rx, tree):
    propihon(x, lx, rx, tree)
    if lx >= r:
        return 0
    if rx <= l:
        return 0
    if lx >= l and rx <= r:
        return tree[x][2]
    else:
        m = (lx + rx) >> 1
        return query(l, r, (x << 1) + 1, lx, m, tree) + query(l, r, (x << 1) + 2, m, rx, tree)


def main():
    n, m = map(int, sys.stdin.readline().split())
    import math
    treeSize = 1 << (math.floor(math.log2(n)) + 1)
    tree = [[-10000000000, -10000000000, 0] for _ in range(2 * treeSize)]
    for i in range(m):
        line = list(map(int, sys.stdin.readline().split()))
        if line[0] == 1:
            update(line[1], line[2], line[3], 0, 0, treeSize, tree)
        elif line[0] == 2:
            add(line[1], line[2], line[3], 0, 0, treeSize, tree)
        else:
            sys.stdout.write(str(query(line[1], line[2], 0, 0, treeSize, tree)) + '\n')


if __name__ == '__main__':
    main()