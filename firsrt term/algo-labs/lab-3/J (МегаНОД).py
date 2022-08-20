from functools import reduce
 
def main():
 
    def gcd(a, b):
        return gcd(b, a % b) if b else a
 
    input()
    s = input()
    mas =[int(x) for x in s.split()]
    print(abs(reduce(gcd, mas)))
 
if __name__ == '__main__':
    main()