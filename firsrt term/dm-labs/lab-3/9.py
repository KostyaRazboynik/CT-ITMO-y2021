import sys

def main():

    def makePosl(n, s, nOpen, nClose):
        if nOpen + nClose == 2 * n:
            sys.stdout.write(s + '\n')
        else:
            if nOpen < n:
                makePosl(n, s + "(", nOpen + 1, nClose)
            if nOpen > nClose:
                makePosl(n, s + ")", nOpen, nClose + 1)

    n = int(sys.stdin.readline())
    S = ""
    start = 0
    end = 0
    makePosl(n, S, start, end)

if __name__ == '__main__':
    main()
