import sys

 
def comp(l, r):
    global last
    
    if last == r:
        sys . stdout . write ("+ " + str(l) + '\n')
        sys.stdout.flush()
        last = l
        if sys.stdin.readline()[0] == 'Y':
            return True
        return False
    
    if last == l:
        sys . stdout . write ("+ " + str(r) + '\n')
        sys.stdout.flush()
        last = r
        if sys.stdin.readline()[0] == 'N':
            return True
        return False
    
    sys . stdout . write ("+ " + str(l) + '\n')
    sys.stdout.flush()
    sys.stdin.readline()
    sys . stdout . write ("+ " + str(r) + '\n')
    sys.stdout.flush()
    last = r
    if sys.stdin.readline()[0] == 'N':
        return True
    return False
        
 
def mergeSort(a):
    m = len(a)//2
    if m > 0:
        l = mergeSort(a[:m])
        r = mergeSort(a[m:])
        return merge(l, r)
    return a
 
def merge(l, r):
    res = []
    i, j = 0, 0
    while i < len(l) and j < len(r):
        
        if comp(l[i], r[j]):
            res.append(l[i])
            i += 1
        else:
            res.append(r[j])
            j += 1
 
    while i < len(l):
        res.append(l[i])
        i += 1
    while j < len(r):
        res.append(r[j])
        j += 1
        
    return res
            
 
 
n, k = map(int, sys.stdin.readline().split())
a=[]
for i in range(1, n + 1):
    a.append(i)
last = -1


res = mergeSort(a)
fin = [0]*n
 
for i in range(len(res)):
    fin[res[i] - 1] = i + 1

s = "!"
for i in fin:
    s += " " + str(i)
sys . stdout . write (s + '\n')
sys.stdout.flush()
