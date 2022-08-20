import sys
 
def main():
 
    n = int(sys.stdin.readline())
    mas = [int(i) for i in sys.stdin.readline().split()]
    p = [i for i in range(n + 1)]
 
    def find(i):
        if p[i] != i:
            p[i] = find(p[i])
        return p[i]
 
    for i in range(n):
        x = find(mas[i])
        sys.stdout.write(str(x) + ' ')
        if x + 1 > n:
            p[find(x)] = find(1)
        else:
            p[find(x)] = find(x + 1)
    sys.stdout.write('\n')
 
if __name__ == '__main__':
    main()