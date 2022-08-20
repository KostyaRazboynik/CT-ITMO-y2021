#include <iostream>
#include <stdio.h>
#include <math.h>
#include <algorithm>

using namespace std;

struct Node {
    int v, w, id;
    double y;
} 
a [100000];

bool cmp (Node a, Node b) {return a.y> b.y;}

int n, k;

bool FFF (double m) {
    for (int i = 0; i < n; i++) {
        a[i].y = a[i].v - m* a[i].w;
    }
    
    sort (a, a + n, cmp);
    
    double sum = 0;
    for (int i = k - 1; i >= 0; i--) {
        sum += a[i].y;
    }
    
    return sum >= 0;
}

int main () {
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> a[i].v >> a[i].w;
        a[i].id = i + 1;
    }
    
    double l = 0, r = 1e8, m;
    while (r - l > 1e-8) {
        m = l + (r - l) / 2;
        if (FFF(m)) {
            l = m;
        } else {
            r = m;
        }
    }
    
    for (int i = 0; i < k; i++) {
        cout << a[i].id << endl; 
    }
    
    return 0;
}