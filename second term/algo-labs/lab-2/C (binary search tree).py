import sys


class Node:
    def __init__(self, key):
        self.key = key
        self.l = None
        self.r = None


def search(root, key):
    if root == None or root.key == key:
        return root
    if root.key < key:
        return search(root.r, key)
    return search(root.l, key)


def next(root, key):
    curr = root
    suc = None
    while curr != None:
        if curr.key > key:
            suc = curr
            curr = curr.l
        else:
            curr = curr.r
    return suc


def prev(root, key):
    curr = root
    suc = None
    while curr != None:
        if curr.key < key:
            suc = curr
            curr = curr.r
        else:
            curr = curr.l
    return suc


def insert(node, key):
    if node == None:
        return Node(key)

    if key < node.key:
        node.l = insert(node.l, key)
    elif key > node.key:
        node.r = insert(node.r, key)
    return node


def minimum(node):
    current = node
    while (current.l != None):
        current = current.l

    return current


def delete(root, key):
    if root == None:
        return root
    if key < root.key:
        root.l = delete(root.l, key)
    elif (key > root.key):
        root.r = delete(root.r, key)
    else:
        if root.l == None:
            curr = root.r
            root = None
            return curr

        elif root.r == None:
            curr = root.l
            root = None
            return curr
        curr = minimum(root.r)
        root.key = curr.key
        root.r = delete(root.r, curr.key)

    return root


def main():
    root = None

    for q in sys.stdin.read().split("\n"):
        try:
            flag, x = q.split()
            if flag == "insert":
                root = insert(root, int(x))
            elif flag == "delete":
                root = delete(root, int(x))
            elif flag == "exists":
                a = search(root, int(x))
                if (a == None):
                    sys.stdout.write("false\n")
                else:
                    sys.stdout.write('true\n')
            elif flag == "next":
                a = next(root, int(x))
                if (a == None):
                    sys.stdout.write("none\n")
                else:
                    sys.stdout.write(str(a.key) + '\n')
            elif flag == "prev":
                a = prev(root, int(x))
                if (a == None):
                    sys.stdout.write("none\n")
                else:
                    sys.stdout.write(str(a.key) + '\n')
        except Exception:
            break


if __name__ == '__main__':
    main()
