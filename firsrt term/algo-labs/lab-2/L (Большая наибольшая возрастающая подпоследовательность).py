import math
import sys
def main():
    n, a_1, k, b, m = map(int, sys.stdin.readline().split())
    mas = [0] * n
    mas[0] = a_1
    for i in range(1, n):
        mas[i] = (k * mas[i - 1] + b) % m
    P =[0] * n
    M = [0] * (n + 1)
    L = 0
    for i in range(n):
        lo = 1
        hi = L
        while lo <= hi:
            mid = math.ceil((lo + hi) / 2)
            if mas[M[mid]] < mas[i]:
                lo = mid + 1
            else:
                hi = mid - 1
        newL = lo
        P[i] = M[newL - 1]
        M[newL] = i
        if newL > L:
            L = newL
    sys.stdout.write(str(L))

if __name__ == '__main__':
    main()