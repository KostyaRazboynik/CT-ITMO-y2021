from math import *
import sys
    
def Encode(code):
    n = len(code)
    bitCount = ceil(log2(n + 1 + ceil(log2(n + 1))))
    res = [0] * (n + bitCount)
    j = 0
    t = 0
    for i in range(len(res)):
        if i + 1 == 2 ** j:
            res[i] = 2
            j += 1
        else:
            res[i] = int(code[t])
            t += 1
    j = 0
    for i in range(len(res)):
        if res[i] == 2:
            summ = 0
            currBit = bin(i + 1)[2:]
            for k in range(i + 1, len(res)):
                sK = bin(k + 1)[2:]
                if int(sK[-(j + 1)]) == 1:
                    summ += int(res[k])
            res[i] = summ % 2
            j += 1
    s = ''
    for i in res:
        s += str(i)
    sys . stdout . write (s + '\n')
    return
    
        

def DecodeAndFix(code):
    n = len(code)
    noProblem = list(code)
    error = 0
    bitCount = ceil(log2(n))
    for i in range(bitCount):
        summ = 0
        for j in range(2 ** i, n):
            sJ = bin(j + 1)[2:]
            if int(sJ[s-(i + 1)]) == 1:
                summ += int(code[j])
        if summ % 2 != int(code[2 ** i - 1]):
            error += 2 ** i
    if error != 0:
        noProblem[error - 1] = str(int(noProblem[error - 1]) ^ 1)
    j = 0
    s = ""
    for i in range(len(noProblem)):
        if i + 1 != 2 ** j:
            s += str(noProblem[i])
        else:
            j += 1
    sys . stdout . write (s + '\n')
    return
      


num = int(sys. stdin . readline())
code = input()

if num == 1:
    Encode(code)
else:
    DecodeAndFix(code)
