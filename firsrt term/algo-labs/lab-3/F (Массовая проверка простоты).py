import sys
def main():
    n = int(sys.stdin.readline())
    mas = []
    for i in range(n):
        mas.append(int(sys.stdin.readline()))
    MAX = max(mas) + 1
    primes = [1] * MAX
    primes[0] = 0
    primes[1] = 0
 
    def sieve():
        i = 2
        while i * i <= MAX + 1:
            if primes[i] == 0:
                i += 1
                continue
 
            j = 2 * i
            while j < MAX:
                primes[j] = 0
                j += i
            i += 1
 
    sieve()
    for i in mas:
        if primes[i] == 1:
            sys.stdout.write("YES" + '\n')
        else:
            sys.stdout.write("NO" + '\n')
 
if __name__ == "__main__":
    main()