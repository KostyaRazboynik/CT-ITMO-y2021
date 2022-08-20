import sys


class Node(object):
    def __init__(self, key):
        self.key = key
        self.l = None
        self.r = None
        self.h = 1


def search(root, key):
    if root is None or root.key == key:
        return root
    if root.key < key:
        return search(root.r, key)
    return search(root.l, key)


def next(root, key):
    curr = root
    res = None
    while curr != None:
        if curr.key > key:
            res = curr
            curr = curr.l
        else:
            curr = curr.r
    return res


def prev(root, key):
    curr = root
    res = None
    while curr != None:
        if curr.key < key:
            res = curr
            curr = curr.r
        else:
            curr = curr.l
    return res


def getHeight(node):
    if node == None:
        return 0
    return node.h


def getBalance(node):
    if node == None:
        return 0
    return getHeight(node.l) - getHeight(node.r)


def rotateRight(node):
    curr = node.l
    tmp = curr.r

    curr.r = node
    node.l = tmp

    node.h = 1 + max(getHeight(node.l), getHeight(node.r))
    curr.h = 1 + max(getHeight(curr.l), getHeight(curr.r))

    return curr


def rotateLeft(node):
    curr = node.r
    tmp = curr.l

    curr.l = node
    node.r = tmp

    node.h = 1 + max(getHeight(node.l), getHeight(node.r))
    curr.h = 1 + max(getHeight(curr.l), getHeight(curr.r))

    return curr


def insert(node, key):
    if node == None:
        return Node(key)
    elif key < node.key:
        node.l = insert(node.l, key)
    elif key > node.key:
        node.r = insert(node.r, key)
    else:
        return node

    node.h = 1 + max(getHeight(node.l), getHeight(node.r))

    balance = getBalance(node)

    if balance > 1 and key < node.l.key:
        return rotateRight(node)

    if balance < -1 and key > node.r.key:
        return rotateLeft(node)

    if balance > 1 and key > node.l.key:
        node.l = rotateLeft(node.l)
        return rotateRight(node)

    if balance < -1 and key < node.r.key:
        node.r = rotateRight(node.r)
        return rotateLeft(node)

    return node


def minimum(node):
    if node == None or node.l == None:
        return node
    return minimum(node.l)


def delete(node, key):
    if node == None:
        return node

    elif key < node.key:
        node.l = delete(node.l, key)

    elif key > node.key:
        node.r = delete(node.r, key)

    else:
        if node.l == None and node.r == None:
            return None

        if node.l == None:
            temp = node.r
            return temp

        elif node.r == None:
            temp = node.l
            return temp

        temp = minimum(node.r)
        node.key = temp.key
        node.r = delete(node.r, temp.key)

    if node == None:
        return node

    node.h = 1 + max(getHeight(node.l), getHeight(node.r))

    balance = getBalance(node)

    if balance > 1 and getBalance(node.l) >= 0:
        return rotateRight(node)

    if balance < -1 and getBalance(node.r) <= 0:
        return rotateLeft(node)

    if balance > 1 and getBalance(node.l) < 0:
        node.l = rotateLeft(node.l)
        return rotateRight(node)

    if balance < -1 and getBalance(node.r) > 0:
        node.r = rotateRight(node.r)
        return rotateLeft(node)

    return node


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