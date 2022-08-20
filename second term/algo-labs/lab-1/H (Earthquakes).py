def update(idx, v, x, lx, rx, tree):
    if rx == lx + 1:
        tree[x] = v
    else:
        m = (lx + rx) >> 1
        if idx < m:
            update(idx, v, (x << 1) + 1, lx, m, tree)
        else:
            update(idx, v, (x << 1) + 2, m, rx, tree)
        tree[x] = min(tree[(x << 1) + 1], tree[(x << 1) + 2])


def query(l, r, p, x, lx, rx, tree):
    if lx >= r:
        return 0
    if rx <= l:
        return 0
    if tree[x] > p:
        return 0

    if rx == lx + 1:
        tree[x] = 10000000000
        return 1
    else:
        m = (lx + rx) >> 1
        tree[x] = min(tree[(x << 1) + 1], tree[(x << 1) + 2])
        return query(l, r, p, (x << 1) + 1, lx, m, tree) + query(l, r, p, (x << 1) + 2, m, rx, tree)


def main():
    import sys
    n, m = map(int, sys.stdin.readline().split())
    import math
    treeSize = 1 << (math.floor(math.log2(n)) + 1)
    tree = [10000000000 for _ in range(2 * treeSize)]
    for i in range(m):
        line = list(map(int, sys.stdin.readline().split()))
        if line[0] == 1:
            update(line[1], line[2], 0, 0, treeSize, tree)
        else:
            sys.stdout.write(str(query(line[1], line[2], line[3], 0, 0, treeSize, tree)) + '\n')


if __name__ == '__main__':
    main()