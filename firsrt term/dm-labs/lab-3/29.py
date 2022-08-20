import sys

def main():
    def nextSum(b):
        b[-1] -= 1
        b[-2] += 1
        if b[-2] > b[-1]:
            b[-2] += b[-1]
            b.remove(b[-1])
        else:
            while b[-2] * 2 <= b[-1]:
                b.append(b[-1] - b[-2])
                b[-2] = b[-3]
        b.sort()
        res = [str(i) for i in b]
        return "+".join(res)

    n = ""
    s = sys.stdin.readline()
    i = 0
    while s[i] != "=":
        n += s[i]
        i += 1

    if n != s[len(n) + 1: len(s) - 1]:
        mas = [int(i) for i in s[len(n) + 1:].split("+")]
        sys.stdout.write(s[:len(n) + 1] + nextSum(mas) + '\n')
    else:
        sys.stdout.write("No solution" + '\n')


if __name__ == '__main__':
    main()