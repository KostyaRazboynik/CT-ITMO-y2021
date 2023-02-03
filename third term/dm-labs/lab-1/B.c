#include <stdio.h>
 
#include <malloc.h>
#include <stdlib.h>
 
int main()
{
 
    int n;
    int **g;
 
    scanf("%i", &n);
    g = (int **) malloc(n * sizeof(int));
    for (int i = 0; i < n; i++)
    {
        g[i] = (int *) malloc(n * sizeof(int));
        for (int j = 0; j < n; j++)
            g[i][j] = 0;
    }
 
    for (int i = 1; i < n; i++)
    {
        char s[4000];
        scanf("%s", s);
        for (int j = 0; j < i; j++)
        {
            if (s[j] == '1')
            {
                g[i][j] = 1;
                g[j][i] = 1;
            }
        }
    }
 
    //        for (int i = 0; i < n; i++) {
    //            for (int j = 0; j < n; j++)
    //                printf("%i ", g[i][j]);
    //            printf("\n");
    //        }
 
 
    int *deq = (int *) malloc(n * n * sizeof(int));
    for (int i = 0; i < n * n; i++)
        deq[i] = 0;
 
    int l = 0;
    int r = n - 1;
 
    for (int i = 0; i < n; i++)
        deq[i] = i;
 
    for (int p = 0; p < n * (n - 1); p++)
    {
        int first = deq[l];
        int second = deq[l + 1];
        if (g[first][second] == 0)
        {
            int i = 2;
            while (g[first][deq[l + i]] == 0 || g[second][deq[l + i + 1]] == 0 && i < n - 1)
                i++;
            if (i >= n - 1) {
                i = 2;
                while (g[first][deq[l + i]] == 0) {
                    i++;
                }
            }
 
            for (int j = 0; l + 1 + j < l + i - j; j++)
            {
                int temp = deq[l + 1 + j];
                deq[l + 1 + j] = deq[l + i - j];
                deq[l + i - j] = temp;
            }
        }
        deq[r + 1] = deq[l];
        l++;
        r++;
    }
 
    while (l != r + 1)
        printf("%i ", deq[l++] + 1);
 
    //    for (int i = 0; i < n; i++)
    //        free(g[i]);
    //    free(deq);
 
    return 0;
}