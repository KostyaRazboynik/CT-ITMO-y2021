n, x, y = map(int, input().split())
l = 0
r = (n - 1) * max(x, y)
while r > l + 1:
    m = (r + l) // 2
    if (m // x + m // y) < n - 1:
        l = m
    else:
        r = m
print(r + min(x, y))
