import sys

def main():
    n, k = map(int, sys.stdin.readline().split())
    mas = list(map(int, input().split()))
    arr = list(map(int, input().split()))
    for i in range (k):
        r = n - 1
        l = 0
        while l < r:
            m = (l + r) // 2
            if mas[m] >= arr[i]:
                r = m
            else:
                l = m + 1
            start = abs(mas[l] - arr[i])
            end = abs(mas[l - 1] - arr[i])
        if l > 0 and mas[l] != arr[i] and end <= start:
            print(mas[l - 1])
        else:
            print(mas[l])

if __name__ == '__main__':
    main()