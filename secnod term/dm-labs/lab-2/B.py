def main():
    f = open("epsilon.in", "r")
    n, start = map(str, f.readline().split())
    n = int(n)
    ARR = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
           "W", "X", "Y", "Z"]
    table = dict()
    eps = set()

    for S in ARR:
        table[S] = []

    for i in range(n):
        line = [_ for _ in f.readline().split()]
        if len(line) == 3:
            A = line[0]
            B = line[2]
            table[A].append(B)
        else:
            eps.add(line[0])

    f.close()

    for j in range(10):
        for S in ARR:
            if S in eps:
                continue
            for el in table[S]:
                flag = True

                for i in range(len(el)):
                    if el[i] not in eps:
                        flag = False
                        break

                if flag:
                    eps.add(S)
                    break
    f = open("epsilon.out", "w")
    for el in sorted(eps):
        f.write(el)
        f.write(" ")
    f.close()


if __name__ == '__main__':
    main()