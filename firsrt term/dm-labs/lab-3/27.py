import sys

def main():
    s = sys.stdin.readline()
    n = len(s)
    res = ""

    balans = 0
    for i in range(len(s) - 2, -1, -1):
        if s[i] == '(':
            balans -= 1
        else:
            balans += 1
        if (s[i] == '(' and balans > 0):
            balans -= 1
            open = (n - i - 1 - balans) // 2
            close = n - i - 1 - open
            res = s[:i] + ')' + '(' * open + ')' * (close - 1)
            break
    if res == "":
        sys.stdout.write("-" + '\n')
    else:
        sys.stdout.write(res + '\n')

if __name__ == '__main__':
    main()