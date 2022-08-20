#include <iostream>
#include <random>
using namespace std;

class Treap
{
    static minstd_rand gen;

    struct Node {
        int pr;
        int key;
        int size;
        Node *l = nullptr;
        Node *r = nullptr;
        Node(int val) : pr(gen()), key(val), size(1){}
    } *root = nullptr;

    static int getSize(Node *n)
    {
        return n ? n->size : 0;
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
            n->size = getSize(n->l) + 1 + getSize(n->r);
            //n->minVal = min(min(getMinValue(n->l), n->key), getMinValue(n->r));
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

    int size() { //размер всего дерева
        return getSize(root);
    }

    int keyByIndex(int index) {
        return keyByIndex(root, index);
    }
};

minstd_rand Treap::gen;


int main()
{
    Treap t;
    int n;
    cin >> n;
    int c, k;
    for (int i = 0; i < n; i++)
    {
        cin >> c >> k;
        if (c == 0) {
            cout << t.keyByIndex(t.size() - k) << '\n';
        }
        else if (c == 1) {
            t.insert(k);
        }
        else {
            t.del(k);
        }
    }

    return 0;
}