from math import sqrt
 
def main():
    n = int(input())
    mas = []
    i = 2
    while i * i <= n:
        if n % i == 0:
            mas.append(i)
            n //= i
        else:
            i += 1
    if n > 1:
        mas.append(n)
    print(*mas)
 
 
if __name__ == '__main__':
    main()