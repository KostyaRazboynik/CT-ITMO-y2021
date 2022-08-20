from collections import deque
Queue = deque([])
minQ = deque([])
n = int(input())
for i in range(n):
    s = input()
    if s[0] == "+":
        num = int(s[2:])
        Queue.append(num)
        if len(minQ) == 0:
            minQ.append(num)
        else:
            while len(minQ) > 0 and minQ[-1] > num :
                minQ.pop()
            minQ.append(num) 
        if len(minQ) > 0:
            print(minQ[0])
            
        else:
            print("-1")
    else:
        if len(Queue) > 0:
            if Queue[0] == minQ[0]:
                minQ.popleft()
            Queue.popleft()
            if len(minQ) > 0:
                print(minQ[0])
            else:
                print("-1")
        else:
            print("-1")
