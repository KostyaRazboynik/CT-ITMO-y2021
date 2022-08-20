#include <iostream>
#include <random>
using namespace std;

class Treap
{
    static minstd_rand gen;

    struct Node {
        int pr;
        int val;
        int size = 1;
        Node *l = nullptr;
        Node *r = nullptr;
        Node(int val) : pr(gen()), val(val) {}
    } *root = nullptr;

    static int getSize(Node *n)
    {
        return n ? n->size : 0;
    }

    static void update(Node *n)
    {
        if (n)
        {
            n->size = getSize(n->l) + 1 + getSize(n->r);
        }
    }

    static Node *merge(Node *a, Node *b)
    {
        if (a == nullptr || b == nullptr)
        {
            if (a == nullptr)
            {
                return b;
            }
            return a;
        }

        if (a->pr > b->pr)
        {
            a->r = merge(a->r, b);
            update(a);
            return a;
        } else
        {
            b->l = merge(a, b->l);
            update(b);
            return b;
        }
    }

    static void split(Node *n, int val, Node *&a, Node *&b)
    {
        if (n == nullptr)
        {
            a = nullptr;
            b = nullptr;
            return;
        }
        if (getSize(n->l) < val)
        {
            split(n->r, val - getSize(n->l) - 1, n->r, b);
            a = n;
        } else
        {
            split(n->l, val, a, n->l);
            b = n;
        }
        update(a);
        update(b);
    }

public:
    int get(int index)
    {
        Node *less, *equal, *more;
        split(root, index, less, more);
        split(more, 1, equal, more);
        int result = equal->val;
        root = merge(merge(less, equal), more);
        return result;
    }

    void pushBack(int val)
    {
        root = merge(root, new Node(val));
    }

    void insert(int index, int val)
    {
        Node *less, *more;
        split(root, index, less, more);
        root = merge(merge(less, new Node(val)), more);
    }

    void del(int index)
    {
        Node *less, *equal, *more;
        split(root, index, less, more);
        split(more, 1, equal, more);
        root = merge(less, more);
    }

    int size()
    {
        return getSize(root);
    }
};

minstd_rand Treap::gen;


int main()
{
    Treap t;
    int n, m;
    string s;
    cin >> n >> m;

    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        t.pushBack(a);
    }

    int a, b;

    for (int i = 0; i < m; i++)
    {
        cin >> s;
        if (s == "del")
        {
            cin >> a;
            t.del(a - 1);
        } else if (s == "add")
        {
            cin >> a >> b;
            t.insert(a, b);
        }
    }

    cout << t.size() << '\n';

    for (int i = 0; i < t.size(); i++)
    {
        cout << t.get(i) << ' ';
    }
    cout << '\n';

    return 0;
}