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
weight = list(map(int, input().split()))
code = input()

alf = "abcdefghijklmnopqrstuvwxyz"
p = int(code, 2)
count_el = 0
res = ""

for i in weight:
    count_el += i
    
weight = list(map(lambda x: Decimal(x) / Decimal(count_el), weight))
rang = encode(0, 1, weight, n)

for j in range (count_el):
    for i in range(n):
        if rang[i][0] <= Decimal(p) / Decimal(2 ** len(code)) < rang[i][1] :
            res += alf[i]
            rang = encode(rang[i][0], rang[i][1], weight, n)
            break
print(res)

