#include <iostream>
#include <random>
using namespace std;

class Treap
{
    static minstd_rand gen;

    struct Node {
        int pr;
        int key;
        long long sum;
        int size;
        Node *l = nullptr;
        Node *r = nullptr;
        Node(int val) : pr(gen()), key(val), size(1), sum(key){}
    } *root = nullptr;

    static int getSize(Node *n)
    {
        return n ? n->size : 0;
    }

    static long long getSum(Node *n) {
        return n ? n->sum : 0;
    }

    static int getMinValue(Node *n)
    {
        if (n == nullptr) {
            return -1;
        }
        while (n->l != nullptr) {
            n = n->l;
        }
        return n->key;
    }

    static void update(Node *n)
    {
        if (n)
        {
            n->size = getSize(n->l) + n->key + getSize(n->r);
            n->sum = getSum(n->l) + getSum(n->r) + n->key;
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

    static void split(Node *n, int key, Node *&a, Node *&b)
    {
        if (n == nullptr)
        {
            a = nullptr;
            b = nullptr;
            return;
        }
        if (n->key < key)
        {
            split(n->r, key, n->r, b);
            a = n;
        } else
        {
            split(n->l, key, a, n->l);
            b = n;
        }
        update(a);
        update(b);
    }

    static int keyByIndex(Node *n, int index) {
        int leftSize = getSize(n->l);
        if (index == leftSize) {
            return n->key;
        }
        if (index < leftSize) {
            return keyByIndex(n->l, index);
        }
        else {
            return keyByIndex(n->r, index - leftSize - 1);
        }
    }

public:
    int contains(int key)
    {
        Node *less, *equal, *more;
        split(root, key, less, more);
        split(more, key + 1, equal, more);
        bool result = equal;
        root = merge(merge(less, equal), more);
        return result;
    }

    void insert(int key)
    {
        Node *less, *more;
        split(root, key, less, more);
        root = merge(merge(less, new Node(key)), more);
    }

    void del(int key) { //удаление по ключу
        Node *less, *equal, *more;
        split(root, key, less, more);
        split(more, key + 1, equal, more);
        root = merge(less, more);
    }

    void del(int l, int r) { //удаление в диапозоне
        Node *less, *equal, *more;
        split(root, l, less, more);
        split(more, r - l + 1, equal, more);
        root = merge(less, more);
    }

    int size() { //размер всего дерева
        return getSize(root);
    }

    int minvalue(int l, int r) {
        Node *less, *equal, *more;
        split(root, l, less, more);
        split(more, r- l + 1, equal, more);
        int result = getSize(equal);
        root = merge(merge(less, equal), more);
        return result;
    }

    int next(int key) //элемент не меньше key или -1
    {
        Node *less, *more;
        split(root, key, less, more);
        int result = getMinValue(more);
        root = merge(less,more);
    }

    int indexByKey(int key) {
        Node *less, *more;
        split(root, key, less, more);
        int result = getSize(less);
        root = merge(less, more);
        return result;
    }

    int keyByIndex(int index) {
        return keyByIndex(root, index);
    }

    long long sum(int l, int r) {
        Node *less, *equal, *more;
        split(root, l, less, more);
        split(more, r + 1, equal, more);
        long long result = getSum(equal);
        root = merge(merge(less, equal), more);
        return result;
    }
};

minstd_rand Treap::gen;


int main()
{
    Treap t;

    int n;
    cin >> n;

    string op;
    int l, r;
    long long prev = 0;

    for (int i = 0; i < n; i++)
    {
        cin >> op;
        if (op == "+") {
            cin >> l;
            l = (l + prev) % 1000000000;
            if (!t.contains(l)) {
                t.insert(l);
            }

            prev = 0;
        }
        else {
            cin >> l >> r;
            prev = t.sum(l, r);
            cout << prev << '\n';
        }
    }

    return 0;
}