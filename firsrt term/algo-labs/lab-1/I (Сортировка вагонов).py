kolvo_dlya_lohov = input()
A1 = list(map(int, input().split()))
n = len(A1)
 
A1 = A1[::-1]
 
T = [0]
t = []
A2 = [0]
res = []

 
while True:
    t.append(A1.pop())
    while t and A1 and t[-1] == A1[-1] + 1:
        t.append(A1.pop())
    if A2 and A1 and A2[-1] + 1 == A1[-1]:
        t.append(A1.pop())
    res.append((1, len(t)))
    T.extend(t)
    t = []
 
    if A2[-1] + 1 == T[-1]:
        t.append(T.pop())
        while t[-1] + 1 == T[-1]:
            t.append(T.pop())
 
    if t:
        res.append((2, len(t)))
        A2.extend(t)
        t = []
 
    if not A1:
        if t:
            res.append((2, len(t)))
            A2.extend(t)
        A2.pop(0)
        if A2 != list(range(1,n + 1)):
            res = 0
        break
if res==0:
    print(0)
else:
    for a in res:
        for i in range (a[1]):
            print (a[0], 1)

            
