/*
It's hard mode, variant Floor (36-37), variant Replace (32- 33) also works
*/

treap(Key, Val, Y, L, R).

split(null, (null, null), Key).
split(treap(Key, Val, Y, L1, R1), (treap(Key, Val, Y, L1, R2), R3), K) :-
    Key < K -> split(R1, (R2, R3), K).
split(treap(Key, Val, Y, L1, R1), (L2, treap(Key, Val, Y, L3, R1)), K) :-
	Key >= K -> split(L1, (L2, L3), K).

merge(null, null, null).
merge(null, T, T).
merge(T, null, T).
merge(treap(Key1, Val1, Y1, L1, R1), treap(Key2, Val2, Y2, L2, R2), treap(Key1, Val1, Y1, L1, Right)) :-
	Y2 < Y1 -> merge(R1, treap(Key2, Val2, Y2, L2, R2), Right).
merge(treap(Key1, Val1, Y1, L1, R1), treap(Key2, Val2, Y2, L2, R2), treap(Key2, Val2, Y2, Left, R2)) :-
	Y2 >= Y1 ->	merge(treap(Key1, Val1, Y1, L1, R1), L2, Left).

map_build([], null).
map_build([(Key, Val) | T], Tree) :-
	map_build(T, TreeR),
	map_put(TreeR, Key, Val, Tree).

map_get(Tree, Key, Val) :- split(Tree, (_, R), Key), split(R, (treap(Key, Val, _, _, _), _), Key + 1).

map_put(Tree, Key, Val, R) :-
	split(Tree, (L1, R1), Key), split(R1, (_, R2), Key + 1),
	rand_int(1000, Y),
	merge(L1, treap(Key, Val, Y, null, null), L2), merge(L2, R2, R).

map_remove(Tree, Key, R) :- split(Tree, (L1, R1), Key), split(R1, (_, R2), Key + 1), merge(L1, R2, R), !.

map_floorKey(Tree, Key, FloorKey) :- split(Tree, (L, _), Key + 1), findMaxRight(L, FloorKey).
findMaxRight(Tree, FloorKey) :- Tree = treap(_, _, _, _, R), \+ R = null, findMaxRight(R, FloorKey).
findMaxRight(Tree, FloorKey) :- Tree = treap(FloorKey, _, _, _, R), R = null.

map_replace(Tree, Key, Val, Result) :-
    map_get(Tree, Key, _) -> map_remove(Tree, Key, R),
    map_put(R, Key, Val, Result);
    Result = Tree.