string = input()
length = len(string)
mas = [''] * length
for i in range(length):
    for j in range(length):
        mas[j] = string[j] + mas[j]
    mas.sort()
print(mas[0])
