def main():
    n = int(input())
    s = input()
    mas = [int(i) for i in s.split()]
    b = [1] * n
    maxx = 1
    index = 0
    for i in range(1, n):
        for j in range(i):
            if mas[j] < mas[i] and b[j] >= b[i]:
                b[i] = b[j] + 1
                if b[i] > maxx:
                    maxx = b[i]
                    index = i

    res = []
    res.append(mas[index])
    for i in range (maxx - 1, 0, -1):
        for j in range(index):
            if mas[j] < mas[index] and b[j] == i:
                index = j
                res.append(mas[j])
                break

    print(len(res))
    s = ''
    for i in range(len(res) - 1, -1, -1):
        s += str(res[i]) + " "
    print(s)

if __name__ == '__main__':
    main()



# import math
# def main():
#     n = int(input())
#     s = input()
#     mas = [int(i) for i in s.split()]
#     P =[0] * n
#     M = [0] * (n + 1)
#     L = 0
#     for i in range (n):
#         lo = 1
#         hi = L
#         while lo <= hi:
#             mid = math.ceil((lo + hi) / 2)
#             if mas[M[mid]] < mas[i]:
#                 lo = mid + 1
#             else:
#                 hi = mid - 1
#         newL = lo
#         P[i] = M[newL - 1]
#         M[newL] = i
#         if newL > L:
#             L = newL
#     S = [0] * L
#     k = M[L]
#     for i in range (L-1, -1, -1):
#         S[i] = mas[k]
#         k = P[k]
#     print (len(S))
#     print(*S)
 
# if __name__ == '__main__':
#     main()