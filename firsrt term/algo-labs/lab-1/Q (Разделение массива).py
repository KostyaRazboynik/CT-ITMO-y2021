def FFF(mas, n, k):
   
    left = max(mas)
    right = 0
 
    for i in range(n):
        right += mas[i]
 
    result = 0
    while (left <= right):
        m = (left + right) // 2
        
        count = 0
        summ = 0
        flag = 0
        for i in range(n):
            if (mas[i] > m):
                flag += 1
                break
            summ += mas[i]
            if (summ > m):
                count += 1
                summ = mas[i]
        count += 1
        
        if (flag == 0 and count <= k):
            result = m
            right = m - 1
        else:
            left = m + 1
 
    print (result)
    return
 
n, k = map(int, input().split())
mas = list(map(int, input().split()))
FFF(mas, n, k)
