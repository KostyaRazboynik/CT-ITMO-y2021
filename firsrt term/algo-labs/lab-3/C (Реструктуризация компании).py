import sys
 
def main():
 
    MAX = 200001
    mas = [0] * MAX
    cur = [0] * MAX
 
    def change(i):
        if mas[i] != i:
            mas[i] = change(mas[i])
        return mas[i]
 
    def magic(i, j):
        if (change(i) != change(j)):
            mas[change(j)] = change(i)
 
    n, q = map(int, sys.stdin.readline().split())
 
    for i in range(1, n + 1):
        mas[i] = i
        cur[i] = i + 1
 
    for l in range(q):
        
        type, x, y = map(int, sys.stdin.readline().split())
 
        if type == 3:
            if change(x) == change(y):
                sys.stdout.write('YES' + '\n')
            else:
                sys.stdout.write('NO' + '\n')
 
        elif type == 1:
            magic(x, y)
 
        else:
            i = x + 1
            while i <= y:
                tmp = cur[i]
                magic(i - 1, i)
                cur[i] = cur[y]
                i = tmp
 
if __name__ == '__main__':
    main()