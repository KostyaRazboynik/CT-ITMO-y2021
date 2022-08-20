import sys

input = sys.stdin.buffer.readline


def propihon(x, lx, rx, tree):
    if (tree[x][0] == -10000000000 or rx == lx + 1):
        return
    tree[(x << 1) + 1][0] = tree[x][0]
    tree[(x << 1) + 1][1] = tree[x][0]
    tree[(x << 1) + 2][0] = tree[x][0]
    tree[(x << 1) + 2][1] = tree[x][0]
    tree[x][0] = -10000000000


def update(l, r, v, x, lx, rx, tree):
    propihon(x, lx, rx, tree)
    if (lx >= r):
        return
    if (rx <= l):
        return
    if lx >= l and rx <= r:
        tree[x][0] = v
        tree[x][1] = v
    else:
        m = (lx + rx) >> 1
        update(l, r, v, (x << 1) + 1, lx, m, tree)
        update(l, r, v, (x << 1) + 2, m, rx, tree)
        tree[x][1] = min(tree[(x << 1) + 1][1], tree[(x << 1) + 2][1])


def query(l, r, x, lx, rx, tree):
    propihon(x, lx, rx, tree)
    if lx >= r:
        return 10000000000
    if rx <= l:
        return 10000000000
    if lx >= l and rx <= r:
        return tree[x][1]
    else:
        m = (lx + rx) >> 1
        return min(query(l, r, (x << 1) + 1, lx, m, tree), query(l, r, (x << 1) + 2, m, rx, tree))


def main():
    n, m = map(int, input().split())
    import math
    treeSize = 1 << (math.floor(math.log2(n)) + 1)
    tree = [[0, 0] for _ in range(2 * treeSize)]
    for i in range(m):
        line = list(map(int, input().split()))
        if line[0] == 1:
            update(line[1], line[2], line[3], 0, 0, treeSize, tree)
        else:
            sys.stdout.write(str(query(line[1], line[2], 0, 0, treeSize, tree)) + '\n')


if __name__ == '__main__':
    main()