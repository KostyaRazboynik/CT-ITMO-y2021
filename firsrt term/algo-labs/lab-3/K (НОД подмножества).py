import sys
 
def main():
 
    n, d = map(int, sys.stdin.readline().split())
    mas = [int(i) for i in sys.stdin.readline().split()]
    res = []
    for i in mas:
        if i % d == 0:
            if len(res) == 0:
                res.append(i)
            else:
                if i % res[-1] != 0:
                    res.append(i)
    if len(res) == 0:
        sys.stdout.write(str(-1) + '\n')
    elif len(res) == 1:
        if res[0] == d:
            sys.stdout.write(str(1) + '\n')
            sys.stdout.write(str(d) + '\n')
        else:
            sys.stdout.write(str(-1) + '\n')            
 
    else:
        sys.stdout.write(str(len(res)) + '\n')
        for i in res:
            sys.stdout.write(str(i) + ' ')
        sys.stdout.write('\n')
 
 
if __name__ == '__main__':
    main()