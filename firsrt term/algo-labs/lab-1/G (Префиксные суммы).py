n,m = map(int, input().split())
mas = list(map(int, input().split()))
prmas = []
prmas.append(mas[0])
for i in range(1, n):
    prmas.append(mas[i] + prmas[i-1])
for j in range(m):  
    l, r = map(int, input().split())
    l -= 1
    r -= 1
    Right = prmas[r]
    Left = prmas[l-1]
    if l <= 0:
        print(Right)
    if l > 0:
        print(Right - Left)
