import copy
import sys


def main():
    s = sys.stdin.readline()

    # prev
    prev = [i for i in s]
    nexT = copy.copy(prev)
    n = len(prev) - 1
    while (n >= 0) and (prev[n] != "1"):
        prev[n] = "1"
        n -= 1
    if n == -1:
        sys.stdout.write("-" + '\n')
    else:
        prev[n] = "0"
        sys.stdout.write("".join(prev) + '\n')
        res = ""
        for i in prev:
            res += str(i)
        sys.stdout.write(res + '\n')

    # next
    n = len(nexT) - 1
    while (n >= 0) and (nexT[n] != "0"):
        nexT[n] = "0"
        n -= 1
    if n == -1:
        sys.stdout.write("-" + '\n')
    else:
        nexT[n] = "1"
        res = ""
        for i in nexT:
            res += str(i)
        sys.stdout.write(res + '\n')


if __name__ == '__main__':
    main()