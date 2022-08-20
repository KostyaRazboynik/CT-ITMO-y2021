import sys


def check(arr, root, mi, ma):
    if root[0] <= mi or ma <= root[0]:
        return False
    if root[1] == -1 and root[2] == -1:
        return True
    if root[1] == -1:
        return check(arr, arr[root[2] - 1], root[0], ma)
    if root[2] == -1:
        return check(arr, arr[root[1] - 1], mi, root[0])
    return check(arr, arr[root[1] - 1], mi, root[0]) and check(arr, arr[root[2] - 1], root[0], ma)


def main():
    n = int(sys.stdin.readline())
    arr = [[0, 0, 0] for _ in range(n)]
    for _ in range(n):
        arr[_][0], arr[_][1], arr[_][2] = map(int, sys.stdin.readline().split())
    m = int(sys.stdin.readline())

    res = check(arr, arr[m - 1], 0, 1000000001)

    if res == True:
        sys.stdout.write("YES\n")
    else:
        sys.stdout.write("NO\n")


if __name__ == '__main__':
    main()