import sys
 
 
def cmp_to_key(mycmp):
 
    class K(object):
        def __init__(self, obj, *args):
            self.obj = obj
 
        def __lt__(self, other):
            return mycmp(self.obj, other.obj) < 0
 
        def __gt__(self, other):
            return mycmp(self.obj, other.obj) > 0
 
        def __eq__(self, other):
            return mycmp(self.obj, other.obj) == 0
 
        def __le__(self, other):
            return mycmp(self.obj, other.obj) <= 0
 
        def __ge__(self, other):
            return mycmp(self.obj, other.obj) >= 0
 
        def __ne__(self, other):
            return mycmp(self.obj, other.obj) != 0
 
    return K
 
 
def compare(x, y):
    print(f"1 {x} {y}")
    ans = input()
    if ans[0] == 'Y':
        return -1
    return 1
 
 
def main():
 
    lamps = [int(i + 1) for i in range(int(sys.stdin.readline()))]
 
    lamps.sort(key=cmp_to_key(compare))
 
    print(0, *lamps)
 
 
if __name__ == '__main__':
    main()