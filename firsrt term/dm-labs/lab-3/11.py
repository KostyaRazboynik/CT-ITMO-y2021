import sys
import copy

def main():
   n = int(sys.stdin.readline())
   mas = []

   def Subsets(curr, mas, n):
       for i in range(curr + 1, n + 1):
           newMas = copy.copy(mas)
           newMas.append(i)
           s = ""
           for el in newMas:
               s += str(el) + " "
           sys.stdout.write(s + '\n')
           Subsets(i, newMas, n)

   sys.stdout.write("" + '\n')
   Subsets(0, mas, n)

if __name__ == '__main__':
    main()
