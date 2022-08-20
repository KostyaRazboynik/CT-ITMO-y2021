n = int(input())
mas = [int(i) for i in input().split()]
def count_inversion(mas):
    return merge_count_inversion(mas) [1]

def merge_count_inversion(mas):
    if len(mas) <= 1:
        return mas, 0
    M = int( len(mas) / 2 )
    left, a = merge_count_inversion(mas[:M])
    right, b = merge_count_inversion(mas[M:])
    res, c = merge_count_split_inversion(left, right)
    return res, (a + b + c)

def merge_count_split_inversion(left, right):
    res = []
    count = 0
    i, j = 0, 0
    left_len = len(left)
    while i < left_len and j < len(right):
        if left[i] <= right[j]:
            res.append(left[i])
            i += 1
        else:
            res.append(right[j])
            count += left_len - i
            j += 1
    res += left[i:]
    res += right[j:]
    return res, count
print(count_inversion (mas))
