def main():
    n, s = map(int, input().split())
    st = input()
    w = [0] * 1000
    curr = [int(i) for i in st.split()]
    for i in range(1, n + 1):
        w[i] = curr[i - 1]
    pr = [1000000000] * 1000000
    pr[0] = 0
    for k in range(1, n + 1):
        for i in range(s - w[k], -1, -1):
            if pr[i + w[k]] > pr[i] + 1:
                pr[i + w[k]] = pr[i] + 1

    if pr[s] < 1000000000:
        print(pr[s])
    else:
        print(0)
if __name__ == '__main__':
    main()