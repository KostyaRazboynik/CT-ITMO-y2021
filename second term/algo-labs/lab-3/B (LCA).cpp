#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> arr;
vector<int> t_in, t_out;
int T = 0;

void dfs(int v)
{
    t_in[v] = T++;
    for (size_t i = 0; i < arr[v].size(); ++i)
    {
        dfs(arr[v][i]);
    }
    t_out[v] = T++;
}


int main()
{
    int n;
    cin >> n;
    int x = 1;
    while ((1<<x) <= n)  ++x;
    int a;
    vector<vector<int>> up;
    up.resize(x);
    for (int i = 0; i < x; i++) {
        up[i].resize(n + 1);
    }
    arr.resize(n + 1);

    for (int i = 1; i < n; i++)  {
        cin >> a;
        arr[a].push_back(i + 1);
        up[0][i + 1] = a;
    }

    for (int d = 1; d < x; d++) {
        for (int i = 0; i < n + 1; i++) {
            up[d][i] = up[d - 1][up[d - 1][i]];
        }
    }
    t_in.resize(n + 1);
    t_out.resize(n + 1);
    dfs(1);

    int m;
    cin >> m;
    int u, v;
    for (int i = 0; i < m; i++) {
        cin >> u;
        cin >> v;
        if (t_in[u] <= t_in[v] && t_out[u] >= t_out[v]) {
            cout << u << '\n';
            continue;
        }

        for (int d = x - 1; d >= 0; d--) {
            if (up[d][u] != 0 && !(t_in[up[d][u]] <= t_in[v] && t_out[up[d][u]] >= t_out[v])) {
                u = up[d][u];
            }
        }
        cout << up[0][u] << '\n';
    }
}