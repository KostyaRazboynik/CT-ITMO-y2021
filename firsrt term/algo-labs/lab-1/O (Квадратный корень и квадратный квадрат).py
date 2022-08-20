C = float(input())
L, R = 0, C
for i in range(100000):
    M = (L + R) / 2
    x = M * M + M ** (1 / 2)
    if x < C:
        L = M
    else:
        R = M
print(R)
