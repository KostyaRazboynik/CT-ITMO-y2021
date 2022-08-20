from math import sqrt
import sys
 
def main():
    def YooYaKrut(n):
        curr = []
        while n % 2 == 0:
            curr.append(2)
            n = n // 2
 
        for i in range(3, int(sqrt(n)) + 1, 2):
            while n % i == 0:
                curr.append(i)
                n = n // i
        if n > 2:
            curr.append(n)
        print(*curr)
 
    for i in range(int(sys.stdin.readline())):
        YooYaKrut(int(sys.stdin.readline()))
 
if __name__ == '__main__':
    main()