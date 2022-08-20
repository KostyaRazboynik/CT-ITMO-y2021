from decimal import *
import math


def encode(low: int, high: int, P: list, n) -> list:
    all_ranges = []
    
    for i in range(n):
        curr_rang = Decimal(high) - Decimal(low)
        
        if i == 0:
            all_ranges.append([Decimal(str(low)), low + curr_rang * Decimal(str(P[i]))])
        else:
            all_ranges.append([all_ranges[i - 1][1], all_ranges[i - 1][1] + curr_rang * Decimal((P[i]))])

    return all_ranges
        
getcontext().prec = 200
n = int(input())
word = input()
weight = [0] * n
for i in word:
    weight[ord(i) - ord("a")] += 1
    
print(n)
print(*weight)

P = weight
weight = list(map(lambda x: Decimal(x) / Decimal(len(word)), weight))
rang = encode(0, 1, weight, n)


for i in word:
    el = ord(i) - ord("a")
    rang = encode(rang[el][0], rang[el][1], weight, n)

flag = 0
for i in range(1, 100000000000):
    if flag == 0:
        p = max(math.floor(rang[0][0] * 2 ** i) - 1, 0)
        while 2 ** i > p:
            if Decimal(p) / Decimal(2 ** i) >= rang[-1][1]:
                break
            elif rang[0][0] <= Decimal(p) / Decimal(2 ** i) < rang[-1][1]:
                print((i - len(bin(p)[2:])) * "0" + bin(p)[2:])
                flag += 1
                break
            p += 1
    else:
        break

