import sys
from math import sqrt, gcd
 
def main():
 
    n, k = map(int, sys.stdin.readline().split())
    ab = n * k
    res = []
 
    for i in range(n, int(sqrt(ab)) + 1, n):
        if ab % i == 0:
            if gcd(i, ab // i) == n:
                res.append([i, ab // i])
                
    if len(res) == 1 and res[0][0] == res[0][1]:
        sys.stdout.write(str(res[0][0]) + ' ' + str(res[0][1]) + '\n')
    else:
        for i in res:
            sys.stdout.write(str(i[0]) + ' ' + str(i[1]) + '\n')
        for i in range(len(res) - 1, -1, -1):
            sys.stdout.write(str(res[i][1]) + ' ' + str(res[i][0]) + '\n')
 
 
if __name__ == '__main__':
    main()